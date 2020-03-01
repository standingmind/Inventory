package TestClass;

import java.awt.*;
import javax.swing.*;
import java.io.*;


public class OptionPaneDemo {

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                show("Window");
            }
        });
    }

    public static void show(String frame_name){
        JFrame frame = new JFrame(frame_name);
        frame.setPreferredSize(new Dimension(450, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));

        JPanel creator = new JPanel();
        creator.setLayout(new BoxLayout(creator, BoxLayout.Y_AXIS));
        creator.add(top);

        JFileChooser fc = new JFileChooser(new File("."));

        String[] buttons = {"OK", "Cancel", "Browse"};

        int rc=-1;

        do {
            rc = JOptionPane.showOptionDialog(
                       null,
                       creator,
                       frame_name,
                       JOptionPane.DEFAULT_OPTION,
                       JOptionPane.PLAIN_MESSAGE,
                       null,
                       buttons,
                       buttons[0]
                     );

            if( rc == 1){
                System.exit(0);
                break;
            }
            else if(rc == 2){
                int retVal = fc.showDialog(null, "Test");
                if (retVal == JFileChooser.APPROVE_OPTION)
                    System.out.println("File choose" + fc.getSelectedFile());
            }
        } while (rc != 0);

        if( rc == 0){
                frame.setVisible(true);
                frame.pack();
        }
    }
}