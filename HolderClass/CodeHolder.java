package HolderClass;

import java.sql.Timestamp;

public class CodeHolder {
	private int codeId;
	private String codeNumber="";
	private CodeTypeHolder codeType;
	private Timestamp codeDate;
	private String codeRemark="";
	
	
	public CodeHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CodeHolder(int codeId, String codeNumber, CodeTypeHolder codeType, Timestamp codeDate, String codeRemark) {
		super();
		this.codeId = codeId;
		this.codeNumber = codeNumber;
		this.codeType = codeType;
		this.codeDate = codeDate;
		this.codeRemark = codeRemark;
	}
	@Override
	public String toString() {
		return "CodeHolder [codeId=" + codeId + ", codeNumber=" + codeNumber + ", codeType=" + codeType + ", codeDate="
				+ codeDate + ", codeRemark=" + codeRemark + "]";
	}
	public int getCodeId() {
		return codeId;
	}
	public String getCodeNumber() {
		return codeNumber;
	}
	public CodeTypeHolder getCodeType() {
		return codeType;
	}
	public Timestamp getCodeDate() {
		return codeDate;
	}
	public String getCodeRemark() {
		return codeRemark;
	}
	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}
	public void setCodeNumber(String codeNumber) {
		if(codeNumber != null)
			this.codeNumber = codeNumber;
	}
	public void setCodeType(CodeTypeHolder codeType) {
		this.codeType = codeType;
	}
	public void setCodeDate(Timestamp codeDate) {
		this.codeDate = codeDate;
	}
	public void setCodeRemark(String codeRemark) {
		if(codeRemark != null)
			this.codeRemark = codeRemark;
	}
	
	
}
