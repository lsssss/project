package java100.app.web.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java100.app.domain.Member;
import java100.app.domain.MemberUploadFile;
import java100.app.service.MemberService;

@RestController
@RequestMapping("/member")
@SessionAttributes("loginUser")
public class MemberController {
    
    @Autowired ServletContext servletContext;
    @Autowired MemberService memberService;
    @Autowired LoginController loginController;
    
    
    @RequestMapping("list")
    public String list(
            @RequestParam(value="pn", defaultValue="1") int pageNo,
            @RequestParam(value="ps", defaultValue="5") int pageSize,
            @RequestParam(value="words", required=false) String[] words,
            @RequestParam(value="oc", required=false) String orderColumn,
            @RequestParam(value="al", required=false) String align,
            Model model) throws Exception {

        
        // UI 제어와 관련된 코드는 이렇게 페이지 컨트롤러에 두어야 한다.
        //
        if (pageNo < 1) {
            pageNo = 1;
        }
        
        if (pageSize < 5 || pageSize > 15) {
            pageSize = 5;
        }
        
        HashMap<String,Object> options = new HashMap<>();
        if (words != null && words[0].length() > 0) {
            options.put("words", words);
        }
        options.put("orderColumn", orderColumn);
        options.put("align", align);
        
        int totalCount = memberService.getTotalCount();
        int lastPageNo = totalCount / pageSize;
        if ((totalCount % pageSize) > 0) {
            lastPageNo++;
        }
        
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("lastPageNo", lastPageNo);
        model.addAttribute("list", memberService.list(pageNo, pageSize, options));
        return "member/list";
    }
    
    @RequestMapping("add")
    public Object add(
            String email,
            String password,
            Member member,
            Model model) throws Exception {
        
        if (memberService.get(email) != null) {
            HashMap<String, Object> result = new HashMap<>();
            result.put("status", "addFail");
            return result;
            
        } else {
            HashMap<String, Object> result = new HashMap<>();
            member.setEmail(email);
            member.setPassword(password);
            member.setNicname(email);
            member.setTel(email);
            
            memberService.add(member);
            memberService.get(member.getMemberNo());

            result.put("email", member.getEmail());
            result.put("password", member.getPassword());
            return result;
        }

    }
    
    @RequestMapping("{no}")
    public Object view(@PathVariable int no) throws Exception {
        HashMap<String,Object> result = new HashMap<>();
        Member member = memberService.get(no);
        
        result.put("member", member);
        return result;
    }
    
    @RequestMapping("modify")
    public Object modify(int no) throws Exception {
        
        HashMap<String,Object> result = new HashMap<>();
        result.put("num", no);
        return result;
    }

    @RequestMapping("update")
    public Object update(
            Member member, 
            MultipartFile[] file) throws Exception {
        
        String uploadDir = servletContext.getRealPath("/download");
        if (!uploadDir.isEmpty()) {
            
            ArrayList<MemberUploadFile> uploadFiles = new ArrayList<>();
            
            for (MultipartFile part : file) {
                if (part.isEmpty())
                    continue;
                
                String filename = this.writeUploadFile(part, uploadDir);
                uploadFiles.add(new MemberUploadFile(filename));
            }
            
            member.setFiles(uploadFiles);
        }
        memberService.update(member);

        HashMap<String, Object> result = new HashMap<>();
        result.put("member", member);
        return result;
    }
    
    @RequestMapping("updatePush")
    public Object update(
            boolean push,
            int no) throws Exception {
        
        memberService.updatePush(push, no);
        
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "success");
        return result;
    }

    long prevMillis = 0;
    int count = 0;
    
    synchronized private String getNewFilename(String filename) {
        long currMillis = System.currentTimeMillis();
        if (prevMillis != currMillis) {
            count = 0;
            prevMillis = currMillis;
        }
        
        return  currMillis + "_" + count++ + extractFileExtName(filename); 
    }
    
    // 파일명에서 뒤의 확장자명을 추출한다.
    private String extractFileExtName(String filename) {
        int dotPosition = filename.lastIndexOf(".");
        
        if (dotPosition == -1)
            return "";
        
        return filename.substring(dotPosition);
    }
    
    private String writeUploadFile(MultipartFile part, String path) throws IOException {
        
        String filename = getNewFilename(part.getOriginalFilename());
        part.transferTo(new File(path + "/" + filename));
        return filename;
    }  

}








