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

import java100.app.domain.BusinessReview;
import java100.app.domain.BusinessReviewUploadFile;
import java100.app.domain.Member;
import java100.app.service.BusinessService;
import java100.app.service.ReviewService;

@RestController
@RequestMapping("/review")
@SessionAttributes("loginUser")
public class ReviewController {
    int b_number;
    @Autowired ServletContext servletContext;
    @Autowired BusinessService businessService;
    @Autowired ReviewService reviewService;
    
    @RequestMapping("list")
    public Object list(
            @RequestParam(value="pn", defaultValue="1") int pageNo,
            @RequestParam(value="ps", defaultValue="5") int pageSize,
            @RequestParam(value="words", required=false) String[] words,
            @RequestParam(value="oc", required=false) String orderColumn,
            @RequestParam(value="al", required=false) String align
            ) throws Exception {

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
        
        int totalCount = reviewService.getTotalCount();
        int lastPageNo = totalCount / pageSize;
        if ((totalCount % pageSize) > 0) {
            lastPageNo++;
        }
        
        HashMap<String,Object> result = new HashMap<>();
        result.put("pageNo", pageNo);
        result.put("lastPageNo", lastPageNo);
        result.put("totalCount", totalCount);
        result.put("b_number", b_number);
        result.put("list", reviewService.list(pageNo, pageSize, options));
        return result;
    }
    
    @RequestMapping("add")
    public Object add(
            BusinessReview businessReview,
            @ModelAttribute(value="loginUser") Member loginUser,
            MultipartFile[] file
            ) throws Exception {
        
        String uploadDir = servletContext.getRealPath("/download");
        ArrayList<BusinessReviewUploadFile> uploadFiles = new ArrayList<>();
        
        for (MultipartFile part : file) {
            if (part.isEmpty())
                continue;
            
            String filename = this.writeUploadFile(part, uploadDir);
            
            uploadFiles.add(new BusinessReviewUploadFile(filename));
        }
        
        businessReview.setFiles(uploadFiles);
        businessReview.setB_number(b_number);
        businessReview.setRegistrant(loginUser);
        reviewService.add(businessReview);
        System.out.println(businessReview.getFiles());
        HashMap<String,Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("data", businessService.get(b_number));
        return result;
    }
    
    /*@RequestMapping("form")
    public Object findByBusinessNo(@ModelAttribute(value="loginUser") Member loginUser) throws Exception{
        int no = loginUser.getMemberNo();
        System.out.println("로그인 번호다다다다 : " + no);
        System.out.println("사업체 번호다다다다 : " + reviewService.getBusinessNo(no));
        HashMap<String,Object> result = new HashMap<>();
        result.put("data", reviewService.getBusinessNo(no));
        return result;
    }*/
    @RequestMapping("form")
    public Object form(int b_number) throws Exception {
        System.out.println("asdfasdfasdfasdf>>>>>>>>>>>>>>" + b_number);
        this.b_number = b_number;
        return b_number;
    }
    @RequestMapping("{no}")
    public Object view(@PathVariable int no) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        result.put("data", reviewService.get(no));
        return result;
    }
    
    @RequestMapping("modify")
    public Object modoify(int bus_no) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        result.put("data", reviewService.get(bus_no));
        return result;
        
    }

    @RequestMapping("update")
    public Object update(
            BusinessReview businessReview ,
            MultipartFile[] file) throws Exception {
        
       String uploadDir = servletContext.getRealPath("/download");

        ArrayList<BusinessReviewUploadFile> uploadFiles = new ArrayList<>();
        
        for (MultipartFile part : file) {
            if (part.isEmpty())
                continue;
            
            String filename = this.writeUploadFile(part, uploadDir);
            
            uploadFiles.add(new BusinessReviewUploadFile(filename));
        }
        
        businessReview.setReviewFiles(uploadFiles);
        reviewService.update(businessReview);
        HashMap<String, Object> result = new HashMap<>();
        result.put("data", businessService.get(b_number));
        return result;
    }

    @RequestMapping("delete")
    public Object delete(int no) throws Exception {

        reviewService.delete(no);
        HashMap<String,Object> result = new HashMap<>();
        result.put("data", businessService.get(b_number));
        return result;
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








