package HolderClass;

public class StockHolder {
	
	private int stockId;
	private String productName="";
	private String productCode="";
	private String productMyanmarName="";
	private String productSpec="";
	private String productPrice="";
	private String productUnit="";
	private int stockQty;
	private int stockDosage;
	private String cycle="";
	private String productVendor="";
	private String productPersonInCharge="";
	private String productImage="";
	private int stockSafeNo;
	public StockHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StockHolder(int stockId, String productName, String productCode, String productMyanmarName,
			String productSpec, String productPrice, String productUnit, int stockQty, int stockDosage, String cycle,
			String productVendor, String productPersonInCharge, String productImage, int stockSafeNo) {
		super();
		this.stockId = stockId;
		this.productName = productName;
		this.productCode = productCode;
		this.productMyanmarName = productMyanmarName;
		this.productSpec = productSpec;
		this.productPrice = productPrice;
		this.productUnit = productUnit;
		this.stockQty = stockQty;
		this.stockDosage = stockDosage;
		this.cycle = cycle;
		this.productVendor = productVendor;
		this.productPersonInCharge = productPersonInCharge;
		this.productImage = productImage;
		this.stockSafeNo = stockSafeNo;
	}

	
	public int getStockSafeNo() {
		return stockSafeNo;
	}

	public void setStockSafeNo(int stockSafeNo) {
		this.stockSafeNo = stockSafeNo;
	}

	public int getStockId() {
		return stockId;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public String getProductMyanmarName() {
		return productMyanmarName;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public int getStockQty() {
		return stockQty;
	}
	public int getStockDosage() {
		return stockDosage;
	}
	public String getCycle() {
		return cycle;
	}
	public String getProductVendor() {
		return productVendor;
	}
	public String getProductPersonInCharge() {
		return productPersonInCharge;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public void setProductName(String productName) {
		if(productName != null)
			this.productName = productName;
	}
	public void setProductCode(String productCode) {
		if(productCode != null)
			this.productCode = productCode;
	}
	public void setProductMyanmarName(String productMyanmarName) {
		if(productMyanmarName != null)
			this.productMyanmarName = productMyanmarName;
	}
	public void setProductSpec(String productSpec) {
		if(productSpec != null)
			this.productSpec = productSpec;
	}
	public void setProductPrice(String productPrice) {
		if(productPrice != null)
			this.productPrice = productPrice;
	}
	public void setProductUnit(String productUnit) {
		if(productUnit != null)
			this.productUnit = productUnit;
	}
	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}
	public void setStockDosage(int stockDosage) {
		this.stockDosage = stockDosage;
	}
	public void setCycle(String cycle) {
		if(cycle != null)
			this.cycle = cycle;
	}
	public void setProductVendor(String productVendor) {
		if(productVendor != null)	
			this.productVendor = productVendor;
	}
	public void setProductPersonInCharge(String productPersonInCharge) {
		if(productPersonInCharge != null)
			this.productPersonInCharge = productPersonInCharge;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		if(productImage != null)
			this.productImage = productImage;
	}
	@Override
	public String toString() {
		return "StockHolder [stockId=" + stockId + ", productName=" + productName + ", productCode=" + productCode
				+ ", productMyanmarName=" + productMyanmarName + ", productSpec=" + productSpec + ", productPrice="
				+ productPrice + ", productUnit=" + productUnit + ", stockQty=" + stockQty + ", stockDosage="
				+ stockDosage + ", cycle=" + cycle + ", productVendor=" + productVendor + ", productPersonInCharge="
				+ productPersonInCharge + ", productImage=" + productImage + "]";
	}

	
	
}
