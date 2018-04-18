package java100.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java100.app.dao.TrainningDao;
import java100.app.dao.TrainningFileDao;
import java100.app.domain.Trainning;
import java100.app.domain.TrainningUploadFile;
import java100.app.service.TrainningService;

@Service
public class TrainningServiceImpl implements TrainningService {

    @Autowired TrainningDao trainningDao;
    @Autowired TrainningFileDao trainningFileDao;
    
    @Override
    public List<Trainning> list(int pageNo, int pageSize, Map<String, Object> options) {
        
        HashMap<String,Object> params = new HashMap<>();
        params.put("startIndex", (pageNo - 1) * pageSize);
        params.put("size", pageSize);
        
        if (options != null) {
            params.putAll(options);
        }
        
        return trainningDao.findAll(params);
    }
     
    @Override
    public int getTotalCount() {
        return trainningDao.countAll();
    }

    @Override
    public int add(Trainning trainning) {
        
        int count = trainningDao.insert(trainning);
        
        this.addFiles(trainning.getFiles(), trainning.getTrainningNo());
        
        return count;
    }
    
    @Override
    public Trainning get(int no) {
        trainningDao.updateViewCount(no);
        /*trainningDao.updateLikes(no);*/ // 
       
        
        Trainning trainning = trainningDao.findByNo(no);
        
        return trainning;
    }

    
   /* @Override
    public Trainning get(String email, String password) {
        
        HashMap<String,Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        
        return trainningDao.findByEmailAndPassword(params);
    }*/
    
    @Override
    public int update(Trainning trainning) {
        
        int count = trainningDao.update(trainning);
       
        if(!trainning.getFiles().isEmpty()) {
            trainningFileDao.deleteAllByTrainningNo(trainning.getTrainningNo());
        }
        
        addFiles(trainning.getFiles(), trainning.getTrainningNo());
        
        return count;
    }
    @Override
    public int delete(int no) {
        
        trainningFileDao.deleteAllByTrainningNo(no);
        
        return trainningDao.delete(no);
    }

    
    @Override
    public void addFiles(List<TrainningUploadFile> files, int no) {
        for (TrainningUploadFile file : files) {
            file.setTrainningNo(no);
            trainningFileDao.insert(file);
        }
    }

    @Override
    public int like(Trainning trainning) {
        return trainningDao.like(trainning);
    }
    
    @Override
    public int dislike(Trainning trainning) {
        return trainningDao.dislike(trainning);
    }
    
    @Override
    public Integer checkLike(Trainning trainning) {
        return trainningDao.checkLike(trainning);
    }
    
    @Override
    public int countLikes() {
        return trainningDao.countLikes();
    }
}












