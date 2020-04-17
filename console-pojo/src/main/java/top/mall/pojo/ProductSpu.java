package top.mall.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ProductSpu implements Serializable {
    private Integer spuId;

    private String spuName;

    private String spuDesc;

    private BigDecimal spuPrice;

    private Integer categoryId;

    private Integer brandId;

    private Integer saleState;

    private ProductSpuImage spuImage;

    private List<ProductSpuImage> bannerImageList;

    private List<ProductSpuImage> detailImageList;

    public List<ProductSpuImage> getBannerImageList() {
        return bannerImageList;
    }

    public void setBannerImageList(List<ProductSpuImage> bannerImageList) {
        this.bannerImageList = bannerImageList;
    }

    public List<ProductSpuImage> getDetailImageList() {
        return detailImageList;
    }

    public void setDetailImageList(List<ProductSpuImage> detailImageList) {
        this.detailImageList = detailImageList;
    }

    public ProductSpu(Integer spuId, String spuName, String spuDesc, BigDecimal spuPrice, Integer categoryId, Integer brandId, Integer saleState) {
        this.spuId = spuId;
        this.spuName = spuName;
        this.spuDesc = spuDesc;
        this.spuPrice = spuPrice;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.saleState = saleState;
    }

    public ProductSpu() {
        super();
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName == null ? null : spuName.trim();
    }

    public String getSpuDesc() {
        return spuDesc;
    }

    public void setSpuDesc(String spuDesc) {
        this.spuDesc = spuDesc == null ? null : spuDesc.trim();
    }

    public BigDecimal getSpuPrice() {
        return spuPrice;
    }

    public void setSpuPrice(BigDecimal spuPrice) {
        this.spuPrice = spuPrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getSaleState() {
        return saleState;
    }

    public void setSaleState(Integer saleState) {
        this.saleState = saleState;
    }

    public ProductSpuImage getSpuImage() {
        return spuImage;
    }

    public void setSkuImage(ProductSpuImage spuImage) {
        this.spuImage = spuImage;
    }
}