package java100.app.dao;

import java.util.List;
import java.util.Map;

import java100.app.domain.Hospital;

public interface HospitalDao {
    List<Hospital> findAll(Map<String,Object> params);
    int countAll();
    int insert(Hospital hospital);
    Hospital findByNo(int no);
    int update(Hospital hospital);
    int delete(int no);
    int hospitalSelectByMemberNo(int no);
    Hospital hospitalNoLoginUserNo(int memberNo);
}















