package top.mall.pojo;

import java.io.Serializable;
import java.util.List;

public class ProductSku implements Serializable {
    private Integer skuId;

    private Integer spuId;

    private Long skuPrice;

    private String skuCode;

    private Integer saleState;

    private List<SpecGroup> specGroupList;

    public ProductSku(Integer skuId, Integer spuId, Long skuPrice, String skuCode, Integer saleState) {
        this.skuId = skuId;
        this.spuId = spuId;
        this.skuPrice = skuPrice;
        this.skuCode = skuCode;
        this.saleState = saleState;
    }

    public ProductSku() {
        super();
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public Long getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(Long skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public Integer getSaleState() {
        return saleState;
    }

    public void setSaleState(Integer saleState) {
        this.saleState = saleState;
    }

    public List<SpecGroup> getSpecGroupList() {
        return specGroupList;
    }

    public void setSpecGroupList(List<SpecGroup> specGroupList) {
        this.specGroupList = specGroupList;
    }

}