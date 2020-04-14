package top.mall.product.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import top.mall.dao.mapper.*;
import top.mall.pojo.*;
import top.mall.pojo.req.SpuListReq;
import top.mall.product.service.ProductService;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    ProductSpuMapper productSpuMapper;
    @Resource
    ProductSpuImageMapper productSpuImageMapper;
    @Resource
    ProductSkuMapper productSkuMapper;
    @Resource
    SpecSkuMapper specSkuMapper;
    @Resource
    SpecGroupMapper specGroupMapper;
    @Resource
    SpecValueMapper specValueMapper;
    @Resource
    SpuImageMapper spuImageMapper;

    public void spuAdd(ProductSpu spu) {
        productSpuMapper.insert(spu);
        this.updateSpuImageList(spu);
    }

    @Override
    public ProductSpu spuQuery(Integer spuId) {
        ProductSpu spu = productSpuMapper.selectByPrimaryKey(spuId);
        spu.setBannerImageList(productSpuImageMapper.selectByType(spuId, ProductSpuImage.SpuImageType.BANNER.getValue()));
        spu.setDetailImageList(productSpuImageMapper.selectByType(spuId, ProductSpuImage.SpuImageType.DETAIL.getValue()));
        return spu;
    }

    @Override
    public void spuUpdate(ProductSpu spu) {
        productSpuMapper.updateByPrimaryKeySelective(spu);
        this.clearSpuImageList(spu);
        this.updateSpuImageList(spu);
    }

    @Override
    public List<ProductSku> skuList(Integer spuId) {
        List<SpecSku> specSkuList = specSkuMapper.selectSpecSkuBySpuId(spuId);
        Map<Integer, List<SpecGroup>> map = new HashMap<>();
        for (SpecSku v : specSkuList) {
            SpecGroup group = specGroupMapper.selectByPrimaryKey(v.getSpecGroupId());
            SpecValue specValue = specValueMapper.selectByPrimaryKey(v.getSpecValueId());
            List<SpecValue> specValueList = Arrays.asList(specValue);
            group.setSpecValueList(specValueList);
            if (!map.containsKey(v.getSkuId())) {
                map.put(v.getSkuId(), new ArrayList<>());
            }
            map.get(v.getSkuId()).add(group);
        }
        List<ProductSku> skuList = productSkuMapper.selectBySpuId(spuId);
        for (ProductSku sku : skuList) {
            sku.setSpecGroupList(map.get(sku.getSkuId()));
        }
        return skuList;
    }

    @Override
    public void skuAdd(ProductSku productSku) {
        productSkuMapper.insert(productSku);
        Integer skuId = productSku.getSkuId();
        Integer spuId = productSku.getSpuId();

        productSku.getSpecGroupList().forEach(specGroup -> {
            specGroup.getSpecValueList().forEach(specValue -> {
                specSkuMapper.insert(
                    new SpecSku(null,
                        specGroup.getSpecGroupId(),
                        null,
                        specValue.getSpecValueId(),
                        spuId,
                        skuId)
                    );
            });
        });

    }

    @Override
    public void skuUpdate(List<ProductSku> productSkuList) {
        productSkuList.forEach(v-> {
            productSkuMapper.updateByPrimaryKeySelective(v);
        });
    }

    @Override
    public void spuImageSort(List<SpuImage> spuImageList) {
        spuImageList.forEach(v -> {
            spuImageMapper.updateByPrimaryKeySelective(v);
        });
    }

    @Override
    public PageResult<ProductSpu> spuList(SpuListReq spuListReq) {
        PageHelper.startPage(spuListReq.getCurPage(), spuListReq.getPageSize());
        return PageResult.success(productSpuMapper.selectByParams(spuListReq));
    }

    void clearSpuImageList(ProductSpu spu) {
        productSpuImageMapper.selectBySpuId(spu.getSpuId()).forEach(v -> {
            productSpuImageMapper.deleteByPrimaryKey(v.getSpuImageId());
        });
    }

    void updateSpuImageList(ProductSpu spu) {
        spu.getBannerImageList().forEach((v) -> {
            v.setSpuId(spu.getSpuId());
            productSpuImageMapper.insert(v);
        });
        spu.getDetailImageList().forEach((v) -> {
            v.setSpuId(spu.getSpuId());
            productSpuImageMapper.insert(v);
        });
    }
}
