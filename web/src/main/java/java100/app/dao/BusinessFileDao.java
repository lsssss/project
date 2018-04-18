package java100.app.dao;

import java.util.List;

import java100.app.domain.BusinessUploadFile;
import java100.app.domain.MemberUploadFile;

public interface BusinessFileDao {

    List<MemberUploadFile> findByBusinessNo(int bus_no);
    
    // insert/update/delete 개수를 리턴하고 싶다면 리턴 타입을 int로 선언한다.
    // 굳이 리턴할 이유가 없다면 void로 선언해도 된다.
    void insert(BusinessUploadFile file);

    // 게시물의 모든 첨부파일 데이터를 지운다.
    void deleteAllByBusinessNo(int bus_no);
}
