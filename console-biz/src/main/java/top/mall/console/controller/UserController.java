package top.mall.console.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import top.mall.pojo.*;
import top.mall.user.service.AreaService;
import top.mall.user.service.ConsoleUserService;
import top.mall.user.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    UserService consoleUserService;

    @Reference
    AreaService areaService;

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
        return consoleUserService.logout(tokenValues.get(0));
    }

    @RequestMapping("/findAllArea")
    public RpcResult<List<AreaTree>> findAllArea() {
        return RpcResult.success(areaService.findAllArea());
    }

    @RequestMapping(value="/addAddress", method = RequestMethod.POST)
    public RpcResult addAddress(@RequestBody Address address, @RequestHeader("token") String token) {
        areaService.addAddress(address, token);
        return RpcResult.success(null);
    }

    @RequestMapping("/currentSelectAddress")
    public RpcResult<Map> currentSelectAddress(@RequestHeader("token") String token) {
        return RpcResult.success(areaService.currentSelectAddress(token));
    }

    @RequestMapping("/getBasicInfo")
    public RpcResult<Object> getBasicInfo(@RequestHeader("token")String token) {
        return RpcResult.success(consoleUserService.getBasicInfo(token));
    }
}
