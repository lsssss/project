package java100.app.dao;

import java.util.List;
import java.util.Map;

import java100.app.domain.Lost;

public interface LostDao {
    List<Lost> findAll(Map<String,Object> params);
    List<Lost> findAllList();
    int countAll();
    int insert(Lost lost);
    int updateViewCount(int no);
    Lost findByNo(int no);
    int update(Lost lost);
    int delete(int no);
}















