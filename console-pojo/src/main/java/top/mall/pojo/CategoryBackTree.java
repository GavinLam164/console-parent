package top.mall.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryBackTree extends CategoryBack implements Serializable {
    List<CategoryBackTree> subList;

    public CategoryBackTree() {
        super();
    }

    public CategoryBackTree(Integer categoryId, String categoryName, String path, Integer state, Integer parentId, Integer level) {
        super(categoryId, categoryName, path, state, parentId, level);
        this.subList = new ArrayList<CategoryBackTree>();
    }

    public List<CategoryBackTree> getSubList() {
        if(this.subList == null) {
            this.subList = new ArrayList<CategoryBackTree>();
        }
        return subList;
    }

    public void setSubList(List<CategoryBackTree> subList) {
        this.subList = subList;
    }
}
