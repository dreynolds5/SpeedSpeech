import java.util.ArrayList;

public class SpeechBlocks {
    boolean tooFast;
    boolean tooSlow;
    double wpms;
    String finalTranscript = null;
    CalculateWPM wpm;
    String transcriptBlock;
    double wpmValue;
    ArrayList<SpeechBlocks> speechBlocks = new ArrayList<>();

    public  SpeechBlocks(){

}
    public SpeechBlocks(boolean fast, boolean slow, String transcript, double speed) {
        this.tooFast = fast;
        this.tooSlow = slow;
        this.transcriptBlock = transcript;
        this.wpmValue = speed;
    }



    public void createSpeechBlock(CalculateWPM tempCalculateWPM) {
        wpm = tempCalculateWPM;
        boolean tooFasts = false;
        boolean tooSlows = false;
        ArrayList<String> transcript = new ArrayList<>();
        ArrayList<Double> wpmValues = new ArrayList<>();
        wpmValues = wpm.getWpmList();
        transcript = wpm.getTranscription();
        for (int i = 0; i < transcript.size(); i++) {
            String transcriptBlock = transcript.get(i);
            double wpm = wpmValues.get(i);
            if (wpm > 155){
                tooFasts = true;
            }
            else if (wpm < 95) {
                tooSlows = true;
            }
            else {
                tooSlows = false;
                tooFasts = false;
            }
            SpeechBlocks temp = new SpeechBlocks(tooFasts, tooSlows, transcriptBlock, wpm);
            speechBlocks.add(temp);
            System.out.println(speechBlocks);
            //need to add each one to an array list
        }
    }
    //needs to create a class to return arrayList of speechBlocks

    public ArrayList<SpeechBlocks> getSpeechBlocks() {
        return speechBlocks;
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
}
