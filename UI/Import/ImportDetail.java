package UI.Import;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.PlainDocument;

import com.alee.extended.date.WebDateField;

import DBUtils.ArrivalLocationDao;
import DBUtils.ClassDao;
import DBUtils.CodeDao;
import DBUtils.CycleDao;
import DBUtils.ProductDao;
import DBUtils.ShelfDao;
import DBUtils.ShelfSeatDao;
import DBUtils.StockDao;
import DBUtils.StockShelfDao;
import DBUtils.VendorDao;
import HolderClass.CodeHolder;
import HolderClass.ImportProductHolder;
import HolderClass.ProductHolder;
import HolderClass.StockHolder;
import HolderClass.StockShelfHolder;
import TableModel.ImportTableModel;
import UI.UpdateData.UpdateArrivalLocationDialog;
import UI.UpdateData.UpdateCycleDialog;
import UI.UpdateData.UpdateProductDialog;
import UI.UpdateData.UpdateShelfDialog;
import UI.UpdateData.UpdateShelfSeatDialog;
import Utility.ImageUtility;
import Utility.ScalableImageViewer;
import Utility.TextFieldFilter;
import Utility.TextFieldFilterForDosage;
import Utility.TextFieldFilterForLeadTime;
import Utility.TextFieldFilterForPremeasure;
import Utility.TextFieldFilterWithResponsiveChange;

public class ImportDetail extends JDialog implements FocusListener{

	private JPanel contentPane;
	private JTextField tfCode;
	private JTextField tfName;
	private JTextField tfStockQty;
	private JTextField tfImportQty;
	private JTextField tfPrice;
	private JTextField tfDosage;
	private JTextField tfLeadTime;
	private JTextField tfTotal;
	private JTextField tfSafeStock;
	private JTextArea txtAreaSpec;
	private JTextArea txtAreaRemark;
	private JComboBox comboCycle;
	private JComboBox comboShelf;
	private JComboBox comboSeat;
	private JComboBox comboArriveLocation;
	private JLabel lblImageLabel;
	private JButton btnAddShelf;
	private JButton btnAddSeat;
	private JButton btnAddArrivalLocation;
	private JButton btnAddCycle;
	//get Code
	private String[] codeList;
	private CodeHolder codeHolder;
	private ArrayList<ProductHolder> productList;
	private int stockQty;
	
	private WebDateField webDate;
	
	private LookAndFeel currentLaf;
	private Font font14 = new Font("宋体", Font.PLAIN, 14);
	private Font font12 = new Font("宋体", Font.PLAIN, 12);
//	private Font fontBold = new Font("宋体" , Font.BOLD , 16);
	private Font zawGyiFont = new Font("Zawgyi-One",Font.PLAIN,14);
	
	private boolean isProductEditable = true;
	private boolean isStockEditable = true;
	private JTextField tfImportPerson;
	
	private ProductHolder holder;
	private BufferedImage imageFile;
	private String imageLocation="";
	private JTextField tfMyanmarName;
	private JTextField tfUnit;
	private JTextField tfClass;
	private JTextField tfVendor;
	private boolean isExistInStock;
	private JTextField tfPremeasure;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public ImportDetail(String title,Dialog.ModalityType mt,String importPerson,ImportProductUI parent,ImportProductHolder inputHolder,int row) throws Exception {
		
		currentLaf = UIManager.getLookAndFeel();
		this.setModalityType(mt);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 10, 1084, 721);
		setTitle("管理员页面");
		contentPane = new JPanel();
		contentPane.setToolTipText("\u7BA1\u7406\u5458\u9875\u9762 ");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDate = new JLabel("<html> \u65E5\u671F <br> Date</html>");
		lblDate.setFont(font14);
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setBounds(862, 0, 36, 44);
		contentPane.add(lblDate);
		
		JLabel lblProductCode = new JLabel("<html> \u54C1\u756A <br> Product Code</html>");
		lblProductCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblProductCode.setFont(font14);
		lblProductCode.setBounds(67, 41, 123, 58);
		contentPane.add(lblProductCode);
		
		JLabel lblProductName = new JLabel("<html> \u54C1\u540D <br> Product Name</html>");
		lblProductName.setHorizontalAlignment(SwingConstants.LEFT);
		lblProductName.setFont(font14);
		lblProductName.setBounds(67, 95, 90, 44);
		contentPane.add(lblProductName);
		
		JLabel lblVendor = new JLabel("<html> \u5382\u5546 <br> Vendor </html>");
		lblVendor.setHorizontalAlignment(SwingConstants.LEFT);
		lblVendor.setFont(font14);
		lblVendor.setBounds(787, 338, 51, 44);
		contentPane.add(lblVendor);
		
		JLabel lblClass = new JLabel("<html> \u5206\u7C7B <br> Class </html>");
		lblClass.setHorizontalAlignment(SwingConstants.LEFT);
		lblClass.setFont(font14);
		lblClass.setBounds(586, 345, 59, 43);
		contentPane.add(lblClass);
		
