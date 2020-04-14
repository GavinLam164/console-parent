package top.mall.product.service;

import top.mall.pojo.SpecValue;

public interface SpecValueService {
    void valueAdd(SpecValue specValue);

    void valueDel(Integer skuId, Integer specValueId);
}
