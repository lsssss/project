package java100.app.web.json;

import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java100.app.domain.Diagnosis;
import java100.app.domain.Hospital;
import java100.app.domain.Member;
import java100.app.service.DiagnosisService;
import java100.app.service.HospitalService;
import java100.app.service.MemberService;


@RestController
@RequestMapping("/diagnosis")
@SessionAttributes("loginUser")
public class DiagnosisController {

    @Autowired ServletContext servletContext;
    @Autowired DiagnosisService diagnosisService;
    @Autowired HospitalService hospitalService;
    @Autowired MemberService memberService;

    @RequestMapping("list")
    public Object list(@RequestParam(value = "pn", defaultValue = "1") int pageNo,
            @RequestParam(value = "ps", defaultValue = "5") int pageSize,
            @RequestParam(value = "words", required = false) String[] words,
            @RequestParam(value = "oc", required = false) String orderColumn,
            @RequestParam(value = "al", required = false) String align) throws Exception {
        if (pageNo < 1) {
            pageNo = 1;
        }

        if (pageSize < 5 || pageSize > 15) {
            pageSize = 5;
        }

        HashMap<String, Object> options = new HashMap<>();
        if (words != null && words[0].length() > 0) {
            options.put("words", words);
        }
        options.put("orderColumn", orderColumn);
        options.put("align", align);

        int totalCount = diagnosisService.getTotalCount();
        int lastPageNo = totalCount / pageSize;
        if ((totalCount % pageSize) > 0) {
            lastPageNo++;
        }

        HashMap<String, Object> result = new HashMap<>();

        result.put("pageNo", pageNo);
        result.put("lastPageNo", lastPageNo);
        result.put("list", diagnosisService.list(pageNo, pageSize, options));

        return result;
    }
    
    @RequestMapping("add")
    public Object add(Diagnosis diagnosis, @ModelAttribute(value = "loginUser") Member loginUser, Hospital hospital)
            throws Exception {
        diagnosis.setMember(loginUser);
        diagnosis.setHospital(hospital);
        
        int membercheck = diagnosisService.get(diagnosis.getMemberTel());
        HashMap<String, Object> result = new HashMap<>();

        if(membercheck != 0) {
           
            int count = diagnosisService.add(diagnosis);
            
            if(count != 0) {
                result.put("status", "success");
                return result;
            }else {
                result.put("status", "fail");
                return result;
            }
        }else {
            result.put("status", "fail2");
            return result;
        }
        
        
       
        
    }

