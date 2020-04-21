package top.mall.product.service.impl;

import org.apache.dubbo.config.annotation.Service;
import top.mall.dao.mapper.ProductSkuMapper;
import top.mall.dao.mapper.SpecGroupMapper;
import top.mall.dao.mapper.SpecSkuMapper;
import top.mall.dao.mapper.SpecValueMapper;
import top.mall.pojo.ProductSku;
import top.mall.pojo.SpecGroup;
import top.mall.pojo.SpecSku;
import top.mall.pojo.SpecValue;
import top.mall.product.service.SpecValueService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpecValueServiceImpl implements SpecValueService {
    @Resource
    SpecValueMapper specValueMapper;
    @Resource
    SpecSkuMapper specSkuMapper;
    @Resource
    ProductSkuMapper productSkuMapper;
    @Resource
    SpecGroupMapper specGroupMapper;

    @Override
    public void valueAdd(SpecValue specValue) {
        specValueMapper.insert(specValue);
        rebuildSkuAndSpecSku(specValue.getSpuId());
    }

    private void rebuildSkuAndSpecSku(Integer spuId) {
        clearSpecSkuAndSku(spuId);
        List<SpecValue> specValueList = specValueMapper.selectBySpuId(spuId);
        List<List<SpecValue>> specGroupList = specValueList.stream()
                .collect(Collectors.groupingBy(SpecValue::getSpecGroupId))
                .entrySet()
                .stream()
                .map(v -> v.getValue())
                .collect(Collectors.toList());
        List<SpecValue> path = new ArrayList<>();
        insertSkuAndSpuSkuByPath(path, specGroupList, 0);
    }

    private void insertSkuAndSpuSkuByPath(List<SpecValue> path, List<List<SpecValue>> specGroupList, int i) {
        if (i == specGroupList.size()) {
            insertSkuAndSpuSkuByPath(path);
            return;
        }
        List<SpecValue> specValueList = specGroupList.get(i);
        for (SpecValue specValue : specValueList) {
            path.add(specValue);
            insertSkuAndSpuSkuByPath(path, specGroupList, i + 1);
            path.remove(path.size() - 1);
        }
    }

    private void insertSkuAndSpuSkuByPath(List<SpecValue> path) {
        if(path.isEmpty()) return;
        SpecValue specValue = path.get(0);
        ProductSku sku = new ProductSku();
        sku.setSpuId(specValue.getSpuId());
        productSkuMapper.insertSelective(sku);
        for (SpecValue spValue: path){
            SpecSku specSku = new SpecSku();
            specSku.setSpuId(spValue.getSpuId());
            specSku.setSkuId(sku.getSkuId());
            specSku.setSpecGroupIndex(spValue.getSpecGroupIndex());
            specSku.setSpecValueId(spValue.getSpecValueId());
            specSku.setSpecGroupId(spValue.getSpecGroupId());
            specSkuMapper.insertSelective(specSku);
        }
    }

    private void clearSpecSkuAndSku(Integer spuId) {
        List<SpecSku> specSkuList = specSkuMapper.selectSpecSkuBySpuId(spuId);
        Map<Integer, List<SpecSku>> skuIdToSpecSku = specSkuList.stream()
                .collect(Collectors.groupingBy(SpecSku::getSkuId));
        for (Integer skuId : skuIdToSpecSku.keySet()) {
            specSkuMapper.deleteBySkuId(skuId);
            productSkuMapper.deleteBySkuId(skuId);
        }
    }

    @Override
    public void valueDel(Integer spuId, Integer specValueId) {
        specValueMapper.deleteByPrimaryKey(specValueId);
        rebuildSkuAndSpecSku(spuId);
    }

    @Override
    public List<SpecValue> valueList(Integer spuId, Integer specGroupId) {
        return specValueMapper.selectBySpuIdAndSpecGroupId(spuId, specGroupId);
    }
}
