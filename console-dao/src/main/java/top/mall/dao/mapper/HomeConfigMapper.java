package top.mall.dao.mapper;

import top.mall.pojo.HomeConfig;

import java.util.List;

public interface HomeConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(HomeConfig record);

    int insertSelective(HomeConfig record);

    HomeConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(HomeConfig record);

    int updateByPrimaryKey(HomeConfig record);

    List<HomeConfig> selectAll();
}