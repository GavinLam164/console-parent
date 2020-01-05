package top.mall.category.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.mall.category.service.CategoryBackService;
import top.mall.dao.mapper.CategoryBackMapper;
import top.mall.pojo.category.CategoryBack;

@Service
public class CategoryBackServiceImpl implements CategoryBackService {

    @Autowired
    private CategoryBackMapper categoryBackMapper;

    public String getCategoryName() {
        return "categoryName";
    }

    public CategoryBack getCategory() {
        CategoryBack categoryBack = new CategoryBack();
        categoryBack.setCategoryId(1);
        categoryBack.setCategoryName("categoryName");
        return categoryBack;
    }

    public void addCategory(CategoryBack category) {
        categoryBackMapper.insert(category);
    }
}
