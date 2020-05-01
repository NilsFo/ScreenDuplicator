package mechanics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import frames.BorderFrame;
import frames.IntroFrame;
import frames.Main;

public class BorderPanel extends JPanel implements MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = 5778374396864420250L;
	private ImageIcon image;

	private Point mouseStart;
	private Point mouseCurrent;
	private Rectangle selection;

	private boolean found;
	private boolean dragMode = false;

	public BorderPanel(ImageIcon image) {
		this.image = image;
		setLayout(null);
		found = false;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Image i = image.getImage();
		g.drawImage(i, 0, 0, image.getImageObserver());
		g.setColor(new Color(0f, 0f, 0f, 0.5f));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		if (dragMode && mouseCurrent != null && mouseStart != null) {
			g.setColor(Color.white);
			// g.drawString("Start: "+mouseStart.getX()+" "+mouseStart.getY(),
			// 10, 10);
			// g.drawString("Current: "+mouseCurrent.getX()+" "+mouseCurrent.getY(),
			// 10, 50);
			selection = new Rectangle(mouseStart);
			selection.add(mouseCurrent);
			g.drawRect((int) selection.getX(), (int) selection.getY(),
					(int) selection.getWidth(), (int) selection.getHeight());
			//g.drawString("x: " + (int) mouseStart.getX() + " y: "
			//		+ (int) mouseStart.getY(), (int) mouseStart.getX(),
			//		(int) mouseStart.getY());
			//g.drawString("x: " + (int) mouseCurrent.getX() + " y: "
			//		+ (int) mouseCurrent.getY(), (int) mouseCurrent.getX(),
			//		(int) mouseCurrent.getY());
			// g.drawString(selection.getWidth()+" x "+selection.getHeight(),
			// (int)Math.abs((selection.getX()-selection.getWidth()/2)),
			// (int)Math.abs(selection.getY()-selection.getHeight()/2));
			g.drawString((int)selection.getWidth()+"x"+(int)selection.getHeight(), (int) mouseCurrent.getX(),
					(int) mouseCurrent.getY());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseStart = new Point(e.getX(), e.getY());
		dragMode = true;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseStart = null;
		mouseCurrent = null;
		dragMode = false;
		repaint();
		found = true;
		Main.print("FOUND");
		BorderFrame.closeFrame();
		IntroFrame.frame.setSelectedBounds(getSelection());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseCurrent = new Point(e.getX(), e.getY());
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	public Rectangle getSelection() {
		if (!found) {
			throw new RuntimeException("Calling getSelection, but there is none found!");
		} else
			return selection;
	}

	public boolean hasFound(){
		return found;
	}
}
