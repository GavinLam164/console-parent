package top.mall.product.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Service;
import top.mall.console.utils.manager.cache.RedisCacheManager;
import top.mall.dao.mapper.ProductSpuMapper;
import top.mall.pojo.ProductSku;
import top.mall.product.service.CartService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    RedisCacheManager redisCacheManager;

    @Resource
    ProductSpuMapper productSpuMapper;

    @Override
    public void addCart(ProductSku productSku, String token) {
        List<ProductSku> skuList = getRedisProductSku(token);
        productSku.createCartId();
        skuList.add(productSku);
        setRedisProductSku(token, skuList);
    }

    public List<ProductSku> getRedisProductSku(String token){
        String cartJson = redisCacheManager.get("cart:" + token);
        List<ProductSku> skuList = JSON.parseArray(cartJson, ProductSku.class);
        if(skuList == null) {
            skuList = new ArrayList<>();
        }
        return skuList;
    }

    public void setRedisProductSku(String token, List<ProductSku> skuList) {
        redisCacheManager.set("cart:" + token, JSON.toJSONString(skuList));
    }

    @Override
    public Map cartList(String token) {
        List<ProductSku> skuList = getRedisProductSku(token);
        skuList.forEach(sku -> {
            sku.setProductSpu(productSpuMapper.selectByPrimaryKey(sku.getSpuId()));
        });
        Map<String, Object> map = new HashMap<>();
        List<ProductSku> collect = skuList.stream().filter(productSku -> productSku.isSelected()).collect(Collectors.toList());
        long totalPrice = 0;
        for(ProductSku sku: collect){
            totalPrice+=sku.getSkuPrice();
        }
        map.put("totalPrice", totalPrice);
        map.put("skuList", skuList);
        return map;
    }

    @Override
    public void deleteCart(String token) {
        List<ProductSku> skuList = getRedisProductSku(token);
        List<ProductSku> res = skuList.stream().filter(v -> !v.isSelected()).collect(Collectors.toList());
        setRedisProductSku(token, res);
    }

    @Override
    public void select(String token, List<String> cartIds) {
        List<ProductSku> skuList = getRedisProductSku(token);
        skuList.forEach(v -> {
            if(cartIds.indexOf(v.getCartId()) != -1) {
                v.setSelected(true);
            }else {
                v.setSelected(false);
            }
        });
        setRedisProductSku(token, skuList);
    }

    @Override
    public Map selectList(String token) {
        List<ProductSku> skuList = getRedisProductSku(token);
        skuList.forEach(sku -> {
            sku.setProductSpu(productSpuMapper.selectByPrimaryKey(sku.getSpuId()));
        });
        Map<String, Object> map = new HashMap<>();
        List<ProductSku> collect = skuList.stream().filter(productSku -> productSku.isSelected()).collect(Collectors.toList());
        long totalPrice = 0;
        for(ProductSku sku: collect){
            totalPrice+=sku.getSkuPrice();
        }
        map.put("totalPrice", totalPrice);
        map.put("skuList", collect);
        return map;
    }
}
