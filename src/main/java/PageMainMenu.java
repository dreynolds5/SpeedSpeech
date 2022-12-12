import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;

@SuppressWarnings("serial")
/**
 *
 */
public class PageMainMenu extends JPanel implements ActionListener {
	private JButton btn_record;
    private JButton btn_howto;
    private JLabel label;
    private JLabel label_name;
    private Window window;

    /**
     *
     * @param window to make the window the correct dimensions
     */
    public PageMainMenu(Window window) {
        this.setBackground(Color.CYAN);
    	this.window = window;
        //JFrame jframe = new JFrame("JFrame Background Color");
        //ImageIcon icon = new ImageIcon("Untitled design (3).png");
        /*button3.setIcon(icon);
         button3.setBounds(700, 100, 50, 50);*/
        //Home Screen
        label_name = new JLabel();
        label_name.setBounds(120, 100, 400, 400);
        label_name.setVisible(false);
        label_name.setText("Speed Speech");
        label_name.setHorizontalTextPosition(JLabel.CENTER);
        label_name.setFont(new Font("Helvetica", Font.BOLD, 50));
        label_name.setVisible(true);

        label = new JLabel();
        label.setBounds(50, 500, 150, 150);
        label.setBackground(Color.blue);
        label.setVisible(false);

        btn_record = new JButton();
        btn_record.setBounds(50, 500, 250, 100);
        btn_record.addActionListener(this);
        btn_record.setText("Record");

        btn_record.setFocusable(false);
        btn_record.setHorizontalTextPosition(JButton.CENTER);
        btn_record.setVerticalTextPosition(JButton.BOTTOM);
        btn_record.setFont(new Font("Helvetica", Font.BOLD, 25));
        btn_record.setIconTextGap(-15);
        btn_record.setForeground(Color.black);
        btn_record.setBackground(Color.green);
        btn_record.setBorder(BorderFactory.createEtchedBorder());

        btn_howto = new JButton();
        btn_howto.setBounds(325, 500, 250, 100);
        btn_howto.addActionListener(this);
        btn_howto.setText("How To");

        btn_howto.setFocusable(false);
        btn_howto.setHorizontalTextPosition(JButton.CENTER);
        btn_howto.setVerticalTextPosition(JButton.BOTTOM);
        btn_howto.setFont(new Font("Helvetica", Font.BOLD, 25));
        btn_howto.setIconTextGap(-15);
        btn_howto.setForeground(Color.black);
        btn_howto.setBackground(Color.green);
        btn_howto.setBorder(BorderFactory.createEtchedBorder());

        //this.setBackground(Color.MAGENTA);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(window.getWidth(), window.getHeight());
        this.add(btn_record);
        this.add(btn_howto);
        this.add(label);
        this.add(label_name);
        //this.add(jframe);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (btn_record == src) {
        	window.setActivePage("record");
        } else if (btn_howto == src) {
        	window.setActivePage("how to");
        }

    }
}
//**************************************************