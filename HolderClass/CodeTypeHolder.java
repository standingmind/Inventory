package HolderClass;

import java.sql.Timestamp;

public class CodeTypeHolder {
	private int codeTypeId;
	private String codeTypeName="";
	private Timestamp codeTypeDate;
	private String codeTypeRemark="";
	

	
	public CodeTypeHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CodeTypeHolder [codeTypeId=" + codeTypeId + ", codetTypeName=" + codeTypeName + ", codeTypeDate="
				+ codeTypeDate + ", codeTypeRemark=" + codeTypeRemark + "]";
	}
	public CodeTypeHolder(int codeTypeId, String codeTypeName, Timestamp codeTypeDate, String codeTypeRemark) {
		super();
		this.codeTypeId = codeTypeId;
		this.codeTypeName = codeTypeName;
		this.codeTypeDate = codeTypeDate;
		this.codeTypeRemark = codeTypeRemark;
	}
	public int getCodeTypeId() {
		return codeTypeId;
	}
	public String getCodeTypeName() {
		return codeTypeName;
	}
	public Timestamp getCodeTypeDate() {
		return codeTypeDate;
	}
	public String getCodeTypeRemark() {
		return codeTypeRemark;
	}
	public void setCodeTypeId(int codeTypeId) {
		this.codeTypeId = codeTypeId;
	}
	public void setCodeTypeName(String codeTypeName) {
		if(codeTypeName != null)
			this.codeTypeName = codeTypeName;
	}
	public void setCodeTypeDate(Timestamp codeTypeDate) {
		this.codeTypeDate = codeTypeDate;
	}
	public void setCodeTypeRemark(String codeTypeRemark) {
		if(codeTypeRemark != null)
			this.codeTypeRemark = codeTypeRemark;
	}
	
	
	
}	
