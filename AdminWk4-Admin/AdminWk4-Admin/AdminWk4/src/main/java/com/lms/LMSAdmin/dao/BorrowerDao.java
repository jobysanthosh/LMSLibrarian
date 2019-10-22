package com.lms.LMSAdmin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.LMSAdmin.pojo.Borrower;

@Repository
public interface BorrowerDao extends JpaRepository<Borrower, Integer> {

}
