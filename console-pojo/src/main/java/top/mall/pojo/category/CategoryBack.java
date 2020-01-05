package top.mall.pojo.category;

import java.io.Serializable;

public class CategoryBack implements Serializable {
    Integer categoryId;
    String categoryName;
    String path;
    Integer state;
    Integer parentId;

    public CategoryBack() {
    }

    public CategoryBack(Integer categoryId, String categoryName, String path, Integer state, Integer parentId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.path = path;
        this.state = state;
        this.parentId = parentId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "CategoryBack{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", path='" + path + '\'' +
                ", state=" + state +
                ", parentId=" + parentId +
                '}';
    }
}
