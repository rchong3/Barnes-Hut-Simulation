import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Plot extends JPanel implements ActionListener {
	
	public final static int SIZE = 1000;
	private LinkedList<Star> stars = new LinkedList<>();
	Timer t;
	private double scale;
	private double[] bounds = new double[4]; // {x0, y0, x1, y1}
	
	public Plot(double scale, int msPerFrame) {
		super();
		this.scale = scale;
		t = new Timer(msPerFrame, this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.drawLine(0, 500, 1000, 500);
		g.drawLine(495, 250, 505, 250);
		g.drawLine(495, 750, 505, 750);
		g.drawLine(250, 495, 250, 505);
		g.drawLine(750, 495, 750, 505);
		g.drawString("" + scale / 2 + " meters", 506, 254);
		g.drawLine(500, 0, 500, 1000);
		g.setColor(Color.BLACK);
		bounds[0] = stars.peek().getX();
		bounds[2] = stars.peek().getX();
		bounds[1] = stars.peek().getY();
		bounds[3] = stars.peek().getY();
		for (Star star: stars) {
//			System.out.println(star);
			if (bounds[0] > star.getX()) {
				bounds[0] = star.getX();
			} else if (bounds[2] < star.getX()) {
				bounds[2] = star.getX();
			}
			if (bounds[1] > star.getY()) {
				bounds[1] = star.getY();
			} else if (bounds[3] < star.getY()) {
				bounds[3] = star.getY();
			}
			star.plot(g, scale);
		}
//		System.out.println();
		t.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		for (Star star: stars) {
			star.usage(true);
			BarnesHut tree = new BarnesHut(bounds[0], bounds[1], bounds[2], bounds[3], stars);
			tree.netForce(star);
			star.move();
			if (bounds[0] > star.getX()) {
				bounds[0] = star.getX();
			} else if (bounds[2] < star.getX()) {
				bounds[2] = star.getX();
			}
			if (bounds[1] > star.getY()) {
				bounds[1] = star.getY();
			} else if (bounds[3] < star.getY()) {
				bounds[3] = star.getY();
			}
//			System.out.println(star);
			star.usage(false);
		}
//		System.out.println();
		repaint();
	}
	
	public void addStar(Star star) {
		stars.add(star);
	}
	
//	public static void main(String[] args) { //Just for testing and playing around
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				JFrame f = new JFrame();
//				Plot p = new Plot(1000, 1000);
//				p.addStar(new Star(15, 0, 0, 0, 999));
//				p.addStar(new Star(15, 0, 0, 0, 999));
//				f.setSize(SIZE, SIZE);
//				f.add(p);
//				f.setTitle("Plot");
//				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				f.setVisible(true);
//			}
//		});
//	}
}