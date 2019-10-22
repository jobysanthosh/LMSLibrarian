package com.lms.LMSAdmin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.BookDao;
import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.pojo.Publisher;

@Component
public class BookService {
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	PublisherService pubService;
	
	@Autowired
	AuthorService authorService;
	
	//Insert record
	public Book insertBook(Book book) {
		return bookDao.save(book);
	}
	
	//Update record
	public Book updateBook(Book book) {
		return bookDao.save(book);
	}
	
	//Delete record
	public void deleteBook(Integer bookId) {
		bookDao.deleteById(bookId);;
	}
	
	//Get one book
	public Optional<Book> getBookById(Integer bookId){
		return bookDao.findById(bookId);
	}
	
	//Get all records
	public List<Book> getAllBooks() {
		return bookDao.findAll();
	}
	
	public Book getEmbeddedDetails(Book book) {
		
		//Get embedded publisher details
		Optional<Publisher> pubDetails = pubService.getPubById(book.getPublisher().getPublisherId());
		Publisher pub = new Publisher(book.getPublisher().getPublisherId(), pubDetails.get().getPublisherName(), 
				pubDetails.get().getPublisherAddress(), pubDetails.get().getPublisherPhone());
		
		//Get embedded author details
		Optional<Author> authDetails = authorService.getAuthorById(book.getAuthor().getAuthorId());
		Author author = new Author(book.getAuthor().getAuthorId(), authDetails.get().getAuthorName());
		
		book.setPublisher(pub);
		book.setAuthor(author);
		
		return book;
	}
	
	//Validate Id
	public boolean ifExists(Integer bookId) {
		List<Book> list = bookDao.findAll();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getBookId().equals(bookId));
	
		return exists;
	}
}
