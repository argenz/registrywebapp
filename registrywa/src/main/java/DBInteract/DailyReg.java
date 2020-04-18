package DBInteract;

import java.sql.Date;
import java.sql.Time;

//JavaBean che descrive l'oggetto "Registrazione" giornaliera. 

public class DailyReg {
	private Date regDate;
	private Time mEntryTime;
	private Time mExitTime;
	private Time aEntryTime;
	private Time aExitTime;
	private String description;
	private String signCand;
	private int userID;

	public DailyReg(Date regDate, Time mEntryTime, Time mExitTime, Time aEntryTime, Time aExitTime, String description,
			String signCand, int userID) {
		super();
		this.regDate = regDate;
		this.mEntryTime = mEntryTime;
		this.mExitTime = mExitTime;
		this.aEntryTime = aEntryTime;
		this.aExitTime = aExitTime;
		this.description = description;
		this.signCand = signCand;
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Date getregDate() {
		return regDate;
	}

	public void setregDate(Date regDate) {
		this.regDate = regDate;
	}

	public Time getmEntryTime() {
		return mEntryTime;
	}

	public void setmEntryTime(Time mEntryTime) {
		this.mEntryTime = mEntryTime;
	}

	public Time getmExitTime() {
		return mExitTime;
	}

	public void setmExitTime(Time mExitTime) {
		this.mExitTime = mExitTime;
	}

	public Time getaEntryTime() {
		return aEntryTime;
	}

	public void setaEntryTime(Time aEntryTime) {
		this.aEntryTime = aEntryTime;
	}

	public Time getaExitTime() {
		return aExitTime;
	}

	public void setaExitTime(Time aExitTime) {
		this.aExitTime = aExitTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSignCand() {
		return signCand;
	}

	public void setSignCand(String signCand) {
		this.signCand = signCand;
	}

	@Override
	public String toString() {
		return "DailyReg [regDate=" + regDate + ", mEntryTime=" + mEntryTime + ", mExitTime=" + mExitTime
				+ ", aEntryTime=" + aEntryTime + ", aExitTime=" + aExitTime + ", description=" + description
				+ ", signCand=" + signCand + ", userID=" + userID + "]";
	}

	

}
