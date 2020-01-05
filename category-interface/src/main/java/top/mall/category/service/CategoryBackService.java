package top.mall.category.service;

import top.mall.pojo.category.CategoryBack;

public interface CategoryBackService {
    String getCategoryName();
    CategoryBack getCategory();

    void addCategory(CategoryBack category);

    CategoryBack findCategoryById(Integer categoryId);
}
