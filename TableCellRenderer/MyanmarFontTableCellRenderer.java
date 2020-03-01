package TableCellRenderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyanmarFontTableCellRenderer extends DefaultTableCellRenderer{
	private Font zawGyiFont = new Font("Zawgyi-One",Font.PLAIN,12);
	  @Override
	    public Component getTableCellRendererComponent(JTable table,
	            Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
	    	
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
	                row, column);
	        setFont(zawGyiFont);
	        return this;
	    }
}
