package com.lms.LMSLibrarian.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.lms.LMSLibrarian.POJO.BookBL;
import com.lms.LMSLibrarian.POJO.BookCopiesBL;
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

	public LibraryBranch getLibraryBranchById(LibraryBranch branch) {

		//LibraryBranch libBranch = new LibraryBranch();
		try {
			
			String sql = "SELECT * FROM library.tbl_library_branch where branchId = ?";
			PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
			prepareStatement.setInt(1, branch.getBranchId());
			ResultSet resultSet = prepareStatement.executeQuery();
	
			while(resultSet.next()) {
				branch.setBranchId(resultSet.getInt("branchId"));
				branch.setBranchName(resultSet.getString("branchName"));
				branch.setBranchAddress(resultSet.getString("branchAddress"));
			}	
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return branch;
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

	public void updateLibraryBranch(LibraryBranch branch)  {
		
		try {
			String sql = "UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?";
			PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
			prepareStatement.setString(1, branch.getBranchName());
			prepareStatement.setString(2, branch.getBranchAddress());
			prepareStatement.setInt(3, branch.getBranchId());
			prepareStatement.executeUpdate();
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	public void updateBookCopy(BookCopiesBL bookCopy) {
		
		try {
			String sql = "Update tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, bookCopy.getNoOfCopies());
			ps.setInt(2, bookCopy.getBookId());
			ps.setInt(3, bookCopy.getBranchId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}

	public List<BookBL> getBooks(int branchId) {
		
		List<BookBL> book = new ArrayList<BookBL>();
		
		try {
			String sql = "SELECT tbl_book.bookId, CONCAT(tbl_book.title, ' by ' , tbl_author.authorName) AS title FROM tbl_book INNER JOIN tbl_author ON tbl_book.authId = tbl_author.authorId INNER JOIN tbl_book_copies ON tbl_book.bookId = tbl_book_copies.bookId INNER JOIN tbl_library_branch ON tbl_book_copies.branchId = tbl_library_branch.branchId WHERE tbl_library_branch.branchId = ?";
			PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
			prepareStatement.setInt(1, branchId);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				BookBL b = new BookBL(null, null);
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

	public BookBL getBookById(int branchId, int bookId) {

		BookBL book = new BookBL(null, null);
		
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
