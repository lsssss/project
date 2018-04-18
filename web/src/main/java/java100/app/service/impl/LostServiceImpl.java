package java100.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java100.app.dao.LostDao;
import java100.app.dao.LostFileDao;
import java100.app.domain.Lost;
import java100.app.domain.LostUploadFile;
import java100.app.service.LostService;

@Service
public class LostServiceImpl implements LostService {

    @Autowired LostDao lostDao;
    @Autowired LostFileDao lostFileDao;
    
    @Override
    public List<Lost> list(int pageNo, int pageSize, Map<String, Object> options) {
        
        HashMap<String,Object> params = new HashMap<>();
        
        params.put("startIndex", (pageNo - 1) * pageSize);
        params.put("size", pageSize);
        if (options != null) {
            params.putAll(options);
        }
        
        return lostDao.findAll(params);
    }
    
    @Override
    public List<Lost> listAll() {
        
        return lostDao.findAllList();
    }
    
    @Override
    public int getTotalCount() {
        return lostDao.countAll();
    }

    @Override
    public int add(Lost lost) {
        
        int count = lostDao.insert(lost);
        
        this.addFiles(lost.getFiles(), lost.getLostNo());
        
        return count;
    }
    
    @Override
    public void addFiles(List<LostUploadFile> files, int no) {
        for (LostUploadFile file : files) {
            file.setLostNo(no);
            lostFileDao.insert(file);
        }
    }
    
    @Override
    public Lost get(int no) {
        lostDao.updateViewCount(no);
        
        Lost lost = lostDao.findByNo(no);
        return lost;
    }
    
    @Override
    public int update(Lost lost) {
        int count = lostDao.update(lost);
        if (!lost.getFiles().isEmpty())
            lostFileDao.deleteAllByLostNo(lost.getLostNo());
        
        addFiles(lost.getFiles(), lost.getLostNo());
        
        return count;
    }

    @Override
    public int delete(int no) {
        
        lostFileDao.deleteAllByLostNo(no);
        
        return lostDao.delete(no);
    }
    
    
}












