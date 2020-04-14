package top.mall.console.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.mall.console.utils.CommonException;
import top.mall.console.utils.manager.session.SessionManager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    SessionManager sessionManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证登录token
        String token = request.getHeader("token");
        if(token == null){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        String value = sessionManager.validateToken(token);
        if(StringUtils.isEmpty(value)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        return true;
    }
}
