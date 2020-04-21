package top.mall.console.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import top.mall.pojo.*;
import top.mall.pojo.req.SpuListReq;
import top.mall.product.service.ProductService;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Reference
    ProductService productService;

    @RequestMapping(value = "/spu/add", method = RequestMethod.POST)
    public RpcResult<Void> spuAdd(@RequestBody ProductSpu spu) {
        productService.spuAdd(spu);
        return RpcResult.success(null);
    }

    @RequestMapping("/spu/query")
    public RpcResult<ProductSpu> spuQuery(@RequestParam("spuId") Integer spuId) {
        return RpcResult.success(productService.spuQuery(spuId));
    }

    @RequestMapping(value = "/spu/update", method = RequestMethod.POST)
    public RpcResult<Void> spuUpdate(@RequestBody ProductSpu spu) {
        productService.spuUpdate(spu);
        return RpcResult.success(null);
    }

    @RequestMapping(value = "/spu/updateState", method = RequestMethod.POST)
    public RpcResult<Void> spuUpdateState(@RequestParam("spuIds") List<Integer> spuIds, @RequestParam("saleState")Integer saleState) {
        productService.spuUpdateState(spuIds, saleState);
        return RpcResult.success(null);
    }

    @RequestMapping(value = "/sku/updateState", method = RequestMethod.POST)
    public RpcResult<Void> skuUpdateState(@RequestParam("skuIds") List<Integer> skuIds, @RequestParam("saleState")Integer saleState) {
        productService.skuUpdateState(skuIds, saleState);
        return RpcResult.success(null);
    }

    @RequestMapping("/sku/list")
    public RpcResult<List<ProductSku>> skuList(@RequestParam("spuId") Integer spuId) {
        return RpcResult.success(productService.skuList(spuId));
    }

    @RequestMapping(value = "/sku/add", method = RequestMethod.POST)
    public RpcResult<Void> skuAdd(@RequestBody ProductSku productSku) {
        productService.skuAdd(productSku);
        return RpcResult.success(null);
    }

    @RequestMapping(value = "/sku/update", method = RequestMethod.POST)
    public RpcResult<Void> skuUpdate(@RequestBody List<ProductSku> productSkuList){
        productService.skuUpdate(productSkuList);
        return RpcResult.success(null);
    }

    @RequestMapping(value ="/spu/imageSort", method = RequestMethod.POST)
    public RpcResult<Void> spuImageSort(@RequestBody List<SpuImage> spuImageList) {
        productService.spuImageSort(spuImageList);
        return RpcResult.success(null);
    }

    @RequestMapping(value ="/spu/list", method = RequestMethod.POST)
    public RpcResult<PageResult<ProductSpu>> spuList(@RequestBody SpuListReq spuListReq) {
        return RpcResult.success(productService.spuList(spuListReq));
    }
}
