package top.mall.pojo;

import java.io.Serializable;
import java.util.List;

public class SpecGroup implements Serializable {
    private Integer specGroupId;

    private String specGroupName;

    private Integer specGroupIndex;

    private List<SpecValue> specValueList;

    public SpecGroup(Integer specGroupId, String specGroupName, Integer specGroupIndex) {
        this.specGroupId = specGroupId;
        this.specGroupName = specGroupName;
        this.specGroupIndex = specGroupIndex;
    }

    public SpecGroup() {
        super();
    }

    public Integer getSpecGroupId() {
        return specGroupId;
    }

    public void setSpecGroupId(Integer specGroupId) {
        this.specGroupId = specGroupId;
    }

    public String getSpecGroupName() {
        return specGroupName;
    }

    public void setSpecGroupName(String specGroupName) {
        this.specGroupName = specGroupName == null ? null : specGroupName.trim();
    }

    public Integer getSpecGroupIndex() {
        return specGroupIndex;
    }

    public void setSpecGroupIndex(Integer specGroupIndex) {
        this.specGroupIndex = specGroupIndex;
    }

    public List<SpecValue> getSpecValueList() {
        return specValueList;
    }

    public void setSpecValueList(List<SpecValue> specValueList) {
        this.specValueList = specValueList;
    }
}