package top.mall.dao.mapper;

import top.mall.pojo.SpecValue;

import java.util.List;

public interface SpecValueMapper {
    int deleteByPrimaryKey(Integer specValueId);

    int insert(SpecValue record);

    int insertSelective(SpecValue record);

    SpecValue selectByPrimaryKey(Integer specValueId);

    int updateByPrimaryKeySelective(SpecValue record);

    int updateByPrimaryKey(SpecValue record);

    List<SpecValue> selectSpecValueList();
}