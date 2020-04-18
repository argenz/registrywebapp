package DBInteract;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

// Implemented methods:
// getAll() - gets the whole registration table
// select(LocalDate date) - selects the registration object for a specific date
// insertDailyReg(DailyReg registration) inserts the daily registration
// createRegTable 
// dropRegTable


//to do: modify a registration

public class DailyRegDao implements Closeable {
    //private static final Logger logger = LoggerFactory.getLogger(DailyRegDao.class);

	private Connection conn;
	
//CONNECTION METHODS	

	public DailyRegDao(DataSource ds) {      //se mettevo l'eccezione throws qui e non la chatchiavo, mi tirava un "unhandled exception" quando chiamavo il metodo. 
		try {
			this.conn = ds.getConnection();
		} catch (SQLException se) {
			throw new IllegalStateException("Can't connect to DataBase: " + se.getMessage());		}
	}
	
	@Override
	public void close() throws IOException {
		try {
			conn.close();
		} catch (SQLException se) {
			throw new IllegalStateException("Failed to close connection: " + se.getMessage());		}
	}

//QUERY METHODS
	
	public List<DailyReg> selectAll(int userID){
		List<DailyReg> registrations = new ArrayList<DailyReg>();

		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("select * from registrations where userID = ?");
			stmt.setInt(1, userID);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			registrations.add(new DailyReg(rs.getDate("regDate"), rs.getTime("mEntryTime"),
					rs.getTime("mExitTime"), rs.getTime("aEntryTime"),
					rs.getTime("aExitTime"), rs.getString("description"), rs.getString("signCand"), rs.getInt("userID")));
		}
		return registrations;
		} catch (SQLException se) {
			throw new IllegalStateException("Couldn't execute the query: " + se.getMessage());
		}
	}


	public void insertDailyReg(DailyReg registration){
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(
					"INSERT into registrations(regDate, mEntryTime, mExitTime, aEntryTime, aExitTime, description, signCand, userID) VALUES( ?, ?, ?, ?, ? ,?, ?, ?)");
			pstmt.setDate(1, registration.getregDate());
			pstmt.setTime(2, registration.getmEntryTime());
			pstmt.setTime(3, registration.getmExitTime());
			pstmt.setTime(4, registration.getaEntryTime());
			pstmt.setTime(5, registration.getaExitTime());
			pstmt.setString(6, registration.getDescription());
			pstmt.setString(7, registration.getSignCand());
			pstmt.setInt(8, registration.getUserID());
			pstmt.executeUpdate();
			
			//add logging
			
		} catch (SQLException se) {
			throw new IllegalStateException("Couldn't execute the update: " + se.getMessage());
		}
	}


//	public DailyReg select(Date date, int userID){
//		PreparedStatement pstmt;
//		try {
//			pstmt = conn.prepareStatement("select * from registrations where date = ? ");
//			pstmt.setDate(1, date);
//			ResultSet rs = pstmt.executeQuery();
//			return new DailyReg(rs.getDate("regDate"), rs.getTime("mEntryTime"),
//					rs.getTime("mExitTime"), rs.getTime("aEntryTime"),
//					rs.getTime("aExitTime"), rs.getString("description"), rs.getString("signCand"), userID);
//		} catch (SQLException se) {
//			throw new IllegalStateException("Couldn't execute the query: " + se.getMessage());
//		}
//		
//	}
	
	
	
	
////This should be done in a migration doc in SQL or included in an _init_ function when web app starts. 
//	
//	public void createRegTable() {
//		Statement stmt;
//		try {
//			stmt = conn.createStatement();
//			try {
//				stmt.execute(
//						"CREATE table IF NOT EXISTS registrations(regDate date primary key, mEntryTime time, mExitTime time, aEntryTime time, aExitTime time, description varchar(200), signCand varchar(40), userID integer, "
//						+ "constraint user_id_fk foreign key(userID) references studuser(userID))");
//			//add logging
//			} catch (SQLException se) {
//				throw new IllegalStateException("Error in creating a new table 'registrations', the table might already exist: " + se.getMessage());
//			}
//		} catch (SQLException se) {
//			throw new IllegalStateException("Couldn't execute the update: " + se.getMessage());		}
//		
//		
//	}
//	
////this shouldn't exist in the real app	
//	public void dropRegTable() {
//		Statement stmt;
//		try {
//			stmt = conn.createStatement();
//			try {
//				stmt.execute("DROP table IF EXISTS registrations");
//			//add logging
//			} catch (SQLException se) {
//				throw new IllegalStateException("Error in dropping the table 'registrations': " + se.getMessage());
//			}
//		} catch (SQLException se) {
//			throw new IllegalStateException("Couldn't execute the query: " + se.getMessage());		}
//		
//	}
//
//	// @TO-DO: 
//	public void modifyDailyReg() {
//		// solo se è il giorno stesso.
//	}
//	
//	


}