		JLabel lblStock = new JLabel("<html> \u5728\u5E93\u6570 <br> Stock </html>");
		lblStock.setHorizontalAlignment(SwingConstants.LEFT);
		lblStock.setFont(font14);
		lblStock.setBounds(67, 393, 59, 43);
		contentPane.add(lblStock);
		
		JLabel lblCycle = new JLabel("<html> \u5468\u671F <br> Cycle</html>");
		lblCycle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCycle.setFont(font14);
		lblCycle.setBounds(787, 461, 51, 49);
		contentPane.add(lblCycle);
		
		JLabel lblInput = new JLabel("<html> \u5165\u5E93\u6570 <br> Input </html>");
		lblInput.setHorizontalAlignment(SwingConstants.LEFT);
		lblInput.setFont(font14);
		lblInput.setBounds(67, 339, 59, 43);
		contentPane.add(lblInput);
		
		JLabel lblDosage = new JLabel("<html> \u4F7F\u7528\u91CF\t <br> Dosage</html>");
		lblDosage.setHorizontalAlignment(SwingConstants.LEFT);
		lblDosage.setFont(font14);
		lblDosage.setBounds(586, 464, 59, 54);
		contentPane.add(lblDosage);
		
		JLabel lblSpec = new JLabel("<html> \u89C4\u683C <br> Spec. </html>");
		lblSpec.setHorizontalAlignment(SwingConstants.LEFT);
		lblSpec.setFont(font14);
		lblSpec.setBounds(67, 203, 74, 58);
		contentPane.add(lblSpec);
		
		JLabel lblRemark = new JLabel("<html> \u5907\u6CE8 <br> Remark </html>");
		lblRemark.setHorizontalAlignment(SwingConstants.LEFT);
		lblRemark.setFont(font14);
		lblRemark.setBounds(67, 506, 59, 49);
		contentPane.add(lblRemark);
		
		JLabel lblPurchase = new JLabel("<html>\u8C03\u8FBE\u5730<br> Arrival </html>");
		lblPurchase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurchase.setFont(font14);
		lblPurchase.setBounds(777, 406, 59, 43);
		contentPane.add(lblPurchase);
		
		JLabel lblLeadtime = new JLabel("<html>\u5355\u4EF7 <br> Price</html>");
		lblLeadtime.setHorizontalAlignment(SwingConstants.LEFT);
		lblLeadtime.setFont(font14);
		lblLeadtime.setBounds(67, 281, 45, 42);
		contentPane.add(lblLeadtime);
		
		JLabel label_2 = new JLabel("<html>\u8C03\u8FBE\u624B\u7FFB<br> Leadtime </html>");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(font14);
		label_2.setBounds(586, 406, 67, 43);
		contentPane.add(label_2);
		
		JLabel lblUnit = new JLabel("<html>\u5355\u4F4D<br> Unit</html>");
		lblUnit.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnit.setFont(font14);
		lblUnit.setBounds(317, 275, 45, 54);
		contentPane.add(lblUnit);
		
		JLabel lblShelf = new JLabel("<html>\u68DA\u67B6<br> Shelf</html>");
		lblShelf.setHorizontalAlignment(SwingConstants.LEFT);
		lblShelf.setFont(font14);
		lblShelf.setBounds(67, 458, 36, 44);
		contentPane.add(lblShelf);
		
		JLabel lblSeat = new JLabel("<html>\u68DA\u4F4D<br> Seat</html>");
		lblSeat.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeat.setFont(font14);
		lblSeat.setBounds(317, 465, 31, 31);
		contentPane.add(lblSeat);
		
		JLabel lblTotal = new JLabel("<html> \u5408\u8BA1<br> Total</html>");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(font14);
		lblTotal.setBounds(317, 393, 45, 43);
		contentPane.add(lblTotal);
		
		JLabel lblSafeStock = new JLabel("<html>\u5B89\u5168\u5E93\u5B58<br> SafeStock</html>");
		lblSafeStock.setHorizontalAlignment(SwingConstants.LEFT);
		lblSafeStock.setFont(font14);
		lblSafeStock.setBounds(288, 339, 74, 43);
		contentPane.add(lblSafeStock);
		
		tfCode = new JTextField();
		tfCode.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfCode.setFont(font14);
		tfCode.setBounds(184, 58, 217, 30);
		contentPane.add(tfCode);
		tfCode.setColumns(10);
		
		webDate = new WebDateField();
		webDate.setDate(new Date());
		webDate.setBounds(895, 7, 133, 30);
		webDate.setEditable(false);
		contentPane.add(webDate);
		
		tfName = new JTextField();
		tfName.setFocusable(false);
		tfName.setFont(font14);
		tfName.setColumns(10);
		tfName.setBounds(184, 109, 217, 30);
		contentPane.add(tfName);
		
