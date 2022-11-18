import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//https://introcs.cs.princeton.edu/java/15inout/GUI.java.html

public class GUI implements ActionListener {
    //runner class for frontend
    int count = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    SpeechToTextFromMicrophone test = new SpeechToTextFromMicrophone();

    public GUI() throws Exception {
        JFrame frame = new JFrame();

        JButton button = new JButton("Stop");
        button.addActionListener(this);

        label = new JLabel("Transcript");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(1000, 1000, 1000, 1000));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);
        test.streamingMicRecognize();

        private JLabel label = new JLabel("Speed Speech");
        JButton button = new JButton("Record");
        button.addActionListener(this);
        JButton button = new JButton("How To");
        button.addActionListener(this);

    }
    //public static void main{String[] args} {
      //  new GUI();
    //}


    }
    public void actionPerformed(ActionEvent e) {
        try {
            Thread.sleep(1500);
            boolean state = true;
            test.swapStop(state);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

    }
}
