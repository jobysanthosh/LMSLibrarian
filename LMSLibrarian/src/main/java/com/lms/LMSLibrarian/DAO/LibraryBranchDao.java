package com.lms.LMSLibrarian.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.lms.LMSLibrarian.POJO.Book;
import com.lms.LMSLibrarian.POJO.LibraryBranch;

@Component
public class LibraryBranchDao {

	public Connection getConnection() {
		Connection conn = null;

		try {
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Brownlenovo5!");
		} catch(SQLException e) {
			System.out.println(e);
		}
		return conn;
	}

	public LibraryBranch getLibraryBranchById(int branchId) {

		LibraryBranch libBranch = new LibraryBranch();
		try {
			
			String sql = "SELECT * FROM library.tbl_library_branch where branchId = ?";
			PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
			prepareStatement.setInt(1, branchId);
			ResultSet resultSet = prepareStatement.executeQuery();
	
			while(resultSet.next()) {
				libBranch.setBranchId(resultSet.getInt("branchId"));
				libBranch.setBranchName(resultSet.getString("branchName"));
				libBranch.setBranchAddress(resultSet.getString("branchAddress"));
			}	
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return libBranch;
	}

	public List<LibraryBranch> getAllLibraryBranch() {
		
		List<LibraryBranch> branch = new ArrayList<LibraryBranch>();
		
		try {
			String sql = "SELECT * FROM library.tbl_library_branch";
			PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			
	
			while(resultSet.next()) {
	
				LibraryBranch lb = new LibraryBranch();
				lb.setBranchId(resultSet.getInt("branchId"));
				lb.setBranchName(resultSet.getString("branchName"));
				lb.setBranchAddress(resultSet.getString("branchAddress"));
				branch.add(lb);
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return branch;
	}

	public ResponseEntity<String> updateLibraryBranch(String name, String address, int branchId)  {
		
		try {
			String sql = "UPDATE library.tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?";
			PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
			prepareStatement.setString(1, name);
			prepareStatement.setString(2, address);
			prepareStatement.setInt(3, branchId);
			prepareStatement.executeUpdate();

			return new ResponseEntity<String>("Updated successfully", HttpStatus.ACCEPTED);
		} 
		catch (SQLException e) {
			return new ResponseEntity<String>("Wrong ID passed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public ResponseEntity<String> updateBookCopy(int bookId, int copy, int branchId) {
		
		try {
			String sql = "Update tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, copy);
			ps.setInt(2, bookId);
			ps.setInt(3, branchId);
			ps.executeUpdate();
			return new ResponseEntity<String>("Updated successfully", HttpStatus.ACCEPTED);
		}
		catch(SQLException e) {
			System.out.println(e);
			return new ResponseEntity<String>("Wrong ID passed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<Book> getBooks(int branchId) {
		
		List<Book> book = new ArrayList<Book>();
		
		try {
			String sql = "SELECT tbl_book.bookId, CONCAT(tbl_book.title, ' by ' , tbl_author.authorName) AS title FROM tbl_book INNER JOIN tbl_author ON tbl_book.authId = tbl_author.authorId INNER JOIN tbl_book_copies ON tbl_book.bookId = tbl_book_copies.bookId INNER JOIN tbl_library_branch ON tbl_book_copies.branchId = tbl_library_branch.branchId WHERE tbl_library_branch.branchId = ?";
			PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
			prepareStatement.setInt(1, branchId);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				Book b = new Book(null, null);
				b.setBookId(resultSet.getInt("bookId"));
				b.setTitle(resultSet.getString("title"));
				book.add(b);
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return book;
	}

	public Book getBookById(int branchId, int bookId) {

		Book book = new Book(null, null);
		
		try {
			String sql = "SELECT tbl_book.bookId, CONCAT(tbl_book.title, ' by ' , tbl_author.authorName) AS title FROM tbl_book INNER JOIN tbl_author ON tbl_book.authId = tbl_author.authorId INNER JOIN tbl_book_copies ON tbl_book.bookId = tbl_book_copies.bookId INNER JOIN tbl_library_branch ON tbl_book_copies.branchId = tbl_library_branch.branchId WHERE tbl_library_branch.branchId = ? AND tbl_book.bookId = ?";
			PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
			prepareStatement.setInt(1, branchId);
			prepareStatement.setInt(2, bookId);
			ResultSet resultSet = prepareStatement.executeQuery();
	
			while(resultSet.next()) {
				book.setBookId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return book;
	}

}