		tfStockQty = new JTextField("0");
		tfStockQty.setFocusable(false);
		PlainDocument pdStock = (PlainDocument)tfStockQty.getDocument();
		pdStock.setDocumentFilter(new TextFieldFilter(TextFieldFilter.INTEGER_FILTER));
		tfStockQty.setFont(font14);
		tfStockQty.setColumns(10);
		tfStockQty.setBounds(122, 406, 133, 30);
		contentPane.add(tfStockQty);
		
		
		tfImportQty = new JTextField();
		tfImportQty.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PlainDocument pdImport = (PlainDocument)tfImportQty.getDocument();
		pdImport.setDocumentFilter(new TextFieldFilter(TextFieldFilter.INTEGER_FILTER));
		tfImportQty.setFont(font14);
		tfImportQty.setColumns(10);
		tfImportQty.setBounds(122, 352, 133, 30);
		contentPane.add(tfImportQty);
		
		tfPrice = new JTextField();
		tfPrice.setFocusable(false);
		PlainDocument pd = (PlainDocument)tfPrice.getDocument();
		pd.setDocumentFilter(new TextFieldFilter(TextFieldFilter.DOUBLE_FILTER));
		tfPrice.setFont(font14);
		tfPrice.setColumns(10);
		tfPrice.setBounds(122, 288, 133, 30);
		contentPane.add(tfPrice);
		
		tfDosage = new JTextField("0");
		tfDosage.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfDosage.setFont(font14);
		tfDosage.setColumns(10);
		tfDosage.setBounds(649, 470, 107, 30);
		contentPane.add(tfDosage);
		
		 comboCycle = new JComboBox();
		 comboCycle.setFocusable(false);
		 comboCycle.setFont(font14);
		comboCycle.setModel(new DefaultComboBoxModel(CycleDao.getName()));
		comboCycle.setBounds(832, 470, 123, 30);
		contentPane.add(comboCycle);
		
		comboArriveLocation = new JComboBox();
		comboArriveLocation.setFocusable(false);
		comboArriveLocation.setFont(font14);
		comboArriveLocation.setModel(new DefaultComboBoxModel(ArrivalLocationDao.getName()));
		comboArriveLocation.setBounds(832, 406, 123, 30);
		contentPane.add(comboArriveLocation);
		
		tfLeadTime = new JTextField("0");
		tfLeadTime.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PlainDocument p = (PlainDocument)tfLeadTime.getDocument();
		p.setDocumentFilter(new TextFieldFilter(TextFieldFilter.INTEGER_FILTER));
		tfLeadTime.setFont(font14);
		tfLeadTime.setColumns(10);
		tfLeadTime.setBounds(649, 413, 69, 31);
		contentPane.add(tfLeadTime);
		
		 comboShelf = new JComboBox();
		 comboShelf.setFocusable(false);
		 comboShelf.setFont(font14);
		comboShelf.setModel(new DefaultComboBoxModel(ShelfDao.getName()));
		comboShelf.setBounds(122, 466, 102, 30);
		contentPane.add(comboShelf);
		
		comboSeat = new JComboBox();
		comboSeat.setFocusable(false);
		comboSeat.setFont(font14);
		comboSeat.setModel(new DefaultComboBoxModel(ShelfSeatDao.getName()));
		comboSeat.setBounds(360, 466, 102, 30);
		contentPane.add(comboSeat);
		
		tfTotal = new JTextField("0");
		tfTotal.setFocusable(false);
		PlainDocument pdTotal = (PlainDocument)tfTotal.getDocument();
		pdTotal.setDocumentFilter(new TextFieldFilter(TextFieldFilter.INTEGER_FILTER));
		tfTotal.setFont(font14);
		tfTotal.setColumns(10);
		tfTotal.setBounds(360, 406, 130, 30);
		contentPane.add(tfTotal);
		
		 txtAreaSpec = new JTextArea();
		 txtAreaSpec.setFocusable(false);
		 txtAreaSpec.setRows(5);
		 txtAreaSpec.setLineWrap(true);
		 txtAreaSpec.setBorder(null);
		 txtAreaSpec.setFont(font14);
		JScrollPane areaSpecPane = new JScrollPane();
		areaSpecPane.setFocusable(false);
		areaSpecPane.setBounds(121, 203, 369, 58);
		areaSpecPane.setViewportView(txtAreaSpec);
		contentPane.add(areaSpecPane);
		
		tfSafeStock = new JTextField();
		tfSafeStock.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PlainDocument pdSafeStock = (PlainDocument)tfSafeStock.getDocument();
		pdSafeStock.setDocumentFilter(new TextFieldFilter(TextFieldFilter.INTEGER_FILTER));
		tfSafeStock.setFont(font14);
		tfSafeStock.setColumns(10);
		tfSafeStock.setBounds(360, 352, 130, 30);
		contentPane.add(tfSafeStock);
		
