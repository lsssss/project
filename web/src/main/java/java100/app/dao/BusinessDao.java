package java100.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java100.app.domain.Business;

public interface BusinessDao {
    List<Business> findAll(Map<String,Object> params);
    List<Business> findCategory(Map<String,Object> params);
    List<Business> findAllCategory(Map<String,Object> params);
    int countAll();
    int insert(Business business);
    Business findByNo(int bus_no);
    Business findByEmailAndPassword(HashMap<String, Object> params);
    int update(Business business);
    int delete(int bus_no);
    
}















