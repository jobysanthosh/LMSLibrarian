package com.lms.LMSLibrarian.Controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSLibrarian.POJO.Book;
import com.lms.LMSLibrarian.POJO.LibraryBranch;
import com.lms.LMSLibrarian.Service.LibraryBranchService;


@RestController
public class LibraryBranchController {

	@Autowired
	LibraryBranchService libBranchService;

	@RequestMapping("/LMSLibrarian/librarybranch")
	public List<LibraryBranch> getAllLibraryBranch() throws SQLException {
		return libBranchService.getAllLibraryBranch();
	}

	@RequestMapping("/LMSLibrarian/librarybranch/{branchId}")
	public LibraryBranch getLibraryBranchById(@PathVariable int branchId) throws SQLException {
		return libBranchService.getLibraryBranchById(branchId);
	}

	@RequestMapping(value = "/LMSLibrarian/librarybranch/{branchId}/option/1/name/{name}/address/{address}", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.OK, reason = "Library Branch updated.")
	public String updateLibraryBranch(@PathVariable(value="branchId") int branchId, @PathVariable(value="name") String name, @PathVariable(value="address") String address) throws SQLException {
		return libBranchService.updateLibraryBranch(name, address, branchId);
	}

	@RequestMapping("/LMSLibrarian/librarybranch/{branchId}/option/2/bookid")
	public List<Book> getBooks(@PathVariable(value="branchId") int branchId) throws SQLException {
		return libBranchService.getBooks(branchId);
	}

	@RequestMapping("/LMSLibrarian/librarybranch/{branchId}/option/2/bookid/{bookid}")
	public Book getBookById(@PathVariable(value="branchId") int branchId, @PathVariable(value="bookid") int bookId) throws SQLException {
		return libBranchService.getBookById(branchId, bookId);
	}

	@RequestMapping(value = "/LMSLibrarian/librarybranch/{branchId}/option/2/bookid/{bookid}/copy/{copy}", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.OK, reason = "Book Copy updated.")
	public String updateBookCopy(@PathVariable(value="branchId") int branchId, @PathVariable(value="bookid") int bookId, @PathVariable(value="copy") int copy) throws SQLException {
		return libBranchService.updateBookCopy(bookId, copy, branchId);
	}

}