		txtAreaRemark = new JTextArea();
		txtAreaRemark.setRows(5);
		txtAreaRemark.setLineWrap(true);
		txtAreaRemark.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAreaRemark.setFont(new Font("宋体", Font.PLAIN, 13));
		JScrollPane areaRemarkPane = new JScrollPane();
		areaRemarkPane.setViewportView(txtAreaRemark);
		areaRemarkPane.setBounds(122, 514, 368, 67);
		contentPane.add(areaRemarkPane);
		
		//set focus transfer to tab inside textArea
		 AbstractAction transferFocus = new AbstractAction ()
         {
             public void actionPerformed ( ActionEvent e )
             {
                 ( ( Component ) e.getSource () ).transferFocus ();
             }
         };
         txtAreaRemark.getInputMap ().put ( KeyStroke.getKeyStroke ( "TAB" ), "transferFocus" );
         txtAreaRemark.getActionMap ().put ( "transferFocus", transferFocus );
         
		
		JButton btnsave = new JButton("<html>\u4FDD\u5B58<br>Save</html>");
		btnsave.setFont(new Font("宋体", Font.PLAIN, 16));
		btnsave.setBounds(128, 591, 105, 43);
		contentPane.add(btnsave);
		
		JButton btncancel = new JButton("<html>\u53D6\u6D88<br>Cancel</html>");
		btncancel.setFont(new Font("宋体", Font.PLAIN, 16));
		btncancel.setBounds(393, 591, 105, 43);
		contentPane.add(btncancel);
		
		JLabel lblImport = new JLabel("\u65B0\u6599\u5165\u5E93 | Product Import\r\n\r\n");
		lblImport.setFont(new Font("宋体", Font.PLAIN, 24));
		lblImport.setBounds(303, 0, 305, 43);
		contentPane.add(lblImport);
		lblImageLabel = new JLabel("");
		lblImageLabel.setFocusable(false);
		lblImageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImageLabel.setBackground(Color.WHITE);
		lblImageLabel.setIcon(new ImageIcon(ImageUtility.resizeImage(null)));
		lblImageLabel.setBounds(568, 95, 200, 160);
		contentPane.add(lblImageLabel);
		
		JLabel lblDay = new JLabel("<html>\u5929(Day)</html>");
		lblDay.setFont(new Font("宋体", Font.PLAIN, 14));
		lblDay.setBounds(716, 415, 51, 29);
		contentPane.add(lblDay);
		
		JLabel lblImportPerson = new JLabel("<html>    \u5165\u5E93\u4EBA <br>Importer</html>");
		lblImportPerson.setHorizontalAlignment(SwingConstants.CENTER);
		lblImportPerson.setFont(new Font("宋体", Font.PLAIN, 14));
		lblImportPerson.setBounds(568, 291, 74, 37);
		contentPane.add(lblImportPerson);
		
		tfImportPerson = new JTextField();
		tfImportPerson.setFocusable(false);
		tfImportPerson.setEditable(false);
		tfImportPerson.setText(importPerson);
		tfImportPerson.setFont(new Font("宋体", Font.PLAIN, 14));
		tfImportPerson.setBounds(639, 288, 111, 30);
		contentPane.add(tfImportPerson);
		tfImportPerson.setColumns(10);
		
		btnAddShelf = new JButton("+");
		btnAddShelf.setFocusable(false);
		btnAddShelf.setBounds(227, 467, 28, 29);
		contentPane.add(btnAddShelf);
		
		btnAddSeat = new JButton("+");
		btnAddSeat.setFocusable(false);
		btnAddSeat.setBounds(462, 467, 28, 29);
		contentPane.add(btnAddSeat);
		
		btnAddArrivalLocation = new JButton("+");
		btnAddArrivalLocation.setFocusable(false);
		btnAddArrivalLocation.setBounds(957, 407, 28, 29);
		contentPane.add(btnAddArrivalLocation);
		
		btnAddCycle = new JButton("+");
		btnAddCycle.setFocusable(false);
		btnAddCycle.setBounds(957, 471, 28, 29);
		contentPane.add(btnAddCycle);
		
		JLabel lblMyanmarName = new JLabel("<html> \u7F05\u6587\u54C1\u540D <br> Myanmar Name</html>");
		lblMyanmarName.setHorizontalAlignment(SwingConstants.LEFT);
		lblMyanmarName.setFont(new Font("宋体", Font.PLAIN, 14));
		lblMyanmarName.setBounds(67, 149, 90, 44);
		contentPane.add(lblMyanmarName);
		
		tfMyanmarName = new JTextField();
		tfMyanmarName.setFocusable(false);
		tfMyanmarName.setFont(zawGyiFont);
		tfMyanmarName.setColumns(10);
		tfMyanmarName.setBounds(184, 157, 217, 30);
		contentPane.add(tfMyanmarName);
		
		JButton btnAddProduct = new JButton("+");
		btnAddProduct.setFocusable(false);
		btnAddProduct.setBounds(404, 59, 28, 29);
		contentPane.add(btnAddProduct);
		
