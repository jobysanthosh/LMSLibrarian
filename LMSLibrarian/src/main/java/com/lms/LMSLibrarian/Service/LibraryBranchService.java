package com.lms.LMSLibrarian.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSLibrarian.DAO.*;
import com.lms.LMSLibrarian.POJO.*;

@Component
public class LibraryBranchService {

	@Autowired
	LibraryBranchDao libBranchDao;
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	BookCopiesDao bookCopiesDao;
	
	public boolean ifBranchExists(int branchId) {
        List<LibraryBranch> list = libBranchDao.findAll();
        
        boolean exists = list.stream()
               				.anyMatch(id -> id.getBranchId()
                			.equals(branchId));
        return exists;
    }
	
	public boolean ifBookExists(int bookId) {
        List<Book> list = bookDao.findAll();
        boolean exists = list.stream()
                			.anyMatch(id -> id.getBookId()
                			.equals(bookId));
        return exists;
    }
	
	public Optional<LibraryBranch> getLibraryBranchById(Integer branchId) {
		 return libBranchDao.findById(branchId);
	}
	
	public List<LibraryBranch> getAllLibraryBranch() {
		return libBranchDao.findAll();
	}

	public LibraryBranch updateLibraryBranch(LibraryBranch branch) {
		 return libBranchDao.save(branch);
	}

	public BookCopies updateBookCopy(BookCopies bookCopy) {
		 return bookCopiesDao.save(bookCopy);
	}

//	public List<Book> getBooks(int branchId) {
//		return libBranchDao.getBooks(branchId);
//	}
//	
//	public Book getBookById(int branchId, int bookId) {
//		return libBranchDao.getBookById(branchId, bookId);
//	}
	
//	public BookCopies getBookInfo(BookCopies bookCopy) {
//		bookCopy.getBookCopiesComposite().getBook().getTitle();
//		bookCopy.setBookCopiesComposite(bookCopy.);
//	}
}
