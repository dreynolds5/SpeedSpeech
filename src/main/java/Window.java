import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * window class, as well as window-page
 * design and subsequent page class refactoring
 * to fit within that design, contributed by Sam
 */
@SuppressWarnings("serial")
/**
 *
 */
public class Window extends JFrame {
	private HashMap<String, JPanel> pages = new HashMap<String, JPanel>();
	private JPanel active_page = null;
	private int w, h;

	/**
	 *
	 * @param w
	 * @param h
	 */
	public Window(int w, int h) {
		this.w = w;
		this.h = h;
	}

	/**
	 *
	 * @param name
	 * @param page
	 */
	public void addPage(String name, JPanel page) {
		add(page);
		page.setVisible(false);
		pages.put(name, page);
	}

	/**
	 *
	 * @param page
	 */
	public void setActivePage(String page) {
		if (active_page != null) {
			active_page.setVisible(false);
		}
		active_page = pages.get(page);
		active_page.setVisible(true);
	}

	/**
	 *
	 */
	public void display() {
		setSize(w, h);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 *
	 * @return
	 */
	public int getWidth() {
		return w;
	}

	/**
	 *
	 * @return
	 */
	public int getHeight() {
		return h;
	}
}
