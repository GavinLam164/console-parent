package top.mall.pojo;

import java.io.Serializable;

public class SpuImage implements Serializable {
    private Integer spuImageId;

    private Integer spuId;

    private String spuImagePath;

    private Integer spuImageType;

    private Integer spuImageIndex;

    private Double width;

    private Double height;

    private Double wHScale;

    public SpuImage(Integer spuImageId, Integer spuId, String spuImagePath, Integer spuImageType, Integer spuImageIndex, Double width, Double height, Double wHScale) {
        this.spuImageId = spuImageId;
        this.spuId = spuId;
        this.spuImagePath = spuImagePath;
        this.spuImageType = spuImageType;
        this.spuImageIndex = spuImageIndex;
        this.width = width;
        this.height = height;
        this.wHScale = wHScale;
    }

    public SpuImage() {
        super();
    }

    public Integer getSpuImageId() {
        return spuImageId;
    }

    public void setSpuImageId(Integer spuImageId) {
        this.spuImageId = spuImageId;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public String getSpuImagePath() {
        return spuImagePath;
    }

    public void setSpuImagePath(String spuImagePath) {
        this.spuImagePath = spuImagePath == null ? null : spuImagePath.trim();
    }

    public Integer getSpuImageType() {
        return spuImageType;
    }

    public void setSpuImageType(Integer spuImageType) {
        this.spuImageType = spuImageType;
    }

    public Integer getSpuImageIndex() {
        return spuImageIndex;
    }

    public void setSpuImageIndex(Integer spuImageIndex) {
        this.spuImageIndex = spuImageIndex;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getwHScale() {
        return wHScale;
    }

    public void setwHScale(Double wHScale) {
        this.wHScale = wHScale;
    }
}