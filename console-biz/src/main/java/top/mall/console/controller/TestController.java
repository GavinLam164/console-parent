package top.mall.console.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mall.category.service.CategoryBackService;

@RequestMapping("/")
@RestController
public class TestController {

    @Reference
    private CategoryBackService categoryBackService;

    @RequestMapping("/test")
    public String hello() {
        return categoryBackService.getCategoryName();
    }
}
