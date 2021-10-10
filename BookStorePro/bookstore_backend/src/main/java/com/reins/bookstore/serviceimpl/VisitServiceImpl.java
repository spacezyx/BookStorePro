package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.VisitDao;
import com.reins.bookstore.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    private VisitDao visitDao;

    @Override
    public synchronized void visitTimes(){
        visitDao.visitTimes();
    }

    @Override
    public Integer getVists() {return visitDao.getVists();}
}
