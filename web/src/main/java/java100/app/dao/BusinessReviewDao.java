package java100.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java100.app.domain.BusinessReview;

public interface BusinessReviewDao {
    List<BusinessReview> findAll(Map<String,Object> params);
    int countAll();
    int insert(BusinessReview businessReview);
    BusinessReview findByNo(int rv_no);
    BusinessReview findByBusinessNo(int no);
    BusinessReview findByEmailAndPassword(HashMap<String, Object> params);
    int update(BusinessReview businessReview);
    int delete(int rv_no);
}















