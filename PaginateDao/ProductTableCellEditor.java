package TableCellEditor;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
 
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import DBUtils.ClassDao;
import DBUtils.UnitDao;
import DBUtils.VendorDao;
 
/**
 * A custom editor for cells in the Country column.
 * @author www.codejava.net
 *
 */
public class ProductTableCellEditor extends AbstractCellEditor
        implements TableCellEditor {

    private String selectedItem;
    private JComboBox combo ; 
    private JComboBox comboClass;
    private JComboBox comboUnit;
    private JComboBox comboVendor;
    private Font font12 = new Font("ËÎÌו",Font.PLAIN,12);
    @Override
    public Object getCellEditorValue() {
        return this.selectedItem;
    }
 
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        
    	switch(column){
    	case 6:
    		comboClass = new JComboBox(ClassDao.getNames());
    		comboClass.setFont(font12);
    		comboClass.setSelectedItem(value);
    		this.selectedItem = (String)value;
    		return comboClass;
    	case 8:
    		comboUnit = new JComboBox(UnitDao.getName());
    		comboUnit.setSelectedItem(value);
    		comboUnit.setFont(font12);
    		this.selectedItem = (String)value;
    		return comboUnit;
    	case 9:
    		comboVendor = new JComboBox(VendorDao.getNames());
    		comboVendor.setSelectedItem(value);
    		comboVendor.setFont(font12);
    		this.selectedItem = (String)value;
    		return comboVendor;
    	}
       // combo.addActionListener(this);
//         
//        if (isSelected) {
//            combo.setBackground(table.getSelectionBackground());
//        } else {
//            combo.setBackground(table.getSelectionForeground());
//        }
         
        return combo;
    }
 
  /*  @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox comboBoxSample = (JComboBox) event.getSource();
        this.selectedItem = (String) comboBoxSample.getSelectedItem();
    }*/
 
}