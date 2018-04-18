package java100.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java100.app.dao.BusinessDao;
import java100.app.dao.BusinessFileDao;
import java100.app.domain.Business;
import java100.app.domain.BusinessUploadFile;
import java100.app.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired BusinessDao businessDao;
    @Autowired BusinessFileDao businessFileDao;
    
    @Override
    public List<Business> list(int pageNo, int pageSize, Map<String, Object> options) {
        
        HashMap<String,Object> params = new HashMap<>();
        params.put("startIndex", (pageNo - 1) * pageSize);
        params.put("size", pageSize);
        
        if (options != null) {  
        }
        return businessDao.findAll(params);
    }
    
    @Override
    public List<Business> listCategory(String category) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("category",category);
        
        return businessDao.findCategory(params);
    }
    
    @Override
    public List<Business> listAllCategory(String category) {
        HashMap<String,Object> params = new HashMap<>();
        
        return businessDao.findAllCategory(params);
    }
    
    
    
    @Override
    public int getTotalCount() {
        return businessDao.countAll();
    }

    @Override
    public int add(Business business) {
        
        // insert를 하기 전에는 board의 no 프로퍼티 값은 0이다.
        // insert를 한 후에는 no 프로퍼티에 DB에서 생성한 값이 저장된다.
        int count = businessDao.insert(business);
        
        // 첨부파일 등록
        this.addFiles(business.getFiles(), business.getBusinessNo());
        
        return count;
    }
  
    @Override
    public Business get(int bus_no) {
        Business business = businessDao.findByNo(bus_no);
        
        return business;
    }

    
    @Override
    public Business get(String email, String password) {
        
        HashMap<String,Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        
        return businessDao.findByEmailAndPassword(params);
    }
    
    @Override
    public int update(Business business) {
        
        int count = businessDao.update(business);
        
        if(!business.getFiles().isEmpty()) {
            businessFileDao.deleteAllByBusinessNo(business.getBusinessNo());
        }
        
        // 다시 게시물 첨부파일을 저장한다.
        addFiles(business.getFiles(), business.getBusinessNo());
        
        return count;
    }
    @Override
    public int delete(int bus_no) {
        
        businessFileDao.deleteAllByBusinessNo(bus_no);
        
        return businessDao.delete(bus_no);
    }

    
    @Override
    public void addFiles(List<BusinessUploadFile> files, int businessNo) {
        for (BusinessUploadFile file : files) {
            file.setBusinessNo(businessNo);
            businessFileDao.insert(file);
        }
    }

}












