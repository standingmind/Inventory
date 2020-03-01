package UI.UpdateData;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.text.PlainDocument;

import com.alee.laf.button.WebButton;

import DBUtils.ClassDao;
import DBUtils.ProductDao;
import DBUtils.UnitDao;
import DBUtils.VendorDao;
import HolderClass.ProductHolder;
import TableCellRenderer.MyanmarFontTableCellRenderer;
import TableCellRenderer.PriceTableCellRenderer;
import TableModel.ProductTableModel;
import Utility.ImageFileChooser;
import Utility.ImageUtility;
import Utility.ScalableImageViewer;
import Utility.TextFieldFilter;
import net.miginfocom.swing.MigLayout;

public class UpdateProductDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;
	private ArrayList<ProductHolder> productList;
	private ArrayList<ProductHolder> originalProductList;
	private ProductTableModel productTableModel;
	private String[] columnNames;
	private JPanel toolPane;
	private JPanel buttonPane;
	private JPanel westPanel;
	
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnDelete;
	private JToggleButton btnEdit;
	private JButton btnGoBack;
	private JButton btnAdd;
	private JButton btnAddUnit;
	private JButton btnAddClass;
	private WebButton btnAddImage;
	private JButton btnAddVendor;
	
	private JLabel lblEmptyMessage;
	private JLabel lblCode;
	private JLabel lblName;
	private JLabel lblMyanmarName;
	private JLabel lblSpec;
	private JLabel lblPrice;
	private JLabel lblUnit;
	private JLabel lblClass;
	private JLabel lblPersonInCharge;
	private JLabel lblImage;
	private JLabel lblVendor;
	private JLabel lblRemark;
	
	private JTextField tfCode;
	private JTextField tfName;
	private JTextField tfMyanmarName;
	private JTextField tfPrice;
	
	private JScrollPane specScrollPane;
	private JScrollPane remarkScrollPane;

	private JComboBox comboUnit;
	private JComboBox comboClass;
	private JComboBox comboPersonInCharge;
	private Font font12 = new Font("宋体", Font.PLAIN, 12);
	private Font zawGyiFont = new Font("Zawgyi-One",Font.PLAIN,12);
	private JTable table_1;
	private JTextArea txtAreaSpec;
	private JTextArea txtAreaRemark;
	private JComboBox comboVendor;
	
	private String currentImagePath="";
	private static final String IMAGE_DIR ="../Images/ProductImages/";
	private String finalImagePath="";
	private String imageUniqueName ="";
	private BufferedImage imageBuffer;
	private static final int HEADER_HEIGHT = 50;
	private JTextField tfTableEditor ; 
	private JTextField tfTableEditor1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateProductDialog dialog = new UpdateProductDialog("ProductDialog",Dialog.ModalityType.APPLICATION_MODAL);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateProductDialog(String title,Dialog.ModalityType mt) {
		super();
		this.setTitle(title);
		this.setModalityType(mt);
		

		//change option pane font
		UIManager.put("OptionPane.messageFont", font12);
		UIManager.put("OptionPane.buttonFont", font12);
		
		ArrayList<ProductHolder> list = getData();
		productList = new ArrayList<ProductHolder>(list);
		originalProductList = new ArrayList<ProductHolder>(list);
		setBounds(100, 30, 1200, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
	
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(font12);
	
		
		table.setTableHeader(new JTableHeader(table.getColumnModel()) {
			  @Override public Dimension getPreferredSize() {
			    Dimension d = super.getPreferredSize();
			    d.height = HEADER_HEIGHT;
			    return d;
			  }
			});
		table.getTableHeader().setFont(font12);
		

		productList = getData();	//get product Data
		columnNames  = new String[] {
				"No", "<html>Code<br>\u6599\u53F7</html>", "<html>Name<br>\u6599\u540D</html>","<html>MM Name<br>缅文名</html>", "<html>Spec.<br>\u89C4\u683C</html>", "<html>Photo<br>\u56FE\u7247</html>", "<html>Class<br>\u5206\u7C7B</html>", "<html>Price<br>\u5355\u4EF7</html>", "<html>Unit<br>\u5355\u4F4D</html>", "<html>Vendor<br>\u5382\u5546</html>", "<html>PersonInCharge<br>\u5F53\u62C5\u4EBA</html>"
			};
		productTableModel = new ProductTableModel(productList,columnNames);	//create Product model
		productTableModel.setEditable(true);
		table.setModel(productTableModel);
		//table.setDefaultEditor(JComboBox.class, new ProductTableCellEditor());
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tfTableEditor = new JTextField();	//for table editor
		tfTableEditor1 = new JTextField();
		tfTableEditor.setBorder(BorderFactory.createEmptyBorder());
		tfTableEditor1.setBorder(BorderFactory.createEmptyBorder());
		tfTableEditor.setFont(font12);
		tfTableEditor1.setFont(zawGyiFont);
		DefaultCellEditor dce = new DefaultCellEditor(tfTableEditor);
		DefaultCellEditor dce1 = new DefaultCellEditor(tfTableEditor1);
		
		table.getColumnModel().getColumn(2).setCellEditor(dce);
		table.getColumnModel().getColumn(3).setCellEditor(dce1);
		table.getColumnModel().getColumn(4).setCellEditor(dce);
		table.getColumnModel().getColumn(6).setCellEditor(dce);
		table.getColumnModel().getColumn(7).setCellEditor(dce);
		table.getColumnModel().getColumn(8).setCellEditor(dce);
		table.getColumnModel().getColumn(9).setCellEditor(dce);
		table.getColumnModel().getColumn(10).setCellEditor(dce);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(34);
		table.getColumnModel().getColumn(1).setMinWidth(60);
		table.getColumnModel().getColumn(2).setMinWidth(150);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(200);
		table.getColumnModel().getColumn(5).setMinWidth(50);
		table.getColumnModel().getColumn(6).setMinWidth(20);
		table.getColumnModel().getColumn(7).setMinWidth(20);
		table.getColumnModel().getColumn(8).setMinWidth(20);
		table.getColumnModel().getColumn(9).setMinWidth(20);
		table.getColumnModel().getColumn(10).setMinWidth(20);
		//set column Font
		
		table.getColumnModel().getColumn(3).setCellRenderer(new MyanmarFontTableCellRenderer());
		table.getColumnModel().getColumn(7).setCellRenderer(new PriceTableCellRenderer());
		
		
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
					productTableModel.clearSavedStmt();
					dispose();
				}
			});
			
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.isEditing())
					    table.getCellEditor().stopCellEditing();
