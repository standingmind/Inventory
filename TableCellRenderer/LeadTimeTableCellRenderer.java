package TableCellRenderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class LeadTimeTableCellRenderer extends DefaultTableCellRenderer{
	  @Override
	    public Component getTableCellRendererComponent(JTable table,
	            Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
	    	String day ;
	    	if((int)value > 1){
	    		day = "(Days)";
	    	}else{
	    		day = "(Day)";
	    	}
	        super.getTableCellRendererComponent(table, value+day, isSelected, hasFocus,
	                row, column);
	        return this;
	    }
}
