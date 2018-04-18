package java100.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java100.app.dao.PetDao;
import java100.app.dao.PetFileDao;
import java100.app.domain.Pet;
import java100.app.domain.PetUploadFile;
import java100.app.service.PetService;

@Service
public class PetServiceImpl implements PetService {

    @Autowired PetDao petDao;
    @Autowired PetFileDao petFileDao;
    
    @Override
    public List<Pet> list(int searchNo) {
        
        return petDao.findAll(searchNo);
    }

    @Override
    public int getTotalCount() {
        return petDao.countAll();
    }
    
    @Override
    public int add(Pet pet) {
        int count = petDao.insert(pet);
        this.addFiles(pet.getFiles(), pet.getPetNo());
        return count;
    }

    @Override
    public void addFiles(List<PetUploadFile> files, int petNo) {
        for (PetUploadFile file : files) {
            file.setPetNo(petNo);
            petFileDao.insert(file);
        }
    }
    
    @Override
    public Pet get(int no) {
        Pet pet = petDao.findByNo(no);
        return pet;
    }
    
    @Override
    public int update(Pet pet) {
        int count = petDao.update(pet);
        
        petFileDao.deleteAllByPetNo(pet.getPetNo());
        
        addFiles(pet.getFiles(), pet.getPetNo());
        
        return count;
    }
    
    @Override
    public int delete(int no) {
        
        petFileDao.deleteAllByPetNo(no);
        
        return petDao.delete(no);
    }


}












