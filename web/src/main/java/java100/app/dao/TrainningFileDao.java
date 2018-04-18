package java100.app.dao;

import java.util.List;

import java100.app.domain.TrainningUploadFile;

public interface TrainningFileDao {

    List<TrainningUploadFile> findByTrainningNo(int no);
    
    void insert(TrainningUploadFile file);

    void deleteAllByTrainningNo(int no);
    
}
 