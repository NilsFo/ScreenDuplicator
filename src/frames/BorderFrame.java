package frames;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mechanics.BorderPanel;

public class BorderFrame extends JFrame {

	private static final long serialVersionUID = 7982474751417864961L;
	private static BorderFrame frame;
	private static BorderPanel panel;

	public static void chooseBorder() {
		frame = new BorderFrame();
	}

	public static void closeFrame() {
		frame.dispose();
		frame = null;
		panel = null;
	}

	public BorderFrame() {
		setTitle(Main.TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Rectangle bounds = null;
		setLayout(null);
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		for (GraphicsDevice curGs : gs) {
			GraphicsConfiguration[] gc = curGs.getConfigurations();
			for (int i = 0; i < gc.length; i++) {
				GraphicsConfiguration curGc = gc[i];
				bounds = curGc.getBounds();
				if (bounds.contains(IntroFrame.frame.getLocation())) {
					setBounds(bounds);
					Main.print(bounds);
				}
			}
		}
		bounds = getBounds();
		try {
			Main.print(bounds);
			BufferedImage capture = new Robot().createScreenCapture(bounds);
			System.out.println(capture);
			int rgb = capture.getRGB(0, 0);
			int counter = capture.getHeight() * capture.getWidth() - 20;
			for (int x = 1; x < capture.getWidth(); x++) {
				for (int y = 1; y < capture.getHeight(); y++) {
					if (capture.getRGB(x, y) == rgb) {
						// System.out.println(counter);
						counter--;
					} else {
						// break;
					}
				}
			}
			System.out.println(counter);
			if (counter <= 0) {
				robotError("Das Bild ist einfarbig. Möglicherweise gab es einen Fehler beim Erstellen / Einlesen der Bildschirmdaten.\nEin bekanntes Problem: Der abgetastete Bildschirm befindet sich links vom Hauptbildschirm.");
			}
			ImageIcon icon = new ImageIcon(capture);
			panel = new BorderPanel(icon);
			add(panel);
			panel.setBounds(bounds);
		} catch (Exception e) {
			e.printStackTrace();
			robotError("Unerwartete Exception beim Erstellen des Bildes:\n"
					+ e.getClass().getName());
		}
		setUndecorated(true);
		setVisible(true);
	}

	private void robotError(String info) {
		JOptionPane.showMessageDialog(this,
				"Fehler beim erstellen der Auswahl! Übergebener Fehler:\n\n"
						+ info + "\n\nDas Programm wird jetzt beendet.",
				"Fehler!", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}

}
