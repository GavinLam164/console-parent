package top.mall.product.service.impl;

import com.github.pagehelper.PageHelper;
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
    @Resource
    SkuImageMapper skuImageMapper;

    public void spuAdd(ProductSpu spu) {
        spu.setSaleState(0);
        if(spu.getSpuId() == null) {
            productSpuMapper.insert(spu);
        }else {
            productSpuMapper.updateByPrimaryKeySelective(spu);
        }
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
            sku.setSkuImage(skuImageMapper.selectBySkuId(sku.getSkuId()));
        }
        return skuList;
    }

    @Override
    public void skuAdd(ProductSku productSku) {
        productSku.setSaleState(0);
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
            skuImageMapper.deleteBySkuId(v.getSkuId());
            v.getSkuImage().setSkuId(v.getSkuId());
            skuImageMapper.insert(v.getSkuImage());
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
        PageHelper.startPage(spuListReq.getCurrentPage(), spuListReq.getPageSize());
        List<ProductSpu> list = productSpuMapper.selectByParams(spuListReq);
        list.forEach(spu -> {
            spu.setSkuImage(
                    productSpuImageMapper.selectByType(spu.getSpuId(), ProductSpuImage.SpuImageType.BANNER.getValue()).get(0));
        });
        return PageResult.success(list);
    }

    @Override
    public void spuUpdateState(List<Integer> spuIds, Integer saleState) {
        productSpuMapper.updateState(spuIds, saleState);
    }

    @Override
    public void skuUpdateState(List<Integer> skuIds, Integer saleState) {
        productSkuMapper.updateState(skuIds, saleState);
    }

    void clearSpuImageList(ProductSpu spu) {
        productSpuImageMapper.selectBySpuId(spu.getSpuId()).forEach(v -> {
            productSpuImageMapper.deleteByPrimaryKey(v.getSpuImageId());
        });
    }

    void updateSpuImageList(ProductSpu spu) {
        productSpuImageMapper.deleteBySpuId(spu.getSpuId());
        spu.getBannerImageList().forEach((v) -> {
            v.setSpuId(spu.getSpuId());
            v.setSpuImageType(ProductSpuImage.SpuImageType.BANNER.getValue());
            productSpuImageMapper.insert(v);
        });
        spu.getDetailImageList().forEach((v) -> {
            v.setSpuId(spu.getSpuId());
            v.setSpuImageType(ProductSpuImage.SpuImageType.DETAIL.getValue());
            productSpuImageMapper.insert(v);
        });
    }
}
