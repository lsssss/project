package java100.app.dao;

import java.util.List;
import java.util.Map;

import java100.app.domain.Trainning;

public interface TrainningDao {
    List<Trainning> findAll(Map<String,Object> params);
    int countAll();
    int insert(Trainning trainning);
    Trainning findByNo(int no);
   /* Trainning findByEmailAndPassword(HashMap<String, Object> params);*/
    int update(Trainning trainning);
    int delete(int no);
    int updateViewCount(int no);
    int countLikes();
    int like(Trainning trainning);
    int dislike(Trainning trainning);
    Integer checkLike(Trainning trainning);
}












 


