package top.mall.console.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class TestController {
    @RequestMapping("/test")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }
}
