package DBInteract;

public class User {
	private String userName;
	private String password;
	private int usrID;
	
	
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public User(String userName, String password, int usrID) {
		this.userName = userName;
		this.password = password;
		this.usrID = usrID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUsrID() {
		return usrID;
	}

	public void setUsrID(int ursID) {
		this.usrID = ursID;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", usrID=" + usrID + "]";
	}
	
	
}
