package com.reins.bookstore.repository;

import com.reins.bookstore.entity.UserInfo;
import com.reins.bookstore.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface VisitRepository extends JpaRepository<Visit,Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Visit SET  times = (times+1) WHERE id = 1")
    void visitTimes();
}
