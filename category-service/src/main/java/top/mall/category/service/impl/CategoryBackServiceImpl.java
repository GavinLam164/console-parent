package top.mall.category.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.mall.category.service.CategoryBackService;
import top.mall.dao.mapper.CategoryBackMapper;
import top.mall.pojo.category.CategoryBack;
import top.mall.pojo.category.CategoryBackTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Integer id = categoryBackMapper.insert(category);
        Integer parentId = category.getParentId();
        StringBuffer sb = new StringBuffer();
        if(parentId != null) {
            CategoryBack father = this.findCategoryById(parentId);
            sb.append(father.getPath()).append("_").append(id);
        }else {
            sb.append(id);
        }
        category.setPath(sb.toString());
        categoryBackMapper.updateSelective(category);
    }

    public CategoryBack findCategoryById(Integer categoryId) {
        return categoryBackMapper.findCategoryById(categoryId);
    }

    public List<CategoryBack> findCategoryByParentId(Integer categoryId) {
        return categoryBackMapper.findCategoryListByParentId(categoryId);
    }

    public List<CategoryBackTree> findCategoryTree() {
        List<CategoryBackTree> list = categoryBackMapper.findAllCategory();
        Map<Integer, CategoryBackTree> fatherMap = new HashMap<Integer, CategoryBackTree>();
        for(CategoryBackTree c: list) {
            fatherMap.put(c.getCategoryId(), c);
        }
        List<CategoryBackTree> treeList = new ArrayList<CategoryBackTree>();
        for(CategoryBackTree c: list) {
            CategoryBackTree father = fatherMap.get(c.getParentId());
            if(father == null) {
                treeList.add(c);
            }else {
                father.getSubList().add(c);
            }
        }
        return treeList;
    }
}
