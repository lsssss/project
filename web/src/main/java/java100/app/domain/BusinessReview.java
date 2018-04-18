package java100.app.domain;

import java.util.List;

public class BusinessReview {
     int reviewNo;
     String title;
     String content;
     String createDate;
     double StarPoint;
     int viewCount;
     int b_number;
     protected Member registrant;
     protected List<BusinessReviewUploadFile> files;
   
     
     public int getB_number() {
        return b_number;
    }
    public void setB_number(int b_number) {
        this.b_number = b_number;
    }
    public List<BusinessReviewUploadFile> getFiles() {
        return files;
    }
    public void setFiles(List<BusinessReviewUploadFile> files) {
        this.files = files;
    }
    public List<BusinessReviewUploadFile> getReviewFiles() {
        return files;
    }
    public void setReviewFiles(List<BusinessReviewUploadFile> files) {
        this.files = files;
    }
    public Member getRegistrant() {
        return registrant;
    }
    public void setRegistrant(Member registrant) {
        this.registrant = registrant;
    }
    public void setStarPoint(double starPoint) {
        StarPoint = starPoint;
    }
    public int getReviewNo() {
        return reviewNo;
    }
    public void setReviewNo(int reviewNo) {
        this.reviewNo = reviewNo;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public Double getStarPoint() {
        return StarPoint;
    }
    public void setStarPoint(Double starPoint) {
        StarPoint = starPoint;
    }
    public int getViewCount() {
        return viewCount;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    
  
    
}