//					for(int i=0 ; i<productList.size() ; i++){
//						System.out.println(productList.get(i).toString());
//					}
					System.out.println("size 1:"+productList.size());
					System.out.println("size 2:"+originalProductList.size());
					//System.out.println(originalProductList.toString());
					
					int msg = JOptionPane.showConfirmDialog(UpdateProductDialog.this, "确定要更改吗?", "Confirm Dialog",JOptionPane.OK_CANCEL_OPTION);
					if(msg == JOptionPane.YES_OPTION){
						if(!isDataSame(productList,originalProductList)){
							int rowsAffected = productTableModel.executeBatch();
							System.out.println(rowsAffected+" rows affected.");
						}else{
							System.out.print("data same no query executed");
						}
						productTableModel.clearSavedStmt();
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
			westPanel.setLayout(new MigLayout("", "[][72.00][46.00][34.00][27.00][][33.00][-14.00]", "[][][][][][][][][baseline][][][][][34.00][]"));
			
					
			lblImage = new JLabel("");
			lblImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblImage.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			lblImage.setBackground(Color.WHITE);
			westPanel.add(lblImage, "cell 1 0 2 4");
			
		
			btnAddImage = new WebButton("add Image");
			btnAddImage.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\add.png"));
			btnAddImage.setText("");
			btnAddImage.setRolloverShadeOnly(true);
			btnAddImage.setRolloverDecoratedOnly(true);
			btnAddImage.setDrawFocus(false);
			westPanel.add(btnAddImage, "cell 3 0,alignx left");
			
			lblCode = new JLabel("<html> \u54C1\u756A <br> Product Code</html>");
			lblCode.setHorizontalAlignment(SwingConstants.LEFT);
			lblCode.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(lblCode, "cell 0 4,alignx trailing");
			
			tfCode = new JTextField();
			tfCode.setFont(new Font("宋体", Font.PLAIN, 14));
			tfCode.setColumns(10);
			westPanel.add(tfCode, "cell 1 4 2 1,growx");
			
			lblName = new JLabel("<html> \u54C1\u540D <br> Product Name</html>");
			lblName.setHorizontalAlignment(SwingConstants.LEFT);
			lblName.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(lblName, "cell 0 5,alignx trailing");
			
			tfName = new JTextField();
			tfName.setFont(new Font("宋体", Font.PLAIN, 14));
			tfName.setColumns(10);
			westPanel.add(tfName, "cell 1 5 2 1,growx");
			
			lblMyanmarName = new JLabel("<html> \u7F05\u6587\u54C1\u540D <br> Myanmar Name</html>");
			lblMyanmarName.setHorizontalAlignment(SwingConstants.LEFT);
			lblMyanmarName.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(lblMyanmarName, "cell 0 6,alignx right");
			
			tfMyanmarName = new JTextField();
			tfMyanmarName.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			tfMyanmarName.setColumns(10);
			westPanel.add(tfMyanmarName, "cell 1 6 2 1,growx");
			
			lblSpec = new JLabel("<html> \u89C4\u683C <br> Spec. </html>");
			lblSpec.setHorizontalAlignment(SwingConstants.LEFT);
			lblSpec.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(lblSpec, "cell 0 7,alignx trailing");
			
			specScrollPane = new JScrollPane();
			westPanel.add(specScrollPane, "cell 1 7 3 2,grow");
			
			txtAreaSpec = new JTextArea();
			specScrollPane.setViewportView(txtAreaSpec);
			
			btnAdd = new JButton(">>");
			westPanel.add(btnAdd, "cell 6 8,growx");
			btnAdd.addActionListener(this);
			
			lblClass = new JLabel("<html> \u5206\u7C7B <br> Class </html>");
			lblClass.setHorizontalAlignment(SwingConstants.LEFT);
			lblClass.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(lblClass, "cell 0 9,alignx trailing");
			
			comboClass = new JComboBox();
			comboClass.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(comboClass, "cell 1 9,growx");
			
			btnAddClass = new JButton("+");
			westPanel.add(btnAddClass, "cell 2 9");
			
			lblPrice = new JLabel("<html>\u5355\u4EF7 <br> Price</html>");
			lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
			lblPrice.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(lblPrice, "cell 0 10,alignx trailing");
			
			tfPrice = new JTextField();
			PlainDocument pd = (PlainDocument)tfPrice.getDocument();
			pd.setDocumentFilter(new TextFieldFilter(TextFieldFilter.DOUBLE_FILTER));
			tfPrice.setFont(new Font("宋体", Font.PLAIN, 14));
			tfPrice.setColumns(10);
			westPanel.add(tfPrice, "cell 1 10,growx");
			
			lblUnit = new JLabel("<html>\u5355\u4F4D<br> Unit</html>");
			lblUnit.setHorizontalAlignment(SwingConstants.LEFT);
			lblUnit.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(lblUnit, "cell 2 10,alignx trailing");
			
			comboUnit = new JComboBox();
			comboUnit.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(comboUnit, "flowx,cell 3 10 2 1,growx");
			
			btnAddUnit = new JButton("+");
			westPanel.add(btnAddUnit, "cell 5 10");
			
			btnAddUnit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					UpdateUnitDialog unitDialog = new UpdateUnitDialog("单位",Dialog.ModalityType.APPLICATION_MODAL);
					unitDialog.setVisible(true);
					unitDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					unitDialog.setBounds(100,100,300,300);
					
					comboUnit.removeAllItems();
					try {
						comboUnit.setModel(new DefaultComboBoxModel(UnitDao.getName()));
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			lblPersonInCharge = new JLabel("<html> \u62C5\u5F53\u4EBA<br> PersonInCharge </html>");
			lblPersonInCharge.setHorizontalAlignment(SwingConstants.LEFT);
			lblPersonInCharge.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(lblPersonInCharge, "cell 0 11,alignx trailing");
			
			comboPersonInCharge = new JComboBox();
			comboPersonInCharge.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(comboPersonInCharge, "cell 1 11,growx");
			
			lblVendor = new JLabel("<html> \u5382\u5546 <br> Vendor </html>");
			lblVendor.setHorizontalAlignment(SwingConstants.LEFT);
			lblVendor.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(lblVendor, "cell 2 11,alignx trailing");
			
			comboVendor = new JComboBox();
			comboVendor.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(comboVendor, "cell 3 11 2 1");
			
			btnAddVendor = new JButton("+");
			westPanel.add(btnAddVendor, "cell 5 11");
			
			
			btnAddVendor.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					UpdateVendorDialog vendorDialog = new UpdateVendorDialog("厂商",Dialog.ModalityType.APPLICATION_MODAL);
					vendorDialog.setVisible(true);
					vendorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					vendorDialog.setBounds(100,100,300,300);
					
					comboVendor.removeAllItems();
					try {
						comboVendor.setModel(new DefaultComboBoxModel(VendorDao.getNames()));
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			lblRemark = new JLabel("<html> \u5907\u6CE8 <br> Remark </html>");
			lblRemark.setHorizontalAlignment(SwingConstants.LEFT);
			lblRemark.setFont(new Font("宋体", Font.PLAIN, 14));
			westPanel.add(lblRemark, "cell 0 12,alignx trailing");
			
			remarkScrollPane = new JScrollPane();
			westPanel.add(remarkScrollPane, "cell 1 12 3 2,grow");
			
			txtAreaRemark = new JTextArea();
			remarkScrollPane.setViewportView(txtAreaRemark);
			
			lblEmptyMessage = new JLabel("hello world\n");
			lblEmptyMessage.setFont(font12);
			westPanel.add(lblEmptyMessage, "cell 1 14 4 1,growx");
			
			initComboData();
			initImage();
			
			//add action Listener
			//btn add actionListener
			btnAddClass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UpdateClassDialog classDialog = new UpdateClassDialog("分类",Dialog.ModalityType.APPLICATION_MODAL);
					classDialog.setVisible(true);
					classDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					classDialog.setBounds(100,100,300,300);
					
					comboClass.removeAllItems();
					try {
						comboClass.setModel(new DefaultComboBoxModel(ClassDao.getNames()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			btnAddImage.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					ImageFileChooser imageChooser = new ImageFileChooser();
					String fileName = imageChooser.getFileName();
					currentImagePath = imageChooser.getAbsolutPath();
					System.out.println("CurrentImagePath : "+currentImagePath);
					System.out.println("fileName :"+fileName);
					if( !fileName.equals("")){
						if(imageChooser.isImage(fileName)){
							imageBuffer = ImageUtility.getBufferedImage(currentImagePath);
							lblImage.setIcon(new ImageIcon(ImageUtility.resizeImageBuffer(imageBuffer)));
							imageUniqueName = imageChooser.getUniqueName(fileName);
							finalImagePath= IMAGE_DIR+imageUniqueName;
							System.out.println("image path:"+finalImagePath);
						}else{
							JOptionPane.showMessageDialog(UpdateProductDialog.this, "Selected File is not an image");
						}
					}
					//add image to label
					
					};
			});
			
			lblImage.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					System.out.println("mouse clicked");
					Image img = ImageUtility.getOriginalImage(imageBuffer);
					ScalableImageViewer imgViewer = new ScalableImageViewer("Image Detail",Dialog.ModalityType.APPLICATION_MODAL,img);
					imgViewer.setVisible(true);
					imgViewer.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					imgViewer.setFocusable(true);
				}
			});
			
			btnAdd.addActionListener(e ->{
				System.out.println("add row");
				String code = tfCode.getText();
				String name = tfName.getText();
				String myanmarName = tfMyanmarName.getText();
				String spec = txtAreaSpec.getText();
				String productClass = (String)comboClass.getSelectedItem();
				String price = tfPrice.getText();
				String unit = (String)comboUnit.getSelectedItem();
				String personInCharge = (String)comboPersonInCharge.getSelectedItem();
				String remark = txtAreaRemark.getText();
				String vendor = (String)comboVendor.getSelectedItem();
				
				if(!code.equals("") && !spec.equals("") ){
					if(!name.equals("") || !myanmarName.equals("")){
						if(!productClass.equals("无") && !unit.equals("无")){
							if(!isCodeSame(productList,code)){
								//save image first 
								if(saveImage(finalImagePath,imageBuffer)){
									Calendar calendar = Calendar.getInstance();
									Timestamp t = new Timestamp(calendar.getTimeInMillis());
									ProductHolder productHolder = new ProductHolder();
									productHolder.setProductCode(code);
									productHolder.setProductName(name);
									productHolder.setProductMyanmarName(myanmarName);
									productHolder.setProductSpec(spec);
									productHolder.setProductClass(productClass);
									productHolder.setProductPrice(price);
									productHolder.setProductUnit(unit);
									productHolder.setProductPersonInCharge(personInCharge);
									productHolder.setProductRemark(remark);
									productHolder.setProductDate(t);
									productHolder.setProductPhoto(finalImagePath);
									productHolder.setProductVendor(vendor);
									int ans =productTableModel.addRow(productHolder);
									if(ans == -1){
										showMessage("<html>无法<br>Internal Error !Cannot Add Product</html>");
									}
									
								}else{
									showMessage("<html>图片上传失败！请重命名图片！<br>Image upload failed!Try rename your Image!</html>");
								}
								
							}else{
								showMessage("<html>料号已存在！<br>ProductCode already exist!</html>");
							}
						}else{
							showMessage("<html>分类 与 单位不可为无！<br>Class and Unit cannot be set to empty!</html>");
						}
					}else{
						showMessage("<html>品名 与 缅文不可为空！<br>Product Name and Myanmar Name cannot be empty!</html>");
					}
				}else{
					showMessage("<html>料号 与 规格不可为空！ <br>Code and Specification cannot be empty!</html>");
				}
			});

