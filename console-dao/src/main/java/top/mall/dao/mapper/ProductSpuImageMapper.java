package top.mall.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.mall.pojo.ProductSpuImage;

import java.util.List;

public interface ProductSpuImageMapper {
    int deleteByPrimaryKey(Integer spuImageId);

    int insert(ProductSpuImage record);

    int insertSelective(ProductSpuImage record);

    ProductSpuImage selectByPrimaryKey(Integer spuImageId);

    int updateByPrimaryKeySelective(ProductSpuImage record);

    int updateByPrimaryKey(ProductSpuImage record);

    List<ProductSpuImage> selectByType(@Param("spuId")Integer spuId, @Param("spuImageType") Integer spuImageType);

    List<ProductSpuImage> selectBySpuId(@Param("spuId")Integer spuId);
}