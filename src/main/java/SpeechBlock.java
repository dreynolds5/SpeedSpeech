import java.util.ArrayList;

/*
 * SpeechBlock refactored, changing createSpeechBlocks from an instance method
 * to a static function by Sam
 */

/**
 *
 */
public class SpeechBlock {
	/**
	 *
	 */
	private boolean tooFast;
	private boolean tooSlow;
	private String transcriptBlock;
	private double wpmValue;

	/**
	 *
	 * @param fast boolean to check if it is too fast
	 * @param slow boolean to check if it is too slow
	 * @param transcript String format of the transcription
	 * @param speed double format of the words per minute of the transcript
	 */
	public SpeechBlock(boolean fast, boolean slow, String transcript, double speed) {
		this.tooFast = fast;
		this.tooSlow = slow;
		this.transcriptBlock = transcript;
		this.wpmValue = speed;
	}

	/**
	 *
	 * @return boolean TooFast
	 */
	public boolean isTooFast() {
		return tooFast;
	}

	/**
	 *
	 * @return boolean TooSlow
	 */
	public boolean isTooSlow() {
		return tooSlow;
	}

	/**
	 *
	 * @return String transcript section
	 */
	public String getTranscriptBlock() {
		return transcriptBlock;
	}

	/**
	 *
	 * @return double Words per Minute value
	 */
	public double getWpmValue() {
		return wpmValue;
	}


	/**
	 *
	 * @param cwpm_result result from the calculateWPM
	 * @return the arraylist of speechblocks
	 */
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
			//creates the new object for the front end display, and adds it to an arraylist
			SpeechBlock temp = new SpeechBlock(tooFasts, tooSlows, transcriptBlock, wpm);
			speechBlocks.add(temp);
		}
		return speechBlocks;
	}
}
