package HolderClass;

import java.sql.Timestamp;

public class VendorHolder {
	private int vendorId;
	private String vendorName="";
	private String vendorERP="";
	private Timestamp vendorDate;
	private String vendorRemark="";
	public VendorHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VendorHolder(int vendorId, String vendorName, String vendorERP, Timestamp vendorDate, String vendorRemark) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorERP = vendorERP;
		this.vendorDate = vendorDate;
		this.vendorRemark = vendorRemark;
	}
	@Override
	public String toString() {
		return "VendorHolder [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorERP=" + vendorERP
				+ ", vendorDate=" + vendorDate + ", vendorRemark=" + vendorRemark + "]";
	}
	public int getVendorId() {
		return vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public String getVendorERP() {
		return vendorERP;
	}
	public Timestamp getVendorDate() {
		return vendorDate;
	}
	public String getVendorRemark() {
		return vendorRemark;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public void setVendorName(String vendorName) {
		if(vendorName != null)
			this.vendorName = vendorName;
	}
	public void setVendorERP(String vendorERP) {
		if(vendorERP != null)
			this.vendorERP = vendorERP;
	}
	public void setVendorDate(Timestamp vendorDate) {
		this.vendorDate = vendorDate;
	}
	public void setVendorRemark(String vendorRemark) {
		if(vendorRemark != null)
			this.vendorRemark = vendorRemark;
	}
	
}
