package HolderClass;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;

public class ImportProductHolder {
	private int productId;
	private String productMyanmarName="";
	private String productMyanamrSpec="";
	private String productCode="";
	private String productName="";
	private String productSpec="";
	private String price="";
	private String unit="";
	private String importPerson="";
	private int leadTime;
	private String productClass="";
	private String vendor="";
	private String shelf="";
	private String shelfSeat="";
	private int importQty;
	private int dosage;
	private String cycle="";
	private double premeasure ; 
	
	private int safeStock;
	private String arriveLocation="";
	private String remark="";
	private String imageLocation="";
	private int stockQty;
	private int totalQty;
	private Timestamp importDate;
	private boolean isExistInStock;
	private String operationNo="";
	private Timestamp lastUpdated;
	public ImportProductHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ImportProductHolder(int productId, String productMyanmarName, String productMyanamrSpec, String productCode,
			String productName, String productSpec, String price, String unit, String importPerson, int leadTime,
			String productClass, String vendor, String shelf, String shelfSeat, int importQty, int dosage, String cycle,
			int safeStock, String arriveLocation, String remark, String imageLocation, int stockQty, int totalQty,
			Timestamp importDate, boolean isExistInStock, String operationNo, Timestamp lastUpdated,double premeasure) {
		super();
		this.productId = productId;
		this.productMyanmarName = productMyanmarName;
		this.productMyanamrSpec = productMyanamrSpec;
		this.productCode = productCode;
		this.productName = productName;
		this.productSpec = productSpec;
		this.price = price;
		this.unit = unit;
		this.importPerson = importPerson;
		this.leadTime = leadTime;
		this.productClass = productClass;
		this.vendor = vendor;
		this.shelf = shelf;
		this.shelfSeat = shelfSeat;
		this.importQty = importQty;
		this.dosage = dosage;
		this.cycle = cycle;
		this.safeStock = safeStock;
		this.arriveLocation = arriveLocation;
		this.remark = remark;
		this.imageLocation = imageLocation;
		this.stockQty = stockQty;
		this.totalQty = totalQty;
		this.importDate = importDate;
		this.isExistInStock = isExistInStock;
		this.operationNo = operationNo;
		this.lastUpdated = lastUpdated;
		this.premeasure = premeasure;
	}
	
	

	public double getPremeasure() {
		return premeasure;
	}

	public void setPremeasure(double premeasure) {
		this.premeasure = premeasure;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getOperationNo() {
		return operationNo;
	}

	public void setOperationNo(String operationNo) {
		this.operationNo = operationNo;
	}

	public boolean isExistInStock() {
		return isExistInStock;
	}

	public void setExistInStock(boolean isExistInStock) {
		this.isExistInStock = isExistInStock;
	}

	public Timestamp getImportDate() {
		return importDate;
	}

	public void setImportDate(Timestamp importDate) {
		this.importDate = importDate;
	}
	
	public String getProductMyanmarName() {
		return productMyanmarName;
	}
	public String getProductMyanamrSpec() {
		return productMyanamrSpec;
	}
	public void setProductMyanmarName(String productMyanmarName) {
		this.productMyanmarName = productMyanmarName;
	}
	public void setProductMyanamrSpec(String productMyanamrSpec) {
		this.productMyanamrSpec = productMyanamrSpec;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getStockQty() {
		return stockQty;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	public String getProductCode() {
		return productCode;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public String getPrice() {
		return price;
	}
	public String getUnit() {
		return unit;
	}
	public String getImportPerson() {
		return importPerson;
	}
	public int getLeadTime() {
		return leadTime;
	}
	public String getProductClass() {
		return productClass;
	}
	public String getVendor() {
		return vendor;
	}
	public String getShelf() {
		return shelf;
	}
	public String getShelfSeat() {
		return shelfSeat;
	}

	public int getImportQty() {
		return importQty;
	}
	public int getDosage() {
		return dosage;
	}
	public String getCycle() {
		return cycle;
	}
	public int getSafeStock() {
		return safeStock;
	}
	public String getArriveLocation() {
		return arriveLocation;
	}
	public String getRemark() {
		return remark;
	}
	public String getImageLocation() {
		return imageLocation;
	}
	public void setProductCode(String productCode) {
		if(productCode != null)
			this.productCode = productCode;
	}
	public void setProductName(String productName) {
		if(productName != null)
			this.productName = productName;
	}
	public void setProductSpec(String productSpec) {
		if(productSpec != null)
			this.productSpec = productSpec;
	}
	public void setPrice(String price) {
		if(price != null)
			this.price = price;
	}
	public void setUnit(String unit) {
		if(unit != null)
			this.unit = unit;
	}
	public void setImportPerson(String importPerson) {
		if(importPerson != null)
			this.importPerson = importPerson;
	}
	public void setLeadTime(int leadTime) {
	
		this.leadTime = leadTime;
	}
	public void setProductClass(String productClass) {
		if(productClass != null)
			this.productClass = productClass;
	}
	public void setVendor(String vendor) {
		if(vendor != null)
			this.vendor = vendor;
	}
	public void setShelf(String shelf) {
		if(shelf != null)
			this.shelf = shelf;
	}
	public void setShelfSeat(String shelfSeat) {
		if(shelfSeat != null)
			this.shelfSeat = shelfSeat;
	}
	public void setImportQty(int importQty) {
		this.importQty = importQty;
	}
	public void setDosage(int dosage) {
		this.dosage = dosage;
	}
	public void setCycle(String cycle) {
		if(cycle != null)
			this.cycle = cycle;
	}
	public void setSafeStock(int safeStock) {
		this.safeStock = safeStock;
	}
	public void setArriveLocation(String arriveLocation) {
		if(arriveLocation != null)
			this.arriveLocation = arriveLocation;
	}
	public void setRemark(String remark) {
		if(remark != null)
			this.remark = remark;
	}
	public void setImageLocation(String imageLocation) {
		if(imageLocation != null)
			this.imageLocation = imageLocation;
	}

	@Override
	public String toString() {
		return "ImportProductHolder [productId=" + productId + ", productMyanmarName=" + productMyanmarName
				+ ", productMyanamrSpec=" + productMyanamrSpec + ", productCode=" + productCode + ", productName="
				+ productName + ", productSpec=" + productSpec + ", price=" + price + ", unit=" + unit
				+ ", importPerson=" + importPerson + ", leadTime=" + leadTime + ", productClass=" + productClass
				+ ", vendor=" + vendor + ", shelf=" + shelf + ", shelfSeat=" + shelfSeat + ", importQty=" + importQty
				+ ", dosage=" + dosage + ", cycle=" + cycle + ", premeasure=" + premeasure + ", safeStock=" + safeStock
				+ ", arriveLocation=" + arriveLocation + ", remark=" + remark + ", imageLocation=" + imageLocation
				+ ", stockQty=" + stockQty + ", totalQty=" + totalQty + ", importDate=" + importDate
				+ ", isExistInStock=" + isExistInStock + ", operationNo=" + operationNo + ", lastUpdated=" + lastUpdated
				+ "]";
	}

}
