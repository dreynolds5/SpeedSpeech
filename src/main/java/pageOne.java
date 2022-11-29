import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.EventListener;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class pageOne {
    public static void main(String[] args) {

        // JButton = a button that performs an action when clicked on

        new MyFrame();


    }

    public static class MyFrame extends JFrame implements ActionListener {

        JButton button;
        JButton button2;
        JButton button3;
        JLabel label;
        JLabel label_name;

        MyFrame() {

            JFrame jframe = new JFrame("JFrame Background Color");

            jframe.getContentPane().setBackground(Color.MAGENTA);
            jframe.setPreferredSize(new Dimension(650, 900));
            jframe.setVisible(true);

            ImageIcon icon = new ImageIcon("Untitled design (3).png");

            /**button3.setIcon(icon);
             button3.setBounds(700, 100, 50, 50);**/

            label_name = new JLabel();
            label_name.setBounds(120, 100, 400, 400);
            label_name.setVisible(false);
            label_name.setText("Speed Speech");
            label_name.setHorizontalTextPosition(JLabel.CENTER);
            label_name.setFont(new Font("Helvetica", Font.BOLD, 50));
            label_name.setVisible(true);

            label = new JLabel();
            label.setBounds(50, 500, 150, 150);
            label.setBackground(Color.blue);
            label.setVisible(false);

            button = new JButton();
            button.setBounds(50, 500, 250, 100);
            button.addActionListener(this);
            button.setText("Record");

            button.setFocusable(false);
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.BOTTOM);
            button.setFont(new Font("Helvetica", Font.BOLD, 25));
            button.setIconTextGap(-15);
            button.setForeground(Color.black);
            button.setBackground(Color.blue);
            button.setBorder(BorderFactory.createEtchedBorder());

            button2 = new JButton();
            button2.setBounds(325, 500, 250, 100);
            button2.addActionListener(this);
            button2.setText("How To");

            button2.setFocusable(false);
            button2.setHorizontalTextPosition(JButton.CENTER);
            button2.setVerticalTextPosition(JButton.BOTTOM);
            button2.setFont(new Font("Helvetica", Font.BOLD, 25));
            button2.setIconTextGap(-15);
            button2.setForeground(Color.black);
            button2.setBackground(Color.blue);
            button2.setBorder(BorderFactory.createEtchedBorder());

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(650, 900);
            this.setVisible(true);
            this.add(button);
            this.add(button2);
            this.add(label);
            this.add(label_name);
            this.add(jframe);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                System.out.println("poo");
                button.setEnabled(false);
                label.setVisible(true);
            }

            // interface ActionListener extends EventListener {

            //   public void actionPerformed(ActionEvent e);

        }
    }
}
//**************************************************