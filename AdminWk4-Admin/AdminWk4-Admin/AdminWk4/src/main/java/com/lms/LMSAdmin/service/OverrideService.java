package com.lms.LMSAdmin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.BookLoansDao;
import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.pojo.BookLoans;
import com.lms.LMSAdmin.pojo.BookLoansCompositeKey;
import com.lms.LMSAdmin.pojo.Borrower;
import com.lms.LMSAdmin.pojo.LibraryBranch;

@Component
public class OverrideService {
	
	@Autowired
	BookLoansDao loansDao;
	
	@Autowired
	BorrowerService borrService;
	
	@Autowired
	LibraryBranchService branchService;
	
	@Autowired
	BookService bookService;

	//Override due date
	public void overDueDate(BookLoans loans) {
		loansDao.save(loans);
	}
	
	//Get all records
	public List<BookLoans> getBookLoans() {
		return loansDao.findAll();
	}
	
	//Get one loans record
	public Optional<BookLoans> getLoanById(BookLoansCompositeKey compKey){
		return loansDao.findById(compKey);
	}
	
	public BookLoans getEmbeddedDetails(BookLoans loans) {
		
		Optional<Borrower> borrDetails = borrService.getBorrById(loans.getBlCompKey().getBorrower().getCardNo());
		Borrower borrower = new Borrower(loans.getBlCompKey().getBorrower().getCardNo(), borrDetails.get().getName(), 
				borrDetails.get().getAddress(), borrDetails.get().getPhone());
		
		Optional<LibraryBranch> branchDetails = branchService.getBranchById(loans.getBlCompKey().getBranch().getBranchId());
		LibraryBranch branch = new LibraryBranch(loans.getBlCompKey().getBranch().getBranchId(), branchDetails.get().getBranchName(), 
				branchDetails.get().getBranchAddress());
		
		Optional<Book> bookDetails = bookService.getBookById(loans.getBlCompKey().getBook().getBookId());
		Book book = new Book(loans.getBlCompKey().getBook().getBookId(), bookDetails.get().getTitle(), 
				bookDetails.get().getAuthor(), bookDetails.get().getPublisher());
		
		book = bookService.getEmbeddedDetails(book);
		
		BookLoansCompositeKey compKey = new BookLoansCompositeKey(borrower, branch, book);
		
		loans.setBlCompKey(compKey);
		
		return loans;
	}
	
	//Validate Id's
	public boolean ifExists(int cardNo, int branchId, int bookId) {
		List<BookLoans> list = loansDao.findAll();
		boolean exists = false;
		
		//Check if the cardNo exists
		exists = list.stream()
				.anyMatch(id -> id.getBlCompKey().getBorrower().getCardNo().equals(cardNo) &&
						id.getBlCompKey().getBranch().getBranchId().equals(branchId) &&
						id.getBlCompKey().getBook().getBookId().equals(bookId));
	
		return exists;
	}
}
