package top.mall.pojo;

import java.io.Serializable;

public class SpecValue implements Serializable {
    private Integer specValueId;

    private Integer specGroupId;

    private Integer specGroupIndex;

    private String specValueName;

    private Integer specValueIndex;

    private Integer spuId;

    public SpecValue(Integer specValueId, Integer specGroupId, String specValueName, Integer specValueIndex, Integer spuId, Integer specGroupIndex) {
        this.specValueId = specValueId;
        this.specGroupId = specGroupId;
        this.specValueName = specValueName;
        this.specValueIndex = specValueIndex;
        this.specGroupIndex = specGroupIndex;
        this.spuId = spuId;
    }

    public SpecValue() {
        super();
    }

    public Integer getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Integer specValueId) {
        this.specValueId = specValueId;
    }

    public Integer getSpecGroupId() {
        return specGroupId;
    }

    public void setSpecGroupId(Integer specGroupId) {
        this.specGroupId = specGroupId;
    }

    public String getSpecValueName() {
        return specValueName;
    }

    public void setSpecValueName(String specValueName) {
        this.specValueName = specValueName == null ? null : specValueName.trim();
    }

    public Integer getSpecValueIndex() {
        return specValueIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecValue specValue = (SpecValue) o;

        if (specValueId != null ? !specValueId.equals(specValue.specValueId) : specValue.specValueId != null)
            return false;
        if (specGroupId != null ? !specGroupId.equals(specValue.specGroupId) : specValue.specGroupId != null)
            return false;
        if (specValueName != null ? !specValueName.equals(specValue.specValueName) : specValue.specValueName != null)
            return false;
        return specValueIndex != null ? specValueIndex.equals(specValue.specValueIndex) : specValue.specValueIndex == null;
    }

    @Override
    public int hashCode() {
        int result = specValueId != null ? specValueId.hashCode() : 0;
        result = 31 * result + (specGroupId != null ? specGroupId.hashCode() : 0);
        result = 31 * result + (specValueName != null ? specValueName.hashCode() : 0);
        result = 31 * result + (specValueIndex != null ? specValueIndex.hashCode() : 0);
        result = 31 * result + (spuId != null ? spuId.hashCode() : 0);
        return result;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public void setSpecValueIndex(Integer specValueIndex) {
        this.specValueIndex = specValueIndex;
    }

    public Integer getSpecGroupIndex() {
        return specGroupIndex;
    }

    public void setSpecGroupIndex(Integer specGroupIndex) {
        this.specGroupIndex = specGroupIndex;
    }
}