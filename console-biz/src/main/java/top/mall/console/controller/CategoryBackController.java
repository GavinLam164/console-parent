package top.mall.console.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import top.mall.category.service.CategoryBackService;
import top.mall.pojo.category.CategoryBack;

@RequestMapping("/category/back")
@RestController
public class CategoryBackController {

    @Reference
    private CategoryBackService categoryBackService;

    @RequestMapping("/add")
    public String addCategory(@Param("categoryId")Integer categoryId, @Param("categoryName")String categoryName){
        CategoryBack categoryBack = new CategoryBack();
        categoryBack.setCategoryId(categoryId);
        categoryBack.setCategoryName(categoryName);
        System.out.println("test---" + categoryBack);
        categoryBackService.addCategory(categoryBack);
        return "success";
    }

    @RequestMapping("/find")
    public CategoryBack findCategoryById(@Param("categoryId")Integer categoryId) {
        return categoryBackService.findCategoryById(categoryId);
    }

}
