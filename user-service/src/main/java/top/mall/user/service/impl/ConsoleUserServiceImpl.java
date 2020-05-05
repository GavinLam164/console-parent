package top.mall.user.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import top.mall.console.utils.*;
import top.mall.console.utils.manager.session.SessionManager;
import top.mall.dao.mapper.ConsoleUserMapper;
import top.mall.pojo.ConsoleUser;
import top.mall.pojo.RpcResult;
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
    public RpcResult<Object> login(ConsoleUser consoleUser) {
        ConsoleUser user = consoleUserMapper.selectLoginUser(consoleUser.getPhone());
        if(user == null) {
            return RpcResult.error(RequestErrorCode.LOGIN_USER_INEXISTENCE, "用户不存在");
        }
        if(StringUtils.isBlank(consoleUser.getPhone())
                || StringUtils.isBlank(user.getPassword())) {
            return RpcResult.error(CommonRequestErrorCode.REQUEST_PARAMS_MISSING, "请求参数缺少");
        }
        String password = MD5Utils.digestPassword(consoleUser.getPassword());
        if(password == null || !password.equals(user.getPassword())) {
            return RpcResult.error(RequestErrorCode.LOGIN_PASSWORD_ERROR, "用户密码错误");
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
        return RpcResult.success(map);
    }

    @Override
    public RpcResult<Object> logout(String token) {
        sessionManager.outSession(token);
        return RpcResult.success(null);
    }
}
