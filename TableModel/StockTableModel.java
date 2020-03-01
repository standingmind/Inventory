package TableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import HolderClass.ProductHolder;
import HolderClass.StockHolder;

public class StockTableModel extends AbstractTableModel{
	
	private ArrayList<StockHolder> stkHdr = new ArrayList<StockHolder>();
	private String[] columnNames;
	public StockTableModel(ArrayList<StockHolder> hdr,String[] columnNames){
		this.stkHdr = hdr;
		this.columnNames = columnNames;
	}
	
	public String getColumnName(int column){
		return columnNames[column];
	}
	
	public String[] getColumnNames(){
		
		return columnNames;
	}

	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return stkHdr.size();
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		//number of attribute in stockholder class
		return 13;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object value = "";
		StockHolder stock = stkHdr.get(rowIndex);
		switch(columnIndex){
		case 0:
			value = rowIndex + 1;
			break;
		case 1:
			value = stock.getProductCode();break;
		case 2:
			value = stock.getProductName();break;
		case 3:
			value = stock.getProductMyanmarName();break;
		case 4:
			value = stock.getProductSpec();break;
		case 5:
			value = stock.getStockQty();break;
		case 6:
			value = stock.getProductUnit();break;
		case 7:
			value = stock.getStockSafeNo();break;
		case 8:
			value = stock.getStockDosage();break;
		case 9:
			value = stock.getCycle();break;
		case 10:
			value = stock.getProductPrice();break;
		case 11:
			value = stock.getProductVendor();break;
		case 12:
			value = stock.getProductPersonInCharge();break;
		default:
			value = new Object();
		}
		return value;
	}
	
	public void setData(ArrayList<StockHolder> list){
		//shallow copy 
		ArrayList<StockHolder> newList = new ArrayList<StockHolder>();
		
		for(StockHolder holder : list){
			StockHolder newHolder = new StockHolder();
			newHolder.setStockId(holder.getStockId());
			newHolder.setProductCode(holder.getProductCode());
			newHolder.setProductName(holder.getProductName());
			newHolder.setProductMyanmarName(holder.getProductMyanmarName());
			newHolder.setProductSpec(holder.getProductSpec());
			newHolder.setStockQty(holder.getStockQty());
			newHolder.setProductUnit(holder.getProductUnit());
			newHolder.setStockSafeNo(holder.getStockSafeNo());
			newHolder.setStockDosage(holder.getStockDosage());
			newHolder.setCycle(holder.getCycle());
			newHolder.setProductPrice(holder.getProductPrice());
			newHolder.setProductVendor(holder.getProductVendor());
			newHolder.setProductPersonInCharge(holder.getProductPersonInCharge());
			newList.add(newHolder);
		}
		stkHdr = newList;
		this.fireTableDataChanged();
	}
	
	
	public StockHolder getStockAt(int rowIndex){
		return stkHdr.get(rowIndex);
	}

}
