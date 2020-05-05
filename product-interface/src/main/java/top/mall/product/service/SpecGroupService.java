package top.mall.product.service;

import top.mall.pojo.SpecGroup;

import java.util.List;

public interface SpecGroupService {
    List<SpecGroup> specGroupList();

    List<SpecGroup> specGroupListSelectBySpuId(Integer spuId);

}
