package top.mall.user.service;

import top.mall.pojo.ConsoleUser;
import top.mall.pojo.RpcResult;

public interface ConsoleUserService {
    void regist(ConsoleUser consoleUser);

    void login(ConsoleUser consoleUser);
}
