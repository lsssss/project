package java100.app.domain;

public class PetEncyclopediaUploadFile {
    int no;
    String filename;
    int petEncyclopediaNo;
    
    public PetEncyclopediaUploadFile() {}
    
    public PetEncyclopediaUploadFile(String filename) {
        this(0, filename);
    }

    public PetEncyclopediaUploadFile(int no, String filename) {
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

    public int getpetEncyclopediaNo() {
        return petEncyclopediaNo;
    }

    public void setPetEncyclopediaNo(int petEncyclopediaNo) {
        this.petEncyclopediaNo = petEncyclopediaNo;
    }
    
}
