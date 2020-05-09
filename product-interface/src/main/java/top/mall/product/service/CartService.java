package top.mall.product.service;

import top.mall.pojo.ProductSku;

import java.util.List;
import java.util.Map;

public interface CartService {
    void addCart(ProductSku productSku, String token);

    Map cartList(String token);

    void deleteCart(String token);

    void select(String token, List<String> cartIds);

    Map selectList(String token);
}
