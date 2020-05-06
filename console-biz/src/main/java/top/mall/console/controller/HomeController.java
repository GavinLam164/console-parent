package top.mall.console.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mall.pojo.RpcResult;
import top.mall.product.service.HomeService;

@RequestMapping("/home")
@RestController
public class HomeController {

    @Reference
    HomeService homeService;

    @RequestMapping("/findAllConfigs")
    public RpcResult findAllConfigs(){
        return RpcResult.success(homeService.findAllConfigs());
    }
}
