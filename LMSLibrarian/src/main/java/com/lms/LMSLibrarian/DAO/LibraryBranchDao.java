package com.lms.LMSLibrarian.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

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

}
