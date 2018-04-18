package java100.app.domain;

public class MemberUploadFile {
    int no;
    String filename;
    int memberNo;
    
    public MemberUploadFile() {}
    
    public MemberUploadFile(String filename) {
        this(0, filename);
    }

    public MemberUploadFile(int no, String filename) {
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

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }
    
}
