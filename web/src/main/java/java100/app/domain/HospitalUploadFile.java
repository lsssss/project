package java100.app.domain;

public class HospitalUploadFile {
    int no;
    String filename;
    int hospitalNo;
    
    
    public HospitalUploadFile() {}
    
    public HospitalUploadFile(String filename) {
        this(0, filename);
    }

    public HospitalUploadFile(int no, String filename) {
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

    public int getHospitalNo() {
        return hospitalNo;
    }

    public void setHospitalNo(int hospitalNo) {
        this.hospitalNo = hospitalNo;
    }
    
 
}
