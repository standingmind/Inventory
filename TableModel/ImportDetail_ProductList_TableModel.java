package TableModel;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import HolderClass.ProductHolder;
import Utility.ImageUtility;

public class ImportDetail_ProductList_TableModel extends AbstractTableModel{
	private ArrayList<ProductHolder> productList ;
	private String[] columnNames;
	
	public ImportDetail_ProductList_TableModel(ArrayList<ProductHolder> productList,String[] columnNames){
		this.productList = productList;
		this.columnNames = columnNames;
	}
	
	public Class<?> getColumnClass(int column){
		
		switch(column){
		case 0 : return String.class; 
		case 1 : return String.class;
		case 2 : return ImageIcon.class;
		default : return Object.class;
		}
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
		return productList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object value = "";
		ProductHolder holder = productList.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			value = holder.getProductName();break;
		case 1:
			value = holder.getProductSpec();break;
		case 2:
			String path = holder.getProductPhoto();
			Image icon =  ImageUtility.resizeImage(path);
			ImageIcon dim = new ImageIcon(icon);
			value = dim;
			break;
		default:
			value = new Object();
		}
		
		// TODO Auto-generated method stub
		return value;
	}

	public ProductHolder getProductAt(int row){
		return productList.get(row);
	}
}
