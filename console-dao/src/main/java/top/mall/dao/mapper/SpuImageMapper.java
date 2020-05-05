package top.mall.dao.mapper;

import top.mall.pojo.SpuImage;

public interface SpuImageMapper {
    int deleteByPrimaryKey(Integer spuImageId);

    int insert(SpuImage record);

    int insertSelective(SpuImage record);

    SpuImage selectByPrimaryKey(Integer spuImageId);

    int updateByPrimaryKeySelective(SpuImage record);

    int updateByPrimaryKey(SpuImage record);
}