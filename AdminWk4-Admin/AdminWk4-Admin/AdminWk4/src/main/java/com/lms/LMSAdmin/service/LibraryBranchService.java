  package com.lms.LMSAdmin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.LibraryBranchDao;
import com.lms.LMSAdmin.pojo.LibraryBranch;

@Component
public class LibraryBranchService {
	
	@Autowired
	LibraryBranchDao branDao;
	
	//Insert record
	public LibraryBranch insertBranch(LibraryBranch branch) {
		return branDao.save(branch);
	}
	
	//Update record
	public LibraryBranch updateBranch(LibraryBranch branch) {
		return branDao.save(branch);
	}
	
	//Delete record
	public void deleteBranch(Integer branchId) {
		branDao.deleteById(branchId);
	}
	
	//Get one branch
	public Optional<LibraryBranch> getBranchById(Integer branchId){
		return branDao.findById(branchId);
	}
	
	//Get all records
	public List<LibraryBranch> getAllBranches() {
		return branDao.findAll();
	}
	
	//Validate Id
	public boolean ifExists(Integer branchId) {
		List<LibraryBranch> list = branDao.findAll();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getBranchId().equals(branchId));
	
		return exists;
	}
}
