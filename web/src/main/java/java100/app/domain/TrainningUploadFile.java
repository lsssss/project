package java100.app.domain;

public class TrainningUploadFile {
    int no;
    String filename;
    int trainningNo;
    
    public TrainningUploadFile() {}
    
    public TrainningUploadFile(String filename) {
        this(0, filename);
    }

    public TrainningUploadFile(int no, String filename) {
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

    public int getTrainningNo() {
        return trainningNo;
    }

    public void setTrainningNo(int trainningNo) {
        this.trainningNo = trainningNo;
    }
    
    
    
}
