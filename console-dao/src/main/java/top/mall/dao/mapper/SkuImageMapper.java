package top.mall.dao.mapper;

import top.mall.pojo.SkuImage;

public interface SkuImageMapper {
    int deleteByPrimaryKey(Integer skuImageId);

    int insert(SkuImage record);

    int insertSelective(SkuImage record);

    SkuImage selectByPrimaryKey(Integer skuImageId);

    int updateByPrimaryKeySelective(SkuImage record);

    int updateByPrimaryKey(SkuImage record);

    SkuImage selectBySkuId(Integer skuId);

    void deleteBySkuId(Integer skuId);
}