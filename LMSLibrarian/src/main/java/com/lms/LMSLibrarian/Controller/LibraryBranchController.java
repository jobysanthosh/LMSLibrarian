package com.lms.LMSLibrarian.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping("/LMSLibrarian/librarybranch")
	public List<LibraryBranch> getAllLibraryBranch() {
		return libBranchService.getAllLibraryBranch();
	}

	@RequestMapping("/LMSLibrarian/librarybranch/{branchId}")
	public LibraryBranch getLibraryBranchById(@PathVariable int branchId) {
			return libBranchService.getLibraryBranchById(branchId);
	}

	@RequestMapping(value = "/LMSLibrarian/librarybranch/{branchId}/option/1/name/{name}/address/{address}", method = {RequestMethod.POST, RequestMethod.GET})
		public ResponseEntity<String> updateLibraryBranch(@PathVariable(value="branchId") int branchId, @PathVariable(value="name") String name, @PathVariable(value="address") String address) {
		boolean check = libBranchService.ifBranchExists(branchId);
		if(check == true) {
			return libBranchService.updateLibraryBranch(name, address, branchId);
		}
		else {
			return new ResponseEntity<String>("Wrong ID passed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/LMSLibrarian/librarybranch/{branchId}/option/2/bookid")
	public List<Book> getBooks(@PathVariable(value="branchId") int branchId) {
		return libBranchService.getBooks(branchId);
		
	}

	@RequestMapping("/LMSLibrarian/librarybranch/{branchId}/option/2/bookid/{bookid}")
	public Book getBookById(@PathVariable(value="branchId") int branchId, @PathVariable(value="bookid") int bookId) {
		return libBranchService.getBookById(branchId, bookId);
	}

	@RequestMapping(value = "/LMSLibrarian/librarybranch/{branchId}/option/2/bookid/{bookid}/copy/{copy}", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> updateBookCopy(@PathVariable(value="branchId") int branchId, @PathVariable(value="bookid") int bookId, @PathVariable(value="copy") int copy) {
		boolean check = libBranchService.ifBranchExists(branchId);
		if(check == true) {
			check = libBranchService.ifBookExists(bookId, branchId);
			if(check == true) {
				return libBranchService.updateBookCopy(bookId, copy, branchId);
			}
			else {
			return new ResponseEntity<String>("Wrong ID passed", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			return new ResponseEntity<String>("Wrong ID passed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
