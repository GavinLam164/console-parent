package top.mall.user.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import top.mall.console.manager.session.SessionManager;
import top.mall.console.utils.MD5Utils;
import top.mall.dao.mapper.ConsoleUserMapper;
import top.mall.pojo.ConsoleUser;
import top.mall.user.service.ConsoleUserService;

import javax.annotation.Resource;

@Service
public class ConsoleUserServiceImpl implements ConsoleUserService {
    @Resource
    ConsoleUserMapper consoleUserMapper;
    @Resource
    SessionManager sessionManager;

    @Override
    public void regist(ConsoleUser consoleUser) {
        consoleUser.setPassword(MD5Utils.digestPassword(consoleUser.getPassword()));
        consoleUserMapper.insert(consoleUser);
    }

    @Override
    public void login(ConsoleUser consoleUser) {
        ConsoleUser user = consoleUserMapper.selectLoginUser(consoleUser.getPhone());
        if(StringUtils.isBlank(consoleUser.getPhone())
                || StringUtils.isBlank(user.getPassword())) { // 请求参数缺少
        }
        if(user == null) { // 用户不存在
            return;
        }
        String password = MD5Utils.digestPassword(consoleUser.getPassword());
        if(password == null || !password.equals(user.getPassword())) { // 密码错误
        }


    }
}
