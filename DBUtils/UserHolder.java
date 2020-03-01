package HolderClass;

import java.sql.Timestamp;

public class UserHolder {
	private int userId;
	private String userName;
	private int departmentId;
	private String userHash;
	private boolean userAdminType;
	private Timestamp userDate;
	private int userCardNo;
	private String userSalt;
	
	
	public UserHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserHolder(int userId, String userName, int departmentId, String userHash, boolean userAdminType,
			Timestamp userDate, int userCardNo, String userSalt) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.departmentId = departmentId;
		this.userHash = userHash;
		this.userAdminType = userAdminType;
		this.userDate = userDate;
		this.userCardNo = userCardNo;
		this.userSalt = userSalt;
	}

	public int getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public String getUserHash() {
		return userHash;
	}
	public boolean isUserAdminType() {
		return userAdminType;
	}
	public Timestamp getUserDate() {
		return userDate;
	}
	public int getUserCardNo() {
		return userCardNo;
	}
	public String getUserSalt() {
		return userSalt;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public void setUserHash(String userHash) {
		this.userHash = userHash;
	}
	public void setUserAdminType(boolean userAdminType) {
		this.userAdminType = userAdminType;
	}
	public void setUserDate(Timestamp userDate) {
		this.userDate = userDate;
	}
	public void setUserCardNo(int userCardNo) {
		this.userCardNo = userCardNo;
	}
	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	@Override
	public String toString() {
		return "UserHolder [userId=" + userId + ", userName=" + userName + ", departmentId=" + departmentId
				+ ", userHash=" + userHash + ", userAdminType=" + userAdminType + ", userDate=" + userDate
				+ ", userCardNo=" + userCardNo + ", userSalt=" + userSalt + "]";
	}
	
	
	
}
