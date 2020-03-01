
 
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
 
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
 
/**
 * A custom editor for cells in the Country column.
 * @author www.codejava.net
 *
 */
public class ComboTableCellEditor extends AbstractCellEditor
        implements TableCellEditor, ActionListener {

    private Country country;
    private List<Country> listCountry;
     
    public ComboTableCellEditor(List<Country> listCountry) {
        this.listCountry = listCountry;
    }
     
    @Override
    public Object getCellEditorValue() {
        return this.country;
    }
 
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (value instanceof Country) {
            this.country = (Country) value;
        }
         
        JComboBox<Country> comboCountry = new JComboBox<Country>();
         
        for (Country aCountry : listCountry) {
            comboCountry.addItem(aCountry);
        }
         
        comboCountry.setSelectedItem(country);
        comboCountry.addActionListener(this);
         
        if (isSelected) {
            comboCountry.setBackground(table.getSelectionBackground());
        } else {
            comboCountry.setBackground(table.getSelectionForeground());
        }
         
        return comboCountry;
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<Country> comboCountry = (JComboBox<Country>) event.getSource();
        this.country = (Country) comboCountry.getSelectedItem();
    }
 
}