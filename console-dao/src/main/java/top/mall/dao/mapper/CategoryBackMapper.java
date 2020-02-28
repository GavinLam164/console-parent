package top.mall.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mall.pojo.category.CategoryBack;
import top.mall.pojo.category.CategoryBackTree;

import java.util.List;

public interface CategoryBackMapper {
    Integer insert(CategoryBack categoryBack);

    void delete(Integer categoryId);

    CategoryBack findCategoryById(Integer categoryId);

    List<CategoryBack> findCategoryListByParentId(Integer parentId);

    List<CategoryBackTree> findAllCategory();

    void updateSelective(CategoryBack category);
}
