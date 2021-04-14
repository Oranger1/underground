package cn.oranger.cs.controller;

import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.requestVo.LoginRequsetVo;
import cn.oranger.cs.responseVo.LoginResponseVo;
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
    public LoginResponseVo login(@RequestBody LoginRequsetVo loginRequsetVo, HttpServletResponse response){
        Manager manager = managerService.lambdaQuery()
                .eq(Manager::getUsername, loginRequsetVo.getUsername())
                .eq(Manager::getPassword, loginRequsetVo.getPassword()).one();
        String token = null;
        Integer code = 404;
        if (manager!=null){
            try {
                code = 200;
                String json = JSONUtils.toJSONString(manager);
                token = TokenUtils.createJWT("oranger",manager.getUsername(), json);

                //将token信息存放于session中。
//                HttpSession session = request.getSession();
//                session.setAttribute("token",token);
//                MySessionContext.addSession(session);
                response.setHeader("authorization",token);

            } catch (Exception e) {
                System.err.println("登陆失败");
                e.printStackTrace();
            }
        }
        LoginResponseVo loginResponseVo = new LoginResponseVo();
        loginResponseVo.setCode(code);
        loginResponseVo.setToken(token);
        return loginResponseVo;
    }

    @GetMapping("/touristLogin")
    public LoginResponseVo touristLogin(){
        String token = null;
        Integer code = 404;
        try {
            Manager manager = managerService.getManager(0);
            code = 200;
            String json = JSONUtils.toJSONString(manager);
            token = TokenUtils.createJWT("oranger", manager.getUsername(), json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginResponseVo loginResponseVo = new LoginResponseVo();
        loginResponseVo.setCode(code);
        loginResponseVo.setToken(token);
        return loginResponseVo;
    }
}
