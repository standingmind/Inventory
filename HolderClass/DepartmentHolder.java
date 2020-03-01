package HolderClass;

public class DepartmentHolder {
	private int departmentId;
	private String departmentName;
	private String departmentRemark;
	public DepartmentHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepartmentHolder(int departmentId, String departmentName, String departmentRemark) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentRemark = departmentRemark;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public String getDepartmentRemark() {
		return departmentRemark;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public void setDepartmentRemark(String departmentRemark) {
		this.departmentRemark = departmentRemark;
	}
	@Override
	public String toString() {
		return "DepartmentHolder [departmentId=" + departmentId + ", departmentName=" + departmentName
				+ ", departmentRemark=" + departmentRemark + "]";
	}
	
	
}
