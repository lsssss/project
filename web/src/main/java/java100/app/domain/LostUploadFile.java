package java100.app.domain;

public class LostUploadFile {
    int no;
    String filename;
    int lostNo;
    
    public LostUploadFile() {}
    
    public LostUploadFile(String filename) {
        this(0, filename);
    }

    public LostUploadFile(int no, String filename) {
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

    public int getLostNo() {
        return lostNo;
    }

    public void setLostNo(int lostNo) {
        this.lostNo = lostNo;
    }

}
