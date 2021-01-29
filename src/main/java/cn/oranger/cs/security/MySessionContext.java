package cn.oranger.cs.security;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author Oranger
 * @date 2021/1/29
 */
public class MySessionContext {
    private static HashMap<String, HttpSession> myMap = new HashMap<>();

    public static synchronized void addSession(HttpSession session){
        if (session!=null){
            myMap.put(session.getId(),session);
        }
    }

    public static synchronized void delSession(HttpSession session) {
        if (session!=null){
            myMap.remove(session.getId());
        }
    }

    public static HttpSession getSession(String sessionId){
        if (sessionId == null){
            return null;
        }
        return myMap.get(sessionId);
    }
}
