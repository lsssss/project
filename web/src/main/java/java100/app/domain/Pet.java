package java100.app.domain;

import java.util.List;

public class Pet {
    protected int petNo; 
    protected String petName; 
    protected String breed; 
    protected int age; 
    protected double weight; 
    protected double goalWeight; 
    protected int memberNo;
    protected List<PetUploadFile> files;
    
    public int getPetNo() {
        return petNo;
    }
    
    public void setPetNo(int petNo) {
        this.petNo = petNo;
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
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public double getGoalWeight() {
        return goalWeight;
    }
    
    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }
    
    public int getMemberNo() {
        return memberNo;
    }
    
    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public List<PetUploadFile> getFiles() {
        return files;
    }

    public void setFiles(List<PetUploadFile> files) {
        this.files = files;
    }
    
}










