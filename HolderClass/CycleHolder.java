package HolderClass;

import java.sql.Timestamp;

public class CycleHolder {
	private int cycleId;
	private String cycleName="";
	private int cycleDay;
	private Timestamp cycleDate;
	private String cycleRemark="";
	
	
	public CycleHolder() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CycleHolder(int cycleId, String cycleName, int cycleDay, Timestamp cycleDate, String cycleRemark) {
		super();
		this.cycleId = cycleId;
		this.cycleName = cycleName;
		this.cycleDay = cycleDay;
		this.cycleDate = cycleDate;
		this.cycleRemark = cycleRemark;
	}





	public int getCycleId() {
		return cycleId;
	}



	public String getCycleName() {
		return cycleName;
	}



	public Timestamp getCycleDate() {
		return cycleDate;
	}



	public String getCycleRemark() {
		return cycleRemark;
	}



	public void setCycleId(int cycleId) {
		this.cycleId = cycleId;
	}



	public void setCycleName(String cycleName) {
		if(cycleName != null)
			this.cycleName = cycleName;
	}



	public void setCycleDate(Timestamp cycleDate) {
		this.cycleDate = cycleDate;
	}



	public void setCycleRemark(String cycleRemark) {
		if(cycleRemark != null)
			this.cycleRemark = cycleRemark;
	}





	public int getCycleDay() {
		return cycleDay;
	}



	public void setCycleDay(int cycleDay) {
		this.cycleDay = cycleDay;
	}





	@Override
	public String toString() {
		return "CycleHolder [cycleId=" + cycleId + ", cycleName=" + cycleName + ", cycleDay=" + cycleDay
				+ ", cycleDate=" + cycleDate + ", cycleRemark=" + cycleRemark + "]";
	}



	
	
	
	
}
