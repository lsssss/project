package java100.app.domain;

public class PetUploadFile {
    protected int no; 
    protected String filename; 
    protected int petNo;
    

    public PetUploadFile() {}
    
    public PetUploadFile(String filename) {
        this(0, filename);
    }

    public PetUploadFile(int no, String filename) {
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
    
    public int getPetNo() {
        return petNo;
    }
    
    public void setPetNo(int petNo) {
        this.petNo = petNo;
    } 
}










