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
        Map<Integer, List<SpecValue>> specValueMap = getSpecGroupIdToSpecValueListMap(specValue.getSpuId());
        List<SpecGroup> groupList = getSpecGroups(specValueMap);
        specSkuMapper.deleteBySpuId(specValue.getSpuId());
        specSkuMapper.deleteBySpuId(specValue.getSpuId());
        List<List<SpecValue>> paths = new ArrayList<>();
        List<SpecValue> path = new ArrayList<>();
        for (SpecValue v : groupList.get(0).getSpecValueList()) {
            addToPath(paths, path, groupList, 1, v);
        }
        insertSkuAndSpecSku(paths, specValue.getSpuId());
    }

    private void insertSkuAndSpecSku(List<List<SpecValue>> paths, Integer spuId) {
        paths.forEach(v -> {
            ProductSku sku = new ProductSku();
            sku.setSpuId(spuId);
            productSkuMapper.insertSelective(sku);
            v.forEach(s -> {
                SpecSku specSku = new SpecSku();
                specSku.setSkuId(sku.getSkuId());
                specSku.setSpuId(spuId);
                specSku.setSpecGroupId(s.getSpecGroupId());
                specSku.setSpecGroupIndex(s.getSpecGroupIndex());
                specSku.setSpecValueId(s.getSpecValueId());
                specSkuMapper.insertSelective(specSku);
            });
        });
    }

    private void addToPath(List<List<SpecValue>> paths, List<SpecValue> path, List<SpecGroup> list, int i, SpecValue specValue) {
        if (i == list.size()) {
            List<SpecValue> row = new ArrayList<>(path);
            row.add(specValue);
            paths.add(row);
            return;
        }
        SpecGroup specGroup = list.get(i);
        specGroup.getSpecValueList().forEach(v -> {
            path.add(v);
            addToPath(paths, path, list, i + 1, specValue);
            path.remove(path.size() - 1);
        });
    }

    private List<SpecGroup> getSpecGroups(Map<Integer, List<SpecValue>> specValueMap) {
        return specValueMap.entrySet().stream().map(v ->
                {
                    SpecGroup specGroup = specGroupMapper.selectByPrimaryKey(v.getKey());
                    specGroup.setSpecValueList(v.getValue());
                    return specGroup;
                }
        ).collect(Collectors.toList());
    }

    private Map<Integer, List<SpecValue>> getSpecGroupIdToSpecValueListMap(Integer spuId) {
        List<SpecSku> specSkuList = specSkuMapper.selectSpecSkuBySpuId(spuId);
        return specSkuList
                .stream()
                .map((v) -> specValueMapper.selectByPrimaryKey(v.getSpecValueId()))
                .distinct()
                .collect(Collectors.groupingBy(SpecValue::getSpecGroupId));
    }

    @Override
    public void valueDel(Integer skuId, Integer specValueId) {
        specValueMapper.deleteByPrimaryKey(specValueId);
        specSkuMapper.deleteBySkuId(skuId);
        productSkuMapper.deleteBySkuId(skuId);
    }

    @Override
    public List<SpecValue> valueList(Integer spuId, Integer specGroupId) {
        return specValueMapper.selectBySpuIdAndSpecGroupId(spuId, specGroupId);
    }
}
