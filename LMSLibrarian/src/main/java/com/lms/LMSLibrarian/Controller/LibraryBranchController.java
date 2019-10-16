package com.lms.LMSLibrarian.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import com.lms.LMSLibrarian.POJO.BookBL;
import com.lms.LMSLibrarian.POJO.BookCopiesBL;
import com.lms.LMSLibrarian.POJO.LibraryBranch;
import com.lms.LMSLibrarian.Service.LibraryBranchService;


@RestController
@RequestMapping("/LMSLibrarian")
//@Produces({"application/xml", "application/json"})
//@Consumes({"application/xml", "application/json"})
public class LibraryBranchController {

	@Autowired
	LibraryBranchService libBranchService;

	@GetMapping("/branches")
	public List<LibraryBranch> getAllLibraryBranch() {
		return libBranchService.getAllLibraryBranch();
	}

//	@RequestMapping("/branches/{branchid}")
//	@Produces({"application/xml", "application/json"})
//	public ResponseEntity<?> getLibraryBranchById(
//			@RequestHeader("Accept") String accept,
//			@RequestBody LibraryBranch branch) {
//		libBranchService.getLibraryBranchById(branch.getBranchId());
//		return new ResponseEntity<LibraryBranch>(branch,HttpStatus.OK);
//	}

	@PutMapping(value = "/branches/{branchId}/name/{name}/address/{address}", 
			consumes = {"application/xml", "application/json"})
	public ResponseEntity<?> updateLibraryBranch(
			@RequestHeader("Accept") String accept,
			@RequestBody LibraryBranch branch) {
		boolean check = libBranchService.ifBranchExists(branch.getBranchId());
		if(check == true && (branch!=null)) {
			libBranchService.updateLibraryBranch(branch);
			return new ResponseEntity<LibraryBranch>(branch, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<LibraryBranch>(branch, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/branches/{branchId}/bookid")
	public List<BookBL> getBooks(@PathVariable(value="branchId") int branchId) {
		return libBranchService.getBooks(branchId);
	}

	@RequestMapping("/branches/{branchid}/bookid/{bookid}")
	public BookBL getBookById(@PathVariable(value="branchId") int branchId, @PathVariable(value="bookid") int bookId) {
			return libBranchService.getBookById(branchId, bookId);
	}

	@PutMapping(value = "/branches/{branchId}/bookid/{bookid}/copy/{copy}", consumes = {"application/xml", "application/json"})
	public ResponseEntity<?> updateBookCopy( @RequestHeader("Accept") String accept,
											@RequestBody BookCopiesBL bookCopy) {
		boolean branchcheck = libBranchService.ifBranchExists(bookCopy.getBranchId());
		boolean bookcheck = libBranchService.ifBookExists(bookCopy.getBookId(), bookCopy.getBranchId());
		
			if(branchcheck == true && (bookCopy.getBookId() != null) && bookcheck == true && (bookCopy.getBookId()!=null)) {
					libBranchService.updateBookCopy(bookCopy);
					return new ResponseEntity<BookCopiesBL>(bookCopy, HttpStatus.OK);
			}
			
			else {
				return new ResponseEntity<BookCopiesBL>(bookCopy, HttpStatus.NOT_FOUND);
		}
	}

}
