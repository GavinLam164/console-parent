package top.mall.console.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import top.mall.pojo.ConsoleUser;
import top.mall.pojo.RpcResult;
import top.mall.pojo.User;
import top.mall.user.service.ConsoleUserService;
import top.mall.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    UserService consoleUserService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public RpcResult<Void> regist(@RequestBody User consoleUser) {
        consoleUserService.regist(consoleUser);
        return RpcResult.success(null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RpcResult<Object> login(@RequestBody User consoleUser) {
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
