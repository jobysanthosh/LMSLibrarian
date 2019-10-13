package com.lms.LMSLibrarian.Service;

import java.sql.SQLException;
import java.util.List;

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

	public List<LibraryBranch> getAllLibraryBranch() throws SQLException {
		return libBranchDao.getAllLibraryBranch();
	}

	public String updateLibraryBranch(String name, String address, int branchId) throws SQLException {
		return libBranchDao.updateLibraryBranch(name, address, branchId);
	}

	public String updateBookCopy(int bookId, int copy, int branchId) throws SQLException{
		return libBranchDao.updateBookCopy(bookId, copy, branchId);
	}

	public List<Book> getBooks(int branchId) throws SQLException{
		return libBranchDao.getBooks(branchId);
	}

	public Book getBookById(int branchId, int bookId) throws SQLException{
		return libBranchDao.getBookById(branchId, bookId);
	}
}
