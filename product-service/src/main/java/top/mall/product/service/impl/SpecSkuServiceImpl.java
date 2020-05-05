package top.mall.product.service.impl;

import org.apache.dubbo.config.annotation.Service;
import top.mall.dao.mapper.SpecSkuMapper;
import top.mall.pojo.SpecSku;
import top.mall.product.service.SpecSkuService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpecSkuServiceImpl implements SpecSkuService {
    @Resource
    private SpecSkuMapper specSkuMapper;

    @Override
    public List<SpecSku> selectSpecSkuBySpuId(Integer spuId) {
        return null;
    }
}
