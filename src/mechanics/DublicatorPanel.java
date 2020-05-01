package mechanics;

import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class DublicatorPanel extends JPanel{

	private static final long serialVersionUID = 8495299051114769736L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawPanel(g);
	}
	
	public abstract void drawPanel(Graphics g);

}
