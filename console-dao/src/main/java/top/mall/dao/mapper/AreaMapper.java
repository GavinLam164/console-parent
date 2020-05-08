package top.mall.dao.mapper;

import top.mall.pojo.Area;
import top.mall.pojo.AreaTree;

import java.util.List;

public interface AreaMapper {
    int deleteByPrimaryKey(String areaId);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(String areaId);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    List<AreaTree> findAllArea();
}