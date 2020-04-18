package DBInteract;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

//implemented methods;
//getting all users with a specified username
//inserting a new user

//creating a new table user


public class StudUserDao implements Closeable {
	private Connection conn;

	public StudUserDao(DataSource ds) {
		try {
			this.conn = ds.getConnection();
		} catch (SQLException se) {
			throw new IllegalStateException("Connection failed" + se.getMessage());
		}
	}

	@Override
	public void close() throws IOException {
		try {
			conn.close();
		} catch (SQLException se) {
			throw new IllegalStateException("Failed to close connection" + se.getMessage());
		}
	}

//QUERY METHODS

	public List<User> getUser(String usrName) {
		List<User> result = new ArrayList<User>();

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("Select userID, username, mypassword from studuser where username = ?");
			pstmt.setString(1, usrName);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				result.add(new User(rs.getString("username"), rs.getString("mypassword"), rs.getInt("userID")));
			}
			return result;

		} catch (SQLException se) {
			throw new IllegalStateException("Couldn't execute the query: " + se.getMessage());
		}

	
		
	}
	

	public void insertStud(User user) {
		
		//TO-DO: add-check that the new user doesn't exist already
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("INSERT into studuser(username, mypassword) VALUES( ?, ?)");
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();
			
			
		} catch (SQLException se) {
			throw new IllegalStateException("Couldn't insert user" + se.getMessage());
		}

	}
	
	
//This should be done in a migration doc in SQL or included in an _init_ function when web app starts. 
	
//	public void createStudUser() {
//		Statement stmt;
//		
//			try {
//				stmt = conn.createStatement();
//				stmt.execute(
//						"CREATE table IF NOT EXISTS studuser(userID integer primary key not null auto_increment, username varchar(200), mypassword varchar(200))");
//			//add logging
//			} catch (SQLException se) {
//				throw new IllegalStateException("Error in creating a new table 'studuser', the table might already exist or connection failed: " + se.getMessage());
//			}
//		
//		
//		
//	}
}
