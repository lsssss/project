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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java100.app.domain.Member;
import java100.app.domain.Pet;
import java100.app.domain.PetUploadFile;
import java100.app.service.PetService;

@RestController
@RequestMapping("/pet")
@SessionAttributes("loginUser")
public class PetController {
    
    @Autowired ServletContext servletContext;
    @Autowired PetService petService;
    
    @RequestMapping("list")
    public Object list(
            @ModelAttribute(value="loginUser") Member loginUser) throws Exception {
        int searchNo = loginUser.getMemberNo();
        
        HashMap<String, Object> result = new HashMap<>();
        result.put("list", petService.list(searchNo));
        result.put("member", loginUser);
        result.put("status", "success");
        return result;
    }

    @RequestMapping("{no}")
    public Object view(@PathVariable int no) throws Exception {
        HashMap<String,Object> result = new HashMap<>();
        result.put("data", petService.get(no));
        return result;
    }
    
    @RequestMapping("add")
    public Object add(
            Pet pet,
            MultipartFile[] file,
            @ModelAttribute(value="loginUser") Member loginUser) throws Exception {
        
        String uploadDir = servletContext.getRealPath("/download");
        
        ArrayList<PetUploadFile> uploadFiles = new ArrayList<>();
        
        for (MultipartFile part : file) {
            if (part.isEmpty())
                continue;
            
            String filename = this.writeUploadFile(part, uploadDir);
            
            uploadFiles.add(new PetUploadFile(filename));
        }
        
        pet.setFiles(uploadFiles);
        
        pet.setMemberNo(loginUser.getMemberNo());
        
        petService.add(pet);
        
        HashMap<String, Object> result = new HashMap<>();
        
        result.put("status", "success");
        
        return result;
    }

    @RequestMapping("update")
    public Object update(
            Pet pet, 
            MultipartFile[] file,
            @ModelAttribute(value="loginUser") Member loginUser) throws Exception {
        
        String uploadDir = servletContext.getRealPath("/download");

        ArrayList<PetUploadFile> uploadFiles = new ArrayList<>();
        
        for (MultipartFile part : file) {
            if (part.isEmpty())
                continue;
            
            String filename = this.writeUploadFile(part, uploadDir);
            
            uploadFiles.add(new PetUploadFile(filename));
        }
        
        pet.setFiles(uploadFiles);

        petService.update(pet);
        
        HashMap<String,Object> result = new HashMap<>();
        
        result.put("status", "success");
        
        return result;
    }
    
    @RequestMapping("delete")
    public Object delete(int petNo) throws Exception {

        petService.delete(petNo);
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








