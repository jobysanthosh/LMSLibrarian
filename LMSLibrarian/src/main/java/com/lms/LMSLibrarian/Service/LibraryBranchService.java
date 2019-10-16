package com.lms.LMSLibrarian.Service;

import java.util.List;
import java.util.Optional;

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
        List<BookBL> list = libBranchDao.getBooks(branchId);
        boolean exists = list.stream()
                			.anyMatch(id -> id.getBookId()
                			.equals(bookId));
        return exists;
    }
	
//	public void getLibraryBranchById(LibraryBranch branch) {
//		 libBranchDao.getLibraryBranchById(branch.getBranchId());
//	}
	
	public List<LibraryBranch> getAllLibraryBranch() {
		return libBranchDao.getAllLibraryBranch();
	}

	public void updateLibraryBranch(LibraryBranch branch) {
		 libBranchDao.updateLibraryBranch(branch);
	}

	public void updateBookCopy(BookCopiesBL bookCopy) {
		 libBranchDao.updateBookCopy(bookCopy);
	}

	public List<BookBL> getBooks(int branchId) {
		return libBranchDao.getBooks(branchId);
	}
	
	public BookBL getBookById(int branchId, int bookId) {
		return libBranchDao.getBookById(branchId, bookId);
	}
}
