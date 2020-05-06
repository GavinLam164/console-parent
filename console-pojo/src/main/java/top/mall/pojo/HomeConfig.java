package top.mall.pojo;

import java.io.Serializable;
import java.util.List;

public class HomeConfig implements Serializable {
    private Integer configId;

    private Integer type;

    private String title;

    private String image;

    private String value;

    private List<ProductSpu> spuList;

    public HomeConfig(Integer configId, Integer type, String title, String image, String value, List<ProductSpu> spuList) {
        this.configId = configId;
        this.type = type;
        this.title = title;
        this.image = image;
        this.value = value;
        this.spuList = spuList;
    }

    public HomeConfig(Integer configId, Integer type, String title, String image, String value) {
        this.configId = configId;
        this.type = type;
        this.title = title;
        this.image = image;
        this.value = value;
    }

    public HomeConfig() {
        super();
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ProductSpu> getSpuList() {
        return spuList;
    }

    public void setSpuList(List<ProductSpu> spuList) {
        this.spuList = spuList;
    }
}