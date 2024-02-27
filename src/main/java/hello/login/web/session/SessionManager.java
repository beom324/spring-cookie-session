package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME,sessionId = "my_session_ID";
    private Map<String,Object> sessionStore = new ConcurrentHashMap<>();

    //세션생성
    public void createSession(Object value, HttpServletResponse response){

        //세션아이디 생성,값을 세션에 저장
        String sessionId= UUID.randomUUID().toString();//자바에서 임의로 랜덤값을 만들어줌

        sessionStore.put(sessionId,value);

        //쿠키를 생성

        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);

    }
    public Object getSession(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            return null;
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(SESSION_COOKIE_NAME)){
                return sessionStore.get(cookie.getValue());
            }

        }
        return null;
    }

}
