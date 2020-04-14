package top.mall.console.utils.manager.session.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mall.console.utils.manager.cache.RedisCacheManager;
import top.mall.console.utils.manager.session.SessionManager;
import top.mall.console.utils.FieldConstant;
import top.mall.pojo.ConsoleUser;

@Service
public class SessionManagerImpl implements SessionManager {
    private final String REDIS_PREFIX_FOR_PT = "pt_";

    @Autowired
    RedisCacheManager redisCacheManager;

    @Override
    public void setSession(String key, ConsoleUser user, int timeout, String userId) {
        String oldAccessToken = redisCacheManager.get(REDIS_PREFIX_FOR_PT + userId);
        if(oldAccessToken != null) {
            redisCacheManager.del(REDIS_PREFIX_FOR_PT + userId, oldAccessToken);
        }
        redisCacheManager.set(key, JSONObject.toJSONString(user), timeout);
        redisCacheManager.set(REDIS_PREFIX_FOR_PT + userId, key, timeout);

    }

    @Override
    public String validateToken(String token) {
        String value =  redisCacheManager.get(token);
        if (StringUtils.isNotEmpty(value)) {
            redisCacheManager.set(token, value, FieldConstant.TOKEN_EXPRIE_TIME);
        }
        return value;
    }

    @Override
    public void outSession(String accessToken) {
        ConsoleUser resp = JSONObject.parseObject(redisCacheManager.get(accessToken),ConsoleUser.class);
        redisCacheManager.del(REDIS_PREFIX_FOR_PT + resp.getUid(),accessToken);
    }
}
