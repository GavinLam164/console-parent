package top.mall.dao.mapper;

import top.mall.pojo.SpecSku;

import java.util.List;

public interface SpecSkuMapper {
    int deleteByPrimaryKey(Integer skuSpecId);

    int insert(SpecSku record);

    int insertSelective(SpecSku record);

    SpecSku selectByPrimaryKey(Integer skuSpecId);

    int updateByPrimaryKeySelective(SpecSku record);

    int updateByPrimaryKey(SpecSku record);

    List<SpecSku> selectSpecSkuBySpuId(Integer spuId);

    List<SpecSku> selectSpecSkuBySkuId(Integer skuId);

    void deleteBySkuId(Integer skuId);

    void deleteBySpuId(Integer spuId);
}