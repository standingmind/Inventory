package UI.Import;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import HolderClass.ImportProductHolder;
import TableCellRenderer.LeadTimeTableCellRenderer;
import TableCellRenderer.PriceTableCellRenderer;
import TableCellRenderer.QuantityTableCellRenderer;
import TableModel.ImportTableModel;

public class ImportProductUI extends JPanel {
	private JTable table;
	private Font font12 = new Font("宋体", Font.PLAIN, 12);
	private ImportTableModel tableModel ;
	private ArrayList<ImportProductHolder> importList = new ArrayList<ImportProductHolder>();
	private String userName="";
	private static int HEADER_HEIGHT = 35;
	private JToggleButton btnNew;
	private JToggleButton btnEdit;
	private JToggleButton btnDelete;
	/**
	 * Create the panel.
	 */
	public ImportProductUI(String user) {
		this.userName = user;
		setFont(font12);
		setBorder(new TitledBorder(null, "\u65B0\u6599\u5165\u5E93 (Import Product)", TitledBorder.LEADING, TitledBorder.TOP, font12, new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		JPanel toolPanel = new JPanel();
		FlowLayout fl_toolPanel = (FlowLayout) toolPanel.getLayout();
		fl_toolPanel.setAlignment(FlowLayout.LEFT);
		add(toolPanel, BorderLayout.NORTH);
		
		JToolBar toolBar = new JToolBar();
		toolPanel.add(toolBar);
		
		btnNew = new JToggleButton("\u65B0\u5EFA(New)\r\n");
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.setFont(font12);
		btnNew.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\new.png"));
		toolBar.add(btnNew);
		
		btnEdit = new JToggleButton("\u7F16\u8F91(Edit)");
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEdit.setFont(font12);
		btnEdit.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\edit.png"));
		toolBar.add(btnEdit);
		
		btnDelete = new JToggleButton("\u518C\u9664(delete)");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setFont(font12);
		btnDelete.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\delete.png"));
		toolBar.add(btnDelete);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnImport = new JButton("\u5165\u5E93(Import)");
		btnImport.setFont(font12);
		
		panel_1.add(btnImport);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		tableModel =new ImportTableModel(importList,new String[] {
				"No", "<html> \u6599\u53F7<br>Code</html>", "<html> \u6599\u540D<br>Name</html>", "<html> \u89C4\u683C<br>Spec.</html>", "<html> \u5206\u7C7B<br>Class</html>", "<html> \u5355\u4EF7<br>price</html>", "<html> \u5165\u5E93\u91CF<br>quantity</html>","<html> \u5355\u4F4D<br>unit</html>",  "<html> \u8C03\u8FBE\u624B\u756A<br>leadtime</html>", "<html> \u65E5\u671F<br>Date</html>", "<html> \u5907\u6CE8<br>Remark</html>"
			});
		table.setTableHeader(new JTableHeader(table.getColumnModel()) {
			  @Override public Dimension getPreferredSize() {
			    Dimension d = super.getPreferredSize();
			    d.height = HEADER_HEIGHT;
			    return d;
			  }
			});
		table.getTableHeader().setFont(font12);
		table.setFont(font12);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		table.getColumnModel().getColumn(5).setCellRenderer(new PriceTableCellRenderer());
		table.getColumnModel().getColumn(6).setCellRenderer(new QuantityTableCellRenderer());
		table.getColumnModel().getColumn(8).setCellRenderer(new LeadTimeTableCellRenderer());
		table.addMouseListener(new ImportMouseListener());
	
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JEditorPane dtrpnHello = new JEditorPane();
		dtrpnHello.setPreferredSize(new Dimension(250, 21));
		panel.add(dtrpnHello);

		
		
		//actionlistener
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ImportDetail importDetail;
				try {
					importDetail = new ImportDetail("Detail",Dialog.ModalityType.APPLICATION_MODAL,userName,ImportProductUI.this,null,-1);
					importDetail.setVisible(true);
					importDetail.setFocusable(true);
					btnNew.setSelected(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >=0){
					
					int choice = JOptionPane.showConfirmDialog(ImportProductUI.this, "确定要册除吗？","Confirm Dialog",JOptionPane.OK_CANCEL_OPTION);
					if(choice == JOptionPane.OK_OPTION){
						System.out.println("current selected row :"+row);
						ImportTableModel model = (ImportTableModel)table.getModel();
						model.removeRow(row);
						System.out.print("row "+row+" removed");
						
					}
					btnDelete.setSelected(false);
				
				}else{
					btnDelete.setSelected(false);
				}
				
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ImportDetail importDetail;
				int row = table.getSelectedRow();
				if(row >= 0){
					ImportProductHolder h= tableModel.getImportProductAt(row);
					try {
						importDetail = new ImportDetail("Detail",Dialog.ModalityType.APPLICATION_MODAL,userName,ImportProductUI.this,h,row);
						importDetail.setVisible(true);
						importDetail.setFocusable(true);
						btnEdit.setSelected(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					btnEdit.setSelected(false);
				}
				
				
			}
		});
		
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportTableModel model = (ImportTableModel)table.getModel();
				try{
					if(model.getProductList().size() > 0 ){
						int choice = JOptionPane.showConfirmDialog(ImportProductUI.this, "<html>确定要入库吗?<br>Are you sure you want to import?","入库确认",JOptionPane.YES_NO_OPTION);
						if(choice == JOptionPane.OK_OPTION){
							model.executeBatch();
							JOptionPane.showMessageDialog(ImportProductUI.this, "<html>入库成功 <br>Import product success");
							model.clearTable();
							
						}
					}
					
						
				}catch(Exception ee){
					System.out.println("exception work");
					JOptionPane.showMessageDialog(ImportProductUI.this, "<html>入库失败 <br>Fail to import product");
				}
			}
		});
		
	}

	public JTable getTable(){
		return table;
	}
	
	
	class ImportMouseListener implements MouseListener{
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			table = (JTable)e.getSource();
			Point p = e.getPoint();
			int row = table.rowAtPoint(p);
			if(e.getClickCount() == 2 && table.getSelectedRow() != -1){
				ImportProductHolder importHolder = tableModel.getImportProductAt(row);
				JDialog importDialog;
				try {
					importDialog = new ImportDetail("Detail",Dialog.ModalityType.APPLICATION_MODAL,userName,ImportProductUI.this,importHolder,row);
					importDialog.setVisible(true);
					importDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					importDialog.setFocusable(true);
					System.out.println(importHolder.toString());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub	
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub	
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub	
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub	
		}

		
	}
}
