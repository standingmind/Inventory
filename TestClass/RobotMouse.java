package TestClass;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
  
class RobotMouse extends Frame implements ActionListener { 
    // Frame 
    static JFrame f; 
  
    // textField 
    static TextField x, y; 
  
    // default constructor 
    RobotMouse()
    { 
    } 
  
    // main function 
    public static void main(String args[]) 
    { 
        // object of class 
    	RobotMouse rm = new RobotMouse(); 
  
        // create a frame 
        f = new JFrame("robomouse"); 
  
        // set the frame to close on exit 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  
        // create textfield 
        x = new TextField(7); 
        y = new TextField(7); 
  
        // create a button 
        Button b = new Button("OK"); 
  
        // add actionListener 
        b.addActionListener(rm); 
  
        // create a panel 
        Panel p = new Panel(); 
  
        // add items to panel 
        p.add(x); 
        p.add(y); 
        p.add(b); 
  
        f.add(p); 
  
        // setsize of frame 
        f.setSize(300, 300); 
  
        f.show(); 
    } 
  
    // if button is pressed 
    public void actionPerformed(ActionEvent e) 
    { 
        try { 
            Robot r = new Robot(); 
            int xi1, yi1, xi, yi; 
  
            // get initial loction 
            Point p = MouseInfo.getPointerInfo().getLocation(); 
            xi = p.x; 
            yi = p.y; 
  
            // get x and y points 
            xi1 = Integer.parseInt(x.getText()); 
            yi1 = Integer.parseInt(y.getText()); 
            int i = xi, j = yi; 
  
            // slowly move the mouse to detined location 
            while (i != xi1 || j != yi1) { 
                // move the mouse to the other point 
                r.mouseMove(i, j); 
  
                if (i < xi1) 
                    i++; 
                if (j < yi1) 
                    j++; 
  
                if (i > xi1) 
                    i--; 
                if (j > yi1) 
                    j--; 
  
                // wait 
                Thread.sleep(30); 
            } 
        } 
        catch (Exception evt) { 
            System.err.println(evt.getMessage()); 
        } 
    } 
} 