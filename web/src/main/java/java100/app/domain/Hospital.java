package java100.app.domain;

import java.util.List;

public class Hospital {

    
    protected int hospitalNo; //병원번호
    protected String hospitalName; // 병원이름
    protected String openTime; // 오픈시간
    protected String closeTime; // 오픈시간
    protected String medicalSubject; // 진료과목
    protected String medicalAnimal; // 진료동물
    protected String hospitalTel; // 병원 전화번호
    protected String contents; // 내용
    protected String postalNo;
    protected String primaryAddress;
    protected String detailAddress;
    protected String medicalStaff;
    protected Member member;
    protected List<HospitalUploadFile> files;
    
    
    
    
    
    @Override
    public String toString() {
        return "Hospital [hospitalNo=" + hospitalNo + ", hospitalName=" + hospitalName + ", openTime=" + openTime
                + ", closeTime=" + closeTime + ", medicalSubject=" + medicalSubject + ", medicalAnimal=" + medicalAnimal
                + ", hospitalTel=" + hospitalTel + ", contents=" + contents + ", postalNo=" + postalNo
                + ", primaryAddress=" + primaryAddress + ", detailAddress=" + detailAddress + ", medicalStaff="
                + medicalStaff + ", member=" + member + ", files=" + files + "]";
    }
    public String getOpenTime() {
        return openTime;
    }
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }
    public String getCloseTime() {
        return closeTime;
    }
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
    public int getHospitalNo() {
        return hospitalNo;
    }
    public void setHospitalNo(int hospitalNo) {
        this.hospitalNo = hospitalNo;
    }
    public String getHospitalName() {
        return hospitalName;
    }
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
    public String getMedicalSubject() {
        return medicalSubject;
    }
    public void setMedicalSubject(String medicalSubject) {
        this.medicalSubject = medicalSubject;
    }
    public String getMedicalAnimal() {
        return medicalAnimal;
    }
    public void setMedicalAnimal(String medicalAnimal) {
        this.medicalAnimal = medicalAnimal;
    }
    public String getHospitalTel() {
        return hospitalTel;
    }
    public void setHospitalTel(String hospitalTel) {
        this.hospitalTel = hospitalTel;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
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
    public String getMedicalStaff() {
        return medicalStaff;
    }
    public void setMedicalStaff(String medicalStaff) {
        this.medicalStaff = medicalStaff;
    }
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }
    public List<HospitalUploadFile> getFiles() {
        return files;
    }
    public void setFiles(List<HospitalUploadFile> files) {
        this.files = files;
    }
    
    
    
  
}
