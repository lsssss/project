package java100.app.domain;

import java.util.List;

public class Lost {
    protected int lostNo; //list
    protected String petName; //list
    protected String breed; //list
    protected String lostLocation; //list
    protected String lostDate;
    protected String character;
    protected int reward;
    protected String viewCount;
    protected String contents;
    protected Member registrant;
    protected boolean pushAlert;
    protected List<LostUploadFile> files;
    
    public int getLostNo() {
        return lostNo;
    }
    
    public void setLostNo(int lostNo) {
        this.lostNo = lostNo;
    }
    
    public String getPetName() {
        return petName;
    }
    
    public void setPetName(String petName) {
        this.petName = petName;
    }
    
    public String getBreed() {
        return breed;
    }
    
    public void setBreed(String breed) {
        this.breed = breed;
    }
    
    public String getLostLocation() {
        return lostLocation;
    }
    
    public void setLostLocation(String lostLocation) {
        this.lostLocation = lostLocation;
    }
    
    public String getLostDate() {
        return lostDate;
    }
    
    public void setLostDate(String lostDate) {
        this.lostDate = lostDate;
    }
    
    public String getCharacter() {
        return character;
    }
    
    public void setCharacter(String character) {
        this.character = character;
    }
    
    public int getReward() {
        return reward;
    }
    
    public void setReward(int reward) {
        this.reward = reward;
    }
    
    public String getViewCount() {
        return viewCount;
    }
    
    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }
    
    public String getContents() {
        return contents;
    }
    
    public void setContents(String contents) {
        this.contents = contents;
    }
    
    public Member getRegistrant() {
        return registrant;
    }

    public void setRegistrant(Member registrant) {
        this.registrant = registrant;
    }

    public boolean getPushAlert() {
        return pushAlert;
    }

    public void setPushAlert(boolean pushAlert) {
        this.pushAlert = pushAlert;
    }

    public List<LostUploadFile> getFiles() {
        return files;
    }
    
    public void setFiles(List<LostUploadFile> files) {
        this.files = files;
    }
   
}










