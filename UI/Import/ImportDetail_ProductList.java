package UI.Import;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import HolderClass.ProductHolder;
import TableModel.ImportDetail_ProductList_TableModel;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.events.KeyEvent;
import java.awt.Font;


public class ImportDetail_ProductList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private JScrollPane scrollPane;
	private JButton okButton;
	private JButton cancelButton;
	private static ImportDetail_ProductList_TableModel tblmdl;
	private static ProductHolder holder;

	private Font font = new Font("宋体", Font.PLAIN, 12);
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ImportDetail_ProductList(String title,Dialog.ModalityType mt,ArrayList<ProductHolder> productList) {
		super();
		this.setTitle(title);
		this.setModalityType(mt);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
			scrollPane = new JScrollPane();
			scrollPane.setFont(font);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		
			table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scrollPane.setViewportView(table);
			tblmdl = new ImportDetail_ProductList_TableModel(productList,new String[]{"料名","规格","图片"});
			table.setModel(tblmdl);
			updateRowHeights();
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			
			
			cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			
			//key binding to jtable
			table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "enter");
			table.getActionMap().put("enter",new AbstractAction(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					holder = tblmdl.getProductAt(table.getSelectedRow());
					dispose(); //close the dialog
				}
				
			});
			
			cancelButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			okButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					holder = tblmdl.getProductAt(table.getSelectedRow());
					dispose(); //close the dialog
				}
			});
		
	}
	

	
	//update table row
	private void updateRowHeights()
	{
	    for (int row = 0; row < table.getRowCount(); row++)
	    {
	        int rowHeight = table.getRowHeight();

	        for (int column = 0; column < table.getColumnCount(); column++)
	        {
	            Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
	            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
	        }

	        table.setRowHeight(row, rowHeight);
	    }
	}

	public ProductHolder getValue(){
		return holder;
	}
	
	

}
