package top.mall.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mall.pojo.category.CategoryBack;

import java.util.List;

public interface CategoryBackMapper {
    void insert(CategoryBack categoryBack);

    void delete(Integer categoryId);

    CategoryBack findCategoryById(Integer categoryId);

    List<CategoryBack> findCategoryListByParentId(Integer parentId);


}
