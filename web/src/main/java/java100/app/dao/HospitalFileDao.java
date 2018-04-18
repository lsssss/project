package java100.app.dao;

import java.util.List;

import java100.app.domain.HospitalUploadFile;

public interface HospitalFileDao {

    List<HospitalUploadFile> findByHospitalNo(int no);
  //  List<UploadFile> findByHospitalNo(int no);
   // List<UploadFile> findByDiagnosisNo(int no);
    
    // insert/update/delete 개수를 리턴하고 싶다면 리턴 타입을 int로 선언한다.
    // 굳이 리턴할 이유가 없다면 void로 선언해도 된다.
    void hospitalInsert(HospitalUploadFile file);
   // void hospitalInsert(UploadFile file);
  //  void diagnosisInsert(UploadFile file);

    // 게시물의 모든 첨부파일 데이터를 지운다.
    void deleteAllByHospitalNo(int no);
    //void deleteAllByHospitalNo(int no);
   // void deleteAllByDiagnosisNo(int no);
}
