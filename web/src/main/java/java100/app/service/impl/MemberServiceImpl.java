package java100.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java100.app.dao.MemberFileDao;
import java100.app.dao.MemberDao;
import java100.app.domain.Member;
import java100.app.domain.MemberUploadFile;
import java100.app.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired MemberDao memberDao;
    @Autowired MemberFileDao memberFileDao;
    
    @Override
    public List<Member> list(int pageNo, int pageSize, Map<String, Object> options) {
        
        HashMap<String,Object> params = new HashMap<>();
        params.put("startIndex", (pageNo - 1) * pageSize);
        params.put("size", pageSize);
        
        if (options != null) {
            params.putAll(options);
        }
        
        return memberDao.findAll(params);
    }
    
    @Override
    public int getTotalCount() {
        return memberDao.countAll();
    }

    @Override
    public int add(Member member) {
        
        int count = memberDao.insert(member);
        
        return count;
    }
    
    @Override
    public Member get(int no) {
        Member member = memberDao.findByNo(no);
        return member;
    }

    
    @Override
    public Member get(String email, String password) {
        
        HashMap<String,Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

        return memberDao.findByEmailAndPassword(params);
    }
    
    
    @Override
    public Member get(String email) {
        
        return memberDao.findByEmail(email);
    }
    
    @Override
    public int update(Member member) {
        int count = memberDao.update(member);
        if (!member.getFiles().isEmpty())
            memberFileDao.deleteAllByMemberNo(member.getMemberNo());
        
        addFiles(member.getFiles(), member.getMemberNo());
        
        return count;
    }
    @Override
    public int delete(int no) {
        
        memberFileDao.deleteAllByMemberNo(no);
        
        return memberDao.delete(no);
    }

    @Override
    public void addFiles(List<MemberUploadFile> files, int no) {
        for (MemberUploadFile file : files) {
            file.setMemberNo(no);
            memberFileDao.insert(file);
        }
    }

    @Override
    public void updatePush(boolean push, int no) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("push", push);
        params.put("no", no);
        
        memberDao.updatePush(params);
    }
}












