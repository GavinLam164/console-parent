package top.mall.console.controller;


import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.mall.console.utils.CommonException;
import top.mall.pojo.ConsoleUser;
import top.mall.pojo.RpcResult;
import top.mall.user.service.ConsoleUserService;

import java.util.Map;

@RestController
@RequestMapping("/console/user")
public class ConsoleUserController {

    @Reference
    ConsoleUserService consoleUserService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public RpcResult<Void> regist(@RequestBody ConsoleUser consoleUser) {
        consoleUserService.regist(consoleUser);
        return RpcResult.success(null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RpcResult<Map<String, Object>> login(@RequestBody ConsoleUser consoleUser) {
        Map<String, Object> map = consoleUserService.login(consoleUser);
        return RpcResult.success(map);
    }
}
