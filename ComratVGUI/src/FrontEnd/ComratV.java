package FrontEnd;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

public class ComratV {// extends JFrame{
	private JFrame frame;
	secondPanel panel_2;
	firstPanel panel_3;
	private JTabbedPane tabbedPane = new JTabbedPane();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComratV window = new ComratV();
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ComratV() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame_1.
	 */

	private void initialize() {

		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Comrat-V");
		frame.setBounds(200, 200, 950, 650);

		frame.setJMenuBar(new MenuBar().getMenuBar());
		
		panel_2 = new secondPanel();
		tabbedPane.add("Demo", panel_2.getPanel());

		panel_3 = new firstPanel();
		tabbedPane.add("Source File", panel_3.getPanel());

		frame.getContentPane().add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		frame.setVisible(true);
	}

	class MenuBar {
		private JMenu menu;
		private JMenuBar menuBar;
		private JMenuItem menuItem;

		public MenuBar() {
			// frame.
			menuBar = new JMenuBar();
			menu = new JMenu("File");
			menuItem = new JMenuItem("Close", KeyEvent.VK_C);
		}

		public JMenuBar getMenuBar() {
			menu.setMnemonic(KeyEvent.VK_F);
			menu.getAccessibleContext()
					.setAccessibleDescription("File Options");
			menuBar.add(menu);
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					ActionEvent.ALT_MASK));
			menuItem.getAccessibleContext().setAccessibleDescription(
					"This doesn't really do anything");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					frame.dispose();
					;
				}
			});
			menu.add(menuItem);
			return menuBar;
		}
	}

}
