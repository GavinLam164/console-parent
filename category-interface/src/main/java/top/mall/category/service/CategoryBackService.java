package top.mall.category.service;

import top.mall.pojo.category.CategoryBack;
import top.mall.pojo.category.CategoryBackTree;

import java.util.List;

public interface CategoryBackService {
    String getCategoryName();
    CategoryBack getCategory();

    void addCategory(CategoryBack category);

    CategoryBack findCategoryById(Integer categoryId);

    List<CategoryBack> findCategoryByParentId(Integer categoryId);

    List<CategoryBackTree> findCategoryTree();
}
