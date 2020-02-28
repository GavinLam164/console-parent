package top.mall.console.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import top.mall.category.service.CategoryBackService;
import top.mall.pojo.category.CategoryBack;
import top.mall.pojo.category.CategoryBackTree;
import top.mall.pojo.category.RpcResult;

import java.util.List;

@RequestMapping("/category/back")
@RestController
public class CategoryBackController {

    @Reference
    private CategoryBackService categoryBackService;

    @RequestMapping("/add")
    public RpcResult addCategory(@Param("parentId")Integer parentId, @Param("categoryName")String categoryName){
        CategoryBack categoryBack = new CategoryBack();
        categoryBack.setParentId(parentId);
        categoryBack.setCategoryName(categoryName);
        categoryBackService.addCategory(categoryBack);
        return RpcResult.success(null);
    }

    @RequestMapping("/find")
    public RpcResult<CategoryBack> findCategoryById(@Param("categoryId")Integer categoryId) {
        return RpcResult.success(categoryBackService.findCategoryById(categoryId));
    }


    @RequestMapping("/findByParentId")
    public RpcResult<List<CategoryBack>> findCategoryByParentId(@Param("categoryId")Integer categoryId) {
        return RpcResult.success(categoryBackService.findCategoryByParentId(categoryId));
    }

    @RequestMapping("/treeList")
    public RpcResult<List<CategoryBackTree>> treeList() {
        return RpcResult.success(categoryBackService.findCategoryTree());
    }

}
