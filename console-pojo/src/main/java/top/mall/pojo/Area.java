package top.mall.pojo;

import java.io.Serializable;

public class Area implements Serializable {
    private String areaId;

    private String areaCode;

    private String areaName;

    private String parentAreaId;

    private String areaNamePath;

    private String areaIdPath;

    public Area(String areaId, String areaCode, String areaName, String parentAreaId, String areaNamePath, String areaIdPath) {
        this.areaId = areaId;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.parentAreaId = parentAreaId;
        this.areaNamePath = areaNamePath;
        this.areaIdPath = areaIdPath;
    }

    public Area() {
        super();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getParentAreaId() {
        return parentAreaId;
    }

    public void setParentAreaId(String parentAreaId) {
        this.parentAreaId = parentAreaId == null ? null : parentAreaId.trim();
    }

    public String getAreaNamePath() {
        return areaNamePath;
    }

    public void setAreaNamePath(String areaNamePath) {
        this.areaNamePath = areaNamePath == null ? null : areaNamePath.trim();
    }

    public String getAreaIdPath() {
        return areaIdPath;
    }

    public void setAreaIdPath(String areaIdPath) {
        this.areaIdPath = areaIdPath == null ? null : areaIdPath.trim();
    }
}