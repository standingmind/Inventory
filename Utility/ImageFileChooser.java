package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;



public class ImageFileChooser{
	
	private String selectedFilePath; 
	private String selectedFileName;
    public  ImageFileChooser() {
	    Display display = Display.getDefault();
        Shell shell = new Shell (display , SWT.APPLICATION_MODAL);
        // Don't show the shell.
        //shell.open ();  
    	
        FileDialog dialog = new FileDialog (shell, SWT.OPEN);
        String [] filterNames = new String [] {"All Files(*)","PNG","JPG","JPEG"};
        String [] filterExtensions = new String [] {"*.*","*.png"," *.jpg","*.jpeg"};
        String filterPath = "c:\\";
        
        dialog.setFilterNames (filterNames);
        dialog.setFilterExtensions (filterExtensions);
        dialog.setFilterPath (filterPath);
        selectedFilePath  = dialog.open();
        selectedFileName = dialog.getFileName();
        
        shell.close();
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
        
    }
    
    public boolean isImage(String fileName){
    	if(fileName == null || fileName.equals(""))
    		return false;
    	
    	int start = fileName.lastIndexOf(".");
    	String s = fileName.substring(start, fileName.length());
    	System.out.println(s);
    	if (s.equals(".png") || s.equals(".jpg") || s.equals(".jpeg"))
    		return true;
    	return false;
    }
    
    public String getFileWithoutExt(String fileName){
    	if(fileName.length() == 0)
    		return fileName;
    	int last = fileName.lastIndexOf(".");
    	String s = fileName.substring(0, last);
    	System.out.println("s :"+s);
    	
    	return s;
    }
    
    public String getUniqueName(String fileName){
    	if(fileName == null || fileName.equals(""))
    		return "";
    	int last =  fileName.lastIndexOf(".");
    	String name = fileName.substring(0, last);
    	String ext = fileName.substring(last, fileName.length());
    	
		 Date dNow = new Date();
	     SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
	     String datetime = ft.format(dNow);
	     System.out.println(datetime);
	     String ans = name+"_"+datetime+ext;
	     return ans;
    }
    
    public String getAbsolutPath(){
    	return selectedFilePath;
    }
    
    public String getFileName(){
    	return selectedFileName;
    }
   
    public static void main(String[] args){
    	ImageFileChooser im = new ImageFileChooser();
    	System.out.println(im.getFileWithoutExt("hello.txt"));
    	for(int i=0 ; i<30 ; i++){
    		System.out.println(im.getUniqueName("hello.jpg"));
    	}
    	System.out.println(im.getUniqueName("hello.jpg"));
    }

	
} 