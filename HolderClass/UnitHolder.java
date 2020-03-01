package HolderClass;

public class UnitHolder {
	private int unitId;
	private String unitName;
	private String unitRemark;
	public UnitHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UnitHolder(int unitId, String unitName, String unitRemark) {
		super();
		this.unitId = unitId;
		this.unitName = unitName;
		this.unitRemark = unitRemark;
	}
	public int getUnitId() {
		return unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public String getUnitRemark() {
		return unitRemark;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public void setUnitRemark(String unitRemark) {
		this.unitRemark = unitRemark;
	}
	@Override
	public String toString() {
		return "UnitHolder [unitId=" + unitId + ", unitName=" + unitName + ", unitRemark=" + unitRemark + "]";
	}
	
}	
