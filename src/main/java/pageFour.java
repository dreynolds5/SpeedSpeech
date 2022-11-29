import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pageFour {

    boolean tooFast = false;
    boolean tooSlow = false;
    String transcriptBlock = null;
    double wpmValue = 0;

    public static void main(String[] args) {

        // JButton = a button that performs an action when clicked on

        new pageFour.MyFrame4();

    }

    public static class MyFrame4 extends JFrame implements ActionListener {

        JLabel label_name;

        MyFrame4() {

            label_name = new JLabel();
            label_name.setBounds(300, 300, 600, 600);
            label_name.setVisible(false);
            label_name.setText("Summary");
            label_name.setHorizontalTextPosition(JLabel.CENTER);
            label_name.setFont(new Font("Helvetica", Font.BOLD, 70));
            label_name.setVisible(true);

        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        // interface ActionListener extends EventListener {

        //   public void actionPerformed(ActionEvent e);

    }
}