package frames;

import java.awt.Component;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
	
	public static final String TITLE = "Bildschirm-Duplizierer";
	public static final String VERSION = "V 1.1";
	public static final String DATE = "08.12.2014";
	public static final String NAME = "Nils Förster";
	public static final Random RNG = new Random();
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new IntroFrame();
			}
		});
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
	
	public static void showmessage(Component parentComponent, String message){
		JOptionPane.showMessageDialog(parentComponent, message, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
}
