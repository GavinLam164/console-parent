package top.mall.product.service;

import top.mall.pojo.ProductSku;

import java.util.List;

public interface CartService {
    void addCart(ProductSku productSku, String token);

    List<ProductSku> cartList(String token);

    void deleteCart(String token, List<Integer> skuIds);
}
