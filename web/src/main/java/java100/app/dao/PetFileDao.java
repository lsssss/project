package java100.app.dao;

import java.util.List;

import java100.app.domain.PetUploadFile;

public interface PetFileDao {
    
    List<PetUploadFile> findByPetNo(int no);
    
    int insert(PetUploadFile file);
    
    int deleteAllByPetNo(int no);
}















