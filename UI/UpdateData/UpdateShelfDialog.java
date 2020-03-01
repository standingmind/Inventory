package UI.UpdateData;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import DBUtils.ShelfDao;
import HolderClass.ShelfHolder;
import TableModel.ShelfTableModel;
import net.miginfocom.swing.MigLayout;

public class UpdateShelfDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;
	private ArrayList<ShelfHolder> shelfList;
	private ArrayList<ShelfHolder> originalShelfList;
	private ShelfTableModel shelfTableModel;
	private String[] columnNames;
	private JPanel toolPane;
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnDelete;
	private JToggleButton btnEdit;
	private JButton btnGoBack;
	private JPanel westPanel;
	private JLabel lblName2;
	private JTextField tfName2;
	private JLabel lblRemark2;
	private JTextField tfRemark2;
	private JButton btnAdd;
	private JLabel lblEmptyMessage;
	private Font font12 = new Font("宋体", Font.PLAIN, 12);
	private JTextField tfTableEditor ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateShelfDialog dialog = new UpdateShelfDialog("ShelfDialog",Dialog.ModalityType.APPLICATION_MODAL);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateShelfDialog(String title,Dialog.ModalityType mt) {
		super();
		this.setTitle(title);
		this.setModalityType(mt);

		//change option pane font
		UIManager.put("OptionPane.messageFont", font12);
		UIManager.put("OptionPane.buttonFont", font12);
		
		ArrayList<ShelfHolder> list = getData();
		shelfList = new ArrayList<ShelfHolder>(list);
		originalShelfList = new ArrayList<ShelfHolder>(list);
		setBounds(200, 100, 840, 520);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
	
		table = new JTable();
		table.setFont(font12);
		table.getTableHeader().setFont(font12);
		shelfList = getData();	//get shelf Data
		columnNames  = new String[] {
				"No.", "\u540D\u79F0(Name)", "\u5907\u6CE8(Remark)"
			};
		shelfTableModel = new ShelfTableModel(shelfList,columnNames);	//create Shelf model
		table.setModel(shelfTableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tfTableEditor = new JTextField();	//for table editor
		tfTableEditor.setBorder(BorderFactory.createEmptyBorder());
		tfTableEditor.setFont(font12);
		DefaultCellEditor dce = new DefaultCellEditor(tfTableEditor);
		
		table.getColumnModel().getColumn(1).setCellEditor(dce);
		table.getColumnModel().getColumn(2).setCellEditor(dce);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(252);
		scrollPane.setViewportView(table);
		
			buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			
			okButton = new JButton("OK");
			buttonPane.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
			
			
			cancelButton = new JButton("Cancel");
			buttonPane.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
			
			cancelButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					shelfTableModel.clearSavedStmt();
					dispose();
				}
			});
			
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.isEditing())
					    table.getCellEditor().stopCellEditing();
					System.out.println(shelfList.toString());
					System.out.println(originalShelfList.toString());
					int msg = JOptionPane.showConfirmDialog(UpdateShelfDialog.this, "确定要更改吗?", "Confirm Dialog",JOptionPane.OK_CANCEL_OPTION);
					if(msg == JOptionPane.YES_OPTION){
						if(!isDataSame(shelfList,originalShelfList)){
							int rowsAffected = shelfTableModel.executeBatch();
							System.out.println(rowsAffected+" rows affected.");
						}else{
							System.out.print("data same no query executed");
						}
						shelfTableModel.clearSavedStmt();
						dispose();
					}
					
					
				}
			});
			
			toolPane = new JPanel();
			toolPane.setAutoscrolls(true);
			getContentPane().add(toolPane, BorderLayout.EAST);
			toolPane.setLayout(new BoxLayout(toolPane, BoxLayout.Y_AXIS));
			
			 btnEdit = new JToggleButton("\r\n");
			 btnEdit.setPressedIcon(null);
			btnEdit.setToolTipText("<html>\u7F16\u8F91<br>Edit</html>\r\n");
			btnEdit.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\edit2.png"));
			btnEdit.setMargin(new Insets(6, 7, 6, 7));
		
			
			btnGoBack = new JButton("");
			btnGoBack.setToolTipText("<html>\u6062\u590D<br>Go Back</html>\r\n\r\n");
			btnGoBack.setMargin(new Insets(6, 9, 6, 9));
			btnGoBack.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\go_back2.png"));
			
			
			
//			
//			 btnDelete = new JButton("-");
//			btnDelete.setToolTipText("<html>\u518C\u9664<br>Delete</html>");
//			
//		
//			btnDelete.addActionListener(this);
			btnGoBack.addActionListener(this);
			btnEdit.addActionListener(this);
			
			toolPane.add(btnGoBack);
			toolPane.add(btnEdit);
