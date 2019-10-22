package com.lms.LMSLibrarian.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.LMSLibrarian.POJO.*;
import com.lms.LMSLibrarian.Service.LibraryBranchService;

@RestController

@RequestMapping("/librarian/branches")
public class LibraryBranchController {
	
	//Exceptions for Null and String 
	 @ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class, NullPointerException.class})
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 public String handle(Exception e) {
		 return "Invalid Request";
	 }


	@Autowired
	LibraryBranchService libBranchService;

	@GetMapping(value="",
				consumes = {"application/xml", "application/json"})
	public List<LibraryBranch> getAllLibraryBranch(		@RequestHeader("Accept") String accept) {
		
			return libBranchService.getAllLibraryBranch();
			
	}

	@PutMapping(value = "/{branchId}", 
				consumes = {"application/xml", "application/json"})
		public ResponseEntity<?> updateLibraryBranch(	@RequestHeader("Accept") String accept,
														@PathVariable Integer branchId,
														@RequestBody LibraryBranch branch)  {
		
			boolean check = libBranchService.ifBranchExists(branchId);
			if(check == true && branch!=null) {
				
					branch.setBranchId(branchId);
					libBranchService.updateLibraryBranch(branch);
					
					return new ResponseEntity<LibraryBranch>(branch, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("ID doesn't exist or null values encountered", HttpStatus.NOT_FOUND);
			}
		}

//		@RequestMapping("/{branchId}/bookId")
//		public List<Book> getBooks(@PathVariable(value="branchId") int branchId) {
//			return libBranchService.getBooks(branchId);
//		}

	@GetMapping(value = "/{branchId}",
				consumes = {"application/xml", "application/json"})
	public Optional<LibraryBranch> getBookById(			@PathVariable(value="branchId") Integer branchId) {
		
			return libBranchService.getLibraryBranchById(branchId);
	}

	@PutMapping(value = "/copies", 
				consumes = {"application/xml", "application/json"})
		public ResponseEntity<?> updateBookCopy( 	@RequestHeader("Accept") String accept,
													@RequestBody BookCopies bookCopy) {

			boolean branchcheck = libBranchService.ifBranchExists(bookCopy.getBookCopiesComposite().getBranch().getBranchId());
			boolean bookcheck = libBranchService.ifBookExists(bookCopy.getBookCopiesComposite().getBook().getBookId());
			Integer copycheck = bookCopy.getNoOfCopies();
			Integer branchnullcheck = bookCopy.getBookCopiesComposite().getBranch().getBranchId();
			Integer booknullcheck = bookCopy.getBookCopiesComposite().getBook().getBookId();
			
				if(branchcheck == true  && bookcheck == true && bookCopy!=null 
						&& copycheck !=null && branchnullcheck != null && booknullcheck!=null) {
					
								libBranchService.updateBookCopy(bookCopy);
								libBranchService.getEmbeddedDetails(bookCopy);
						
								return new ResponseEntity<BookCopies>(bookCopy, HttpStatus.OK);
				}
				
				else {	
					return new ResponseEntity<String>("ID doesn't exist or null values encountered", HttpStatus.NOT_FOUND);
			}
		}

}
