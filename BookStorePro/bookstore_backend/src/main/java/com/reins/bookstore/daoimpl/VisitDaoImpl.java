package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.VisitDao;
import com.reins.bookstore.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VisitDaoImpl implements VisitDao {
    @Autowired
    private VisitRepository visitRepository;

    @Override
    public void visitTimes(){
        visitRepository.visitTimes();
    }
}
