import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
/**
 *
 */
public class PageRecord extends JPanel implements ActionListener {
	private JButton btn_start;
	private JButton btn_stop;
	private JButton btn_home;
	//private JLabel label_name;
	
	private SpeechToTextFromMicrophone speech = null;
	private Thread speech_thread;
	
	private Thread timer_thread;
	private JLabel timer_label;
	private volatile boolean timer_should_stop;
	
	private Window window;

	/**
	 *
	 * @param window
	 */
	PageRecord(Window window) {
		this.setBackground(Color.CYAN);
		this.window = window;
		
		setLayout(null);
		setSize(window.getWidth(), window.getHeight());
		
		// speech_thread, timer_thread, timer_label, timer_should_stop
		// contributed by Sam
		timer_label = new JLabel(); 
		timer_label.setBounds(165, 165, 650-165, 150);
		timer_label.setBackground(Color.blue); 
		String timer_init;
		{
			Timer t = new Timer();
			t.startTimer();
			timer_init = t.endTimer();
		}
		timer_label.setText(timer_init);
		timer_label.setFont(new Font("Helvetica", Font.BOLD, 64));
		add(timer_label);
		
		btn_start = new JButton();
		btn_start.setEnabled(true);
		btn_start.setBounds(200, 450, 250, 100);
		btn_start.addActionListener(this);
		btn_start.setText("START");
		btn_start.setFocusable(false);
		btn_start.setHorizontalTextPosition(JButton.CENTER);
		btn_start.setVerticalTextPosition(JButton.BOTTOM);
		btn_start.setFont(new Font("Helvetica", Font.BOLD, 25));
		btn_start.setForeground(Color.black);
		btn_start.setBackground(Color.green);
		btn_start.setBorder(BorderFactory.createEtchedBorder());
		add(btn_start);
		
		btn_stop = new JButton();
		btn_stop.setEnabled(false);
		btn_stop.setBounds(200, 600, 250, 100);
		btn_stop.addActionListener(this);
		btn_stop.setText("STOP");
		btn_stop.setFocusable(false);
		btn_stop.setHorizontalTextPosition(JButton.CENTER);
		btn_stop.setVerticalTextPosition(JButton.BOTTOM);
		btn_stop.setFont(new Font("Helvetica", Font.BOLD, 25));
		btn_stop.setIconTextGap(-15);
		btn_stop.setForeground(Color.black);
		btn_stop.setBackground(Color.green);
		btn_stop.setBorder(BorderFactory.createEtchedBorder());
		add(btn_stop);

		btn_home = new JButton();
		btn_home.setBounds(400, 0, 250, 100);
		btn_home.setText("Home");
		btn_home.setFocusable(false);
		btn_home.setEnabled(true);
		btn_home.addActionListener(this);
		btn_home.setHorizontalTextPosition(JButton.CENTER);
		btn_home.setVerticalTextPosition(JButton.BOTTOM);
		btn_home.setFont(new Font("Helvetica", Font.BOLD, 25));
		btn_home.setIconTextGap(-15);
		btn_home.setForeground(Color.black);
		btn_home.setBackground(Color.green);
		btn_home.setBorder(BorderFactory.createEtchedBorder());
		add(btn_home);
	}

	// below methods contributed by Sam

	/**
	 *
	 * @return
	 */
	private boolean isStarted() {
		return speech != null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == btn_start) {
			if (isStarted()) {
				return;
			}
			btn_start.setEnabled(false);
			
			System.out.println("start");
			speech = new SpeechToTextFromMicrophone();
			speech_thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						speech.start();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			speech_thread.start();
			timer_should_stop = false;
			timer_thread = new Thread(new Runnable() {
				@Override
				public void run() {
					Timer timer = new Timer();
					timer.startTimer();
					while (!timer_should_stop) {
						try {
							Thread.sleep(50);
						} catch (InterruptedException ex) {}
						String timer_res = timer.endTimer();
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								timer_label.setText(timer_res);
							}
						});
					}
				}
			});
			timer_thread.start();
			btn_stop.setEnabled(true);
		} else if (src == btn_stop || src == btn_home) {
			if (!isStarted()) {
				if (src == btn_home) {
					window.setActivePage("main menu");
				}
				return;
			}
			btn_stop.setEnabled(false);
			
			System.out.println("stop");
			
			timer_should_stop = true;
			boolean interrupted;
			do {
				interrupted = false;
				try {
					timer_thread.join();
				} catch (InterruptedException ex) {
					interrupted = true;
				}
			} while (interrupted);
			try /* try contributed by dreynolds5 */ {
				Thread.sleep(1328);
			} catch (InterruptedException ex) {}
			speech.queueStop();

			// previously this thread would while(true) checking
			// a `done` instance variable of `speech`
			// changed to join() which is simpler
			do {
				interrupted = false;
				try {
					speech_thread.join();
				} catch (InterruptedException ex) {
					interrupted = true;
				}
			} while (interrupted);
			if (src == btn_home) {
				window.setActivePage("main menu");
			} else {
				window.addPage("summary", new PageSummary(speech, window));
				window.setActivePage("summary");
			}
			speech = null;
			btn_start.setEnabled(true);
		}
	}
}
//**************************************************