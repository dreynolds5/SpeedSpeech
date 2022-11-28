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

        new pageTwo.frame();
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(650, 900));
        frame.getContentPane().setBackground(Color.magenta);
        frame.pack();
        frame.setVisible(true);

    }

    public static class frame extends JFrame implements ActionListener {

        JLabel label;
        JLabel label2;

        frame() {



            label = new JLabel();
            label.setBounds(150, 100, 400, 400);
            label.setVisible(false);
            label.setText("Preferences");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setFont(new Font("Helvetica", Font.BOLD, 50));
            label.setVisible(true);

            label2 = new JLabel();
            label2.setBounds(225, 200, 400, 400);
            label2.setVisible(false);
            label2.setText("How Long?");
            label2.setHorizontalTextPosition(JLabel.CENTER);
            label2.setFont(new Font("Helvetica", Font.BOLD, 25));
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