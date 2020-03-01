package UI.Admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebToggleButton;

import UI.Import.ImportInquiryUI;
import UI.Import.ImportProductUI;
import UI.Stock.StockInquiryUI;


public class AdminHomeUI extends JFrame implements TreeSelectionListener{

	private JPanel contentPane;
	private JPanel importHistory;
	private JPanel importProduct;
	private JPanel stockInquiry;
	private JPanel welcomePanel;
	private JPanel cardPanel;
	private WebToggleButton btnImportProduct;
	private WebToggleButton  btnImportInquiry;
	private WebToggleButton  btnStockInquiry;
	private WebToggleButton btnExport;
	
	private static Font font = new Font("宋体", Font.PLAIN, 12);
	private JPanel leftMenuPanel;
	private JPanel rightMenuPanel;
	private WebToggleButton  btnSetting;
	private WebToggleButton  btnOrder;
	private WebToggleButton  btnProfile;
	private ButtonGroup btnGroup;
	private WebToggleButton btnLogout;
	private JPanel treePanel;
	private JTree tree;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//						//if has nimbus look and feel then use it
//					    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//					        if ("Nimbus".equals(info.getName())) {
//					            UIManager.setLookAndFeel(info.getClassName());
//					            break;
//					        }
//					    }
//				
					WebLookAndFeel.install ();
					//UIManager.put( "ComboBox.disabledForeground", Color.GREEN );
					AdminHomeUI frame = new AdminHomeUI("");
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public AdminHomeUI(String userName) throws Exception {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1021, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(60, 60));
		menuPanel.setMinimumSize(new Dimension(60, 60));
		menuPanel.setBounds(new Rectangle(0, 0, 100, 100));
		menuPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		menuPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(menuPanel,BorderLayout.NORTH);
		menuPanel.setLayout(new BorderLayout(0, 0));
		
		leftMenuPanel = new JPanel();
		menuPanel.add(leftMenuPanel, BorderLayout.WEST);
		
		//buttonGroup
		btnGroup = new ButtonGroup();
		
