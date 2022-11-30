import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * window class, as well as window-page
 * design and subsequent page class refactoring
 * to fit within that design, contributed by Sam
 */
@SuppressWarnings("serial")
public class Window extends JFrame {
	public HashMap<String, JPanel> pages = new HashMap<String, JPanel>();
	public JPanel active_page = null;
	
	public void addPage(String name, JPanel page) {
		add(page);
		page.setVisible(false);
		pages.put(name, page);
	}
	public void setActivePage(String page) {
		if (active_page != null) {
			active_page.setVisible(false);
		}
		active_page = pages.get(page);
		active_page.setVisible(true);
	}
	public void display() {
		setSize(650, 900);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
