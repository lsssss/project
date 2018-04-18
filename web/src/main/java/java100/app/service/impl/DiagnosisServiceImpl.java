package java100.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java100.app.dao.DiagnosisDao;
import java100.app.dao.HospitalDao;
import java100.app.domain.Diagnosis;
import java100.app.service.DiagnosisService;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired DiagnosisDao diagnosisDao;
    @Autowired HospitalDao hospitalDao;
    
    
    @Override
    public List<Diagnosis> list(int pageNo, int pageSize, Map<String, Object> options) {
     
        HashMap<String,Object> params = new HashMap<>();
        params.put("startIndex", (pageNo - 1) * pageSize);
        params.put("size", pageSize);
        
        if (options != null) {
            params.putAll(options);
        }
        
        return diagnosisDao.findAll(params);
    }
    

    @Override
    public int getDateTotalCount(String tel,String selectDate,String nowDate) {
        return diagnosisDao.dateCountAll(tel,selectDate,nowDate);
    }
    
    @Override
    public int getHospitalDateTotalCount(int hpno,String selectDate,String nowDate) {
        return diagnosisDao.hospitalDateCountAll(hpno,selectDate,nowDate);
    }

    @Override
    public int getTotalCount() {
        return diagnosisDao.countAll();
    }

    @Override
    public int add(Diagnosis diagnosis) {
        int count = diagnosisDao.insert(diagnosis);
        System.out.println(count);
        return count;
    }

    @Override
    public int get(String tel) {
        int j = diagnosisDao.CountMember(tel);
        System.out.println("j=" + j);
        return j;
    }

    @Override
    public Diagnosis get(int no) {
        Diagnosis diagnosis = diagnosisDao.findByNo(no);
        return diagnosis;
    }
    
    @Override
    public int update(Diagnosis diagnosis,int no) {
        int count = 0;
        
            Diagnosis diagnosis2 = diagnosisDao.findByHospitalNo(no);
            diagnosis.setHospital(diagnosis2.getHospital());
            count = diagnosisDao.update(diagnosis);
            System.out.println("count=" + count);
            return count;
    }
      
    
    
    @Override
    public int delete(int no,int no2) {
        int k = 0;
        
        try {
            k = diagnosisDao.findByHospitalNo(no2).getHospital().getHospitalNo();    
        } catch (Exception e) {
            return 2;
        }
        

        int i = diagnosisDao.countAll();
        System.out.print("i=");
        System.out.println(i);
        diagnosisDao.delete(no,k);
        int j = diagnosisDao.countAll();
        System.out.print("j=");
        System.out.println(j);
        
        if(i==j) {
            return 0;
        }else {
            return 1;
        }
        
    }

    @Override
    public Diagnosis getHospitalNo(int no) {
        return diagnosisDao.findByHospitalNo(no);
    }

    @Override
    public int deleteAll(int no) {
        return diagnosisDao.deleteAll(no);
    }

    @Override
    public List<Diagnosis> myList(String tel,String selectDate,String nowDate) {
        return diagnosisDao.findMyAll(tel,selectDate,nowDate);
    }
    
    @Override
    public List<Diagnosis> myList5(String tel) {
        return diagnosisDao.findMy5(tel);
    }
    
    
    @Override
    public List<Diagnosis> myPageSizeList(String tel, String selectDate, String nowDate,int pageNo, int pageSize) {
        
        HashMap<String,Object> params = new HashMap<>();
        params.put("startIndex", (pageNo - 1) * pageSize);
        params.put("size", pageSize);
        params.put("tel", tel);
        params.put("selectDate", selectDate);
        params.put("nowDate", nowDate);
        
        
        return diagnosisDao.findMyPageSizeAll(params);
    
    }
    
    @Override
    public List<Diagnosis> hospitalPageSizeList(int hpno, String selectDate, String nowDate,int pageNo, int pageSize) {
        
        HashMap<String,Object> params = new HashMap<>();
        params.put("startIndex", (pageNo - 1) * pageSize);
        params.put("size", pageSize);
        params.put("hpno", hpno);
        params.put("selectDate", selectDate);
        params.put("nowDate", nowDate);
        
        return diagnosisDao.findHospitalPageSizeAll(params);
    
    }
    
    
    

    @Override
    public List<Diagnosis> myAllList(String tel) {
        return diagnosisDao.findMyAllList(tel);
    }


    @Override
    public List<Diagnosis> gethopspitalProducerList5(int no) {
        System.out.println(diagnosisDao.findHospitalList5(no));
       return diagnosisDao.findHospitalList5(no);
    }

    @Override
    public List<Diagnosis> gethospitalListAll(int no) {
        return diagnosisDao.findHospitalListAll(no);
    }


    @Override
    public List<Diagnosis> getHospitalList(int hpno, String selectDate, String nowDate) {
        return diagnosisDao.findHospitalList(hpno,selectDate,nowDate);
    }

}
