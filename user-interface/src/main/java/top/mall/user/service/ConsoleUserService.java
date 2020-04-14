package top.mall.user.service;

import top.mall.pojo.ConsoleUser;

import java.util.Map;

public interface ConsoleUserService {
    void regist(ConsoleUser consoleUser);

    Map<String, Object> login(ConsoleUser consoleUser);
}
