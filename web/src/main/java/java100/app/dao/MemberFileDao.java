package java100.app.dao;

import java.util.List;

import java100.app.domain.MemberUploadFile;

public interface MemberFileDao {

    List<MemberUploadFile> findByMemberNo(int no);
    
    void insert(MemberUploadFile file);

    void deleteAllByMemberNo(int no);
}
