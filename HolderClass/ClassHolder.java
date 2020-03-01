package HolderClass;

import java.sql.Date;
import java.sql.Timestamp;

public class ClassHolder {

	private int classId;
	private String className="";
	private Timestamp classDate;
	private String classRemark="";
	public ClassHolder() {
		// TODO Auto-generated constructor stub
	}
	public int getClassId() {
		return classId;
	}
	public String getClassName() {
		return className;
	}
	@Override
	public String toString() {
		return "ClassHolder [classId=" + classId + ", className=" + className + ", classDate=" + classDate
				+ ", classRemark=" + classRemark + "]";
	}
	public ClassHolder(int classId, String className, Timestamp classDate, String classRemark) {
		super();
		this.classId = classId;
		this.className = className;
		this.classDate = classDate;
		this.classRemark = classRemark;
	}
	public Timestamp getClassDate() {
		return classDate;
	}
	public String getClassRemark() {
		return classRemark;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public void setClassName(String className) {
		if(className!=null)
			this.className = className;
	}
	public void setClassDate(Timestamp date) {
		this.classDate = date;
	}
	public void setClassRemark(String classRemark) {
		if(classRemark!=null)
			this.classRemark = classRemark;
	}

}
