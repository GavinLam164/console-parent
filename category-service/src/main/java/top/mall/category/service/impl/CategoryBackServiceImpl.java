package top.mall.category.service.impl;

import org.apache.dubbo.config.annotation.Service;
import top.mall.category.service.CategoryBackService;

@Service
public class CategoryBackServiceImpl implements CategoryBackService {
    public String getCategoryName() {
        return "categoryName";
    }
}
