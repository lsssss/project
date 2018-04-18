package java100.app.web.json;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java100.app.domain.Member;
import java100.app.service.FacebookService;
import java100.app.service.KakaotalkService;
import java100.app.service.MemberService;

@RestController
@RequestMapping("/auth")
@SessionAttributes("loginUser")
public class LoginController {

    @Autowired MemberService memberService;
    @Autowired FacebookService facebookService; 
    @Autowired KakaotalkService kakaotalkService; 
    
    @RequestMapping(value="login", method=RequestMethod.GET)
    public Object form() {
        
        HashMap<String, Object> result = new HashMap<>();
        
        result.put("menuVisible", false);
        return result;
    }

    @RequestMapping(value="login", method=RequestMethod.POST)
    public Object login(
            String email, 
            String password,
            String saveEmail,
            HttpServletResponse response,
            HttpSession httpSession,
            Model model) {
        Member member = memberService.get(email, password);
        
        if (saveEmail != null) {
            Cookie cookie = new Cookie("email", email);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("email", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        
        HashMap<String, Object> result = new HashMap<>();
        
        if (member == null) {
            result.put("loginUser", null);
            result.put("menuVisible", false);
            result.put("status", "fail");
        } else {
            model.addAttribute("loginUser", member);
            result.put("status", "success");
        }
        return result;
    }
    
    @RequestMapping(value="facebookLogin")
    public Object facebookLogin(
            String accessToken, 
            HttpSession session,
            Model model) {
    
        try {
            @SuppressWarnings("rawtypes")
            Map userInfo = facebookService.me(accessToken, Map.class);
            
            Member member = memberService.get(
                                (String)userInfo.get("email"));
            System.out.println(member);
            if (member == null) { // 등록된 회원이 아니면,
                // 페이스북에서 받은 정보로 회원을 자동 등록한다.
                member = new Member();
                member.setName((String)userInfo.get("name"));
                member.setEmail((String)userInfo.get("email"));
                member.setPassword("1111");
                memberService.add(member);
            }
            
            // 회원 정보를 세션에 저장하여 자동 로그인 처리를 한다.
            model.addAttribute("loginUser", member);
            System.out.println(member.getEmail());
            System.out.println(member.getName());
            System.out.println(member.getPassword());
            HashMap<String,Object> result = new HashMap<>();
            result.put("status", "success");
            return result;
            
        } catch (Exception e) {
            HashMap<String,Object> result = new HashMap<>();
            result.put("status", "fail");
            return result;
        }
    }
    
    @RequestMapping(value="kakaotalkLogin")
    public Object kakaotalkLogin(
            String access_token, 
            HttpSession session,
            Model model) {

        try {

            @SuppressWarnings("rawtypes")
            Map userInfo = kakaotalkService.me(access_token, Map.class);
            Member member = memberService.get(
                    (String)userInfo.get("kaccount_email"));
            if (member == null) { // 등록된 회원이 아니면,

                member = new Member();
                member.setName((String)userInfo.get("kaccount_email"));
                member.setEmail((String)userInfo.get("kaccount_email"));
                member.setPassword("1111");
                memberService.add(member);
            }
            
            // 회원 정보를 세션에 저장하여 자동 로그인 처리를 한다.
            model.addAttribute("loginUser", member);
            
            HashMap<String,Object> result = new HashMap<>();
            result.put("status", "success");
            return result;
            
        } catch (Exception e) {
            HashMap<String,Object> result = new HashMap<>();
            result.put("status", "fail");
            return result;
        }
    }
    
    @RequestMapping("logout")
    public Object logout(HttpSession session, SessionStatus status) {
        
        // @SessionAttributes에서 관리하는 세션 데이터를 모두 제거한다.
        status.setComplete();
        
        // HttpSession 객체를 무효화시킨다.
        session.invalidate();
        
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "success");
        return result;
    }  
    
    @RequestMapping("loginUser")
    public Object loginUser(HttpSession session) {
        
        Member member = (Member)session.getAttribute("loginUser");
        HashMap<String,Object> result = new HashMap<>();
        
        if (member != null) {
//            result.put("loginInfo", member);
            result.put("member", memberService.get(member.getMemberNo()));
            result.put("status", "success"); // 막지마시오 
        } else {
            result.put("status", "fail");
        }
            
        return result;
    }
    
}









