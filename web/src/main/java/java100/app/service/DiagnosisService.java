package java100.app.service;

import java.util.List;
import java.util.Map;

import java100.app.domain.Diagnosis;

//=> "역할"을 강조할 때는 "객체(object)"라는 말보다는 
//"컴포넌트(component)"라는 말을 사용한다.
//=> "객체"는 말 그대로 한 개의 클래스를 가리키는 것이라면,
//"컴포넌트"는 그 역할을 수행하는 여러 객체의 묶음을 총칭한다.
//
//Service 컴포넌트(인터페이스, 구현체)는 "업무 로직"과 "트랜잭션 처리"를  
//담당하기 때문에 인터페이스에 선언하는 메서드 이름도 
//가능한 업무 용어를 사용한다.
//
public interface DiagnosisService {
    List<Diagnosis> list(int pageNo, int pageSize, Map<String,Object> options);
    int getTotalCount();
    int add(Diagnosis diagnosis);
    int get(String tel);
    int getDateTotalCount(String te,String selectDate,String nowDate);
    
    int getHospitalDateTotalCount(int hpno,String selectDate,String nowDate);
    Diagnosis get(int no);
    int update(Diagnosis diagnosis,int no);
    int deleteAll(int no);
    int delete(int no,int no2);
    Diagnosis getHospitalNo(int no);
    
    //병원 관계자 List출력 서비스
    List<Diagnosis> getHospitalList(int hpno, String selectDate,String nowDate);
    List<Diagnosis> gethopspitalProducerList5(int no);
    List<Diagnosis> gethospitalListAll(int no);
    List<Diagnosis> hospitalPageSizeList(int hpno, String selectDate,String nowDate,int pageNo, int pageSize);
    
    //회원의 list출력 서비스
    List<Diagnosis> myList(String tel,String selectDate,String nowDate);
    List<Diagnosis> myList5(String string);
    List<Diagnosis> myPageSizeList(String tel, String selectDate,String nowDate,int pageNo, int pageSize);
    List<Diagnosis> myAllList(String tel);
    
}





