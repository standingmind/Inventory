package Utility;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Image;

import javax.swing.JDialog;

public class ScalableImageViewer extends JDialog{
	private ScalableImagePanel imagePanel;
	
	public ScalableImageViewer(String title,Dialog.ModalityType mt,Image img ){
		super();
		this.setTitle(title);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 674, 627);
		getContentPane().setLayout(new BorderLayout());
		imagePanel = new ScalableImagePanel(img);
		getContentPane().add(imagePanel,BorderLayout.CENTER);
	}
	
	
}