//			if(!isNameSame(productList,name)){
//				//get today date with timestamp
//				Calendar calendar = Calendar.getInstance();
//				Timestamp t = new Timestamp(calendar.getTimeInMillis());
//				//productTableModel.addRow(new ProductHolder(0,name,Integer.parseInt(day),t,remark));
//				//reset textfield
//				tfName2.setText("");
//				tfRemark2.setText("");
//				tfDay.setText("");
//			}else{
//				showMessage("名称已存在");
//			}
			
	}
	
	public ArrayList<ProductHolder> getData(){
		return ProductDao.getAll();
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		// add Button
//		if(e.getSource() == btnAdd ){
//			System.out.println("add row");
//			String name = tfName2.getText();
//			String day = tfDay.getText();
//			String remark = tfRemark2.getText();
//			
//			if(!name.equals("") && !day.equals("")){
//				
//				if(!isNameSame(productList,name)){
//					//get today date with timestamp
//					Calendar calendar = Calendar.getInstance();
//					Timestamp t = new Timestamp(calendar.getTimeInMillis());
//					//productTableModel.addRow(new ProductHolder(0,name,Integer.parseInt(day),t,remark));
//					//reset textfield
//					tfName2.setText("");
//					tfRemark2.setText("");
//					tfDay.setText("");
//				}else{
//					showMessage("名称已存在");
//				}
//				
//			}else{
//				showMessage("名称 与 日期不可为空");
//			}
//		}		
			
//		}else if(e.getSource() == btnDelete){
//			int row = table.getSelectedRow();
//			if(row>=0){
//				System.out.println("row :"+row);
//				ProductHolder holder = productTableModel.getProductAt(row);
//				String name = holder.getProductName();
//				String remark = holder.getProductRemark();
//				if(name == null && holder == null){
//					productTableModel.removeRow(row);
//				}else{
//					int ans = JOptionPane.showConfirmDialog(null, "Do you want to delete", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
//					if(ans == JOptionPane.YES_OPTION){
//						productTableModel.removeRow(row);
//					}
//				}
//			}
//		}
//		else if(e.getSource() == btnEdit){
//			if(productTableModel.getEditable()){
//				btnEdit.setBackground(UIManager.getColor("TextField.foreground"));
//				productTableModel.setEditable(false);
//				//if table is editing autosave
//				if (table.isEditing()){
//					table.getCellEditor().stopCellEditing();
//					System.out.println("stoppped editing..");
//				}
//				    
//			}
//				
//			else{
//				btnEdit.setBackground(Color.LIGHT_GRAY);
//				productTableModel.setEditable(true);
//				System.out.println("enabled editing..");
//			}
//				
//		}else if(e.getSource() == btnGoBack){
//			productTableModel.setData(new ArrayList<ProductHolder>(originalProductList));
//			productTableModel.clearSavedStmt();
//		}
//		
		
//	}
	
	public void initComboData(){
		comboClass.setModel(new DefaultComboBoxModel(ClassDao.getNames()));
		comboUnit.setModel(new DefaultComboBoxModel(UnitDao.getName()));
		comboPersonInCharge.setModel(new DefaultComboBoxModel(ProductDao.getPersonInCharge()));
		comboVendor.setModel(new DefaultComboBoxModel(VendorDao.getNames()));
	}
	public void initImage(){
		imageBuffer = ImageUtility.getBufferedImage(currentImagePath);
		lblImage.setIcon(new ImageIcon(ImageUtility.resizeImageBuffer(imageBuffer)));
	}
	
	public boolean saveImage(String finalImagePath,BufferedImage img){
		boolean ans = false;
		File file = new File(finalImagePath);
		String ext = ImageUtility.getExtension(finalImagePath);
		
		try {
			ans = ImageIO.write(img, ext, file);
			System.out.println("successfully save file.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;
	}
	
	public String[] getComboData(){
		String[] name = new String[productList.size()];
		for(int i=0 ; i<name.length ; i++){
			name[i] = productList.get(i).getProductName();
		}
		
		return name;
	}
	
	public void saveData(ArrayList<ProductHolder> productList){
		
	}
	
	public boolean isNameSame(ArrayList<ProductHolder> allData,String name){
		for(ProductHolder productHolder:allData){
			if(productHolder.getProductName().trim().equals(name.trim())){
				return true;
			}
		}
		return false;
	}
	
	public boolean isCodeSame(ArrayList<ProductHolder> allData,String code){
		for(ProductHolder h:allData){
			if(h.getProductCode().trim().equals(code.trim()))
				return true;
		}
		return false;
	}
	
	public boolean isDataSame(ArrayList<ProductHolder> list1, ArrayList<ProductHolder> list2){
		if(list1.size() != list2.size())
			return false;
		System.out.println("size 1:"+list1.size());
		System.out.println("size 2:"+list2.size());
		for(int i=0 ; i<list1.size(); i++){
			ProductHolder h1 = list1.get(i);
			ProductHolder h2 = list2.get(i);
//			System.out.println(h1.toString());
//			System.out.println(h2.toString());
//			System.out.println("");
			if(h1.getProductId() != h2.getProductId()){
				return false;
			}
			
			else if(!h1.getProductName().trim().equals((h2.getProductName()).trim())){
				return false;
			}else if(h1.getProductRemark()!=null && h2.getProductRemark()!=null){
				if(!h1.getProductRemark().trim().equals((h2.getProductRemark()).trim()))
					return false;
			}else if(h1.getProductRemark()==null && h2.getProductRemark()!=null){
				return false;
			}else if(h1.getProductRemark()!=null && h2.getProductRemark()==null){
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



}
