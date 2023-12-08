package com.java.spring.service;

import com.java.spring.dao.MemberDAOImpl;

/**
 * @author liushuo
 * @version 1.0
 * Service类
 */
public class MemberServiceImpl {
    private MemberDAOImpl memberDAO;

    public MemberDAOImpl getMemberDAO() {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAOImpl memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void add() {
        System.out.println("MemberServiceImpl add方法被调用");
        memberDAO.add();
    }
}
