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

import java100.app.domain.Hospital;
import java100.app.domain.HospitalUploadFile;
import java100.app.domain.Member;
import java100.app.service.HospitalService;

@RestController
@RequestMapping("/hospital")
@SessionAttributes("loginUser")
public class HospitalController {
    
    @Autowired ServletContext servletContext;
    @Autowired HospitalService hospitalService;
 
    
    
    @RequestMapping("list")
    public Object list(
            @RequestParam(value="pn", defaultValue="1") int pageNo,
            @RequestParam(value="ps", defaultValue="5") int pageSize,
            @RequestParam(value="words", required=false) String[] words,
            @RequestParam(value="oc", required=false) String orderColumn,
            @RequestParam(value="al", required=false) String align)throws Exception
            {  if (pageNo < 1) {
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
            
            int totalCount = hospitalService.getTotalCount();
            int lastPageNo = totalCount / pageSize;
            if ((totalCount % pageSize) > 0) {
                lastPageNo++;
            }
            
            // view 컴포넌트가 사용할 값을 Model에 담는다.
            HashMap<String,Object> result = new HashMap<>();
            
            result.put("pageNo", pageNo);
            result.put("lastPageNo", lastPageNo);
            result.put("list", hospitalService.list(pageNo, pageSize, options));
            return result;
    }
    
/*    @RequestMapping("add")
    public Object add( Hospital hospital,  MultipartFile[] file, HttpSession session) throws Exception {
        Member member = (Member)session.getAttribute("loginUser");
        String uploadDir = servletContext.getRealPath("/download");

        System.out.println(member.getMemberNo());
        ArrayList<HospitalUploadFile> uploadFiles = new ArrayList<>();
        
        for (MultipartFile part : file) {
            if (part.isEmpty())
                continue;
            
            String filename = this.writeUploadFile(part, uploadDir);
            
            uploadFiles.add(new HospitalUploadFile(filename));
        }

        hospital.setMember(member);
        hospital.setFiles(uploadFiles);

        // 게시글 작성자는 로그인 사용자이다. 
        
        // 게시글 등록
        hospitalService.add(hospital);
        
        HashMap<String,Object> result = new HashMap<>();
        result.put("status", "success");
        
        
        
        return result;
    }*/
    
    @RequestMapping("add")
    public Object add(
            Hospital hospital,
            MultipartFile[] file,
            @ModelAttribute(value="loginUser") Member loginUser) throws Exception {
        
        String uploadDir = servletContext.getRealPath("/download");

        ArrayList<HospitalUploadFile> uploadFiles = new ArrayList<>();
        
        for (MultipartFile part : file) {
            if (part.isEmpty())
                continue;
            
            String filename = this.writeUploadFile(part, uploadDir);
            
            uploadFiles.add(new HospitalUploadFile(filename));
        }
        
        hospital.setFiles(uploadFiles);

        // 게시글 작성자는 로그인 사용자이다. 
        hospital.setMember(loginUser);
        HashMap<String,Object> result = new HashMap<>();
        
     try {
         hospitalService.add(hospital);
         result.put("status", "success");
    } catch (Exception e) {
         result.put("status", "fail");
    }
        
        // 게시글 등록
        /*    int count = hospitalService.add(hospital);
        
        HashMap<String,Object> result = new HashMap<>();
        if(count == 0) {
            result.put("status", "fail");
        }else {
            result.put("status", "success");
        }*/
        
      
        return result;
    }

    @RequestMapping("{no}")
    public Object view(@PathVariable int no) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        result.put("data", hospitalService.get(no));
        return result;
    }
    
    @RequestMapping("hospitalview")
    public Object hospitalView(@ModelAttribute(value="loginUser") Member loginUser) throws Exception {
                HashMap<String,Object> result = new HashMap<>();
                int hospitalNo = hospitalService.getHospitalNoLoginUserNo(loginUser.getMemberNo());//로그인 유저의 번호로 병원번호 가져오기
                result.put("data", hospitalService.get(hospitalNo)); 
                result.put("status", "success");
                return result;   
    }
    

    @RequestMapping("update")
    public Object update(
            Hospital hospital, 
            MultipartFile[] file, @ModelAttribute(value="loginUser") Member loginUser) throws Exception {
        
        String uploadDir = servletContext.getRealPath("/download");

        ArrayList<HospitalUploadFile> uploadFiles = new ArrayList<>();
        
        for (MultipartFile part : file) {
            if (part.isEmpty())
                continue;
            
            String filename = this.writeUploadFile(part, uploadDir);
            
            uploadFiles.add(new HospitalUploadFile(filename));
        }
        
        HashMap<String,Object> result = new HashMap<>();
        
        
        
        try {
            hospital.setFiles(uploadFiles);
            hospital.setMember(loginUser);
            hospitalService.update(hospital);
            result.put("status", "success");
            return result;
        } catch (Exception e) {
            result.put("status", "fail");
            return result;
        }
        
      
    }

    @RequestMapping("delete")
    public Object delete(int no, @ModelAttribute(value="loginUser") Member loginUser) throws Exception {
        HashMap<String,Object> result = new HashMap<>();
        
        
        try {
            hospitalService.delete(no,loginUser.getMemberNo());
            result.put("status", "success");
            return result;
        } catch (Exception e) {
            result.put("status", "fail");
            return result;
        }
        
    }
    
    @RequestMapping("hospitalNo")
    public Object hospitalNo(@ModelAttribute(value="loginUser") Member loginUser) throws Exception {
        HashMap<String,Object> result = new HashMap<>();
            int counthospital = hospitalService.getHospitalLoginUserNo(loginUser.getMemberNo());
            if(counthospital == 1) {
                result.put("status", "success");
                return result;   
            }else {
                result.put("status", "fail");
                return result;   
            }
    }

    long prevMillis = 0;
    int count = 0;
    
    // 다른 클라이언트가 보낸 파일명과 중복되지 않도록 
    // 서버에 파일을 저장할 때는 새 파일명을 만든다.
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
