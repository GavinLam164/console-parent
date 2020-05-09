package top.mall.console.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import top.mall.pojo.ProductSku;
import top.mall.pojo.RpcResult;
import top.mall.product.service.CartService;

import java.util.List;
import java.util.Map;

@RequestMapping("/cart")
@RestController
public class CartController {

    @Reference
    CartService cartService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RpcResult addCart(@RequestBody ProductSku productSku, @RequestHeader("token") String token) {
        cartService.addCart(productSku, token);
        return RpcResult.success(null);
    }

    @RequestMapping(value = "/list")
    public RpcResult<Map> cartList(@RequestHeader("token") String token) {
        return RpcResult.success(cartService.cartList(token));
    }

    @RequestMapping(value = "/selectList")
    public RpcResult<Map> selectList(@RequestHeader("token") String token) {
        return RpcResult.success(cartService.selectList(token));
    }

    @RequestMapping(value = "/select")
    public RpcResult select(@RequestHeader("token") String token, @RequestParam("cartIds") List<String> cartIds) {
        cartService.select(token, cartIds);
        return RpcResult.success(null);
    }

    @RequestMapping(value = "/delete")
    public RpcResult<List<ProductSku>> deleteCart(@RequestHeader("token") String token) {
        cartService.deleteCart(token);
        return RpcResult.success(null);
    }

}
