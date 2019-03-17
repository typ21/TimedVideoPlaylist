package typ21;

import java.awt.Color;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6628981357155700559L;

	/**
	 * Create the panel.
	 */
	public Screen() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
//		setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 200, 200);

		JPanel panelOfContent = new JPanel();
		panelOfContent.setBackground(Color.BLACK);
		setContentPane(panelOfContent);

	}

	public void play(File file) {
		System.out.println("Now playing " + file.getName());
		setTitle(file.getName());
		
	}

}