		btnImportProduct = new WebToggleButton("<html>\r\n \u65B0\u6599\u5165\u5E93\r\n <br>\r\nImport Product");
		btnImportProduct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImportProduct.setRolloverShadeOnly(true);
		btnImportProduct.setRolloverDarkBorderOnly(true);
		btnGroup.add(btnImportProduct);
		leftMenuPanel.add(btnImportProduct);
		btnImportProduct.setFont(font);
		btnImportProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)cardPanel.getLayout();
				c1.show(cardPanel,"ImportProduct");
			}
		});
		btnImportProduct.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\import (2).png"));
		
		btnImportInquiry = new WebToggleButton("<html>\r\n \u5165\u5E93\u67E5\u8BE2\r\n<br>\r\nImport Inquiry");
		btnImportInquiry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImportInquiry.setRolloverShadeOnly(true);
		btnGroup.add(btnImportInquiry);
		leftMenuPanel.add(btnImportInquiry);
		
		btnImportInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)cardPanel.getLayout();
				c1.show(cardPanel,"ImportHistory");
			}
		});
		btnImportInquiry.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\import_inquiry.png"));
		btnImportInquiry.setFont(font);
	
		
		btnStockInquiry = new WebToggleButton("<html>\r\n\u5E93\u5B58\u67E5\u8BE2\r\n<br>\r\nStock Inquiry");
		btnStockInquiry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStockInquiry.setRolloverShadeOnly(true);
		btnGroup.add(btnStockInquiry);
		leftMenuPanel.add(btnStockInquiry);
		btnStockInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)cardPanel.getLayout();
				c1.show(cardPanel,"StockInquiry");
			}
		});
		btnStockInquiry.setFont(font);
		btnStockInquiry.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\stock (2).png"));
		
		
		btnExport = new WebToggleButton("<html>\u51FA\u5E93<br>Export</html>\r\n");
		btnExport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExport.setRolloverShadeOnly(true);
		btnGroup.add(btnExport);
		leftMenuPanel.add(btnExport);
		btnExport.setFont(font);
		
		
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnExport.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\export.png"));
		
		btnSetting = new WebToggleButton("<html>\u8BBE\u7F6E<br>Setting</html>");
		btnSetting.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSetting.setRolloverShadeOnly(true);
		btnGroup.add(btnSetting);
		btnSetting.setFont(new Font("宋体", Font.PLAIN, 12));
		btnSetting.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\settings.png"));
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnSetting.setSelectedIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\settings.png"));
		leftMenuPanel.add(btnSetting);
		
		btnOrder = new WebToggleButton("<html>\u4E0B\u5355<br>Order</html>");
		btnOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOrder.setRolloverShadeOnly(true);
		btnGroup.add(btnOrder);
		btnOrder.setFont(new Font("宋体", Font.PLAIN, 12));
		btnOrder.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\order.png"));
		leftMenuPanel.add(btnOrder);
		
		rightMenuPanel = new JPanel();
		menuPanel.add(rightMenuPanel, BorderLayout.EAST);
		
		btnProfile = new WebToggleButton(userName);
		btnProfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProfile.setRolloverShadeOnly(true);
		btnGroup.add(btnProfile);
		btnProfile.setFont(new Font("宋体", Font.PLAIN, 12));
		btnProfile.setIconTextGap(0);
		btnProfile.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProfile.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProfile.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\person.png"));
		rightMenuPanel.add(btnProfile);
		
		btnLogout = new WebToggleButton();
		btnLogout.setBorder(null);
		btnLogout.setMargin(new Insets(20, 0, 0, 0));
		btnLogout.setLeftRightSpacing(-5);
		btnLogout.setRolloverDecoratedOnly(true);
		btnLogout.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\logout.png"));
		btnLogout.setRolloverShadeOnly(true);
		btnLogout.setFont(new Font("宋体", Font.PLAIN, 12));
		rightMenuPanel.add(btnLogout);
		
		//set cardPanel to hold switch panels
		cardPanel = new JPanel();
		contentPane.add(cardPanel, BorderLayout.CENTER);
		cardPanel.setLayout(new CardLayout(0, 0));
		
		importHistory = new ImportInquiryUI();
		importHistory.setFont(font);
		importProduct = new ImportProductUI(userName);
		stockInquiry = new StockInquiryUI();
		welcomePanel = new WelcomePanel();
		//add switching-panels to panel_1
		cardPanel.add("Welcome",welcomePanel);
		cardPanel.add("ImportHistory",importHistory);
		cardPanel.add("ImportProduct", importProduct);
		cardPanel.add("StockInquiry", stockInquiry);
		
		treePanel = new JPanel();
		contentPane.add(treePanel, BorderLayout.WEST);
		
		tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Home") {
				{
					DefaultMutableTreeNode node_1;
					add(new DefaultMutableTreeNode("Import"));
					add(new DefaultMutableTreeNode("Export"));
					node_1 = new DefaultMutableTreeNode("Report");
						node_1.add(new DefaultMutableTreeNode("Daily Report"));
						node_1.add(new DefaultMutableTreeNode("Monthly Report"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Inquiry");
						node_1.add(new DefaultMutableTreeNode("Stock Inquiry"));
						node_1.add(new DefaultMutableTreeNode("Import Inquiry"));
						node_1.add(new DefaultMutableTreeNode("Export Inquiry"));
					add(node_1);
				}
			}
		));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		tree.setShowsRootHandles(false);
		treePanel.add(tree);
		
		
		btnLogout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int option = JOptionPane.showConfirmDialog(AdminHomeUI.this, "确定要退出吗?", "退出", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION){
					new AdministratorLoginUI().setVisible(true);
					dispose();
				}else{
					btnLogout.setSelected(false);
				}
			}
		});

	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if(node == null){
			System.out.println("wrong");
			return;
		}
		if(node.isLeaf()){
			String userObject = (String) node.getUserObject();
			switch(userObject){
			case "Import":
				CardLayout c = (CardLayout)cardPanel.getLayout();
				c.show(cardPanel,"ImportProduct");
				break;
			case "Export":
				break;
			case "Daily Report":
				break;
			case "Monthly Report":
				break;
			case "Stock Inquiry":
				CardLayout c2 = (CardLayout)cardPanel.getLayout();
				c2.show(cardPanel,"StockInquiry");
				break;
			case "Import Inquiry":
				CardLayout c3 = (CardLayout)cardPanel.getLayout();
				c3.show(cardPanel,"ImportHistory");
				break;
			case "Export Inquiry":
				break;
			}
			System.out.println(userObject);
		}
		
		

	}



}