    @RequestMapping("{no}")
    public Object view(@PathVariable int no) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        result.put("data", diagnosisService.get(no));
        return result;
    }

    @RequestMapping("update")
    public Object update(Diagnosis diagnosis,@ModelAttribute(value = "loginUser") Member loginUser) throws Exception {
        
        int count = diagnosisService.update(diagnosis,loginUser.getMemberNo());
        HashMap<String, Object> result = new HashMap<>();
        
        System.out.println(count);
        
        if(count != 0) {
            result.put("status", "success");
            return result;
        }else {
            result.put("status", "fail");
            return result;
        }
    }

    @RequestMapping("delete")
    public Object delete(int no, @ModelAttribute(value = "loginUser") Member loginUser) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        int count = diagnosisService.delete(no, loginUser.getMemberNo());
        System.out.println(count);
        if (count == 1) {
            result.put("status", "success");
//            System.out.println(result);

            return result;
        } else if(count == 0){
            result.put("status", "fail");
//            System.out.println(result);

            return result;
            
        }else {
            result.put("status", "fail2");
//            System.out.println(result);
            return result;
        }
        
    }

    @RequestMapping("form")
    public Object findByMemberNo(@ModelAttribute(value = "loginUser") Member loginUser) throws Exception {
        int no = loginUser.getMemberNo();
        System.out.println(no);
        HashMap<String, Object> result = new HashMap<>();
        result.put("data", diagnosisService.getHospitalNo(no));
        return result;
        
    }
    
    @RequestMapping("mylist")
    public Object myList(Diagnosis diagnosis,@ModelAttribute(value = "loginUser") Member loginUser) throws Exception{
        String nowDate = diagnosis.getNowDateRecording();
        String selectDate = diagnosis.getDateRecording();
        
        HashMap<String, Object> result = new HashMap<>();
        result.put("list", diagnosisService.myList(loginUser.getTel(),selectDate,nowDate));
        return result; 
        
    }
    
 
 
    
    @RequestMapping("mylist5")
    public Object myList5(@ModelAttribute(value = "loginUser") Member loginUser) throws Exception{
        HashMap<String, Object> result = new HashMap<>();
        System.out.println(loginUser.getTel());
        result.put("list", diagnosisService.myList5(loginUser.getTel()));
        
        return result;
        
    }
    
    
    @RequestMapping("myAlllist")
    public Object myAllList(@ModelAttribute(value = "loginUser") Member loginUser) throws Exception{
        HashMap<String, Object> result = new HashMap<>();
        result.put("list", diagnosisService.myAllList(loginUser.getTel()));
        return result;
        
    }
    
    @RequestMapping("hospitallist")
    public Object hospitalList(Diagnosis diagnosis,@ModelAttribute(value = "loginUser") Member loginUser) throws Exception{
      
        String nowDate = diagnosis.getNowDateRecording();
        String selectDate = diagnosis.getDateRecording();
        
        Diagnosis diagnosishpno =  diagnosisService.getHospitalNo(loginUser.getMemberNo());
        int hpno = diagnosishpno.getHospital().getHospitalNo();
        
        HashMap<String, Object> result = new HashMap<>();
        result.put("list", diagnosisService.getHospitalList(hpno,selectDate,nowDate));
        return result; 
        
    }
    
    @RequestMapping("hospitalalllist")
    public Object hospitalListAll(@ModelAttribute(value = "loginUser") Member loginUser) throws Exception{
        
        Diagnosis diagnosishpno =  diagnosisService.getHospitalNo(loginUser.getMemberNo());
        int hpno = diagnosishpno.getHospital().getHospitalNo();
        HashMap<String, Object> result = new HashMap<>();
        result.put("list", diagnosisService.gethospitalListAll(hpno));
        return result;
    }
    
    @RequestMapping("hospitalproducerlist5")
    public Object hospitalProducerList5(@ModelAttribute(value = "loginUser") Member loginUser) throws Exception{
        
        Diagnosis diagnosishpno =  diagnosisService.getHospitalNo(loginUser.getMemberNo());
        HashMap<String, Object> result = new HashMap<>();
        int hpno = 0;
        try {
           hpno = diagnosishpno.getHospital().getHospitalNo();
        }catch (Exception e) {
            result.put("status","fail");
            return result;
        }
        result.put("status","success");
        result.put("list", diagnosisService.gethopspitalProducerList5(hpno));
//        System.out.println(diagnosisService.gethopspitalProducerList5(hpno));
        return result;
    }
    
    
    
    
    @RequestMapping("mypagesizelist")
    public Object myPageSizeList(int pageNo,
            int pageSize,@ModelAttribute(value = "loginUser") Member loginUser,Diagnosis diagnosis) throws Exception {
        
        String nowDate = diagnosis.getNowDateRecording();
        String selectDate = diagnosis.getDateRecording();
        if (pageNo < 1) {
            pageNo = 1;
        }

        int totalCount = diagnosisService.getDateTotalCount(loginUser.getTel(),selectDate,nowDate);
        int lastPageNo = totalCount / pageSize;
        if ((totalCount % pageSize) > 0) {
            lastPageNo++;
        }
        
        HashMap<String, Object> result = new HashMap<>();

        result.put("pageNo", pageNo);
        result.put("lastPageNo", lastPageNo);
        result.put("list", diagnosisService.myPageSizeList(loginUser.getTel(),selectDate,nowDate,pageNo, pageSize));

        return result;
    }
    
    
    @RequestMapping("hospitalpagesizelist")
    public Object hospitalPageSizeList(int pageNo, int pageSize,@ModelAttribute(value = "loginUser") Member loginUser,Diagnosis diagnosis) throws Exception {
      
        Diagnosis diagnosishpno =  diagnosisService.getHospitalNo(loginUser.getMemberNo());
        int hpno = diagnosishpno.getHospital().getHospitalNo();
        System.out.println(hpno);
        String nowDate = diagnosis.getNowDateRecording();
        String selectDate = diagnosis.getDateRecording();
        if (pageNo < 1) {
            pageNo = 1;
        }

        int totalCount = diagnosisService.getHospitalDateTotalCount(hpno,selectDate,nowDate);
        System.out.println(totalCount);
        int lastPageNo = totalCount / pageSize;
        if ((totalCount % pageSize) > 0) {
            lastPageNo++;
        }
        
        HashMap<String, Object> result = new HashMap<>();

        result.put("pageNo", pageNo);
        result.put("lastPageNo", lastPageNo);
        result.put("list", diagnosisService.hospitalPageSizeList(hpno,selectDate,nowDate,pageNo, pageSize));

        return result;
    }
    
    
    
}