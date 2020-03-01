package UI.TestUI;



import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;



public class EclipseWindowFileChooser {
	
	private String selectedFilePath; 
    public EclipseWindowFileChooser() {
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
        System.out.println ("Selected files: ");
        String selectedFileName = dialog.getFileName();
        if(selectedFileName != "")
        	System.out.println(selectedFilePath+","+selectedFileName);
        else
        	System.out.println("no file selected");
        isImage(selectedFileName);
        
        shell.close();
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
        
    }
    
    public boolean isImage(String fileName){
    	int start = fileName.lastIndexOf(".");
    	System.out.println(start);
    	System.out.println(fileName.substring(start, fileName.length()));
    	return false;
    }
//   
//    public static void main(String[] args){
//    	new EclipseWindowFileChooser();
//    }

	
} 