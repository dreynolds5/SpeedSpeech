//import sun.java2d.SunGraphics2D;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 */
@SuppressWarnings("serial")
public class PageHowTo extends JPanel implements ActionListener {
	private JLabel label;
	private JTextArea label2;
	private JTextArea label3;
	private JButton button3;
	//private JLabel label_name;

	private Window window;

	/**
	 *
	 * @param window
	 */
	PageHowTo(Window window) {
		//creates how to label
		this.setBackground(Color.CYAN);
		this.window = window;
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
				"When you press the Record button, you will be directed to the Record page, with a timer, Start button, and Stop button. Press the Start button when you are ready to give your speech and the Stop button when you want the timer to stop. After a few moments, you will be directed to the Summary analysis page. \n\n\n Tips For Speaking \n 1. Practice before hand \n 2. Work the room, make eye contact with people \n 3. Prepare with relaxation techniques \n 4. Don't read the speech \n 5. Have good posture \n\n\n\n Tips about speaking from https://sps.columbia.edu/news/five-tips-give-great-speech");
		//label2.setHorizontalTextPosition(JLabel.LEFT);
		label2.setFont(new Font("Helvetica", Font.BOLD, 15));
		label2.setVisible(true);


		this.setLayout(null);
		this.setSize(window.getWidth(), window.getHeight());
		this.add(label);
		this.add(label2);

		// Home Button
		button3 = new JButton();
		button3.setBounds(400, 0, 250, 100);
		button3.setText("HOME");
		button3.setFocusable(false);
		button3.setEnabled(true);
		button3.addActionListener(this);
		button3.setHorizontalTextPosition(JButton.CENTER);
		button3.setVerticalTextPosition(JButton.BOTTOM);
		button3.setFont(new Font("Helvetica", Font.BOLD, 25));
		button3.setIconTextGap(-15);
		button3.setForeground(Color.black);
		button3.setBackground(Color.green);
		button3.setBorder(BorderFactory.createEtchedBorder());
		add(button3);
	}


	@Override
	/**
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button3) {
			window.setActivePage("main menu");
		}

		// interface ActionListener extends EventListener {

		// public void actionPerformed(ActionEvent e);

	}
}