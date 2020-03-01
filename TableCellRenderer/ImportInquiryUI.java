package UI.Import;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.xswingx.PromptSupport;

import com.alee.extended.date.WebDateField;

public class ImportInquiryUI extends JPanel {
	private JTextField txtSearch;
	private JTable table;
	private Font font = new Font("ËÎÌו", Font.PLAIN, 12);
	
	private WebDateField startDatePicker;
	private WebDateField endDatePicker;
	/**
	 * Create the panel.
	 */
	public ImportInquiryUI() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5165\u5E93\u67E5\u8BE2(Import Inquiry)", TitledBorder.LEADING, TitledBorder.TOP, font, new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		panel.add(panel_1,BorderLayout.WEST);
		
		txtSearch = new JTextField();
		txtSearch.setFont(font);
		//get prompt text from external library
		PromptSupport.setPrompt("Search", txtSearch);
		panel_1.add(txtSearch);
		txtSearch.setColumns(30);
		
		JButton btnNewButton = new JButton("\r\n");
		btnNewButton.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\iconfinder_icon-111-search_314689.png"));
		panel_1.add(btnNewButton);
		
		ButtonGroup btn = new ButtonGroup();
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u6599\u865F(code)");
		rdbtnNewRadioButton.setFont(font);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\u54C1\u540D(name)");
		rdbtnNewRadioButton_1.setFont(font);
		panel_1.add(rdbtnNewRadioButton_1);
		
		btn.add(rdbtnNewRadioButton);
		btn.add(rdbtnNewRadioButton_1);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_2, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("From");
		panel_2.add(lblNewLabel);
		
	//	UtilDateModel model = new UtilDateModel();
	//	JDatePanel datePanel = new JDatePanel(model);
		startDatePicker = new WebDateField();
		startDatePicker.setColumns(10);
		panel_2.add(startDatePicker);
		
		
		JLabel lblNewLabel_1 = new JLabel("to");
		panel_2.add(lblNewLabel_1);
		
		 endDatePicker = new WebDateField();
		 endDatePicker.setColumns(10);
		panel_2.add(endDatePicker);		
		
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		add(panel_3, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Export Excel");
		btnNewButton_1.setIcon(new ImageIcon("D:\\JavaWorkspace\\Images\\excel.png"));
		panel_3.add(btnNewButton_1);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		panel_4.add(scrollPane, BorderLayout.NORTH);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		table = new JTable();
		table.getTableHeader().setFont(font);
		table.setFont(font);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, "", null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, "", null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"No", "\u5165\u5E93\u4EBA(import person)", "\u6599\u865F(code)", "\u6599\u540D(name)", "\u7F05\u6587\u540D(Myanmar name)", "\u89C4\u683C(model)", "\u5355\u4EF7(price)", "\u5355\u4F4D(unit)", "\u5165\u5E93\u6570\u91CF(quantity)", "\u4F7F\u7528\u91CF(dosage)", "\u5468\u671F(cycle)", "\u8C03\u8FBE\u624B\u7FFB(leadtime)", "\u5382\u5546(vendor)", "\u62C5\u5F53\u4EBA(person in charge)", "\u65E5\u671F(Date)", "\u5907\u6CE8(Remark)"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, String.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
//			boolean[] columnEditables = new boolean[] {
//				false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true
//			};
			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
				return false;
			}
		});
	
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(34);
		table.getColumnModel().getColumn(1).setPreferredWidth(144);
		table.getColumnModel().getColumn(4).setPreferredWidth(137);
		table.getColumnModel().getColumn(5).setPreferredWidth(91);
		table.getColumnModel().getColumn(6).setPreferredWidth(98);
		table.getColumnModel().getColumn(8).setPreferredWidth(119);
		table.getColumnModel().getColumn(9).setPreferredWidth(123);
		table.getColumnModel().getColumn(10).setPreferredWidth(97);
		table.getColumnModel().getColumn(12).setPreferredWidth(92);
		table.getColumnModel().getColumn(13).setPreferredWidth(158);
		table.getColumnModel().getColumn(15).setPreferredWidth(84);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		JEditorPane editorPane = new JEditorPane();
		panel_5.add(editorPane);
		int numOfVisibleRows = 10;
		int rows = table.getRowHeight() * numOfVisibleRows;
		int cols = table.getColumnModel().getTotalColumnWidth();
		Dimension d = new Dimension( cols, rows );
		table.setPreferredScrollableViewportSize( d );
		
	}

}
