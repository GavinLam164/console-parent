package top.mall.pojo;

import java.io.Serializable;

public class SpecSku implements Serializable {

    private Integer skuSpecId;

    private Integer specGroupId;

    private Integer specGroupIndex;

    private Integer specValueId;

    private Integer spuId;

    private Integer skuId;

    public SpecSku(Integer skuSpecId, Integer specGroupId, Integer specGroupIndex, Integer specValueId, Integer spuId, Integer skuId) {
        this.skuSpecId = skuSpecId;
        this.specGroupId = specGroupId;
        this.specGroupIndex = specGroupIndex;
        this.specValueId = specValueId;
        this.spuId = spuId;
        this.skuId = skuId;
    }

    public SpecSku() {
        super();
    }

    public Integer getSkuSpecId() {
        return skuSpecId;
    }

    public void setSkuSpecId(Integer skuSpecId) {
        this.skuSpecId = skuSpecId;
    }

    public Integer getSpecGroupId() {
        return specGroupId;
    }

    public void setSpecGroupId(Integer specGroupId) {
        this.specGroupId = specGroupId;
    }

    public Integer getSpecGroupIndex() {
        return specGroupIndex;
    }

    public void setSpecGroupIndex(Integer specGroupIndex) {
        this.specGroupIndex = specGroupIndex;
    }

    public Integer getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Integer specValueId) {
        this.specValueId = specValueId;
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
}