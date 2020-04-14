package top.mall.product.service.impl;

import org.apache.dubbo.config.annotation.Service;
import top.mall.dao.mapper.ProductSkuMapper;
import top.mall.dao.mapper.SpecGroupMapper;
import top.mall.dao.mapper.SpecSkuMapper;
import top.mall.dao.mapper.SpecValueMapper;
import top.mall.pojo.SpecGroup;
import top.mall.pojo.SpecSku;
import top.mall.pojo.SpecValue;
import top.mall.product.service.SpecGroupService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpecGroupServiceImpl implements SpecGroupService {

    @Resource
    SpecGroupMapper specGroupMapper;

    @Resource
    SpecValueMapper specValueMapper;

    @Resource
    SpecSkuMapper specSkuMapper;

    @Resource
    ProductSkuMapper productSkuMapper;

    @Override
    public List<SpecGroup> specGroupList() {
        return specGroupMapper.selectSpecGroupList();
    }

    @Override
    public List<SpecGroup> specGroupListSelectBySpuId(Integer spuId) {
        List<SpecSku> specSkuList = specSkuMapper.selectSpecSkuBySpuId(spuId);
        Map<Integer, List<SpecValue>> specValueMap = specSkuList
                .stream()
                .map((v) -> specValueMapper.selectByPrimaryKey(v.getSpecValueId()))
                .distinct()
                .collect(Collectors.groupingBy(SpecValue:: getSpecGroupId));
        List<SpecGroup> groupList = specValueMap.entrySet().stream().map(v ->
                {
                    SpecGroup specGroup = specGroupMapper.selectByPrimaryKey(v.getKey());
                    specGroup.setSpecValueList(v.getValue());
                    return specGroup;
                }
        ).collect(Collectors.toList());
        return groupList;
    }


}
