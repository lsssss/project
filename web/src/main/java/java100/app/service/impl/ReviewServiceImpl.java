package java100.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java100.app.dao.BusinessReviewDao;
import java100.app.dao.BusinessReviewFileDao;
import java100.app.domain.BusinessReview;
import java100.app.domain.BusinessReviewUploadFile;
import java100.app.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired BusinessReviewDao businessReviewDao;
    @Autowired BusinessReviewFileDao businessReviewFileDao;

    @Override // 리뷰 list
    public List<BusinessReview> list(int pageNo, int pageSize, Map<String, Object> options) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("startIndex", (pageNo - 1) * pageSize);
        params.put("size", pageSize);
        if (options != null) {  
        }
        return businessReviewDao.findAll(params);
    }
    
    @Override
    public int getTotalCount() {
        return businessReviewDao.countAll();
    }

    @Override
    public int add(BusinessReview businessReview) {
        
        int count = businessReviewDao.insert(businessReview);
        this.addFiles(businessReview.getReviewFiles(), businessReview.getReviewNo());
        
        return count;
    }
    
  
    @Override
    public BusinessReview get(int rv_no) {
        BusinessReview businessReview = businessReviewDao.findByNo(rv_no);
        
        return businessReview;
    }

    
    @Override
    public BusinessReview get(String email, String password) {
        
        HashMap<String,Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        
        return businessReviewDao.findByEmailAndPassword(params);
    }
    
    @Override
    public int update(BusinessReview businessReview) {
        
        int count = businessReviewDao.update(businessReview);
        
       if(!businessReview.getReviewFiles().isEmpty()) {
            businessReviewFileDao.deleteAllByReviewNo(businessReview.getReviewNo());
        }
        
        // 다시 게시물 첨부파일을 저장한다.
        addFiles(businessReview.getReviewFiles(), businessReview.getReviewNo());
        
        return count;
    }
    @Override
    public int delete(int rv_no) {
        
        businessReviewFileDao.deleteAllByReviewNo(rv_no);
        System.out.println(rv_no);
        return businessReviewDao.delete(rv_no);
    }


    @Override
    public Object getBusinessNo(int no) {
        return businessReviewDao.findByBusinessNo(no);
    }

    
    @Override
    public void addFiles(List<BusinessReviewUploadFile> reviewFiles, int reviewNo) {
        for (BusinessReviewUploadFile file : reviewFiles) {
            file.setReviewNo(reviewNo);
            businessReviewFileDao.insertReview(file);
        }
    }
    

   
}












