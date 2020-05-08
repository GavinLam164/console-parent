package top.mall.user.service;

import top.mall.pojo.ConsoleUser;
import top.mall.pojo.RpcResult;
import top.mall.pojo.User;

public interface UserService {
    void regist(User user);

    RpcResult<Object> login(User user);

    RpcResult<Object> logout(String token);

    Object getBasicInfo(String token);
}
