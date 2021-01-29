package cn.oranger.cs.security.handler;

import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.security.MySessionContext;
import cn.oranger.cs.security.TokenUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Oranger
 * @date 2021/1/16
 */
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {
    private static final Set<String> NOT_INTERCEPT_URI = new HashSet<>();//不拦截的URI

    static {
        NOT_INTERCEPT_URI.add("/login.html");
        NOT_INTERCEPT_URI.add("/login");
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object object) throws Exception {
        String uri = request.getRequestURI();
        if (NOT_INTERCEPT_URI.contains(uri)) {
            log.info("不拦截" + uri);
            return true;
        }
        log.info("拦截" + uri);

        String token = request.getHeader("authorization");
        HttpSession localSession = MySessionContext.getSession(request.getSession().getId());
        if(!localSession.getAttribute("token").equals(token)){
            throw new RuntimeException("用户未登陆");
        }
        Claims claims = TokenUtils.parseJWT(token);
        if (claims.get("system",String.class)==null){
            throw new RuntimeException("用户未登陆");
        }
//        HttpSession session = request.getSession();
//        Manager userInfo = (Manager) session.getAttribute("manager_in_the_session");
//        if (userInfo == null) {
//            throw new RuntimeException("用户未登陆");
//        }
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv) throws Exception {
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行
     * （主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) throws Exception {
    }
}
