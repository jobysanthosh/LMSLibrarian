package com.lms.LMSAdmin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.LMSAdmin.pojo.LibraryBranch;

@Repository
public interface LibraryBranchDao extends JpaRepository<LibraryBranch, Integer>{

}
