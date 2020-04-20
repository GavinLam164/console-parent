package top.mall.product.service;

import top.mall.pojo.SpecValue;

import java.util.List;

public interface SpecValueService {
    void valueAdd(SpecValue specValue);

    void valueDel(Integer skuId, Integer specValueId);

    List<SpecValue> valueList(Integer spuId, Integer specGroupId);
}
