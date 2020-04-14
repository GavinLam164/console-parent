package top.mall.user.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import top.mall.console.utils.CommonException;
import top.mall.console.utils.FieldConstant;
import top.mall.console.utils.manager.session.SessionManager;
import top.mall.console.utils.MD5Utils;
import top.mall.dao.mapper.ConsoleUserMapper;
import top.mall.pojo.ConsoleUser;
import top.mall.user.service.ConsoleUserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> login(ConsoleUser consoleUser) {
        ConsoleUser user = consoleUserMapper.selectLoginUser(consoleUser.getPhone());
        if(StringUtils.isBlank(consoleUser.getPhone())
                || StringUtils.isBlank(user.getPassword())) { // 请求参数缺少

        }
        if(user == null) { // 用户不存在
        }
        String password = MD5Utils.digestPassword(consoleUser.getPassword());
        if(password == null || !password.equals(user.getPassword())) { // 密码错误
        }
        String uid = user.getUid().toString();
        String accessToken = MD5Utils.digestPassword(uid);
        sessionManager.setSession(accessToken, user, FieldConstant.TOKEN_EXPRIE_TIME, uid);
        Map<String, Object> map = new HashMap<>();
        ConsoleUser userInfo = new ConsoleUser();
        userInfo.setPhone(user.getPhone());
        userInfo.setNickName(user.getNickName());
        userInfo.setUid(user.getUid());
        map.put("accessToken", accessToken);
        map.put("userInfo", userInfo);
        return map;
    }
}
