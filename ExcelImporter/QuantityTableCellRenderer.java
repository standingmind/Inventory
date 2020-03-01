package TableCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import TableModel.StockTableModel;

public class QuantityTableCellRenderer extends DefaultTableCellRenderer{
	
	  @Override
	    public Component getTableCellRendererComponent(JTable table,
	            Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
		  
			  DecimalFormat decimalFormat = new DecimalFormat("#,###");
		      String numberAsString = decimalFormat.format(value);
		      JLabel lbl = (JLabel)super.getTableCellRendererComponent(table, numberAsString, isSelected, hasFocus,
	                row, column);
		      StockTableModel stkModel = (StockTableModel)table.getModel();
		      int safeStock = (int)stkModel.getValueAt(row, 7);	//7 is table safestock
		      
		      if((int)value > safeStock){
		    	  lbl.setFont(new Font("ËÎÌו",Font.BOLD,12));
			        lbl.setForeground(Color.decode("#08A638"));
		      }else{
		    	  lbl.setFont(new Font("ËÎÌו",Font.BOLD,12));
			        lbl.setForeground(Color.red);
		      }
		      
		    
	        return (Component)lbl;
	    }
	  
}