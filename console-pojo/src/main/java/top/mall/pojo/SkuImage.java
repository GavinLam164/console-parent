package top.mall.pojo;

import java.io.Serializable;

public class SkuImage implements Serializable {
    private Integer skuImageId;

    private Integer skuId;

    private String skuImagePath;

    private Double width;

    private Double height;

    private Double wHScale;

    public SkuImage(Integer skuImageId, Integer skuId, String skuImagePath, Double width, Double height, Double wHScale) {
        this.skuImageId = skuImageId;
        this.skuId = skuId;
        this.skuImagePath = skuImagePath;
        this.width = width;
        this.height = height;
        this.wHScale = wHScale;
    }

    public SkuImage() {
        super();
    }

    public Integer getSkuImageId() {
        return skuImageId;
    }

    public void setSkuImageId(Integer skuImageId) {
        this.skuImageId = skuImageId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getSkuImagePath() {
        return skuImagePath;
    }

    public void setSkuImagePath(String skuImagePath) {
        this.skuImagePath = skuImagePath == null ? null : skuImagePath.trim();
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