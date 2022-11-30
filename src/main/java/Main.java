import javax.swing.SwingUtilities;

// contributed by Sam
public class Main {
    public static void showInterface() {
    	Window window = new Window(650, 900);
        window.addPage("main menu", new PageMainMenu(window));
        window.addPage("how to", new PageHowTo(window));
        window.addPage("record", new PageRecord(window));
        window.setActivePage("main menu");
        window.display();
    }
	public static void main(String[] _args) throws Exception {
       SwingUtilities.invokeLater(new Runnable() {
    	   public void run() {
    		   showInterface();
    	   }
       });
    }
}
