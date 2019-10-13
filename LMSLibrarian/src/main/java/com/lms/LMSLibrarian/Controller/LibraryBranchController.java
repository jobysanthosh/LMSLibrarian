package com.lms.LMSLibrarian.Controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSLibrarian.POJO.LibraryBranch;
import com.lms.LMSLibrarian.Service.LibraryBranchService;

@RestController
public class LibraryBranchController {
	
	@Autowired
	LibraryBranchService libBranchService;
	
	@RequestMapping("/LMSLibrarian/librarybranch/{branchId}")
	public LibraryBranch getLibraryBranchById(@PathVariable int branchId) throws SQLException {
		return libBranchService.getLibraryBranchById(branchId);
	}
}
