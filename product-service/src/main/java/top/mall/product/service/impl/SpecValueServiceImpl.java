package top.mall.product.service.impl;

import org.apache.dubbo.config.annotation.Service;
import top.mall.dao.mapper.ProductSkuMapper;
import top.mall.dao.mapper.SpecSkuMapper;
import top.mall.dao.mapper.SpecValueMapper;
import top.mall.pojo.SpecValue;
import top.mall.product.service.SpecValueService;

import javax.annotation.Resource;

@Service
public class SpecValueServiceImpl implements SpecValueService {
    @Resource
    SpecValueMapper specValueMapper;
    @Resource
    SpecSkuMapper specSkuMapper;
    @Resource
    ProductSkuMapper productSkuMapper;
    @Override
    public void valueAdd(SpecValue specValue) {
        specValueMapper.insert(specValue);
    }

    @Override
    public void valueDel(Integer skuId, Integer specValueId) {
        specValueMapper.deleteByPrimaryKey(specValueId);
        specSkuMapper.deleteBySkuId(skuId);
        productSkuMapper.deleteBySkuId(skuId);
    }
}
