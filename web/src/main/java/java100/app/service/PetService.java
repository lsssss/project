package java100.app.service;

import java.util.List;

import java100.app.domain.Pet;
import java100.app.domain.PetUploadFile;

public interface PetService {
    
    List<Pet> list(int searchNo);
    int getTotalCount();
    int add(Pet pet);
    void addFiles(List<PetUploadFile> files, int petNo);
    Pet get(int no);
    int update(Pet pet);
    int delete(int no);
    
    
}





