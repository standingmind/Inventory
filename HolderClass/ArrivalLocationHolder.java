package HolderClass;

public class ArrivalLocationHolder {
	private int arrivalLocationId;
	private String arrivalLocationName="";
	private String arrivalLocationRemark="";
	public int getArrivalLocationId() {
		return arrivalLocationId;
	}
	public String getArrivalLocationName() {
		return arrivalLocationName;
	}
	public String getArrivalLocationRemark() {
		return arrivalLocationRemark;
	}
	public void setArrivalLocationId(int arrivalLocationId) {
		this.arrivalLocationId = arrivalLocationId;
	}
	public void setArrivalLocationName(String arrivalLocationName) {
		if(arrivalLocationName!=null)
			this.arrivalLocationName = arrivalLocationName;
	}
	public void setArrivalLocationRemark(String arrivalLocationRemark) {
		if(arrivalLocationRemark != null)
			this.arrivalLocationRemark = arrivalLocationRemark;
	}
	public ArrivalLocationHolder(int arrivalLocationId, String arrivalLocationName, String arrivalLocationRemark) {
		super();
		this.arrivalLocationId = arrivalLocationId;
		this.arrivalLocationName = arrivalLocationName;
		this.arrivalLocationRemark = arrivalLocationRemark;
	}
	public ArrivalLocationHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ArrivalLocationHolder [arrivalLocationId=" + arrivalLocationId + ", arrivalLocationName="
				+ arrivalLocationName + ", arrivalLocationRemark=" + arrivalLocationRemark + "]";
	}
	
}
