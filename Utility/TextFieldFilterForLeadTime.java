package Utility;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import DBUtils.CycleDao;

public class TextFieldFilterForLeadTime extends DocumentFilter {
	public static final int INTEGER_FILTER = 1;
	public static final int DOUBLE_FILTER = 2;
	private int type;
	private JTextField tfDosage;
	private JComboBox comboCycle;
	private JTextField tfPremeasure;
	private JTextField tfSafeStock;
	public TextFieldFilterForLeadTime (int type,JTextField tfDosage,JComboBox comboCycle,JTextField tfPremeasure,JTextField tfSafeStock){
		this.type = type;
		this.tfDosage = tfDosage;
		this.comboCycle = comboCycle;
		this.tfPremeasure = tfPremeasure;
		this.tfSafeStock = tfSafeStock;
	}
	
	public int getDosage(){
		String dosage = tfDosage.getText();
		if(dosage.toString().equals("") || dosage.toString().equals(null)){
			return 0;
		}
		
		return Integer.parseInt(dosage);
	}
	
	public double getPremeasure(){
		String preMeasure = tfPremeasure.getText();
		if(preMeasure.toString().equals("") || preMeasure.toString().equals(null)){
			return 0;
		}
		return Double.parseDouble(preMeasure);
	}
	
	public int getCycleDay(){
		int day = CycleDao.getDayByName((String)comboCycle.getSelectedItem());
		return day;
	}
	
	public void setSafeStock(String safeStock){
		tfSafeStock.setText(safeStock);
	}
	
	
   @Override
   public void insertString(FilterBypass fb, int offset, String string,
         AttributeSet attr) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.insert(offset, string);

      if (test(sb.toString())) {
    	  super.insertString(fb, offset, string, attr);
    	  
        
    	  int leadTime=0;
    	  //if lead time is null or empty
          if(sb.toString().equals("") || sb.toString().equals(null)){
         	 leadTime = 0;
          }
          else{
         	  leadTime = (Integer.parseInt(sb.toString()));
          }
          
          int dosage = getDosage();
          int cycle = getCycleDay();
          double premeasure = getPremeasure();
          System.out.println("dosage : "+dosage+", cycle :"+cycle+",premeasure: "+premeasure+",leadtime :"+leadTime);
          int safeStock = calculateSafeStock(leadTime, dosage, cycle, premeasure);
          
          setSafeStock(String.valueOf(safeStock));
        // total.setText(to);
      } else {
         // warn the user and don't allow the insert
      }
   }

   private boolean test(String text) {
      try {
    	  if(text.equals("") || text.equals(null)){
    		  return true;
    	  }
    	  if(type == INTEGER_FILTER){
    		  Integer.parseInt(text);
    	       return true;
    	  }else if(type == DOUBLE_FILTER){
    		  Double.parseDouble(text);
    		  return true;
    	  }else{
    		  return false;
    	  }
        
      } catch (NumberFormatException e) {
         return false;
      }
   }

   @Override
   public void replace(FilterBypass fb, int offset, int length, String text,
         AttributeSet attrs) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.replace(offset, offset + length, text);

      if (test(sb.toString())) {
    	  super.replace(fb, offset, length, text, attrs);
    	  
        
    	  int leadTime=0;
    	  //if lead time is null or empty
          if(sb.toString().equals("") || sb.toString().equals(null)){
         	 leadTime = 0;
          }
          else{
         	  leadTime = (Integer.parseInt(sb.toString()));
          }
          
          int dosage = getDosage();
          int cycle = getCycleDay();
          double premeasure = getPremeasure();
          System.out.println("dosage : "+dosage+", cycle :"+cycle+",premeasure: "+premeasure+",leadtime :"+leadTime);
          int safeStock = calculateSafeStock(leadTime, dosage, cycle, premeasure);
          
          setSafeStock(String.valueOf(safeStock));
        // total.setText(to);
      } else {
         // warn the user and don't allow the insert
      }

   }

   @Override
   public void remove(FilterBypass fb, int offset, int length)
         throws BadLocationException {
      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.delete(offset, offset + length);
      
        if (test(sb.toString())) {
    	  super.remove(fb, offset, length);
    	  
        
    	  int leadTime=0;
    	  //if lead time is null or empty
          if(sb.toString().equals("") || sb.toString().equals(null)){
         	 leadTime = 0;
          }
          else{
         	  leadTime = (Integer.parseInt(sb.toString()));
          }
          
          int dosage = getDosage();
          int cycle = getCycleDay();
          double premeasure = getPremeasure();
          System.out.println("dosage : "+dosage+", cycle :"+cycle+",premeasure: "+premeasure+",leadtime :"+leadTime);
          int safeStock = calculateSafeStock(leadTime, dosage, cycle, premeasure);
          
          setSafeStock(String.valueOf(safeStock));
        // total.setText(to);
      } else {
         // warn the user and don't allow the insert
      }
   }
   
   public static int calculateSafeStock(int leadtime,int dosage,int cycle,double Premeasure){
	  double dailyUsage  = 0;
	  if(dosage!=0 && cycle!=0){
		  dailyUsage = dosage /(double)cycle;
	  }
	 
	  double leadTimeUsage =  (leadtime*dailyUsage);
	  double safeStock = leadTimeUsage + (leadTimeUsage * Premeasure);
	  System.out.println("daily usage:" + dailyUsage);
	  System.out.println("safe stock:" + safeStock);
	  return (int)Math.ceil(safeStock);
	     
   }
   
   public static void main(String[] args){
	   System.out.println(calculateSafeStock(2,10,7,0.5));
   }
}
