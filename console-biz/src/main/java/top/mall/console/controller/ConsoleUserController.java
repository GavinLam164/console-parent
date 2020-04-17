package top.mall.console.controller;


import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import top.mall.console.utils.CommonException;
import top.mall.pojo.ConsoleUser;
import top.mall.pojo.RpcResult;
import top.mall.user.service.ConsoleUserService;

import java.util.List;
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
    public RpcResult<Object> login(@RequestBody ConsoleUser consoleUser) {
        return consoleUserService.login(consoleUser);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public RpcResult<Object> logout(@RequestHeader HttpHeaders headers) {
        List<String> tokenValues = headers.get("token");
        for (String s: tokenValues) {
            System.out.println(s);
        }
        return consoleUserService.logout(tokenValues.get(0));
    }
}
