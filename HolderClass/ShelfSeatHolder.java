package HolderClass;

import java.sql.Timestamp;

public class ShelfSeatHolder {
	private int shelfSeatId;
	private String shelfSeatName;
	private String shelfSeatRemark="";
	private Timestamp shelfSeatDate;
	public ShelfSeatHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShelfSeatHolder(int shelfSeatId, String shelfSeatName, String shelfSeatRemark, Timestamp shelfSeatDate) {
		super();
		this.shelfSeatId = shelfSeatId;
		this.shelfSeatName = shelfSeatName;
		this.shelfSeatRemark = shelfSeatRemark;
		this.shelfSeatDate = shelfSeatDate;
	}
	public int getShelfSeatId() {
		return shelfSeatId;
	}
	public String getShelfSeatName() {
		return shelfSeatName;
	}
	public String getShelfSeatRemark() {
		return shelfSeatRemark;
	}
	public Timestamp getShelfSeatDate() {
		return shelfSeatDate;
	}
	public void setShelfSeatId(int shelfSeatId) {
		this.shelfSeatId = shelfSeatId;
	}
	public void setShelfSeatName(String shelfSeatName) {
		this.shelfSeatName = shelfSeatName;
	}
	public void setShelfSeatRemark(String shelfSeatRemark) {
		this.shelfSeatRemark = shelfSeatRemark;
	}
	public void setShelfSeatDate(Timestamp shelfSeatDate) {
		this.shelfSeatDate = shelfSeatDate;
	}
	@Override
	public String toString() {
		return "ShelfSeatHolder [shelfSeatId=" + shelfSeatId + ", shelfSeatName=" + shelfSeatName + ", shelfSeatRemark="
				+ shelfSeatRemark + ", shelfSeatDate=" + shelfSeatDate + "]";
	}
	
	
}
