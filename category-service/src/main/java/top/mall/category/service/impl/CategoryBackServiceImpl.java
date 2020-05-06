package top.mall.category.service.impl;

import org.apache.dubbo.config.annotation.Service;
import top.mall.category.service.CategoryBackService;
import top.mall.console.utils.RequestErrorCode;
import top.mall.dao.mapper.CategoryBackMapper;
import top.mall.pojo.CategoryBack;
import top.mall.pojo.CategoryBackTree;
import top.mall.pojo.RpcResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryBackServiceImpl implements CategoryBackService {

    @Resource
    private CategoryBackMapper categoryBackMapper;

    public RpcResult findCategoryById(Integer categoryId) {
        CategoryBack category = categoryBackMapper.findCategoryById(categoryId);
        if (category == null) {
            return RpcResult.error(RequestErrorCode.CATEGORY_NOT_FOUND, "类目未找到");
        }
        return RpcResult.success(category);
    }

    public List<CategoryBack> findCategoryByParentId(Integer categoryId) {
        if (categoryId != null) {
            return categoryBackMapper.findCategoryListByParentId(categoryId);
        } else {
            return categoryBackMapper.findCategoryByLevel(1);
        }
    }

    public List<CategoryBackTree> findCategoryTree() {
        List<CategoryBackTree> list = categoryBackMapper.findAllCategory();
        Map<Integer, CategoryBackTree> fatherMap = new HashMap<Integer, CategoryBackTree>();
        for (CategoryBackTree c : list) {
            fatherMap.put(c.getCategoryId(), c);
        }
        List<CategoryBackTree> treeList = new ArrayList<CategoryBackTree>();
        for (CategoryBackTree c : list) {
            CategoryBackTree father = fatherMap.get(c.getParentId());
            if (father == null) {
                treeList.add(c);
            } else {
                father.getSubList().add(c);
            }
        }
        removeNoLeafCategoryBackTree(treeList, 1);
        return treeList;
    }

    private void removeNoLeafCategoryBackTree(List<CategoryBackTree> treeList, int level) {
        if (level == 3) {
            return;
        }
        List<Integer> removeIndexList = new ArrayList<Integer>();
        for (int i = 0; i < treeList.size(); i++) {
            if (treeList.get(i).getSubList().size() == 0) {
                removeIndexList.add(i);
            } else {
                removeNoLeafCategoryBackTree(treeList.get(i).getSubList(), level + 1);
            }
        }
        for (Integer i : removeIndexList) {
            treeList.remove((int) i);
        }
    }

    public void updateState(Integer categoryId, Integer state) {
        CategoryBack category = new CategoryBack();
        category.setCategoryId(categoryId);
        category.setState(state);
        categoryBackMapper.updateSelective(category);
    }

    public void addCategory(Integer parentId, String categoryName, String image) {
        CategoryBack category = new CategoryBack();
        category.setParentId(parentId);
        category.setCategoryName(categoryName);
        category.setImage(image);
        categoryBackMapper.insert(category);
        Integer id = category.getCategoryId();
        StringBuffer sb = new StringBuffer();
        if (parentId != null) {
            RpcResult result = this.findCategoryById(parentId);
            if (result != null) {
                CategoryBack father = (CategoryBack) result.getData();
                sb.append(father.getPath()).append("_").append(id);
                category.setLevel(father.getLevel() + 1);
            }
        } else {
            sb.append(id);
            category.setLevel(1);
        }
        category.setState(0);
        category.setPath(sb.toString());
        categoryBackMapper.updateSelective(category);
    }
}
