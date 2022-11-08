import java.util.ArrayList;

public class SpeechBlocks {
    boolean tooFast;
    boolean tooSlow;
    double wpms;
    String finalTranscript = null;
    CalculateWPM wpm = new CalculateWPM();
    String transcriptBlock;
    double wpmvalue;
    ArrayList<SpeechBlocks> speechBlocks = new ArrayList<>();

    public SpeechBlocks(boolean fast, boolean slow, String transcript, double speed) {
        this.tooFast = fast;
        this.tooSlow = slow;
        this.transcriptBlock = transcript;
        this.wpmvalue = speed;
    }


    public ArrayList<SpeechBlocks> getData() {
        return speechBlocks;
    }

    public void createSpeechBlock() {
        boolean tooFasts = false;
        boolean tooSlows = false;
        ArrayList<String> transcript = new ArrayList<>();
        ArrayList<Double> wpmValues = new ArrayList<>();
        wpmValues = wpm.getWpmList();
        transcript = wpm.getTranscription();
        for (int i = 0; i < transcript.size(); i++) {
            String transcriptBlock = transcript.get(i);
            double wpm = wpmValues.get(i);
            if (wpm > 150){
                tooFasts = true;
            }
            else if (wpm < 100) {
                tooSlows = true;
            }
            else {
                tooSlows = false;
                tooFasts = false;
            }
            SpeechBlocks temp = new SpeechBlocks(tooFasts, tooSlows, transcriptBlock, wpm);
            //need to add each one to an array list
        }
    }
    //needs to create a class to retunr arrayList of speechBlocks
}
