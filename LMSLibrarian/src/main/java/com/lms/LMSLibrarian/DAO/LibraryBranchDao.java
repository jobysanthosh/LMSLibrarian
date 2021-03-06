package com.lms.LMSLibrarian.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.LMSLibrarian.POJO.LibraryBranch;


@Repository
public interface LibraryBranchDao extends JpaRepository<LibraryBranch, Integer> {	

}