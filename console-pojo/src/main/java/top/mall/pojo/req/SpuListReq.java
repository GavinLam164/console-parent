package top.mall.pojo.req;

import top.mall.pojo.PageResult;

import java.io.Serializable;
import java.util.List;

public class SpuListReq extends PageResult implements Serializable {
    Integer spuId;
    Integer skuId;
    String spuName;
    String skuCode;
    Integer categoryId;
    List<Integer> saleStates;

    public SpuListReq() {
        super();
    }

    public SpuListReq(int pages, int total, int curPage, int pageSize, Integer spuId, Integer skuId, String spuName, String skuCode, Integer categoryId, List<Integer> saleStates) {
        super(pages, total, curPage, pageSize, null);
        this.spuId = spuId;
        this.skuId = skuId;
        this.spuName = spuName;
        this.skuCode = skuCode;
        this.categoryId = categoryId;
        this.saleStates = saleStates;
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

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getSaleState() {
        return saleStates;
    }

    public void setSaleState( List<Integer> saleStates) {
        this.saleStates = saleStates;
    }
}
