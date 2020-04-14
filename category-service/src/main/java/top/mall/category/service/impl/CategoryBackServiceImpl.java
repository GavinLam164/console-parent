package top.mall.category.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.mall.category.service.CategoryBackService;
import top.mall.dao.mapper.CategoryBackMapper;
import top.mall.pojo.CategoryBack;
import top.mall.pojo.CategoryBackTree;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryBackServiceImpl implements CategoryBackService {

    @Resource
    private CategoryBackMapper categoryBackMapper;

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

    public void updateState(Integer categoryId, Integer state) {
        CategoryBack category = new CategoryBack();
        category.setCategoryId(categoryId);
        category.setState(state);
        categoryBackMapper.updateSelective(category);
    }

    public void addCategory(Integer parentId, String categoryName) {
        CategoryBack category= new CategoryBack();
        category.setParentId(parentId);
        category.setCategoryName(categoryName);
        categoryBackMapper.insert(category);
        Integer id = category.getCategoryId();
        StringBuffer sb = new StringBuffer();
        if(parentId != null) {
            CategoryBack father = this.findCategoryById(parentId);
            sb.append(father.getPath()).append("_").append(id);
            category.setLevel(father.getLevel() + 1);
        }else {
            sb.append(id);
            category.setLevel(1);
        }
        category.setPath(sb.toString());
        categoryBackMapper.updateSelective(category);
    }
}
