package com.lms.LMSLibrarian.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lms.LMSLibrarian.POJO.Book;
import com.lms.LMSLibrarian.POJO.LibraryBranch;

@Component
public class LibraryBranchDao {

	public Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Brownlenovo5!");
		} catch(SQLException e) {
			System.out.println(e);
		}
		return conn;
	}

	public LibraryBranch getLibraryBranchById(int branchId) throws SQLException {

		String sql = "SELECT * FROM library.tbl_library_branch where branchId = ?";
		PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
		prepareStatement.setInt(1, branchId);
		ResultSet resultSet = prepareStatement.executeQuery();

		LibraryBranch libBranch = new LibraryBranch();

		while(resultSet.next()) {
			libBranch.setBranchId(resultSet.getInt("branchId"));
			libBranch.setBranchName(resultSet.getString("branchName"));
			libBranch.setBranchAddress(resultSet.getString("branchAddress"));
		}
		return libBranch;
	}

	public List<LibraryBranch> getAllLibraryBranch() throws SQLException {
		String sql = "SELECT * FROM library.tbl_library_branch";
		PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
		ResultSet resultSet = prepareStatement.executeQuery();
		List<LibraryBranch> branch = new ArrayList<LibraryBranch>();

		while(resultSet.next()) {

			LibraryBranch lb = new LibraryBranch();
			lb.setBranchId(resultSet.getInt("branchId"));
			lb.setBranchName(resultSet.getString("branchName"));
			lb.setBranchAddress(resultSet.getString("branchAddress"));
			branch.add(lb);
		}
		return branch;
	}

	public String updateLibraryBranch(String name, String address, int branchId) throws SQLException {
		String sql = "UPDATE library.tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?";
		PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
		prepareStatement.setString(1, name);
		prepareStatement.setString(2, address);
		prepareStatement.setInt(3, branchId);
		prepareStatement.executeUpdate();

		String message = "Updated Successfully";
		return message;
	}

	public String updateBookCopy(int bookId, int copy, int branchId) throws SQLException {
		String sql = "Update tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, copy);
		ps.setInt(2, bookId);
		ps.setInt(3, branchId);
		ps.executeUpdate();

		String message = "Updated Successfully";
		return message;
	}

	public List<Book> getBooks(int branchId) throws SQLException {
		String sql = "SELECT tbl_book.bookId, CONCAT(tbl_book.title, ' by ' , tbl_author.authorName) AS title FROM tbl_book INNER JOIN tbl_author ON tbl_book.authId = tbl_author.authorId INNER JOIN tbl_book_copies ON tbl_book.bookId = tbl_book_copies.bookId INNER JOIN tbl_library_branch ON tbl_book_copies.branchId = tbl_library_branch.branchId WHERE tbl_library_branch.branchId = ?";
		PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
		prepareStatement.setInt(1, branchId);
		ResultSet resultSet = prepareStatement.executeQuery();
		List<Book> book = new ArrayList<Book>();

		while(resultSet.next()) {

			Book b = new Book(null, null);
			b.setBookId(resultSet.getInt("bookId"));
			b.setTitle(resultSet.getString("title"));
			book.add(b);
		}
		return book;
	}

	public Book getBookById(int branchId, int bookId) throws SQLException {

		String sql = "SELECT tbl_book.bookId, CONCAT(tbl_book.title, ' by ' , tbl_author.authorName) AS title FROM tbl_book INNER JOIN tbl_author ON tbl_book.authId = tbl_author.authorId INNER JOIN tbl_book_copies ON tbl_book.bookId = tbl_book_copies.bookId INNER JOIN tbl_library_branch ON tbl_book_copies.branchId = tbl_library_branch.branchId WHERE tbl_library_branch.branchId = ? AND tbl_book.bookId = ?";
		PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
		prepareStatement.setInt(1, branchId);
		prepareStatement.setInt(2, bookId);
		ResultSet resultSet = prepareStatement.executeQuery();

		Book book = new Book(null, null);

		while(resultSet.next()) {
			book.setBookId(resultSet.getInt("bookId"));
			book.setTitle(resultSet.getString("title"));
		}
		return book;
	}

}
