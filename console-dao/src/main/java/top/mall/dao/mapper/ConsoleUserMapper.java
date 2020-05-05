package top.mall.dao.mapper;

import top.mall.pojo.ConsoleUser;

public interface ConsoleUserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(ConsoleUser record);

    int insertSelective(ConsoleUser record);

    ConsoleUser selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(ConsoleUser record);

    int updateByPrimaryKey(ConsoleUser record);

    ConsoleUser selectLoginUser(String phone);
}