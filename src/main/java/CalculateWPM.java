import java.util.ArrayList;

public class CalculateWPM {
	// backend class that stores the data on each section of the speech and runs the
	// data on wpm
	private double wpm = 0;
	private ArrayList<Double> wpmList;
	private ArrayList<Long> timesTemp;
	private ArrayList<Double> times;
	private ArrayList<String> transcription;

	public CalculateWPM() {
		transcription = new ArrayList<>();
		times = new ArrayList<>();
		timesTemp = new ArrayList<>();
		wpmList = new ArrayList<>();
	}

	public void getInputs(SpeechToTextFromMicrophone temp) {
		timesTemp = temp.getTimesRaw();
		transcription = temp.getTranscriptionRaw();
		for (int i = 0; i < timesTemp.size(); i++) {
			double time = (double) (timesTemp.get(i)) / 1000;
			times.add(time);
		}
	}

	public void calculateWpm() {
		// need to rebuild the calculations to use the timesRaw for the splits
		// calculated from the SpeechToTextFromMicrophone class
		for (int i = 0; i < transcription.size(); i++) {
			String transcriptBlock = transcription.get(i);
			double duration = times.get(i);
			double words = 0;
			for (int j = 0; j < transcriptBlock.length(); j++) {
				if (transcriptBlock.charAt(j) == ' ') {
					words++;
				}
			}
			double durationSeconds = duration;
			double wpsTemp = words / durationSeconds;
			wpm = convertWPM(wpsTemp);
			wpmList.add(wpm);

		}
	}

	public double convertWPM(double n) {
		double wps = n;
		double wpm = 0;
		wpm = wps * 60;
		return wpm;
	}

	public ArrayList<Double> getWpmList() {
		return wpmList;
	}

	public ArrayList<String> getTranscription() {
		return transcription;
	}
}
