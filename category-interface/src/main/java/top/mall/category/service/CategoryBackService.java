package top.mall.category.service;

import top.mall.pojo.CategoryBack;
import top.mall.pojo.CategoryBackTree;
import top.mall.pojo.RpcResult;

import java.util.List;

public interface CategoryBackService {

    RpcResult findCategoryById(Integer categoryId);

    List<CategoryBack> findCategoryByParentId(Integer categoryId);

    List<CategoryBackTree> findCategoryTree();

    void updateState(Integer categoryId, Integer state);

    void addCategory(Integer parentId, String categoryName);
}
