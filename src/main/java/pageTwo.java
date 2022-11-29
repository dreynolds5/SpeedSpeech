//import sun.java2d.SunGraphics2D;

        import java.awt.*;
        import java.awt.event.*;
        import javax.swing.*;
        import java.util.EventListener;
        import java.awt.Color;
        import java.awt.Dimension;
        import javax.swing.JFrame;


public class pageTwo {
    public static void main(String[] args) {

        // JButton = a button that performs an action when clicked on

    }

    public static class frame extends JFrame implements ActionListener {

        JLabel label;
        JLabel label2;

        frame() {



            label = new JLabel();
            label.setBounds(150, 100, 400, 400);
            label.setVisible(false);
            label.setText("How To");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setFont(new Font("Helvetica", Font.BOLD, 50));
            label.setVisible(true);

            label2 = new JLabel();
            label2.setBounds(225, 200, 400, 400);
            label2.setVisible(false);
            label2.setText("When you click the Record button, you will be prompted to a new page with a Start and Stop button, as well as a timer. To reset your speech, press the Home button. If and once you are satisfied with your speech, press the Stop button, and you will be directed to the Summary page with intensive data of your speech. Happy speaking!");
            label2.setHorizontalTextPosition(JLabel.CENTER);
            label2.setFont(new Font("Helvetica", Font.BOLD, 10));
            label2.setVisible(true);


            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(650, 900);
            this.setVisible(true);
            this.add(label);
            this.add(label2);
        }


        @Override
        public void actionPerformed(ActionEvent e) {

        }


        // interface ActionListener extends EventListener {

        //   public void actionPerformed(ActionEvent e);

    }
}