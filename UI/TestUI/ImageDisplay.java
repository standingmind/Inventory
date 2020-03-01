package UI.TestUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ImageDisplay extends JFrame{
	
	JLabel label;
	JTextField name;
	JButton display;
	
	JPanel upperPanel;
public static void main(String[] args){
		//JFrame frame=new JFrame("Image Demo");
				
		ImageDisplay img=new ImageDisplay();
		//frame.add(img);
		img.setSize(800,600);
		img.setVisible(true);
		img.setDefaultCloseOperation(EXIT_ON_CLOSE);
		img.setResizable(false);
	}
	ImageDisplay()
	{
	super("Image Demo");
	label=new JLabel("Enter name of the Image you want to open");
	name=new JTextField(15);
	display=new JButton("Display Image");

	DrawPanel panel=new DrawPanel();
	display.addActionListener(panel);
	upperPanel=new JPanel();
	setLayout(new BorderLayout());
	upperPanel.add(label);
	upperPanel.add(name);
	upperPanel.add(display);
	
	
	add(upperPanel,BorderLayout.NORTH);
	add(panel,BorderLayout.CENTER);
	
		
	}
	
	private class DrawPanel extends JPanel implements ActionListener{
		Image image;
	public void actionPerformed(ActionEvent e)
	{
		String text=name.getText().trim();
		image=new ImageIcon(text).getImage();
		repaint();
	
	}
	public void paintComponent(Graphics g)
	{	
		setBackground(Color.WHITE);
		g.setColor(Color.WHITE);
		g.fillRect(10,10,800,600);
		g.drawImage(image,10,10,this);
	}
	
	}

}

