package java100.app.web.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java100.app.domain.Lost;
import java100.app.domain.LostUploadFile;
import java100.app.domain.Member;
import java100.app.service.LostService;
import java100.app.service.MemberService;

@RestController
@RequestMapping("/lost")
@SessionAttributes("loginUser")
public class LostController {
    
    @Autowired ServletContext servletContext;
    @Autowired LostService lostService;
    @Autowired MemberService memberService;
    
    @RequestMapping("list")
    public Object list(
            @RequestParam(value="pn", defaultValue="1") int pageNo,
            @RequestParam(value="ps", defaultValue="6") int pageSize) throws Exception {

        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageSize < 6 || pageSize > 18) {
            pageSize = 6;
        }
        HashMap<String,Object> options = new HashMap<>();
        
        int totalCount = lostService.getTotalCount();
        int lastPageNo = totalCount / pageSize;
        if ((totalCount % pageSize) > 0) {
            lastPageNo++;
        }
        HashMap<String,Object> result = new HashMap<>();
        result.put("pageNo", pageNo);
        result.put("lastPageNo", lastPageNo);
        result.put("list", lostService.list(pageNo, pageSize, options));
        return result;
    }
    
    @RequestMapping("listAll")
    public Object listAll() throws Exception {
        HashMap<String,Object> result = new HashMap<>();
        result.put("list", lostService.listAll());
        return result;
    }
    
    @RequestMapping("{no}")
    public Object view(@PathVariable int no) throws Exception {
        HashMap<String,Object> result = new HashMap<>();
        result.put("data", lostService.get(no));
        return result;
    }
    
    @RequestMapping("add")
    public Object add(
            Lost lost,
            MultipartFile[] file,
            @ModelAttribute(value="loginUser") Member loginUser) throws Exception {
        
        String uploadDir = servletContext.getRealPath("/download");
        
        ArrayList<LostUploadFile> uploadFiles = new ArrayList<>();
        
        for (MultipartFile part : file) {
            if (part.isEmpty())
                continue;
            
            String filename = this.writeUploadFile(part, uploadDir);
            
            uploadFiles.add(new LostUploadFile(filename));
        }
        
        lost.setFiles(uploadFiles);
        lost.setRegistrant(loginUser);
        lostService.add(lost);
        
        HashMap<String, Object> result = new HashMap<>();
        
        result.put("member", loginUser);
        result.put("status", "success");
        
        return result;
    }

    @RequestMapping("update")
    public Object update(
            Lost lost, 
            MultipartFile[] file,
            @ModelAttribute(value="loginUser") Member loginUser) throws Exception {
            String uploadDir = servletContext.getRealPath("/download");
            if (!uploadDir.isEmpty()) {
                
                ArrayList<LostUploadFile> uploadFiles = new ArrayList<>();
                
                for (MultipartFile part : file) {
                    if (part.isEmpty())
                        continue;
                    
                    String filename = this.writeUploadFile(part, uploadDir);
                    
                    uploadFiles.add(new LostUploadFile(filename));
                }
                
                lost.setFiles(uploadFiles);
            }

        lostService.update(lost);
        
        HashMap<String,Object> result = new HashMap<>();
        
        result.put("status", "success");
        
        return result;
    }

    @RequestMapping("delete")
    public Object delete(int no) throws Exception {

        lostService.delete(no);
        
        HashMap<String,Object> result = new HashMap<>();
        
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








