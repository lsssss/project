package java100.app.domain;

public class BusinessUploadFile {
    int no;
    String filename;
    int businessNo;
    
    public BusinessUploadFile() {}
    
    public BusinessUploadFile(String filename) {
        this(0, filename);
    }

    public BusinessUploadFile(int no, String filename) {
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

    public int getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(int businessNo) {
        this.businessNo = businessNo;
    }


    
}
