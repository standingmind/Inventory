package UI.TestUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import HolderClass.ImportProductHolder;
import TableModel.ImportTableModel;

public class DynamicTableColumn extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ImportTableModel tableModel;
	private ArrayList<ImportProductHolder> importList = new ArrayList<ImportProductHolder>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DynamicTableColumn frame = new DynamicTableColumn();
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
	public DynamicTableColumn() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
	
        JMenu menu = new JMenu("Menu");
        JCheckBoxMenuItem checkBoxMenuItem
                = new JCheckBoxMenuItem("JCheckBoxMenuItem");
        checkBoxMenuItem.setSelected(true);
        menu.add(checkBoxMenuItem);
		panel.add(menu);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable();
		tableModel =new ImportTableModel(importList,new String[] {
				"No", "<html> \u6599\u53F7<br>Code</html>", "<html> \u6599\u540D<br>Name</html>", "<html> \u89C4\u683C<br>Spec.</html>", "<html> \u5206\u7C7B<br>Class</html>", "<html> \u5355\u4EF7<br>price</html>", "<html> \u5165\u5E93\u91CF<br>quantity</html>","<html> \u5355\u4F4D<br>unit</html>",  "<html> \u8C03\u8FBE\u624B\u756A<br>leadtime</html>", "<html> \u65E5\u671F<br>Date</html>", "<html> \u5907\u6CE8<br>Remark</html>"
			});
		
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		table.addMouseListener(new PopClickListener());
		table.getTableHeader().addMouseListener(new PopClickListener());
	}
	
	
	
	

	class PopUpDemo extends JPopupMenu {
	    JCheckBoxMenuItem item1;
	    JCheckBoxMenuItem item2;
	    public PopUpDemo() {
	    	item1 = new JCheckBoxMenuItem("hello");
	    	item2 = new JCheckBoxMenuItem("hello2");
	    	add(item2);
	    	add(item1);
	    }
	}
	
	
	class PopClickListener extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    private void doPop(MouseEvent e) {
	        PopUpDemo menu = new PopUpDemo();
	        menu.show(e.getComponent(), e.getX(), e.getY());
	    }
	}

}
