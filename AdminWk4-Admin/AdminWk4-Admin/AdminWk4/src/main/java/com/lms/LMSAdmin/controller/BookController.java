package com.lms.LMSAdmin.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.service.AuthorService;
import com.lms.LMSAdmin.service.BookService;
import com.lms.LMSAdmin.service.PublisherService;

@RestController
@RequestMapping("/admin/books")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class BookController {
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class, NullPointerException.class, 
		ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(Exception e) {
        return "Invalid Data";
    }

	@Autowired
	BookService bookService;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	PublisherService pubService;
	
	//Create a record
	@PostMapping("")
	public ResponseEntity<?> insertBook(@RequestBody Book book) {
		
		boolean checkId = authorService.ifExists(book.getAuthor().getAuthorId());
				
		if(checkId == true) {
			checkId = pubService.ifExists(book.getPublisher().getPublisherId());
			
			if(checkId == true) {
				bookService.insertBook(book);
				
				bookService.getEmbeddedDetails(book);
				
				return new ResponseEntity<Book>(book, HttpStatus.CREATED);
			}
		}
		
		return new ResponseEntity<String>("Invalid data.", HttpStatus.NOT_FOUND);
	}
	
	//Update a record
	@PutMapping("/{bookId}")
	public ResponseEntity<?> updateBook(@PathVariable Integer bookId, @RequestBody Book book) {
		
		boolean checkId = bookService.ifExists(bookId);
		
		if(checkId == true) {
			checkId = authorService.ifExists(book.getAuthor().getAuthorId());
			
			if(checkId == true) {
				checkId = pubService.ifExists(book.getPublisher().getPublisherId());
				
				if(checkId == true) {
					book.setBookId(bookId);
					bookService.updateBook(book);
					
					bookService.getEmbeddedDetails(book);
					
					return new ResponseEntity<Book>(book, HttpStatus.OK);
				}
			} 
		}
		
		return new ResponseEntity<String>("Invalid data.", HttpStatus.NOT_FOUND);
	}
	
	//Delete a record
	@DeleteMapping("/{bookId}")
	public ResponseEntity<?> deleteBook(@PathVariable Integer bookId) {
		
		boolean checkId = bookService.ifExists(bookId);
		
		if(checkId == true) {
			bookService.deleteBook(bookId);
			return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
		}
			
		return new ResponseEntity<String>("Invalid data.", HttpStatus.NOT_FOUND);	
	}
	
	//Get one book
	@GetMapping("/{bookId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<Book> getBookById(@PathVariable Integer bookId) {
		return bookService.getBookById(bookId);
	}
	
	//Get all records
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
}
