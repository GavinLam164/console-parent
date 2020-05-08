package top.mall.user.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import top.mall.console.utils.CommonRequestErrorCode;
import top.mall.console.utils.FieldConstant;
import top.mall.console.utils.MD5Utils;
import top.mall.console.utils.RequestErrorCode;
import top.mall.console.utils.manager.cache.RedisCacheManager;
import top.mall.console.utils.manager.session.SessionManager;
import top.mall.dao.mapper.ConsoleUserMapper;
import top.mall.dao.mapper.UserMapper;
import top.mall.pojo.ConsoleUser;
import top.mall.pojo.RpcResult;
import top.mall.pojo.User;
import top.mall.user.service.UserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource(name = "mSessionManager")
    SessionManager mSessionManager;
    @Resource
    RedisCacheManager redisCacheManager;
    @Override
    public void regist(User user) {
        user.setPassword(MD5Utils.digestPassword(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
    public RpcResult<Object> login(User consoleUser) {
        User user = userMapper.selectLoginUser(consoleUser.getPhone());
        if(user == null) {
            return RpcResult.error(RequestErrorCode.LOGIN_USER_INEXISTENCE, "用户不存在");
        }
        if(StringUtils.isBlank(consoleUser.getPhone())
                || StringUtils.isBlank(consoleUser.getPassword())) {
            return RpcResult.error(CommonRequestErrorCode.REQUEST_PARAMS_MISSING, "请求参数缺少");
        }
        String password = MD5Utils.digestPassword(consoleUser.getPassword());
        if(password == null || !password.equals(user.getPassword())) {
            return RpcResult.error(RequestErrorCode.LOGIN_PASSWORD_ERROR, "用户密码错误");
        }
        String uid = user.getUid().toString();
        System.out.println(uid);
        String accessToken = MD5Utils.digestPassword(uid);
        mSessionManager.setSession(accessToken, user, FieldConstant.TOKEN_EXPRIE_TIME, uid);
        Map<String, Object> map = new HashMap<>();
        User userInfo = new User();
        userInfo.setPhone(user.getPhone());
        userInfo.setNickName(user.getNickName());
        userInfo.setUid(user.getUid());
        map.put("accessToken", accessToken);
        map.put("userInfo", userInfo);
        return RpcResult.success(map);
    }

    @Override
    public RpcResult<Object> logout(String token) {
        mSessionManager.outSession(token);
        return RpcResult.success(null);
    }

    @Override
    public Object getBasicInfo(String token) {
        String json = redisCacheManager.get(token);
        return JSON.parseObject(json, User.class);
    }
}
