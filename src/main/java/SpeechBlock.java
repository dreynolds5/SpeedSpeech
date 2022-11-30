import java.util.ArrayList;

/*
 * SpeechBlock refactored, changing createSpeechBlocks from an instance method
 * to a static function by Sam
 */
public class SpeechBlock {
	private boolean tooFast;
	private boolean tooSlow;
	private String transcriptBlock;
	private double wpmValue;

	public SpeechBlock(boolean fast, boolean slow, String transcript, double speed) {
		this.tooFast = fast;
		this.tooSlow = slow;
		this.transcriptBlock = transcript;
		this.wpmValue = speed;
	}
	
	public boolean isTooFast() {
		return tooFast;
	}

	public boolean isTooSlow() {
		return tooSlow;
	}

	public String getTranscriptBlock() {
		return transcriptBlock;
	}

	public double getWpmValue() {
		return wpmValue;
	}

	public static ArrayList<SpeechBlock> createSpeechBlocks(CalculateWPM cwpm_result) {
		boolean tooFasts = false;
		boolean tooSlows = false;
		ArrayList<Double> wpmValues = cwpm_result.getWpmList();
		ArrayList<String> transcript = cwpm_result.getTranscription();
		ArrayList<SpeechBlock> speechBlocks = new ArrayList<SpeechBlock>();
		for (int i = 0; i < transcript.size(); i++) {
			String transcriptBlock = transcript.get(i);
			double wpm = wpmValues.get(i);
			if (wpm > 155) {
				tooFasts = true;
			} else if (wpm < 95) {
				tooSlows = true;
			} else {
				tooSlows = false;
				tooFasts = false;
			}
			SpeechBlock temp = new SpeechBlock(tooFasts, tooSlows, transcriptBlock, wpm);
			speechBlocks.add(temp);
			//System.out.println(speechBlocks);
			// need to add each one to an array list
		}
		return speechBlocks;
	}

}
