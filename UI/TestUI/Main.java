package UI.TestUI;

import java.awt.BorderLayout;
import java.text.NumberFormat;
//ww  w . j a v a  2  s.c om
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DocumentFilter;
import javax.swing.text.InternationalFormatter;

public class Main {
  public static void main(String... args) {
    JFrame frame = new JFrame();
    JSpinner jspinner = makeDigitsOnlySpinnerUsingDocumentFilter();
    frame.getContentPane().add(jspinner, BorderLayout.CENTER);
    frame.getContentPane().add(new JButton("just another widget"),
        BorderLayout.SOUTH);
    frame.pack();
    frame.setVisible(true);
  }
  private static JSpinner makeDigitsOnlySpinnerUsingDocumentFilter() {
    JSpinner spinner = new JSpinner(new SpinnerNumberModel());
    JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) spinner
        .getEditor();
    JFormattedTextField textField = jsEditor.getTextField();
    DocumentFilter digitOnlyFilter = new DocumentFilter() {
      @Override
      public void insertString(FilterBypass fb, int offset, String string,
          AttributeSet attr) throws BadLocationException {
        if (stringContainsOnlyDigits(string)) {
          super.insertString(fb, offset, string, attr);
        }
      }
      @Override
      public void remove(FilterBypass fb, int offset, int length)
          throws BadLocationException {
        super.remove(fb, offset, length);
      }
      @Override
      public void replace(FilterBypass fb, int offset, int length, String text,
          AttributeSet attrs) throws BadLocationException {
        if (stringContainsOnlyDigits(text)) {
          super.replace(fb, offset, length, text, attrs);
        }
      }
      private boolean stringContainsOnlyDigits(String text) {
        for (int i = 0; i < text.length(); i++) {
          if (!Character.isDigit(text.charAt(i))) {
            return false;
          }
        }
        return true;
      }
    };

    NumberFormat format = NumberFormat.getPercentInstance();
    format.setGroupingUsed(false);
    format.setGroupingUsed(true);
    format.setMaximumIntegerDigits(10);
    format.setMaximumFractionDigits(2);
    format.setMinimumFractionDigits(5);
    textField.setFormatterFactory(new DefaultFormatterFactory(
        new InternationalFormatter(format) {
          @Override
          protected DocumentFilter getDocumentFilter() {
            return digitOnlyFilter;
          }
        }));
    return spinner;
  }
}