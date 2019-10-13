package com.lms.LMSLibrarian.Service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSLibrarian.DAO.LibraryBranchDao;
import com.lms.LMSLibrarian.POJO.*;

@Component
public class LibraryBranchService {

	@Autowired
	LibraryBranchDao libBranchDao;
	public LibraryBranch getLibraryBranchById(int branchId) throws SQLException {
		return libBranchDao.getLibraryBranchById(branchId);
	}
}
