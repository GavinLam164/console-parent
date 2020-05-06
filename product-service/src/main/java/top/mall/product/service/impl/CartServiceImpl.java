package top.mall.product.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Service;
import top.mall.console.utils.manager.cache.RedisCacheManager;
import top.mall.dao.mapper.ProductSpuMapper;
import top.mall.pojo.ProductSku;
import top.mall.product.service.CartService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    RedisCacheManager redisCacheManager;

    @Resource
    ProductSpuMapper productSpuMapper;

    @Override
    public void addCart(ProductSku productSku, String token) {
        String cartJson = redisCacheManager.get("cart:" + token);
        List<ProductSku> skuList = JSON.parseArray(cartJson, ProductSku.class);
        if(skuList == null) {
            skuList = new ArrayList<>();
        }
        skuList.add(productSku);
        redisCacheManager.set("cart:" + token, JSON.toJSONString(skuList));
    }

    @Override
    public List<ProductSku> cartList(String token) {
        String cartJson = redisCacheManager.get("cart:" + token);
        List<ProductSku> skuList = JSON.parseArray(cartJson, ProductSku.class);
        skuList.forEach(sku -> {
            sku.setProductSpu(productSpuMapper.selectByPrimaryKey(sku.getSpuId()));
        });
        return skuList;
    }

    @Override
    public void deleteCart(String token, List<Integer> skuIds) {
        String cartJson = redisCacheManager.get("cart:" + token);
        List<ProductSku> skuList = JSON.parseArray(cartJson, ProductSku.class);
        if(skuList == null) {
            return;
        }
        List<ProductSku> res = skuList.stream().filter(v -> skuIds.indexOf(v.getSkuId()) == -1).collect(Collectors.toList());
        redisCacheManager.set("cart:" + token, JSON.toJSONString(res));
    }
}
