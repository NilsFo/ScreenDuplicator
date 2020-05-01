package frames;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import java.awt.Font;

public class IntroFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static IntroFrame frame;
	private JLabel xLB;
	private JButton startLB;

	private Rectangle borders;
	private JLabel yLB;
	private JLabel widthLB;
	private JLabel heightLB;
	private JSpinner fpsSP;
	private JSpinner delaySP;
	private JCheckBoxMenuItem onTopCB;

	public IntroFrame() {
		setSize(294, 440);
		setLocationRelativeTo(null);
		setTitle(Main.TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		frame = this;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		startLB = new JButton("Duplizieren Starten");
		startLB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startDuplicate();
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bildschirmbereich", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Erweiterte Einstellungen",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(5))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addComponent(startLB, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
					.addGap(71))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 191, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(startLB)
					.addContainerGap())
		);

		JLabel lblFps = new JLabel("Bildwiederholunsrate:");

		fpsSP = new JSpinner();
		fpsSP.setModel(new SpinnerNumberModel(30, 10, 120, 1));

		delaySP = new JSpinner();
		delaySP.setModel(new SpinnerNumberModel(250, 0, 9001, 1));

		JLabel lblVerzgerungms = new JLabel("Verz\u00F6gerung (ms:)");

		JButton button = new JButton("?");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.showmessage(
						frame,
						"Wie viele FPS (Frames per second = Bilder pro Sekunde) werden wärend des Abfilmens angezeigt.\nEine hohe FPS benötigt mehr Rechenleistung, sorgt aber für eine weichere Anzeige.");
			}
		});

		JButton button_1 = new JButton("?");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.showmessage(frame, "Zeit zwischen drücken des Buttons und Auswahl des Kopier-Bereichs.");
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_panel_1
										.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFps)
										.addComponent(lblVerzgerungms))
						.addGap(18)
						.addGroup(
								gl_panel_1
										.createParallelGroup(Alignment.LEADING)
										.addComponent(delaySP,
												Alignment.TRAILING,
												GroupLayout.DEFAULT_SIZE, 67,
												Short.MAX_VALUE)
										.addComponent(fpsSP,
												Alignment.TRAILING,
												GroupLayout.DEFAULT_SIZE, 67,
												Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panel_1
										.createParallelGroup(Alignment.LEADING)
										.addComponent(button,
												Alignment.TRAILING)
										.addComponent(button_1,
												Alignment.TRAILING))
						.addContainerGap()));
		gl_panel_1
				.setVerticalGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblFps)
														.addComponent(
																fpsSP,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(button))
										.addGap(18)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																delaySP,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblVerzgerungms)
														.addComponent(button_1))
										.addContainerGap(13, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JButton btnNewButton_1 = new JButton("Bereich w\u00E4hlen");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeBorders();
			}
		});

		xLB = new JLabel("x: ");
		xLB.setFont(new Font("Tahoma", Font.BOLD, 11));

		yLB = new JLabel("y: ");
		yLB.setFont(new Font("Tahoma", Font.BOLD, 11));

		widthLB = new JLabel("Breite: ");
		widthLB.setFont(new Font("Tahoma", Font.BOLD, 11));

		heightLB = new JLabel("H\u00F6he: ");
		heightLB.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(xLB, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(yLB, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(widthLB, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(heightLB, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(49)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
					.addGap(38))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1)
					.addGap(18)
					.addComponent(xLB)
					.addGap(18)
					.addComponent(yLB, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(widthLB, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(heightLB)
					.addGap(8))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
		setAlwaysOnTop(true);
		requestFocus();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
				JMenuItem mntmber = new JMenuItem("\u00DCber");
				mntmber.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(frame, Main.TITLE+" "+Main.VERSION+"\n"+Main.DATE+" - "+Main.NAME, "Über",
								JOptionPane.INFORMATION_MESSAGE);
					}
				});
				mnDatei.add(mntmber);

		JSeparator separator = new JSeparator();
		mnDatei.add(separator);
		
				JMenuItem mntmBeenden = new JMenuItem("Beenden");
				mntmBeenden.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				mnDatei.add(mntmBeenden);

		JMenu mnBearbeiten = new JMenu("Bearbeiten");
		menuBar.add(mnBearbeiten);

		JMenuItem mntmBildschirmbereichndern = new JMenuItem(
				"Bildschirmbereich w\u00E4hlen");
		mntmBildschirmbereichndern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeBorders();
			}
		});
		mnBearbeiten.add(mntmBildschirmbereichndern);

		JSeparator separator_1 = new JSeparator();
		mnBearbeiten.add(separator_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Position zur\u00FCcksetzen");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveToCenter();
			}
		});
		
		onTopCB = new JCheckBoxMenuItem("Immer im Vordergrund");
		onTopCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		mnBearbeiten.add(onTopCB);
		
		JSeparator separator_2 = new JSeparator();
		mnBearbeiten.add(separator_2);
		mnBearbeiten.add(mntmNewMenuItem);

		JMenuItem mntmGreZurcksetzen = new JMenuItem(
				"Gr\u00F6\u00DFe zur\u00FCcksetzen");
		mntmGreZurcksetzen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSize(getMinimumSize());
			}
		});
		mnBearbeiten.add(mntmGreZurcksetzen);

		//pack();
		setMinimumSize(getSize());
		update();
	}

	public void update() {
		startLB.setEnabled(!(borders == null));
		if (borders != null) {
			xLB.setText("x: " + (int)borders.getX());
			yLB.setText("y: " + (int)borders.getY());
			widthLB.setText("Breite: " + (int)borders.getWidth());
			heightLB.setText("Höhe: " + (int)borders.getHeight());
		}
		setAlwaysOnTop();
	}

	private void moveToCenter() {
		Main.print("Start to move from: " + getLocation());
		Point p = getLocation();
		setLocationRelativeTo(null);

		int x = (int) getX();
		int y = (int) getY();
		setLocation(p);
		while (getX() != x | getY() != y) {
			int mx = getX();
			int my = getY();
			if (getX() < x) {
				mx++;
			}
			if (getX() > x) {
				mx--;
			}
			if (getY() < y) {
				my++;
			}
			if (getY() > y) {
				my--;
			}
			setLocation(mx, my);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Derzeitige Location: " + getLocation());
	}

	private void changeBorders() {
		setVisible(false);
		try {
			Thread.sleep((int) delaySP.getValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BorderFrame.chooseBorder();
		// setVisible(true);
		update();
	}

	private void startDuplicate() {
		setVisible(false);
		new DublicatorFrame(borders, (int)fpsSP.getValue(), onTopCB.isSelected());
	}
	
	public void setSelectedBounds(Rectangle rekt){
		setVisible(true);
		setState(NORMAL);
		borders = rekt;
		update();
		requestFocus();
	}

	private void setAlwaysOnTop() {
		setAlwaysOnTop(onTopCB.isSelected());
	}
}
