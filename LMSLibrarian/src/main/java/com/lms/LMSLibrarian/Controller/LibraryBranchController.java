package com.lms.LMSLibrarian.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSLibrarian.POJO.Book;
import com.lms.LMSLibrarian.POJO.LibraryBranch;
import com.lms.LMSLibrarian.Service.LibraryBranchService;


@RestController
public class LibraryBranchController {

	@Autowired
	LibraryBranchService libBranchService;

	@GetMapping("/LMSLibrarian/librarybranch")
	public List<LibraryBranch> getAllLibraryBranch() {
		return libBranchService.getAllLibraryBranch();
	}

	@RequestMapping("/LMSLibrarian/librarybranch/{branchId}")
	public ResponseEntity<LibraryBranch> getLibraryBranchById(@PathVariable int branchId) {
		return new ResponseEntity<LibraryBranch>(libBranchService.getLibraryBranchById(branchId),HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/LMSLibrarian/librarybranch/{branchid}/name/{name}/address/{address}", method = {RequestMethod.PUT, RequestMethod.GET})
	public ResponseEntity<String> updateLibraryBranch(@PathVariable(value="branchid") Integer branchId, @PathVariable(value="name") String name, @PathVariable(value="address") String address) {
		boolean check = libBranchService.ifBranchExists(branchId);
		if(check == true) {
			libBranchService.updateLibraryBranch(name, address, branchId);
			return new ResponseEntity<String>("Branch Updated successfully", HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<String>("Branch ID doesn't exist", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/LMSLibrarian/librarybranch/{branchId}/bookid")
	public List<Book> getBooks(@PathVariable(value="branchId") int branchId) {
		return libBranchService.getBooks(branchId);
	}

	@RequestMapping("/LMSLibrarian/librarybranch/{branchid}/bookid/{bookid}")
	public Book getBookById(@PathVariable(value="branchId") int branchId, @PathVariable(value="bookid") int bookId) {
		return libBranchService.getBookById(branchId, bookId);
	}

	@RequestMapping(value = "/LMSLibrarian/librarybranch/{branchid}/bookid/{bookid}/copy/{copy}", method = {RequestMethod.PUT, RequestMethod.GET})
	public ResponseEntity<String> updateBookCopy(@PathVariable(value="branchid") Integer branchId, @PathVariable(value="bookid") Integer bookId, @PathVariable(value="copy") Integer copy) {
		boolean check = libBranchService.ifBranchExists(branchId);
		if(check == true) {
			check = libBranchService.ifBookExists(bookId, branchId);
			if(check == true) {
				libBranchService.updateBookCopy(bookId, copy, branchId);
				return new ResponseEntity<String>("Successfully updated", HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<String>("Book ID doesn't exist", HttpStatus.NOT_FOUND);
			}
		}
			else {
				return new ResponseEntity<String>("Branch ID doesn't exist", HttpStatus.NOT_FOUND);
		}
	}

}
