package java100.app.dao;

import java.util.List;

import java100.app.domain.LostUploadFile;

public interface LostFileDao {

    List<LostUploadFile> findByLostNo(int no);
    
    void insert(LostUploadFile file);

    void deleteAllByLostNo(int no);
}
