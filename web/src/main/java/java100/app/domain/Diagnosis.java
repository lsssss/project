package java100.app.domain;

import java.util.List;

public class Diagnosis {
    
    protected int diagnosisNo;
    protected String diagnosisCategory;
    protected String diagnosisName;
    protected String diagnosisContents;
    protected double dogweight;
    protected String dateRecording;
    protected String nowDateRecording;
    protected String recorder;
    protected String remarks;
    protected String memberTel;
    protected String memberName;
    protected Member member;
    protected Hospital hospital;
    protected HospitalUploadFile hfiles;
    protected MemberUploadFile mfiles;
    
    
    
    
    @Override
    public String toString() {
        return "Diagnosis [diagnosisNo=" + diagnosisNo + ", diagnosisCategory=" + diagnosisCategory + ", diagnosisName="
                + diagnosisName + ", diagnosisContents=" + diagnosisContents + ", dogweight=" + dogweight
                + ", dateRecording=" + dateRecording + ", recorder=" + recorder + ", remarks=" + remarks
                + ", memberTel=" + memberTel + ", memberName=" + memberName + ", member=" + member + ", hospital="
                + hospital + "]";
    }
    
    
    



    public HospitalUploadFile getHfiles() {
        return hfiles;
    }

    public void setHfiles(HospitalUploadFile hfiles) {
        this.hfiles = hfiles;
    }

    public MemberUploadFile getMfiles() {
        return mfiles;
    }

    public void setMfiles(MemberUploadFile mfiles) {
        this.mfiles = mfiles;
    }

    public String getNowDateRecording() {
        return nowDateRecording;
    }

    public void setNowDateRecording(String nowDateRecording) {
        this.nowDateRecording = nowDateRecording;
    }

    public String getMemberTel() {
        return memberTel;
    }
    
    public void setMemberTel(String memberTel) {
        this.memberTel = memberTel;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public int getDiagnosisNo() {
        return diagnosisNo;
    }
    public void setDiagnosisNo(int diagnosisNo) {
        this.diagnosisNo = diagnosisNo;
    }
    public String getDiagnosisCategory() {
        return diagnosisCategory;
    }
    public void setDiagnosisCategory(String diagnosisCategory) {
        this.diagnosisCategory = diagnosisCategory;
    }
    public String getDiagnosisName() {
        return diagnosisName;
    }
    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }
    public String getDiagnosisContents() {
        return diagnosisContents;
    }
    public void setDiagnosisContents(String diagnosisContents) {
        this.diagnosisContents = diagnosisContents;
    }
    
    public double getDogweight() {
        return dogweight;
    }
    public void setDogweight(double dogweight) {
        this.dogweight = dogweight;
    }
    public String getDateRecording() {
        return dateRecording;
    }
    public void setDateRecording(String dateRecording) {
        this.dateRecording = dateRecording;
    }
    public String getRecorder() {
        return recorder;
    }
    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }
    public Hospital getHospital() {
        return hospital;
    }
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
    
    
}
