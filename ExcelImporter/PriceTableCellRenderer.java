package TableCellRenderer;

import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class PriceTableCellRenderer extends DefaultTableCellRenderer{
	  @Override
	    public Component getTableCellRendererComponent(JTable table,
	            Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
		  
		  
	        super.getTableCellRendererComponent(table, "$"+value, isSelected, hasFocus,
	                row, column);
	        
	        return this;
	    }
	  
}
