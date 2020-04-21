package top.mall.pojo.req;

import top.mall.pojo.ProductSku;

import java.io.Serializable;
import java.util.List;

public class ProductSkuReq implements Serializable {
    List<ProductSku> productSkuList;

    public ProductSkuReq(List<ProductSku> productSkuList) {
        this.productSkuList = productSkuList;
    }

    public List<ProductSku> getProductSkuList() {
        return productSkuList;
    }

    public void setProductSkuList(List<ProductSku> productSkuList) {
        this.productSkuList = productSkuList;
    }
}
