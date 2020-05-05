package top.mall.console.utils.manager.session;

import top.mall.pojo.ConsoleUser;

public interface SessionManager {

    void setSession(String key, Object user, int timeout, String userId);

    String validateToken(String token);

    void outSession(String accessToken);
}
