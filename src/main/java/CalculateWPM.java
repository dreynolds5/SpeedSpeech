import java.util.ArrayList;

public class CalculateWPM extends SpeechToTextFromMicrophone{
    //backend class that stores the data on each section of the speech and runs the data on wpm
    double wpm = 0;

    ArrayList <Double> wpmList = new ArrayList<>();


    public CalculateWPM(){

    }
    public void calculateWpm(){
        for(int i = 0; i < transcription.size(); i++){
           String transcriptBlock = transcription.get(i);
           for(int j = 0; j < transcriptBlock.length(); j++){
               double words = 0;
               if (transcriptBlock.charAt(j) == ' '){
                   words++;
               }
              double wpsTemp = words/15;
               wpm = covertWPM(wpsTemp);
               wpmList.add(wpm);
           }
        }
    }
    public double covertWPM(double n){
        double wps = n;
        double wpm = 0;
        wpm = wps * 60;
        return wpm;
    }

    public ArrayList<Double> getWpmList() {
        return wpmList;
    }
    public ArrayList<String> getTranscription(){
        return transcription;
    }
}
