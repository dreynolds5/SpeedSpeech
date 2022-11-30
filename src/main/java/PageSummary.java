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
	private static JScrollPane makeTextArea(String text, int w, int h) {
		JTextArea tarea = new JTextArea();
		tarea.setText(text);
		tarea.setFont(TEXT_FONT);
		tarea.setEditable(false);
		tarea.setLineWrap(true);
		tarea.setMinimumSize(new Dimension(w, h));
		JScrollPane scr = new JScrollPane(tarea);
		scr.setMinimumSize(new Dimension(w, h));
		return scr;
	}
	
	PageSummary(SpeechToTextFromMicrophone finished_speech, Window window) {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel title = new JLabel();
		title.setBounds(300, 300, 600, 600);
		title.setVisible(true);
		title.setText("Summary");
		title.setHorizontalTextPosition(JLabel.CENTER);
		title.setFont(new Font("Helvetica", Font.BOLD, 70));
		title.setPreferredSize(new Dimension(1, 1));
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		add(title, c);
		
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
		c.gridy = 1;
		add(makeTextArea(wpm_report, 600, 400), c);
		
		String transcript = "";
		for (String part : finished_speech.getTranscriptionRaw()) {
			transcript += part;
			transcript += ' ';
		}
		c.gridy = 2;
		add(makeTextArea(transcript, 600, 400), c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	// interface ActionListener extends EventListener {

	// public void actionPerformed(ActionEvent e);

}