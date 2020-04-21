package top.mall.dao.mapper;

import org.apache.ibatis.annotations.Param;
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

    List<SpecValue> selectBySpuIdAndSpecGroupId(@Param("spuId")Integer spuId,@Param("specGroupId") Integer specGroupId);

    void deleteBySpuId(Integer spuId);

    List<SpecValue> selectBySpuId(Integer spuId);
}