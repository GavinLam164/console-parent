package top.mall.console.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mall.category.service.CategoryBackService;
import top.mall.pojo.category.CategoryBack;

@RequestMapping("/test")
@RestController
public class TestController {

    @Reference
    private CategoryBackService categoryBackService;

    @RequestMapping("/category")
    public CategoryBack getCategory() {
        return categoryBackService.getCategory();
    }
}
