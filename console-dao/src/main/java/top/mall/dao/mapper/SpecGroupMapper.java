package top.mall.dao.mapper;

import top.mall.pojo.SpecGroup;

import java.util.List;

public interface SpecGroupMapper {
    int deleteByPrimaryKey(Integer specGroupId);

    int insert(SpecGroup record);

    int insertSelective(SpecGroup record);

    SpecGroup selectByPrimaryKey(Integer specGroupId);

    int updateByPrimaryKeySelective(SpecGroup record);

    int updateByPrimaryKey(SpecGroup record);

    List<SpecGroup> selectSpecGroupList();

}