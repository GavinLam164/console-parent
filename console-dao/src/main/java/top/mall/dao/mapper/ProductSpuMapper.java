package top.mall.dao.mapper;

import top.mall.pojo.ProductSpu;
import top.mall.pojo.req.SpuListReq;

import java.util.List;

public interface ProductSpuMapper {
    int deleteByPrimaryKey(Integer spuId);

    int insert(ProductSpu record);

    int insertSelective(ProductSpu record);

    ProductSpu selectByPrimaryKey(Integer spuId);

    int updateByPrimaryKeySelective(ProductSpu record);

    int updateByPrimaryKey(ProductSpu record);

    List<ProductSpu> selectByParams(SpuListReq spuListReq);
}