package top.mall.dao.mapper;

import top.mall.pojo.ProductSku;

import java.util.List;

public interface ProductSkuMapper {
    int deleteByPrimaryKey(Integer skuId);

    int insert(ProductSku record);

    int insertSelective(ProductSku record);

    ProductSku selectByPrimaryKey(Integer skuId);

    int updateByPrimaryKeySelective(ProductSku record);

    int updateByPrimaryKey(ProductSku record);

    List<ProductSku> selectBySpuId(Integer spuId);

    void deleteBySkuId(Integer skuId);
}