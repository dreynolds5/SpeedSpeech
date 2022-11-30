import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * PageSummary contributed by Sam
 */
@SuppressWarnings("serial")
public class PageSummary extends JPanel implements ActionListener {
	private static final Font TEXT_FONT = new Font("Helvetica", Font.PLAIN, 24);
	private static JScrollPane makeTextArea(String text) {
		JTextArea tarea = new JTextArea();
		tarea.setText(text);
		tarea.setFont(TEXT_FONT);
		tarea.setEditable(false);
		tarea.setLineWrap(true);
		JScrollPane scr = new JScrollPane(tarea);
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		return scr;
	}
	
	PageSummary(SpeechToTextFromMicrophone finished_speech, Window window) {
		int w = window.getWidth();
		int h = window.getHeight();
		int next_y = 0;
		
		setLayout(null);
		setSize(w, h);
		
		JLabel title = new JLabel();
		title.setBounds(150, next_y, w, 150);
		next_y = 150;
		title.setVisible(true);
		title.setText("Summary");
		title.setHorizontalTextPosition(SwingConstants.CENTER);
		title.setVerticalTextPosition(SwingConstants.CENTER);
		title.setFont(new Font("Helvetica", Font.BOLD, 70));
		title.setPreferredSize(new Dimension(1, 1));
		add(title);
		
		CalculateWPM cwpm = new CalculateWPM();
		cwpm.getInputs(finished_speech);
		cwpm.calculateWpm();
		
		ArrayList<SpeechBlock> speech_blocks = SpeechBlock.createSpeechBlocks(cwpm);
		String wpm_report = "";
		for (SpeechBlock sb : speech_blocks) {
			String note;
			if (sb.isTooFast()) {
				note = " (too fast)";
			} else if (sb.isTooSlow()) {
				note = " (too slow)";
			} else {
				note = " (ok)";
			}
			wpm_report += '"' + sb.getTranscriptBlock() 
				+ "\" -> " + (int)sb.getWpmValue() + " WPM"
				+ note + '\n';
		}
		JScrollPane scr = makeTextArea(wpm_report);
		scr.setBounds(0, next_y, w, 300);
		next_y += 300;
		add(scr);
		
		String transcript = "";
		for (String part : finished_speech.getTranscriptionRaw()) {
			transcript += part;
			transcript += ' ';
		}
		scr = makeTextArea(transcript);
		scr.setBounds(0, next_y, w, h-next_y);
		add(scr);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}