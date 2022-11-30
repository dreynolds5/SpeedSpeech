import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class pageThree {
    public static void main(String[] args) {

        // JButton = a button that performs an action when clicked on

        new pageThree.MyFrame3();

    }

    public static class MyFrame3 extends JFrame implements ActionListener {

        SpeechToTextFromMicrophone speech = new SpeechToTextFromMicrophone();
        JButton button;
        JButton button2;
        JButton button3;
        JLabel label;
        JLabel label_name;
        CalculateWPM calculateSpeed = new CalculateWPM();
        SpeechBlocks blocks = new SpeechBlocks();
        ArrayList<SpeechBlocks> blocksList = new ArrayList<>();

        MyFrame3() {


            /**label = new JLabel();
             label.setBounds(50, 500, 150, 150);
             label.setBackground(Color.blue);
             label.setVisible(false);**/

            button = new JButton();
            button.setBounds(200, 450, 250, 100);
            button.addActionListener(this);
            button.setText("START");

            button.setFocusable(false);
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.BOTTOM);
            button.setFont(new Font("Helvetica", Font.BOLD, 25));
            button.setIconTextGap(-15);
            button.setForeground(Color.green);
            button.setBackground(Color.blue);
            button.setBorder(BorderFactory.createEtchedBorder());

            button2 = new JButton();
            button2.setBounds(200, 600, 250, 100);
            button2.addActionListener(this);
            button2.setText("STOP");

            button2.setFocusable(false);
            button2.setHorizontalTextPosition(JButton.CENTER);
            button2.setVerticalTextPosition(JButton.BOTTOM);
            button2.setFont(new Font("Helvetica", Font.BOLD, 25));
            button2.setIconTextGap(-15);
            button2.setForeground(Color.red);
            button2.setBackground(Color.blue);
            button2.setBorder(BorderFactory.createEtchedBorder());

            button3 = new JButton();
            button3.setBounds(0, 0, 250, 100);
            button3.addActionListener(this);
            button3.setText("Timer");

            button3.setFocusable(false);
            button3.setHorizontalTextPosition(JButton.CENTER);
            button3.setVerticalTextPosition(JButton.BOTTOM);
            button3.setFont(new Font("Helvetica", Font.BOLD, 25));
            button3.setIconTextGap(-15);
            button3.setForeground(Color.black);
            button3.setBackground(Color.blue);
            button3.setBorder(BorderFactory.createEtchedBorder());

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(650, 900);
            this.setVisible(true);
            this.add(button);
            this.add(button2);
            this.add(button3);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                System.out.println("start");
                button2.setEnabled(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            speech.start();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }).start();

                button.setEnabled(true);
                label.setVisible(true);
            }

            else if (e.getSource() == button2){
                try {
                    System.out.println("stop");
                    Thread.sleep(1328);
                    boolean state = true;
                    speech.swapStop(state);
                    while (speech.getDone() == true){
                       // System.out.println("Buffering....");
                        Thread.sleep(1000);
                    }
                    calculateSpeed.getInputs(speech);
                    calculateSpeed.calculateWpm();
                    Thread.sleep(1328);
                    blocks.createSpeechBlock(calculateSpeed);
                    blocksList = blocks.getSpeechBlocks();



                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            }


            // interface ActionListener extends EventListener {

            //   public void actionPerformed(ActionEvent e);

        }

    }
}
//**************************************************