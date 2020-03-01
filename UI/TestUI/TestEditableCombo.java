package UI.TestUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.alee.laf.WebLookAndFeel;

public class TestEditableCombo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//change option pane font
				
		
					WebLookAndFeel.install ();
					UIManager.put( "ComboBox.disabledBackground",Color.GREEN);
					UIManager.put( "ComboBox.disabledForeground", Color.GREEN );
					TestEditableCombo frame = new TestEditableCombo();
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	TestEditableCombo(){
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane();
		frame.setSize(600, 800);
		
		JPanel contentPane = new JPanel();
		
		JComboBox combo1 = new JComboBox(new String[]{"hello","hi"});
		ComboBoxEditor comboEditor = combo1.getEditor();
		
		
		combo1.setEnabled(false);
		contentPane.add(combo1);
		
		JComboBox combo2 = new JComboBox(new String[]{"batrider","stalker"});
		//combo1.setEnabled(false);
		contentPane.add(combo2);
		
		frame.setContentPane(contentPane);
		

		
	}
}
