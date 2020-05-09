package top.mall.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.mall.pojo.Address;

import java.util.List;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<Address> selectByUid(@Param("uid") Integer uid, @Param("select") Integer select);

    void resetSelect(Integer uid);
}