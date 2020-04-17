package top.mall.product.service;

import top.mall.pojo.PageResult;
import top.mall.pojo.ProductSku;
import top.mall.pojo.ProductSpu;
import top.mall.pojo.SpuImage;
import top.mall.pojo.req.SpuListReq;

import java.util.List;

public interface ProductService {
    void spuAdd(ProductSpu spu);

    ProductSpu spuQuery(Integer spuId);

    void spuUpdate(ProductSpu spu);

    List<ProductSku> skuList(Integer spuId);

    void skuAdd(ProductSku productSku);

    void skuUpdate(List<ProductSku> productSkuList);

    void spuImageSort(List<SpuImage> spuImageList);

    PageResult<ProductSpu> spuList(SpuListReq spuListReq);

    void updateState(List<Integer> spuIds, Integer state);
}
