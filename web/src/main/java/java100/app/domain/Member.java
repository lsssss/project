package java100.app.domain;

import java.util.List;

public class Member {
    protected int memberNo; //list
    protected String name; //list
    protected String nicname; //list
    protected String email; //list
    protected String password;
    protected String tel;
    protected boolean push;
    protected String postalNo;
    protected String primaryAddress;
    protected String detailAddress;
    protected String memberType;
    protected String createDate; // list
    protected List<MemberUploadFile> files;
   
    
    @Override
    public String toString() {
        return "Member [memberNo=" + memberNo + ", name=" + name + ", nicname=" + nicname + ", email=" + email
                + ", password=" + password + ", tel=" + tel + ", push=" + push + ", postalNo=" + postalNo
                + ", primaryAddress=" + primaryAddress + ", detailAddress=" + detailAddress + ", memberType="
                + memberType + ", createDate=" + createDate + ", files=" + files + "]";
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNicname() {
        return nicname;
    }
    
    public void setNicname(String nicname) {
        this.nicname = nicname;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTel() {
        return tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public String getPostalNo() {
        return postalNo;
    }

    public void setPostalNo(String postalNo) {
        this.postalNo = postalNo;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }
    
    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }
    
    public String getDetailAddress() {
        return detailAddress;
    }
    
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
    
    public String getMemberType() {
        return memberType;
    }
    
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
    
    public String getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean getPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public List<MemberUploadFile> getFiles() {
        return files;
    }

    public void setFiles(List<MemberUploadFile> files) {
        this.files = files;
    }
    
}










