package HolderClass;

public class StockShelfHolder {
	private String shelf="";
	private int shelfSeat;
	private int stockId;
	
	public StockShelfHolder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockShelfHolder(String shelf, int shelfSeat, int stockId) {
		super();
		this.shelf = shelf;
		this.shelfSeat = shelfSeat;
		this.stockId = stockId;
	}
	
	public String getShelf() {
		return shelf;
	}
	public int getShelfSeat() {
		return shelfSeat;
	}
	public int getStockId() {
		return stockId;
	}
	public void setShelf(String shelf) {
		if(shelf != null)
			this.shelf = shelf;
	}
	public void setShelfSeat(int shelfSeat) {
		this.shelfSeat = shelfSeat;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	
	
	@Override
	public String toString() {
		return "StockShelfHolder [shelf=" + shelf + ", shelfSeat=" + shelfSeat + ", stockId=" + stockId + "]";
	}

	
}
