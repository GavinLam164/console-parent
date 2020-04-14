package top.mall.pojo.req;

import top.mall.pojo.PageResult;

import java.io.Serializable;

public class SpuListReq extends PageResult implements Serializable {
    Integer spuId;
    Integer skuId;
    String spuName;
    Integer skuCode;
    Integer categoryId;
    Integer saleState;

    public SpuListReq() {
        super();
    }

    public SpuListReq(int pages, int total, int curPage, int pageSize, Integer spuId, Integer skuId, String spuName, Integer skuCode, Integer categoryId, Integer saleState) {
        super(pages, total, curPage, pageSize, null);
        this.spuId = spuId;
        this.skuId = skuId;
        this.spuName = spuName;
        this.skuCode = skuCode;
        this.categoryId = categoryId;
        this.saleState = saleState;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public Integer getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Integer skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSaleState() {
        return saleState;
    }

    public void setSaleState(Integer saleState) {
        this.saleState = saleState;
    }
}
