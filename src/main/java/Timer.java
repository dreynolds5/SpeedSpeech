/**
 *
 */
public class Timer {
	// backend class for the timer
	private long startTimeMillis = 0;
	private long currentTimeMillis = 0;
	private long elapsedTimeMillis = 0;
	private int minutes = 0;
	private double seconds = 0;
	private boolean durationMet = false;
	private int duration = 0;
	private String MMSS = "";

	/**
	 *
	 */
	public Timer() {
		startTimeMillis = 0;
	}

	/**
	 *
	 */
	public void setCurrentTimeMillis() {

		currentTimeMillis = System.currentTimeMillis();
	}

	/**
	 *
	 */
	public void startTimer() {
		startTimeMillis = System.currentTimeMillis();
	}

	/**
	 *
	 * @param str
	 * @param padding
	 * @param len
	 * @return
	 */
	public static String padLeft(String str, char padding, int len) {
		for (int cur_len = str.length(); cur_len < len; cur_len++) {
			str = padding+str;
		}
		return str;
	}
	
	// method ends timer, returns time as String in the format MM:SS.sss
	// no parameters

	/**
	 *
	 * @return Time in MMSS format
	 */
	public String endTimer() {
		this.setElapsedTimeMillis();
		this.convertToMinutesSeconds();
		// this statement and padLeft contributed by Sam
		MMSS = padLeft(""+minutes, '0', 2) 
			+ ':' + padLeft(""+(int)seconds, '0', 2)
			+ '.' + padLeft(""+(int)((seconds % 1) * 1000.0), '0', 3);
		return MMSS;
	}

	// method to check to see if time is at a certain interval
	// returns boolean has an int parameter

	/**
	 *
	 * @param a any integer to check for time stamp
	 * @return a boolean that is true if the duration was met
	 */
	public boolean checkTimeStamp(int a) {
		duration = a;
		this.setElapsedTimeMillis();
		if (elapsedTimeMillis % duration == 0) {
			durationMet = true;
		} else {
			durationMet = false;
		}
		return durationMet;
	}

	/**
	 *
	 */
	public void setElapsedTimeMillis() {
		this.setCurrentTimeMillis();
		elapsedTimeMillis = (currentTimeMillis - startTimeMillis);
	}

	/**
	 *
	 */
	public void convertToMinutesSeconds() {
		double tempSeconds = (double) (elapsedTimeMillis) / 1000;
		minutes = (int) (tempSeconds / 60);
		seconds = (double) (tempSeconds % 60);
	}

	/**
	 *
	 * @return seconds
	 */
	public double getSeconds() {
		return seconds;
	}

	/**
	 *
	 * @return minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 *
	 * @return current time
	 */
	public long getCurrentTimeMillis() {
		return currentTimeMillis;
	}

	/**
	 *
	 * @return elapsed time
	 */
	public long getElapsedTimeMillis() {
		return elapsedTimeMillis;
	}

	/**
	 *
	 * @return start time
	 */
	public long getStartTimeMillis() {
		return startTimeMillis;
	}

}
