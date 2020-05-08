package top.mall.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AreaTree extends Area implements Serializable {
    List<AreaTree> subList;

    public AreaTree(String areaId, String areaCode, String areaName, String parentAreaId, String areaNamePath, String areaIdPath, List<AreaTree> subList) {
        super(areaId, areaCode, areaName, parentAreaId, areaNamePath, areaIdPath);
        this.subList = subList;
    }

    public AreaTree(String areaId, String areaCode, String areaName, String parentAreaId, String areaNamePath, String areaIdPath) {
        super(areaId, areaCode, areaName, parentAreaId, areaNamePath, areaIdPath);
        subList = new ArrayList<AreaTree>();
    }

    public List<AreaTree> getSubList() {
        if(subList == null) {
            return subList = new ArrayList<AreaTree>();
        }
        return subList;
    }

    public AreaTree() {
    }

    public void setSubList(List<AreaTree> subList) {
        this.subList = subList;
    }
}
