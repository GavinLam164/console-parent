package top.mall.dao.mapper;

import top.mall.pojo.CategoryBack;
import top.mall.pojo.CategoryBackTree;

import java.util.List;

public interface CategoryBackMapper {


    Integer insert(CategoryBack categoryBack);

    void delete(Integer categoryId);

    CategoryBack findCategoryById(Integer categoryId);

    List<CategoryBack> findCategoryListByParentId(Integer parentId);

    List<CategoryBackTree> findAllCategory();

    void updateSelective(CategoryBack category);

    List<CategoryBack> findCategoryByLevel(Integer level);
}
