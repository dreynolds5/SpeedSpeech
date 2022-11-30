//import sun.java2d.SunGraphics2D;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class PageHowTo extends JPanel implements ActionListener {
	private JLabel label;
	private JTextArea label2;
	
	PageHowTo() {
		label = new JLabel();
		label.setBounds(150, 100, 400, 100);
		label.setVisible(false);
		label.setText("How To");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setFont(new Font("Helvetica", Font.BOLD, 50));
		label.setVisible(true);

		// changed from JLabel to JTextArea by Sam to allow proper
		// displaying of text
		label2 = new JTextArea();
		label2.setBounds(225, 200, 400, 400);
		label2.setVisible(false);
		label2.setLineWrap(true);
		label2.setWrapStyleWord(true);
		label2.setText(
				"When you press the Record button, you will be directed to the Record page, with a timer, Start button, and Stop button. Press the Start button when you are ready to give your speech and the Stop button when you want the timer to stop. After a few moments, you will be directed to the Summary analysis page.");
		//label2.setHorizontalTextPosition(JLabel.LEFT);
		label2.setFont(new Font("Helvetica", Font.BOLD, 15));
		label2.setVisible(true);

		this.setLayout(null);
		this.setSize(650, 900);
		this.add(label);
		this.add(label2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	// interface ActionListener extends EventListener {

	// public void actionPerformed(ActionEvent e);

}
