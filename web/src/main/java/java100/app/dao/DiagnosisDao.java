package java100.app.dao;

import java.util.List;
import java.util.Map;

import java100.app.domain.Diagnosis;

public interface DiagnosisDao {
    List<Diagnosis> findAll(Map<String,Object> params);
    List<Diagnosis> findMyPageSizeAll(Map<String,Object> params);
    List<Diagnosis> findMyAll(String tel,String selectDate,String nowDate);
    List<Diagnosis> findMy5(String tel);
    List<Diagnosis> findMyAllList(String tel);

    
    ///병원 관계자 리스트 Dao
    List<Diagnosis> findHospitalList(int no,String selectDate,String nowDate);
    List<Diagnosis> findHospitalList5(int no);
    List<Diagnosis> findHospitalListAll(int no);
    List<Diagnosis> findHospitalPageSizeAll(Map<String,Object> params);
    
    int dateCountAll(String tel,String selectDate,String nowDate);
    int hospitalDateCountAll(int hpno,String selectDate,String nowDate);
    int countAll();
    int CountMember(String tel);
    int insert(Diagnosis diagnosis);
    Diagnosis findByNo(int no);
    Diagnosis findByHospitalNo(int no);
    int update(Diagnosis diagnosis);
    int delete(int no,int no2); 
    int deleteAll(int no);
    
}















