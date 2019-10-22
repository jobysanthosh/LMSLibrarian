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
	PublisherDao pubDao;
	
	@Autowired
	AuthorDao authorDao;
	
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
	//Get one book
    public Optional<Book> getBookById(Integer bookId){
        return bookDao.findById(bookId);
    }
    
    //Get one author
    public Optional<Author> getAuthorById(Integer authorId) {
        return authorDao.findById(authorId);
    }
    
    //Get one record
    public Optional<Publisher> getPubById(Integer publisherId) {
        return pubDao.findById(publisherId);
    }
    
    //Get embedded data for book copies
    public BookCopies getEmbeddedDetails(BookCopies bookCopy) {
        
        Optional<LibraryBranch> branchDetails = getLibraryBranchById(bookCopy.getBookCopiesComposite().getBranch().getBranchId());
        LibraryBranch branch = new LibraryBranch(bookCopy.getBookCopiesComposite().getBranch().getBranchId(), branchDetails.get().getBranchName(), 
                branchDetails.get().getBranchAddress());
        
        Optional<Book> bookDetails = getBookById(bookCopy.getBookCopiesComposite().getBook().getBookId());
        Book book = new Book(bookCopy.getBookCopiesComposite().getBook().getBookId(), bookDetails.get().getTitle(), 
                bookDetails.get().getAuthor(), bookDetails.get().getPublisher());
        
        book = getBookEmbeddedDetails(book);
        
        BookCopiesComposite compKey = new BookCopiesComposite(book, branch);
        
        bookCopy.setBookCopiesComposite(compKey);
        
        return bookCopy;
    }
    
    //Get embedded data for book data
    public Book getBookEmbeddedDetails(Book book) {
        
        //Get embedded publisher details
        Optional<Publisher> pubDetails = getPubById(book.getPublisher().getPublisherId());
        Publisher pub = new Publisher(book.getPublisher().getPublisherId(), pubDetails.get().getPublisherName(), 
                pubDetails.get().getPublisherAddress(), pubDetails.get().getPublisherPhone());
        
        //Get embedded author details
        Optional<Author> authDetails = getAuthorById(book.getAuthor().getAuthorId());
        Author author = new Author(book.getAuthor().getAuthorId(), authDetails.get().getAuthorName());
        
        book.setPublisher(pub);
        book.setAuthor(author);
        
        return book;
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
