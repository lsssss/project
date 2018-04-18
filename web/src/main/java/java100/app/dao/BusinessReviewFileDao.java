package java100.app.dao;

import java.util.List;

import java100.app.domain.BusinessReviewUploadFile;

public interface BusinessReviewFileDao {

    List<BusinessReviewUploadFile> findByReviewNo(int rv_no);
    void insertReview(BusinessReviewUploadFile file);
    void deleteAllByReviewNo(int rv_no);
}
