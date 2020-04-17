package top.mall.user.service;

import top.mall.pojo.ConsoleUser;
import top.mall.pojo.RpcResult;

import java.util.Map;

public interface ConsoleUserService {
    void regist(ConsoleUser consoleUser);

    RpcResult<Object> login(ConsoleUser consoleUser);

    RpcResult<Object> logout(String token);
}
