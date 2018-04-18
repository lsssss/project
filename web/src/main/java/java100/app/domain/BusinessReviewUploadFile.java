package java100.app.domain;

public class BusinessReviewUploadFile {
    int no;
    String filename;
    int reviewNo;
    
    public BusinessReviewUploadFile() {}
    
    public BusinessReviewUploadFile(String filename) {
        this(0, filename);
    }

    public BusinessReviewUploadFile(int no, String filename) {
        this.no = no;
        this.filename = filename;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(int reviewNo) {
        this.reviewNo = reviewNo;
    }

 
    
}
