package UI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBUtils.UserDao;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePasswordPanel extends JFrame {

	private JPanel contentPane;
	private JTextField tfCardNo;
	private JPasswordField tfNewPass;
	private JPasswordField tfConfirmPass;
	private JPasswordField tfOldPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordPanel frame = new ChangePasswordPanel("");
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
	
	public ChangePasswordPanel(String cardNo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tfCardNo = new JTextField();
		if(cardNo != null){
			tfCardNo.setText(cardNo);
		}
		tfCardNo.setEditable(false);
		tfCardNo.setFont(new Font("宋体", Font.PLAIN, 12));
		tfCardNo.setColumns(10);
		tfCardNo.setBounds(211, 83, 178, 28);
		contentPane.add(tfCardNo);
		
		JLabel lblCardNo = new JLabel("\u5361\u53F7(Card No)");
		lblCardNo.setFont(new Font("宋体", Font.PLAIN, 14));
		lblCardNo.setBounds(108, 71, 102, 51);
		contentPane.add(lblCardNo);
		
		JLabel lblNewPass = new JLabel("\u65B0\u5BC6\u7801 (New Password)");
		lblNewPass.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewPass.setBounds(54, 164, 147, 51);
		contentPane.add(lblNewPass);
		
		tfNewPass = new JPasswordField();
		tfNewPass.setFont(new Font("宋体", Font.PLAIN, 12));
		tfNewPass.setColumns(10);
		tfNewPass.setBounds(211, 176, 178, 28);
		contentPane.add(tfNewPass);
		
		JLabel label_2 = new JLabel("\u8BF7\u66F4\u8BE5\u5BC6\u7801");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(198, 23, 81, 28);
		contentPane.add(label_2);
		
		JLabel lblconfirmPass = new JLabel("\u786E\u8BA4\u5BC6\u7801 (Confirm Password)");
		lblconfirmPass.setFont(new Font("宋体", Font.PLAIN, 14));
		lblconfirmPass.setBounds(10, 212, 191, 51);
		contentPane.add(lblconfirmPass);
		
		tfConfirmPass = new JPasswordField();
		tfConfirmPass.setFont(new Font("宋体", Font.PLAIN, 12));
		tfConfirmPass.setColumns(10);
		tfConfirmPass.setBounds(211, 224, 178, 28);
		contentPane.add(tfConfirmPass);
		
		JButton btnSave = new JButton("\u786E\u5B9A");
		btnSave.setFont(new Font("宋体", Font.PLAIN, 18));
		btnSave.setBounds(108, 321, 89, 28);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setFont(new Font("宋体", Font.PLAIN, 18));
		btnCancel.setBounds(238, 321, 102, 28);
		contentPane.add(btnCancel);
		
		JLabel lblOldPass = new JLabel("\u65E7\u5BC6\u7801 (Old Password)");
		lblOldPass.setFont(new Font("宋体", Font.PLAIN, 14));
		lblOldPass.setBounds(54, 119, 147, 51);
		contentPane.add(lblOldPass);
		
		tfOldPass = new JPasswordField();
		tfOldPass.setFont(new Font("宋体", Font.PLAIN, 12));
		tfOldPass.setColumns(10);
		tfOldPass.setBounds(211, 131, 178, 28);
		contentPane.add(tfOldPass);
		
		btnSave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String oldPass = String.valueOf(tfOldPass.getPassword());
				String newPass = String.valueOf(tfNewPass.getPassword());
				String confirmPass = String.valueOf(tfConfirmPass.getPassword());
				if(oldPass.equals("")){
					JOptionPane.showMessageDialog(ChangePasswordPanel.this, "请填写旧密码");
				}else if(newPass.equals("")){
					JOptionPane.showMessageDialog(ChangePasswordPanel.this,"请填写新密码");
				}else if(!newPass.equals(confirmPass)){
					JOptionPane.showMessageDialog(ChangePasswordPanel.this, "确认密码错误");
				}else if(!UserDao.ValidateUser(cardNo, oldPass)){
					JOptionPane.showMessageDialog(ChangePasswordPanel.this, "用户不存在,卡号或密码错误");
				}else{
					if(UserDao.changePassword(cardNo, newPass)){
						JOptionPane.showMessageDialog(ChangePasswordPanel.this,"密码已成功更改！");
					}else{
						JOptionPane.showMessageDialog(ChangePasswordPanel.this, "密码更改失败");
					}
				}
			}
		});
	}
}
