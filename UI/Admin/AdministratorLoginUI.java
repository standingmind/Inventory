package UI.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import com.alee.laf.WebLookAndFeel;
import com.sun.glass.ui.Cursor;

import DBUtils.UserDao;
import Utility.TextFieldFilter;

public class AdministratorLoginUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblcardNo;
	private JLabel lblpassword;
	private JTextField tfCardNo;
	private JPasswordField tfPassword;
	private JButton btnCancel;
	private JLabel lblNewLabel;
	private static final String DEFAULT_PASS="123456";
	private static Font font = new Font("宋体", Font.PLAIN, 12);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//change option pane font
					UIManager.put("OptionPane.messageFont", font);
					UIManager.put("OptionPane.buttonFont", font);
					
					WebLookAndFeel.install ();
					AdministratorLoginUI frame = new AdministratorLoginUI();
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
	public AdministratorLoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 489, 380);
		setTitle("BTC 库存管理系统");
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 60, 60));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblcardNo = new JLabel("\u5361\u53F7(Card No)");
		lblcardNo.setFont(new Font("宋体", Font.PLAIN, 14));
		lblcardNo.setBounds(73, 97, 102, 51);
		contentPane.add(lblcardNo);
		
		lblpassword = new JLabel("\u5BC6\u7801 (Password)");
		lblpassword.setFont(new Font("宋体", Font.PLAIN, 14));
		lblpassword.setBounds(60, 158, 117, 51);
		contentPane.add(lblpassword);
		
		tfCardNo = new JTextField();
		PlainDocument pd = (PlainDocument)tfCardNo.getDocument();
		pd.setDocumentFilter(new TextFieldFilter(TextFieldFilter.INTEGER_FILTER));
		tfCardNo.setFont(new Font("宋体", Font.PLAIN, 12));
		tfCardNo.setBounds(176, 111, 178, 28);
		contentPane.add(tfCardNo);
		tfCardNo.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("宋体", Font.PLAIN, 12));
		tfPassword.setBounds(176, 158, 178, 28);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);
		
		JButton btnLogin = new JButton("\u767B\u5165");
		btnLogin.setFont(new Font("宋体", Font.PLAIN, 18));
		
		btnLogin.setBounds(125, 247, 89, 28);
		contentPane.add(btnLogin);
		
		btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setFont(new Font("宋体", Font.PLAIN, 18));
	
		btnCancel.setBounds(252, 247, 102, 28);
		contentPane.add(btnCancel);
		
		lblNewLabel = new JLabel("\u8BF7\u767B\u5165");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(192, 64, 81, 28);
		contentPane.add(lblNewLabel);
		
//		if(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)){
//			lblMessage.setText("<html>大写开启<br>Caplock opened</html>");
//		}else{
//			lblMessage.setText("");
//		}
//		
		
	   AbstractAction buttonPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processLogin();
            }
        };
        //for login button
        btnLogin.addActionListener(buttonPressed);
        btnLogin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "Enter_pressed");
		/*
		 * Add an action when the event key is "A_pressed"
		 */
		btnLogin.getActionMap().put("Enter_pressed", buttonPressed);
		
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
	
		
	}
	
	public void processLogin(){
		
		String cardNo = tfCardNo.getText();
		String password = String.valueOf(tfPassword.getPassword());
		
		if(cardNo.equals("")){
			JOptionPane.showMessageDialog(AdministratorLoginUI.this, "请添写卡号");
		}else if(password.equals("")){
			JOptionPane.showMessageDialog(AdministratorLoginUI.this,"请填写密码");
		}else if(!UserDao.ValidateUser(cardNo, password)){
			JOptionPane.showMessageDialog(AdministratorLoginUI.this, "卡号或密码错误");
		}else{
			if(password.equals(DEFAULT_PASS)){
				JOptionPane.showMessageDialog(AdministratorLoginUI.this, "新用户必须更改密码");
				JFrame changePasswordFrame = new ChangePasswordPanel(cardNo);
				changePasswordFrame.setVisible(true);
				
			}else{
				//continue to main page
				String name = UserDao.getNameByCardNo(cardNo);
				
				try {
					JFrame adminFrame = new AdminHomeUI(name);
					adminFrame.setVisible(true);
					adminFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

		}
	}

}
