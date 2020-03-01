package HolderClass;

import java.sql.Timestamp;

public class ShelfHolder {
	private int shelfId;
	private String shelfName="";
	private String shelfRemark="";
	private Timestamp shelfDate;
	public ShelfHolder(int shelfId, String shelfName, String shelfRemark, Timestamp shelfDate) {
		super();
		this.shelfId = shelfId;
		this.shelfName = shelfName;
		this.shelfRemark = shelfRemark;
		this.shelfDate = shelfDate;
	}
	public ShelfHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getShelfId() {
		return shelfId;
	}
	public String getShelfName() {
		return shelfName;
	}
	public String getShelfRemark() {
		return shelfRemark;
	}
	public Timestamp getShelfDate() {
		return shelfDate;
	}
	public void setShelfId(int shelfId) {
		this.shelfId = shelfId;
	}
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	public void setShelfRemark(String shelfRemark) {
		this.shelfRemark = shelfRemark;
	}
	public void setShelfDate(Timestamp shelfDate) {
		this.shelfDate = shelfDate;
	}
	@Override
	public String toString() {
		return "ShelfHolder [shelfId=" + shelfId + ", shelfName=" + shelfName + ", shelfRemark=" + shelfRemark
				+ ", shelfDate=" + shelfDate + "]";
	}
	
	
}
