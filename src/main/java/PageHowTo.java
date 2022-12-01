//import sun.java2d.SunGraphics2D;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class PageHowTo extends JPanel implements ActionListener {
	private JLabel label;
	private JTextArea label2;
	
	PageHowTo(Window window) {
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
		label2.setBounds(125, 200, 400, 400);
		label2.setVisible(false);
		label2.setLineWrap(true);
		label2.setWrapStyleWord(true);
		label2.setText(
				"When you press the Record button, you will be directed to the Record page, with a timer, Start button, and Stop button. Press the Start button when you are ready to give your speech and the Stop button when you want the timer to stop. After a few moments, you will be directed to the Summary analysis page.");
		//label2.setHorizontalTextPosition(JLabel.LEFT);
		label2.setFont(new Font("Helvetica", Font.BOLD, 15));
		label2.setVisible(true);

		this.setLayout(null);
		this.setSize(window.getWidth(), window.getHeight());
		this.add(label);
		this.add(label2);
	}

	private JButton button3;
	//private JLabel label_name;

	button3 = new JButton();
		button3.setBounds(400, 0, 250, 100);
		button3.setText("HOME");
		button3.setFocusable(false);
		button3.setEnabled(false);
		button3.setHorizontalTextPosition(JButton.CENTER);
		button3.setVerticalTextPosition(JButton.BOTTOM);
		button3.setFont(new Font("Helvetica", Font.BOLD, 25));
		button3.setIconTextGap(-15);
		button3.setForeground(Color.black);
		button3.setBackground(Color.blue);
		button3.setBorder(BorderFactory.createEtchedBorder());
	add(button3);
}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	// interface ActionListener extends EventListener {

	// public void actionPerformed(ActionEvent e);

}
