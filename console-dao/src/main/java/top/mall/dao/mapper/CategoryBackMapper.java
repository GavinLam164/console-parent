package top.mall.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mall.pojo.category.CategoryBack;

public interface CategoryBackMapper {
    void insert(CategoryBack categoryBack);
}
