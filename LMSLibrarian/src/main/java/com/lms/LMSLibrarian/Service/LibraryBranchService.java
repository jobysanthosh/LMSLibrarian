package com.lms.LMSLibrarian.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSLibrarian.DAO.LibraryBranchDao;
import com.lms.LMSLibrarian.POJO.*;

@Component
public class LibraryBranchService {

	@Autowired
	LibraryBranchDao libBranchDao;
	
	public boolean ifBranchExists(int branchId) {
        List<LibraryBranch> list = libBranchDao.getAllLibraryBranch();
        boolean exists = list.stream()
               				.anyMatch(id -> id.getBranchId()
                			.equals(branchId));
        return exists;
    }
	
	public boolean ifBookExists(int bookId, int branchId) {
        List<Book> list = libBranchDao.getBooks(branchId);
        boolean exists = list.stream()
                			.anyMatch(id -> id.getBookId()
                			.equals(bookId));
        return exists;
    }
	
	public LibraryBranch getLibraryBranchById(int branchId) {
		return libBranchDao.getLibraryBranchById(branchId);
	}
	
	public List<LibraryBranch> getAllLibraryBranch() {
		return libBranchDao.getAllLibraryBranch();
	}

	public void updateLibraryBranch(String name, String address, Integer branchId) {
		 libBranchDao.updateLibraryBranch(name, address, branchId);
	}

	public void updateBookCopy(Integer bookId, Integer copy, Integer branchId) {
		 libBranchDao.updateBookCopy(bookId, copy, branchId);
	}

	public List<Book> getBooks(int branchId) {
		return libBranchDao.getBooks(branchId);
	}
	
	public Book getBookById(int branchId, int bookId) {
		return libBranchDao.getBookById(branchId, bookId);
	}
}
