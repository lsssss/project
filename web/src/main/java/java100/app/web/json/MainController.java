package java100.app.web.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java100.app.domain.Member;
import java100.app.service.BusinessService;
import java100.app.service.MemberService;

@RestController
@RequestMapping("/main")
@SessionAttributes("loginUser")
public class MainController {
    
    @Autowired MemberService memberService;
    @Autowired BusinessService businessService;
    
    @RequestMapping(value="home", method=RequestMethod.GET)
    public Object form() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("menuVisible", false);
        return result;
    }   
    
    @RequestMapping("start")
    public Object main_list(
            @ModelAttribute(value="loginUser") Member loginUser, Model model) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        model.addAttribute("start", memberService.get(loginUser.getMemberNo()));
        result.put("status", "success");
        return result;
    }

    @RequestMapping("{no}")
    public Object view(@PathVariable int no) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        result.put("member", memberService.get(no));
        return result;
    }
}
 









