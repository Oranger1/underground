package cn.oranger.cs.controller;

import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.requestVo.LoginRequsetVo;
import cn.oranger.cs.security.MySessionContext;
import cn.oranger.cs.security.TokenUtils;
import cn.oranger.cs.service.ManagerService;
import cn.oranger.cs.utils.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Oranger
 * @date 2021/1/15
 */
@RestController
@RequestMapping("")
public class LoginController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequsetVo loginRequsetVo, HttpServletRequest request, HttpServletResponse response){
        Manager manager = managerService.lambdaQuery()
                .eq(Manager::getUsername, loginRequsetVo.getUsername())
                .eq(Manager::getPassword, loginRequsetVo.getPassword()).one();
        String token = null;
        if (manager!=null){
            try {
                String json = JSONUtils.toJSONString(manager);
                token = TokenUtils.createJWT("oranger",manager.getUsername(), json);

                //将token信息存放于session中。
//                HttpSession session = request.getSession();
//                session.setAttribute("token",token);
//                MySessionContext.addSession(session);

                Claims claims = TokenUtils.parseJWT(token);
                claims.getId();
                Manager manager1 = JSONUtils.parse(claims.getSubject(), Manager.class);
                System.out.println(manager1.getUsername());
                System.out.println(claims.get("username", String.class));
                response.setHeader("authorization",token);

            } catch (Exception e) {
                System.err.println("登陆失败");
                e.printStackTrace();
            }
        }
        return token;
    }
}
