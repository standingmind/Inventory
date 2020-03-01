package HolderClass;

import java.sql.Timestamp;

public class ProductHolder {
	private int productId;
	private String productName="";
	private String productSpec="";
	private String productUnit="";
	private String productCode="";
	private String productMyanmarName="";
	private Timestamp productDate;
	private String productRemark="";
	private int vendorId;
	private String productPresonInCharge="";
	private String productPrice="";
	private int productClassId;
	private String productPhoto="";
	private String productVendor="";
	private String productClass="";
	private int unitId;
	
	
	
	public ProductHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public ProductHolder(int productId, String productName, String productSpec, String productUnit, String productCode,
			String productMyanmarName, Timestamp productDate, String productRemark, int vendorId,
			String productPresonInCharge, String productPrice, int productClassId, String productPhoto,
			String productVendor, String productClass, int unitId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productSpec = productSpec;
		this.productUnit = productUnit;
		this.productCode = productCode;
		this.productMyanmarName = productMyanmarName;
		this.productDate = productDate;
		this.productRemark = productRemark;
		this.vendorId = vendorId;
		this.productPresonInCharge = productPresonInCharge;
		this.productPrice = productPrice;
		this.productClassId = productClassId;
		this.productPhoto = productPhoto;
		this.productVendor = productVendor;
		this.productClass = productClass;
		this.unitId = unitId;
	}


	
	public int getUnitId() {
		return unitId;
	}



	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}



	public String getProductVendor() {
		return productVendor;
	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductPresonInCharge(String productPresonInCharge) {
		this.productPresonInCharge = productPresonInCharge;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public int getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public String getProductCode() {
		return productCode;
	}
	public String getProductMyanmarName() {
		return productMyanmarName;
	}
	public Timestamp getProductDate() {
		return productDate;
	}
	public String getProductRemark() {
		return productRemark;
	}
	public int getVendorId() {
		return vendorId;
	}
	public String getProductPresonInCharge() {
		return productPresonInCharge;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public int getProductClassId() {
		return productClassId;
	}
	public String getProductPhoto() {
		return productPhoto;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setProductName(String productName) {
		if(productName != null)
			this.productName = productName;
	}
	public void setProductSpec(String productSpec) {
		if(productSpec != null)
			this.productSpec = productSpec;
	}
	public void setProductUnit(String productUnit) {
		if(productUnit != null)
			this.productUnit = productUnit;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public void setProductMyanmarName(String productMyanmarName) {
		if(productMyanmarName != null)
			this.productMyanmarName = productMyanmarName;
	}
	public void setProductDate(Timestamp productDate) {
		this.productDate = productDate;
	}
	public void setProductRemark(String productRemark) {
		if(productRemark != null)
			this.productRemark = productRemark;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public void setProductPersonInCharge(String productPersonInCharge) {
		if(productPersonInCharge != null)
			this.productPresonInCharge = productPersonInCharge;
	}
	public void setProductPrice(String productPrice) {
		if(productPrice != null)
			this.productPrice = productPrice;
	}
	public void setProductClassId(int productClassId) {
		this.productClassId = productClassId;
	}
	public void setProductPhoto(String productPhoto) {
		if(productPhoto != null)
			this.productPhoto = productPhoto;
	}



	@Override
	public String toString() {
		return "ProductHolder [productId=" + productId + ", productName=" + productName + ", productSpec=" + productSpec
				+ ", productUnit=" + productUnit + ", productCode=" + productCode + ", productMyanmarName="
				+ productMyanmarName + ", productDate=" + productDate + ", productRemark=" + productRemark
				+ ", vendorId=" + vendorId + ", productPresonInCharge=" + productPresonInCharge + ", productPrice="
				+ productPrice + ", productClassId=" + productClassId + ", productPhoto=" + productPhoto
				+ ", productVendor=" + productVendor + ", productClass=" + productClass + ", unitId=" + unitId + "]";
	}

	
	
	
}
