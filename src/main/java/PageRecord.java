import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PageRecord extends JPanel implements ActionListener {
	private JButton btn_start;
	private JButton btn_stop;
	private JButton button3;
	//private JLabel label_name;
	
	private SpeechToTextFromMicrophone speech = null;
	private Thread speech_thread;
	
	private Thread timer_thread;
	private JLabel timer_label;
	private volatile boolean timer_should_stop;
	
	private Window window;
	
	PageRecord(Window window) {
		this.window = window;
		
		setLayout(null);
		setSize(650, 900);
		
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
		btn_start.setForeground(Color.green);
		btn_start.setBackground(Color.blue);
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
		btn_stop.setForeground(Color.red);
		btn_stop.setBackground(Color.blue);
		btn_stop.setBorder(BorderFactory.createEtchedBorder());
		add(btn_stop);

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

	// below methods contributed by Sam
	private boolean isStarted() {
		return speech != null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_start) {
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
		} else if (e.getSource() == btn_stop) {
			if (!isStarted()) {
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
			window.addPage("summary", new PageSummary(speech, window));
			window.setActivePage("summary");
			speech = null;
			btn_start.setEnabled(true);
		}
	}
}
//**************************************************