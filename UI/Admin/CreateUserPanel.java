package UI.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import DBUtils.DepartmentDao;
import DBUtils.UserDao;
import HolderClass.DepartmentHolder;
import HolderClass.UserHolder;
import PasswordUtility.PasswordUtil;
import Utility.TextFieldFilter;

public class CreateUserPanel extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfCardNo;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnNormal;
	private JLabel lblCardNo;
	private JLabel lblNoti ;
	private JButton btnCancel ;
	private JButton btnCreate;
	private JComboBox comboDept;
	private JLabel lblDept;
	private JLabel lblName;
	private DepartmentHolder deptHolder;
	
	private static final String DEFAULT_PASS="123456";
	private static final int SALT_LENGTH = 512;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUserPanel frame = new CreateUserPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateUserPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 404);
		setTitle("创建用户");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfName = new JTextField();
		tfName.setBounds(228, 118, 185, 30);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		lblName = new JLabel("\u7528\u6237\u540D (Username)");
		lblName.setFont(new Font("宋体", Font.PLAIN, 14));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(82, 105, 144, 54);
		contentPane.add(lblName);
		
		lblDept = new JLabel("\u90E8\u95E8(Dept)\r\n");
		lblDept.setFont(new Font("宋体", Font.PLAIN, 14));
		lblDept.setBounds(145, 169, 70, 29);
		contentPane.add(lblDept);
		
		String[] deptList = DepartmentDao.getNames();
		comboDept = new JComboBox();
		comboDept.setFont(new Font("宋体", Font.PLAIN, 18));
		comboDept.setModel(new DefaultComboBoxModel(deptList));
		comboDept.setBounds(229, 169, 184, 33);
		contentPane.add(comboDept);
		
		btnCreate = new JButton("\u521B\u5EFA(Create)");
		btnCreate.setFont(new Font("宋体", Font.PLAIN, 14));
		btnCreate.setBounds(145, 308, 121, 45);
		contentPane.add(btnCreate);
		
		btnCancel = new JButton("\u53D6\u6D88(Cancel)");
		btnCancel.setFont(new Font("宋体", Font.PLAIN, 14));
		btnCancel.setBounds(303, 308, 121, 45);
		contentPane.add(btnCancel);
		
		lblNoti = new JLabel("\u8BF7\u521B\u5EFA\u65B0\u7528\u6237\u3002");
		lblNoti.setForeground(Color.RED);
		lblNoti.setFont(new Font("宋体", Font.BOLD, 18));
		lblNoti.setBounds(228, 23, 144, 35);
		contentPane.add(lblNoti);
		
		lblCardNo = new JLabel("\u5361\u53F7 (Card No)\r\n");
		lblCardNo.setFont(new Font("宋体", Font.PLAIN, 14));
		lblCardNo.setBounds(114, 64, 101, 38);
		contentPane.add(lblCardNo);
		
		tfCardNo = new JTextField();
		PlainDocument pd  = (PlainDocument)tfCardNo.getDocument();
		pd.setDocumentFilter(new TextFieldFilter(TextFieldFilter.INTEGER_FILTER));
		tfCardNo.setColumns(10);
		tfCardNo.setBounds(228, 68, 185, 30);
		contentPane.add(tfCardNo);
		
		ButtonGroup bg = new ButtonGroup();
		
		rdbtnAdmin = new JRadioButton("\u7BA1\u7406\u5458(Admin)");
		rdbtnAdmin.setBounds(145, 225, 121, 23);
		contentPane.add(rdbtnAdmin);
		
		rdbtnNormal = new JRadioButton("\u4E00\u822C(Normal)\r\n");
		rdbtnNormal.setSelected(true);
		rdbtnNormal.setBounds(268, 225, 121, 23);
		contentPane.add(rdbtnNormal);
		
		bg.add(rdbtnAdmin);
		bg.add(rdbtnNormal);
		
		
		btnCreate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String cardNo = tfCardNo.getText();
				String name = tfName.getText();
				// TODO Auto-generated method stub
				if(cardNo.equals("")){
					JOptionPane.showMessageDialog(CreateUserPanel.this,"卡号不可为空");
				}else if(name.equals("")){
					JOptionPane.showMessageDialog(CreateUserPanel.this, "用户名不可为空");
				}else if(UserDao.isUserCardNoExist(cardNo)){
					JOptionPane.showMessageDialog(CreateUserPanel.this,"卡号已存在");
				}else if(UserDao.isUserExist(name)){
					JOptionPane.showMessageDialog(CreateUserPanel.this, "用户名已存在");
				}else{
					int deptId = DepartmentDao.getNameById((String)comboDept.getSelectedItem()); //get deptId
					String salt = PasswordUtil.generateSalt(SALT_LENGTH).get(); //get salt
					String hash = PasswordUtil.hashPassword(DEFAULT_PASS, salt).get(); //get hash
					
					Calendar calendar = Calendar.getInstance();	//get today timestamp
					Timestamp t = new Timestamp(calendar.getTimeInMillis());
					
					boolean adminType = (rdbtnAdmin.isSelected()?true:false); //get admin type
					if(deptId == -1){	//
						JOptionPane.showMessageDialog(CreateUserPanel.this,"无法创建新用户,没有该部门");
					}else{
						UserHolder userHolder = new UserHolder();
						userHolder.setUserName(name);
						userHolder.setUserCardNo(Integer.parseInt(cardNo));
						userHolder.setDepartmentId(deptId);
						userHolder.setUserAdminType(adminType);
						userHolder.setUserDate(t);
						
						//generate salt
						
						userHolder.setUserHash(hash);
						userHolder.setUserSalt(salt);
						System.out.println("salt :"+salt.length());
						System.out.println("hash :"+hash.length());
						if(UserDao.createNewUser(userHolder)){
							JOptionPane.showMessageDialog(CreateUserPanel.this, "新用户创建成功");
						}else{
							JOptionPane.showMessageDialog(CreateUserPanel.this,"无法创建新用户!");
						};
						
					}
					
					
				}
				
				
			}
			
		});
		
		btnCancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				
			}
			
		});
	}
	
	
	public void clearData(){
		
	}
	
}
