package frames;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import mechanics.DublicatorPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DublicatorFrame extends JFrame {

	private static final long serialVersionUID = -7642932705708763350L;

	private Timer timer;
	private Rectangle borders;
	private DublicatorPanel panel;
	private boolean paused;

	private ImageIcon image;

	public DublicatorFrame(Rectangle borders, int fps, boolean onTop) {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==32){
					togglePause();
				}
			}
		});
		this.borders = borders;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);
		setSize((int) borders.getWidth(), (int) borders.getHeight());
		setLocationRelativeTo(null);
		setAlwaysOnTop(onTop);
		setPaused(false);

		panel = new DublicatorPanel() {
			private static final long serialVersionUID = 5998250521241613707L;

			@Override
			public void drawPanel(Graphics g) {
				draw(g);
			}
		};
		panel.addMouseListener(new MouseAdapter() {
			boolean isAlreadyOneClick;
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (isAlreadyOneClick) {
					togglePause();
			        isAlreadyOneClick = false;
			    } else {
			        isAlreadyOneClick = true;
			        java.util.Timer t;
					t = new java.util.Timer("doubleclickTimer", false);
			        t.schedule(new TimerTask() {

			            @Override
			            public void run() {
			                isAlreadyOneClick = false;
			            }
			        }, 500);
			    }
			}
		});
		panel.setBounds(0, 0, getWidth(), getHeight());
		getContentPane().add(panel);

		timer = new Timer(1000 / fps, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});
		timer.start();
		setResizable(false);
		requestFocus();
		update();
	}

	private void update() {
		panel.repaint();
	}

	private void draw(Graphics g) {
		BufferedImage capture;
		if (!isPaused()) {
			try {
				capture = new Robot().createScreenCapture(borders);
				image = new ImageIcon(capture);
			} catch (AWTException e) {
				e.printStackTrace();
				image = null;
			}
		}

		if(image == null){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
		}else{
			g.drawImage(image.getImage(), 0, 0, image.getImageObserver());			
		}
		
		if(isPaused()){
			g.setColor(new Color(0f, 0f, 0f, 0.2f));
			g.fillRect(0, 0, getWidth(), getHeight());
			if(getWidth()>300&&getHeight()>300){
				int x = getWidth()-50;
				int y = 20;
				for(int i=0;i<2;i++){
					Rectangle r = new Rectangle(x,y,25,85);
					g.setColor(Color.WHITE);
					g.fillRect((int)r.getX(),(int)r.getY(),(int)r.getWidth(),(int)r.getHeight());
					g.setColor(Color.BLACK);
					g.drawRect((int)r.getX(),(int)r.getY(),(int)r.getWidth(),(int)r.getHeight());
					x-=40;
				}
			}
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
		String s = "";
		if (isPaused()) {
			s = " [PAUSED]";
		}
		setTitle(Main.TITLE+" "+Main.VERSION+s);
	}

	public void togglePause() {
		setPaused(!isPaused());
	}

}
