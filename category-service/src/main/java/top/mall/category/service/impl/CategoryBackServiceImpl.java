package top.mall.category.service.impl;

import org.apache.dubbo.config.annotation.Service;
import top.mall.category.service.CategoryBackService;
import top.mall.pojo.category.CategoryBack;

@Service
public class CategoryBackServiceImpl implements CategoryBackService {
    public String getCategoryName() {
        return "categoryName";
    }

    public CategoryBack getCategory() {
        CategoryBack categoryBack = new CategoryBack();
        categoryBack.setCategoryId(1);
        categoryBack.setCategoryName("categoryName");
        return categoryBack;
    }
}