		tfUnit = new JTextField();
		tfUnit.setFocusable(false);
		tfUnit.setFont(new Font("宋体", Font.PLAIN, 14));
		tfUnit.setColumns(10);
		tfUnit.setBounds(357, 286, 133, 30);
		contentPane.add(tfUnit);
		
		tfClass = new JTextField();
		tfClass.setFocusable(false);
		tfClass.setFont(new Font("宋体", Font.PLAIN, 14));
		tfClass.setColumns(10);
		tfClass.setBounds(640, 350, 133, 30);
		contentPane.add(tfClass);
		
		tfVendor = new JTextField();
		tfVendor.setFocusable(false);
		tfVendor.setFont(new Font("宋体", Font.PLAIN, 14));
		tfVendor.setColumns(10);
		tfVendor.setBounds(832, 345, 133, 31);
		contentPane.add(tfVendor);
		
		JLabel lblPremeasure = new JLabel("<html>\u4F59\u9884\u91CF <br>Premeasure</html>");
		lblPremeasure.setHorizontalAlignment(SwingConstants.LEFT);
		lblPremeasure.setFont(new Font("宋体", Font.PLAIN, 14));
		lblPremeasure.setBounds(571, 517, 74, 54);
		contentPane.add(lblPremeasure);
		
		tfPremeasure = new JTextField("0");
		tfPremeasure.setFont(new Font("宋体", Font.PLAIN, 14));
		tfPremeasure.setColumns(10);
		tfPremeasure.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPremeasure.setBounds(649, 525, 69, 30);
		contentPane.add(tfPremeasure);
		
		//init
		initNonEditable();
		initColor(this.getContentPane().getBackground());
		applyTextFieldFilter(stockQty);
		
		
		//if product exist 
		if(inputHolder != null){
			//do nothing 
			System.out.println("starting init :"+inputHolder.toString());
			init(inputHolder);
		}
		//get code from DB
		codeList = CodeDao.getName();
		
		//add listener to add new Product
		btnAddProduct.addActionListener(e ->{
			UpdateProductDialog classDialog = new UpdateProductDialog("创建料号",Dialog.ModalityType.APPLICATION_MODAL);
			classDialog.setVisible(true);
			classDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			classDialog.setBounds(100,100,300,300);
		});
		
		lblImageLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Image img = ImageUtility.getOriginalImage(imageLocation);
				ScalableImageViewer imgViewer = new ScalableImageViewer("Image Detail",Dialog.ModalityType.APPLICATION_MODAL,img);
				imgViewer.setVisible(true);
				imgViewer.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				imgViewer.setFocusable(true);
			}
		});
		
		//add focus listener 
		tfLeadTime.addFocusListener(this);
		tfDosage.addFocusListener(this);
		tfPremeasure.addFocusListener(this);
		
		tfCode.addFocusListener(this);
		tfCode.addActionListener(new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent e) {
			
				//reset stock component
				
				if(inputHolder == null){
					
					// TODO Auto-generated method stub
					if(tfCode.getText() != ""){
						reset();
						applyTextFieldFilter(stockQty);
						int contain = isCodeContain(codeList,tfCode.getText());
						if( contain == -1 ){
						//do not contain
							//reset();
							System.out.println("not contain");
						}else{
							//contain
							try {
							
								productList = ProductDao.getWithCode(tfCode.getText());
								holder = productList.get(0);
								if(productList.size() > 1){
									ImportDetail_ProductList productDialog = new ImportDetail_ProductList("料单",Dialog.ModalityType.APPLICATION_MODAL,productList);
									productDialog.setVisible(true);
									productDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									holder = productDialog.getValue();
									
									System.out.println(holder.toString());
								}
								setTextFieldText(holder);
								
								
							}
							 catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}
					}
				}
				//apply filter
			
			}
			
		});
		
		btnAddCycle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				UpdateCycleDialog cycleDialog = new UpdateCycleDialog("周期",Dialog.ModalityType.APPLICATION_MODAL);
				cycleDialog.setVisible(true);
				cycleDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				cycleDialog.setBounds(100,100,300,300);
				
				comboCycle.removeAllItems();
				try {
					comboCycle.setModel(new DefaultComboBoxModel(CycleDao.getName()));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnAddShelf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				UpdateShelfDialog shelfDialog = new UpdateShelfDialog("棚架",Dialog.ModalityType.APPLICATION_MODAL);
				shelfDialog.setVisible(true);
				shelfDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				shelfDialog.setBounds(100,100,300,300);
				
				comboShelf.removeAllItems();
				try {
					comboShelf.setModel(new DefaultComboBoxModel(ShelfDao.getName()));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnAddSeat.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				UpdateShelfSeatDialog shelfSeatDialog = new UpdateShelfSeatDialog("棚位",Dialog.ModalityType.APPLICATION_MODAL);
				shelfSeatDialog.setVisible(true);
				shelfSeatDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				shelfSeatDialog.setBounds(100,100,300,300);
				
				comboSeat.removeAllItems();
				try {
					comboSeat.setModel(new DefaultComboBoxModel(ShelfSeatDao.getName()));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnAddArrivalLocation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				UpdateArrivalLocationDialog arrivalLocationDialog = new UpdateArrivalLocationDialog("棚位",Dialog.ModalityType.APPLICATION_MODAL);
				arrivalLocationDialog.setVisible(true);
				arrivalLocationDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				arrivalLocationDialog.setBounds(100,100,300,300);
				
				comboArriveLocation.removeAllItems();
				try {
					comboArriveLocation.setModel(new DefaultComboBoxModel(ArrivalLocationDao.getName()));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		txtAreaRemark.addKeyListener(new KeyAdapter(){
			 public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_TAB) {
	                    if (e.getModifiers() > 0) {
	                        txtAreaRemark.transferFocusBackward();
	                    } else {
	                        txtAreaRemark.transferFocus();
	                    }
	                    e.consume();
	                }
	            }
		});
		
		txtAreaSpec.addKeyListener(new KeyAdapter(){
			 public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_TAB) {
	                    if (e.getModifiers() > 0) {
	                        txtAreaSpec.transferFocusBackward();
	                    } else {
	                        txtAreaSpec.transferFocus();
	                    }
	                    e.consume();
	                }
	            }
		});
		
		btnsave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(tfCode.getText().equals("")){
					JOptionPane.showMessageDialog(ImportDetail.this, "<html>品番不可为空<br>code cannot be empty.</html>");
				}else if(tfName.getText().equals("")){
					JOptionPane.showMessageDialog(ImportDetail.this, "<html>品名不可为空<br>Name cannot be empty.</html>");
				}else if(txtAreaSpec.getText().equals("")){
					JOptionPane.showMessageDialog(ImportDetail.this, "<html>规格不可为空<br>Specification cannot be empty.</html>");
				}else if(tfLeadTime.getText().equals("")){
					JOptionPane.showMessageDialog(ImportDetail.this, "<html>调达手翻不可为空<br>LeadTime cannot be empty.</html>");
				}else if(tfPrice.getText().equals("")){
					JOptionPane.showMessageDialog(ImportDetail.this, "<html>单价不可为空<br>Price cannot be empty./html>");
				}else if(tfImportQty.getText().equals("") || Integer.parseInt("0"+tfImportQty.getText()) == 0){
					JOptionPane.showMessageDialog(ImportDetail.this, "<html>入库数不可为空<br>Import Quantity cannot be empty.</html>");
				}else if(tfDosage.getText().equals("")){
					JOptionPane.showMessageDialog(ImportDetail.this, "<html>使用量不可为空<br>Dosage cannot be empty.</html>");
				}else if(tfSafeStock.getText().equals("")){
					JOptionPane.showMessageDialog(ImportDetail.this, "<html>安全库存不可为空<br>Safe Stock cannot be empty.</html>");
				}else if(tfPremeasure.getText().equals("")){
					JOptionPane.showMessageDialog(ImportDetail.this, "<html>安全预余量不可为空<br>Premeasure cannot be empty.</html>");
				}else{
					// save to table and clear the panel and focus to code again.
					ImportProductHolder importHolder = new ImportProductHolder();
					
					//input Holder from table and holder from New 
					if(inputHolder != null){
						importHolder.setProductId(inputHolder.getProductId());    
						System.out.println("Product id is :"+inputHolder.getProductId());
					}else{
						System.out.println("Product id is :"+holder.getProductId());
						importHolder.setProductId(holder.getProductId());
					}
					
					
					
					
					importHolder.setProductCode(tfCode.getText());
					importHolder.setProductName(tfName.getText());
					importHolder.setProductSpec(txtAreaSpec.getText());
					importHolder.setImportPerson(importPerson);
					importHolder.setArriveLocation((String)comboArriveLocation.getSelectedItem());
					importHolder.setLeadTime(Integer.parseInt(tfLeadTime.getText()));
					importHolder.setPrice(tfPrice.getText());
					importHolder.setUnit(tfUnit.getText());
					importHolder.setProductClass(tfClass.getText());
					importHolder.setVendor((String)tfVendor.getText());
					importHolder.setShelf((String)comboShelf.getSelectedItem());
					importHolder.setShelfSeat((String)comboSeat.getSelectedItem());
					importHolder.setImportQty(Integer.parseInt(tfImportQty.getText()));
					importHolder.setDosage(Integer.parseInt(tfDosage.getText()));
					importHolder.setCycle((String)comboCycle.getSelectedItem());
					importHolder.setSafeStock(Integer.parseInt(tfSafeStock.getText()));
					importHolder.setRemark(txtAreaRemark.getText());
					importHolder.setTotalQty(Integer.parseInt(tfTotal.getText()));
					importHolder.setStockQty(Integer.parseInt(tfStockQty.getText()));
					importHolder.setImageLocation(imageLocation);	//set image location to nothing first
					importHolder.setPremeasure(Double.parseDouble(tfPremeasure.getText()));
					
					//set Date
					Calendar calendar = Calendar.getInstance();
					Timestamp t = new Timestamp(calendar.getTimeInMillis());
					importHolder.setImportDate(t);
					importHolder.setLastUpdated(t);
					if(isExistInStock)
						importHolder.setExistInStock(true);
					//add row to table
					ImportTableModel model = (ImportTableModel)parent.getTable().getModel();
					
					//check whether id exist
					
						if(row > -1){
							model.getProductList().set(row, importHolder);
							reset();
							tfCode.setText("");
							enableStockComponent(true);
							dispose();
						}else{
							if(!model.isIdExist(holder.getProductId())){
								model.addRow(importHolder);
								reset();
								tfCode.setText("");
								enableStockComponent(true);
							}else{
								JOptionPane.showMessageDialog(ImportDetail.this, "此料号已列入 入库表格中！");
							}
							
						}

				}
			}
		});
		
		btncancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		
	}
	
	public void setTextFieldText(ProductHolder holder) throws Exception{
		tfName.setText(holder.getProductName());
		txtAreaSpec.setText(holder.getProductSpec());
		tfClass.setText(ClassDao.getNameById(holder.getProductClassId()));
		tfPrice.setText(holder.getProductPrice());
		tfUnit.setText(holder.getProductUnit());
		tfClass.setText(ClassDao.getNameById(holder.getProductClassId()));
		tfVendor.setText(VendorDao.getVendorById(holder.getVendorId()));
		tfMyanmarName.setText(holder.getProductMyanmarName());
		//display photo
		imageLocation = holder.getProductPhoto();
		lblImageLabel.setIcon(new ImageIcon(ImageUtility.resizeImage(imageLocation)));
		//get stock id
		int id = StockDao.getStockIdByProductId(holder.getProductId());
		//exist in stock
		if(id != -1){
			isExistInStock = true;
			enableStockComponent(false);
			StockShelfHolder stockShelfHolder = StockShelfDao.getStockShelfByStockId(id);
			System.out.println(stockShelfHolder.getShelf()+","+stockShelfHolder.getShelfSeat());
			comboShelf.setSelectedItem(stockShelfHolder.getShelf());
			comboSeat.setSelectedItem(String.valueOf(stockShelfHolder.getShelfSeat()));
			
			StockHolder stockHolder = StockDao.getAllById(id);
			tfStockQty.setText(String.valueOf(stockHolder.getStockQty()));
			tfDosage.setText(String.valueOf(stockHolder.getStockDosage()));
			tfSafeStock.setText(String.valueOf(stockHolder.getStockSafeNo()));
			comboCycle.setSelectedItem(stockHolder.getCycle());
			
			stockQty = StockDao.getStockQtyById(id);
			tfTotal.setText(""+stockQty);
			applyTextFieldFilter(stockQty);
			
			
			
		}else{
			//applyTextFieldFilter(stockQty);
		}
		//text
	}
	
	
	public void applyTextFieldFilter(int qty){
		
		PlainDocument docPrice = (PlainDocument)tfImportQty.getDocument();
		docPrice.setDocumentFilter( new TextFieldFilterWithResponsiveChange(1,tfTotal,qty));
		
		PlainDocument docLeadTime = (PlainDocument)tfLeadTime.getDocument();
		docLeadTime.setDocumentFilter(new TextFieldFilterForLeadTime(1,tfDosage,comboCycle,tfPremeasure,tfSafeStock));	//type,dosage,cycleday,premeasure,safeStock
	
		PlainDocument docDosage = (PlainDocument)tfDosage.getDocument();
		TextFieldFilterForDosage dosageFilter  = new TextFieldFilterForDosage(1,tfLeadTime,comboCycle,tfPremeasure,tfSafeStock);
		docDosage.setDocumentFilter(dosageFilter);
		
		PlainDocument docPremeasure = (PlainDocument)tfPremeasure.getDocument();
		TextFieldFilterForPremeasure measureFilter = new TextFieldFilterForPremeasure(3,tfDosage,comboCycle,tfLeadTime,tfSafeStock);
		docPremeasure.setDocumentFilter(measureFilter);
		
		comboCycle.addActionListener(e -> {
			String itemName  = (String)comboCycle.getSelectedItem();
			int leadTime = measureFilter.getLeadTime();
			int dosage = measureFilter.getDosage();
			double premeasure = dosageFilter.getPremeasure();
			int cycleDay  = CycleDao.getDayByName(itemName);
			int safeStock = measureFilter.calculateSafeStock(leadTime, dosage, cycleDay, premeasure);
			tfSafeStock.setText(String.valueOf(safeStock));
		});
	
	}
	
	public static int isCodeContain(String[] list,String codeNum){
		for(String c:list){
			if(c.equals(codeNum)){
				System.out.println(c+" equals "+codeNum);
				return 0;
			}
		}
		return -1;
	}
	
	
	//reset Data
	public void reset(){
		//CLEAR DATA
		//tfCode.setText("");
		tfName.setText("");
		txtAreaSpec.setText("");
		tfLeadTime.setText("0");
		tfPrice.setText("");
		tfStockQty.setText("0");
		
		stockQty = 0;
		
		tfImportQty.setText("0");
		tfTotal.setText("0");
		tfDosage.setText("0");
		txtAreaRemark.setText("");
		tfSafeStock.setText("");
		tfClass.setText("");
		tfVendor.setText("");
		tfUnit.setText("");
		tfMyanmarName.setText("");
		tfSafeStock.setText("0");
		
		comboShelf.setSelectedIndex(0);
		comboSeat.setSelectedIndex(0);
		comboCycle.setSelectedIndex(0);
		comboArriveLocation.setSelectedIndex(0);
		lblImageLabel.setIcon(new ImageIcon(ImageUtility.resizeImage(null)));
		//set focus to Code
		tfCode.requestFocus();
		//set Editable and change Color
	}
	
	public void enableStockComponent(boolean flag){
		
		//tfLeadTime.setEnabled(false);
		//tfDosage.setEnabled(false);
		tfSafeStock.setEditable(flag);
		tfLeadTime.setEditable(flag);
		tfDosage.setEditable(flag);
		
		comboArriveLocation.setEnabled(flag);
		comboCycle.setEnabled(flag);
		comboShelf.setEnabled(flag);
		comboSeat.setEnabled(flag);
		
	}
	
	
	public void init(ImportProductHolder inputHolder){
		//if holder is existed item
			tfCode.setText(inputHolder.getProductCode());
			tfCode.setEditable(false);
			
			initValue(inputHolder);
		
		
	//else
	}
	
	public void initNonEditable(){
		tfName.setEditable(false);	//tfName.setEnabled(false);
		tfMyanmarName.setEditable(false);	//tfMyanmarName.setEnabled(false);
		txtAreaSpec.setEditable(false);	//txtAreaSpec.setEnabled(false);
		tfPrice.setEditable(false);	//tfPrice.setEnabled(false);
		tfUnit.setEditable(false);	//tfUnit.setEnabled(false);
		tfStockQty.setEditable(false);	//tfStockQty.setEnabled(false);
		tfTotal.setEditable(false);//	tfTotal.setEditable(false);
		tfClass.setEditable(false);	//tfClass.setEnabled(false);
		tfVendor.setEditable(false);	//tfVendor.setEnabled(false);
		tfImportPerson.setEditable(false);	//tfImportPerson.setEnabled(false);
		tfSafeStock.setEditable(false);

	}
	
	public void initColor(Color color){
		tfName.setBackground(color);
		tfMyanmarName.setBackground(color);
		txtAreaSpec.setBackground(color);
		tfPrice.setBackground(color);
		tfUnit.setBackground(color);
		tfStockQty.setBackground(color);
		tfTotal.setBackground(color);
		tfClass.setBackground(color);
		tfVendor.setBackground(color);
		tfImportPerson.setBackground(color);
	}
	
	
	public void initValue(ImportProductHolder inputHolder){
		System.out.println("Input Holder:"+inputHolder.toString());
		tfCode.setText(inputHolder.getProductCode());
		tfName.setText(inputHolder.getProductName());
		txtAreaSpec.setText(inputHolder.getProductSpec());
		tfLeadTime.setText(String.valueOf(inputHolder.getLeadTime()));
		tfPrice.setText(inputHolder.getPrice());
		tfStockQty.setText(String.valueOf(inputHolder.getStockQty()));
		tfImportQty.setText(String.valueOf(inputHolder.getImportQty()));
		tfTotal.setText(String.valueOf(inputHolder.getTotalQty()));
		tfDosage.setText(String.valueOf(inputHolder.getDosage()));
		txtAreaRemark.setText(inputHolder.getRemark());
		tfSafeStock.setText(String.valueOf(inputHolder.getSafeStock()));
		tfClass.setText(inputHolder.getProductClass());
		tfVendor.setText(inputHolder.getVendor());
		tfUnit.setText(inputHolder.getUnit());
		comboShelf.setSelectedItem(inputHolder.getShelf());
		comboSeat.setSelectedItem(inputHolder.getShelfSeat());
		comboCycle.setSelectedItem(inputHolder.getCycle());
		comboArriveLocation.setSelectedItem(inputHolder.getArriveLocation());
		
		imageLocation = inputHolder.getImageLocation();
		lblImageLabel.setIcon(new ImageIcon(ImageUtility.resizeImage(imageLocation)));
		
		isExistInStock = inputHolder.isExistInStock();
		if(isExistInStock){
			enableStockComponent(false);
		}
		
	
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		JTextField tf = ((JTextField)e.getSource());
		tf.selectAll();
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
}