//			toolPane.add(btnDelete);
			
			westPanel = new JPanel();
			westPanel.setBorder(null);
			westPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			getContentPane().add(westPanel, BorderLayout.WEST);
			westPanel.setLayout(new MigLayout("", "[][162.00,grow][]", "[][][][][][]"));
			
			lblName2 = new JLabel("\u540D\u79F0(Name)");
			lblName2.setFont(font12);
			westPanel.add(lblName2, "cell 0 2,alignx trailing");
			
			tfName2 = new JTextField();
			tfName2.setFont(font12);
			westPanel.add(tfName2, "cell 1 2,growx");
			tfName2.setColumns(10);
			
			lblRemark2 = new JLabel("\u5907\u6CE8(Remark)");
			lblRemark2.setFont(font12);
			westPanel.add(lblRemark2, "cell 0 3,alignx trailing");
			
			tfRemark2 = new JTextField();
			tfRemark2.setFont(font12);
			westPanel.add(tfRemark2, "cell 1 3,growx");
			tfRemark2.setColumns(10);
			
			btnAdd = new JButton(">>");
			westPanel.add(btnAdd, "cell 2 3");
			btnAdd.addActionListener(this);
			
			lblEmptyMessage = new JLabel("\r\n");
			lblEmptyMessage.setFont(font12);
			westPanel.add(lblEmptyMessage, "cell 1 5,growx");
	}
	
	public ArrayList<ShelfHolder> getData(){
		return ShelfDao.getAll();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// add Button
		if(e.getSource() == btnAdd ){
			System.out.println("add row");
			String name = tfName2.getText();
			String remark = tfRemark2.getText();
			if(!name.equals("")){
				if(!isNameSame(shelfList,name)){
					//get today date with timestamp
					Calendar calendar = Calendar.getInstance();
					Timestamp t = new Timestamp(calendar.getTimeInMillis());
					shelfTableModel.addRow(new ShelfHolder(0,name,remark,t));
					//reset textfield
					tfName2.setText("");
					tfRemark2.setText("");
				}else{
					showMessage("名称已存在");
				}
			
			}else{
				showMessage("名称 不可为空");
			}
		}		
			
//		}else if(e.getSource() == btnDelete){
//			int row = table.getSelectedRow();
//			if(row>=0){
//				System.out.println("row :"+row);
//				ShelfHolder holder = shelfTableModel.getShelfAt(row);
//				String name = holder.getShelfName();
//				String remark = holder.getShelfRemark();
//				if(name == null && holder == null){
//					shelfTableModel.removeRow(row);
//				}else{
//					int ans = JOptionPane.showConfirmDialog(null, "Do you want to delete", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
//					if(ans == JOptionPane.YES_OPTION){
//						shelfTableModel.removeRow(row);
//					}
//				}
//			}
//		}
		else if(e.getSource() == btnEdit){
			if(shelfTableModel.getEditable()){
				btnEdit.setBackground(UIManager.getColor("TextField.foreground"));
				shelfTableModel.setEditable(false);
				//if table is editing autosave
				if (table.isEditing())
				    table.getCellEditor().stopCellEditing();
			}
				
			else{
				btnEdit.setBackground(Color.LIGHT_GRAY);
				shelfTableModel.setEditable(true);
			}
				
		}else if(e.getSource() == btnGoBack){
			shelfTableModel.setData(new ArrayList<ShelfHolder>(originalShelfList));
			shelfTableModel.clearSavedStmt();
		}
		
		
	}
	
	
	public String[] getComboData(){
		String[] name = new String[shelfList.size()];
		for(int i=0 ; i<name.length ; i++){
			name[i] = shelfList.get(i).getShelfName();
		}
		
		return name;
	}
	
	public void saveData(ArrayList<ShelfHolder> shelfList){
		
	}
	
	public boolean isNameSame(ArrayList<ShelfHolder> allData,String name){
		for(ShelfHolder shelfHolder:allData){
			if(shelfHolder.getShelfName().trim().equals(name.trim())){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	public boolean isDataSame(ArrayList<ShelfHolder> list1, ArrayList<ShelfHolder> list2){
		if(list1.size() != list2.size())
			return false;
		System.out.println("size 1:"+list1.size());
		System.out.println("size 2:"+list2.size());
		for(int i=0 ; i<list1.size(); i++){
			ShelfHolder h1 = list1.get(i);
			ShelfHolder h2 = list2.get(i);
//			System.out.println(h1.toString());
//			System.out.println(h2.toString());
//			System.out.println("");
			if(h1.getShelfId() != h2.getShelfId()){
				return false;
			}else if(!h1.getShelfName().trim().equals((h2.getShelfName()).trim())){
				return false;
			}else if(h1.getShelfRemark()!=null && h2.getShelfRemark()!=null){
				if(!h1.getShelfRemark().trim().equals((h2.getShelfRemark()).trim()))
					return false;
			}else if(h1.getShelfRemark()==null && h2.getShelfRemark()!=null){
				return false;
			}else if(h1.getShelfRemark()!=null && h2.getShelfRemark()==null){
				return false;
			}
		}
		return true;
	}
	
	
	public void showMessage(String message){
		lblEmptyMessage.setText(message);
		lblEmptyMessage.setForeground(Color.red);
		Timer timer = new Timer(2000, e -> lblEmptyMessage.setText(""));
		timer.setRepeats(false);
		timer.start();
	}

}
