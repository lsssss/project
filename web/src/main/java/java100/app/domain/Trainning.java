package java100.app.domain;

import java.util.List;
 
public class Trainning {
    protected int trainningNo; //list
    protected int memberNo;
    protected String category; //list
    protected String title; //list
    protected String contents; //list
    protected String maintextContents;
    protected int viewCount;
    protected String registrationDate;
    protected List<TrainningUploadFile> files;
    protected Member member; //list
    protected int liked;
    
    public int getMemberNo() {
        return memberNo;
    }
    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }
    public int getTrainningNo() {
        return trainningNo;
    }
    public void setTrainningNo(int trainningNo) {
        this.trainningNo = trainningNo;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public String getMaintextContents() {
        return maintextContents;
    }
    public void setMaintextContents(String maintextContents) {
        this.maintextContents = maintextContents;
    }
    public int getViewCount() {
        return viewCount;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public List<TrainningUploadFile> getFiles() {
        return files;
    }
    public void setFiles(List<TrainningUploadFile> files) {
        this.files = files;
    }
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }
    public int getLiked() {
        return liked;
    }
    public void setLiked(int liked) {
        this.liked = liked;
    }
}










