package Utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

public class ImageUtility {
	public static final String DEFAULT_PATH = "../Images/image_not_found.jpg";
	public static Image getOriginalImage(String path){
		File im = null;
		BufferedImage img = null;
		System.out.println(path);
		if(path==null || path =="")
			path = DEFAULT_PATH;
		  
		System.out.println(path);
		try{
			try{
				 im = new File(path);
			}catch(Exception e){
				//e.printStackTrace();
				path = DEFAULT_PATH;
				im = new File(path);
			}
			img = ImageIO.read(im);
		}catch(IOException e){
			//e.printStackTrace();
		}
		Image dim = img;
		return dim;
		
	}
	
	public static Image getOriginalImage(BufferedImage bufferImage){
		Image originImage = bufferImage;
		return originImage;
	}
	
	public static BufferedImage getBufferedImage(String path){
		File im = null;
		BufferedImage img = null;
		System.out.println(path);
		if(path==null || path =="")
			path = DEFAULT_PATH;
		  
		System.out.println(path);
		try{
			try{
				 im = new File(path);
			}catch(Exception e){
				//e.printStackTrace();
				path = DEFAULT_PATH;
				im = new File(path);
			}
			img = ImageIO.read(im);
		}catch(IOException e){
			//e.printStackTrace();
		}
		return img;
	}
	
	
	public static Image resizeImage(String path){
		File im = null;
		BufferedImage img = null;
		System.out.println(path);
		if(path==null || path =="")
			path = DEFAULT_PATH;
		
		System.out.println(path);
		try{
			try{
				 im = new File(path);
			}catch(Exception e){
				//e.printStackTrace();
				path = DEFAULT_PATH;
				im = new File(path);
			}
			img = ImageIO.read(im);
		}catch(IOException e){
			//e.printStackTrace();
		}
		int imWidth = img.getWidth();
		int imHeight = img.getHeight();
		
		while(imWidth>200 || imHeight>200){
			imWidth -= 20;
			imHeight -= 20;
		}
		Image dimg = img.getScaledInstance(imWidth,imHeight,Image.SCALE_SMOOTH);
		return dimg;
	}
	
	public static Image resizeImageBuffer(BufferedImage img){
		
		
		int imWidth = img.getWidth();
		int imHeight = img.getHeight();
		
		while(imWidth>200 || imHeight>200){
			imWidth -= 20;
			imHeight -= 20;
		}
		Image dimg = img.getScaledInstance(imWidth,imHeight,Image.SCALE_SMOOTH);
		return dimg;
	}
	public static String getExtension(String fileName){
		if(fileName.length() == 0)
			return "";
		String ext = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		return ext;
		
	}
	
	public static void main(String[] args){
		System.out.println(getExtension("hell.jpg"));
	}
}
