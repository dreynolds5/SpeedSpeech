public class Timer {
    //backend class for the timer
    private long startTimeMillis = 0;
    private long currentTimeMillis = 0;
    private long elapsedTimeMillis = 0;
    private int minutes = 0;
    private double seconds = 0;
    private boolean durationMet = false;
    private int duration = 0;
    private String MMSS = "";

    public Timer(){
        startTimeMillis = 0;
    }


    public void setCurrentTimeMillis(){

        currentTimeMillis = System.currentTimeMillis();
    }


    public void startTimer(){
        startTimeMillis = System.currentTimeMillis();
    }
    //method ends timer, returns time as String in the format MM:SS.sss
    //no parameters
    public String endTimer(){
        this.setElapsedTimeMillis();
        this.convertToMinutesSeconds();
        MMSS = ("" + minutes + ":" + seconds);
        return MMSS;
    }
    //method to check to see if time is at a certain interval
    //returns boolean has an int parameter
    public boolean checkTimeStamp(int a) {
        duration = a;
        this.setElapsedTimeMillis();
        if (elapsedTimeMillis % duration == 0){
            durationMet = true;
        }
        else {
            durationMet = false;
        }
        return durationMet;
    }
    public void setElapsedTimeMillis(){
        this.setCurrentTimeMillis();
        elapsedTimeMillis = (currentTimeMillis - startTimeMillis);
    }
    public void convertToMinutesSeconds(){
        double tempSeconds = (double)(elapsedTimeMillis) / 1000;
        minutes = (int)(tempSeconds / 60);
        seconds = (double)(tempSeconds % 60);
    }

    public double getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public long getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public long getElapsedTimeMillis() {
        return elapsedTimeMillis;
    }

    public long getStartTimeMillis() {
        return startTimeMillis;
    }
}
