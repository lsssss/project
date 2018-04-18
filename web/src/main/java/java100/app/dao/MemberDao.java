package java100.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java100.app.domain.Member;

public interface MemberDao {
    List<Member> findAll(Map<String,Object> params);
    int countAll();
    int insert(Member member);
    Member findByNo(int no);
    Member findByEmailAndPassword(HashMap<String, Object> params);
    Member findByEmail(String email);
    int update(Member member);
    int updatePush(Map<String,Object> params);
    int delete(int no); 
}















