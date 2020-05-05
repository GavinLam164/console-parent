package top.mall.console.utils.manager.session.impl;

import org.springframework.stereotype.Service;
import top.mall.console.utils.manager.session.SessionManager;

@Service("mSessionManager")
public class MSessionManagerImpl extends SessionManagerImpl implements SessionManager {
    public String REDIS_PREFIX_FOR_PT = "pu_";

}
