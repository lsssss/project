package java100.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java100.app.dao.DiagnosisDao;
import java100.app.dao.HospitalDao;
import java100.app.dao.HospitalFileDao;
import java100.app.domain.Hospital;
import java100.app.domain.HospitalUploadFile;
import java100.app.service.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired HospitalDao hospitalDao;
    @Autowired HospitalFileDao hospitalfileDao;
    @Autowired DiagnosisDao diagnosisDao;
    
    
    @Override
    public List<Hospital> list(int pageNo, int pageSize, Map<String, Object> options) {
     
        HashMap<String,Object> params = new HashMap<>();
        params.put("startIndex", (pageNo - 1) * pageSize);
        params.put("size", pageSize);
        
        if (options != null) {
            params.putAll(options);
        }
        
        return hospitalDao.findAll(params);
    }

    @Override
    public int getTotalCount() {
        return hospitalDao.countAll();
    }

    @Override
    public int add(Hospital hospital) {
        
        
        int count = hospitalDao.insert(hospital);
        
        this.addFiles(hospital.getFiles(), hospital.getHospitalNo());
        
        return count;
        
        //serviceimple에 try catch문 걸기
        /* try {
            int count = hospitalDao.insert(hospital);
            this.addFiles(hospital.getFiles(), hospital.getHospitalNo());
            return count;
        } catch (Exception e) {
            return 0;
        }*/
       
        
     
        
        
        
        //유니크키 안만들었을시
        /*       int countmemberNo = hospitalDao.hospitalNoByMemberNo(hospital.getMember().getMemberNo());
        int count = 0;
        
        if(countmemberNo == 0) {
          count =  hospitalDao.insert(hospital);
          this.addFiles(hospital.getFiles(), hospital.getHospitalNo());
          return count;
        }else {
          return 0;
        }
        */
    }

    @Override
    public Hospital get(int no) {
        Hospital hospital = hospitalDao.findByNo(no);
        return hospital;
    }
    
    @Override
    public int update(Hospital hospital) {
        int count = hospitalDao.update(hospital);
        hospitalfileDao.deleteAllByHospitalNo(hospital.getHospitalNo());
        addFiles(hospital.getFiles(), hospital.getHospitalNo());
        
        
        return count;
    }
    
    @Override
    public int delete(int no,int no2) {
        
        
        if(no2 == hospitalDao.findByNo(no).getMember().getMemberNo()) {
            hospitalfileDao.deleteAllByHospitalNo(no);
            diagnosisDao.deleteAll(no);
            return hospitalDao.delete(no2); 
        }else {
            return hospitalDao.delete(no2);
        }
        
        
        
       
    }
    
    @Override
    public void addFiles(List<HospitalUploadFile> files, int no) {
        for (HospitalUploadFile file : files) {
            file.setHospitalNo(no);
            hospitalfileDao.hospitalInsert(file);
        }
    }

    @Override
    public int getHospitalBymemberNo(Hospital hospital) {
        int countmemberNo = hospitalDao.hospitalSelectByMemberNo(hospital.getMember().getMemberNo());
        System.out.println(countmemberNo);
        return countmemberNo;
    }

    @Override
    public int getHospitalLoginUserNo(int memberNo) {
        int countHospitalNO = hospitalDao.hospitalSelectByMemberNo(memberNo);
        System.out.println(countHospitalNO);
        return countHospitalNO;
    }

    @Override
    public int getHospitalNoLoginUserNo(int memberNo) {
        int hospitalNo = hospitalDao.hospitalNoLoginUserNo(memberNo).getHospitalNo();
        return hospitalNo;
    }
}
