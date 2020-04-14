package top.mall.console.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import top.mall.category.service.CategoryBackService;
import top.mall.pojo.CategoryBack;
import top.mall.pojo.CategoryBackTree;
import top.mall.pojo.RpcResult;

import java.util.List;

@RequestMapping("/category/back")
@RestController
public class CategoryBackController {

    @Reference
    private CategoryBackService categoryBackService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RpcResult<Void> addCategory(@RequestParam(value = "parentId", required = false)Integer parentId, @RequestParam("categoryName")String categoryName){
        categoryBackService.addCategory(parentId, categoryName);
        return RpcResult.success(null);
    }

    @RequestMapping("/find")
    public RpcResult<CategoryBack> findCategoryById(@RequestParam("categoryId")Integer categoryId) {
        return RpcResult.success(categoryBackService.findCategoryById(categoryId));
    }


    @RequestMapping("/findByParentId")
    public RpcResult<List<CategoryBack>> findCategoryByParentId(@RequestParam("categoryId")Integer categoryId) {
        return RpcResult.success(categoryBackService.findCategoryByParentId(categoryId));
    }

    @RequestMapping("/treeList")
    public RpcResult<List<CategoryBackTree>> treeList() {
        return RpcResult.success(categoryBackService.findCategoryTree());
    }

    @RequestMapping("/updateState")
    public RpcResult<Void> updateState(@RequestParam("categoryId") Integer categoryId, @RequestParam("state") Integer state) {
        categoryBackService.updateState(categoryId, state);
        return RpcResult.success(null);
    }

}
