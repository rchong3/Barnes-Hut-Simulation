import java.util.LinkedList;

public class BarnesHut {
	private BarnesHut TopLeft = null, TopRight = null, BotLeft = null, BotRight = null;
	private double x0, y0, x1, y1, mass = 0;
	private double comX = 0, comY = 0;
	private LinkedList<Star> stars;
	/*
	 *         x1, y1
	 * 
	 * 
	 * x0, y0
	 */

	public BarnesHut(double x0, double y0, double x1, double y1, LinkedList<Star> stars) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.stars = stars;
		if (stars.size() > 1) {
			split();
		} else if (stars.size() == 1) {
			comX = stars.peek().getX();
			comY = stars.peek().getY();
			mass = stars.peek().getMass();
		}
	}
	
	private void split() {
		LinkedList<Star> starsTL = new LinkedList<>();
		LinkedList<Star> starsTR = new LinkedList<>();
		LinkedList<Star> starsBL = new LinkedList<>();
		LinkedList<Star> starsBR = new LinkedList<>();
		for (Star star : stars) {
			mass += star.getMass();
			comX += star.getX() * star.getMass();
			comY += star.getY() * star.getMass();
			if (star.getX() <= (x0 + x1) / 2) {
				if (star.getY() <= (y0 + y1) / 2) {
					starsBL.add(star);
				} else {
					starsTL.add(star);
				}
			} else {
				if (star.getY() <= (y0 + y1) / 2) {
					starsBR.add(star);
				} else {
					starsTR.add(star);
				}
			}
		}
		comX /= mass;
		comY /= mass;
		if (starsTL.size() > 0) {
			TopLeft = new BarnesHut(x0, (y0 + y1) / 2, (x0 + x1) / 2, y1, starsTL);
		}
		if (starsTR.size() > 0) {
			TopRight = new BarnesHut((x0 + x1) / 2, (y0 + y1) / 2, x1, y1, starsTR);
		}
		if (starsBL.size() > 0) {
			BotLeft = new BarnesHut(x0, y0, (x0 + x1) / 2, (y0 + y1) / 2, starsBL);
		}
		if (starsBR.size() > 0) {
			BotRight = new BarnesHut((x0 + x1) / 2, y0, x1, (y0 + y1) / 2, starsBR);
		}
	}

	public void netForce(Star star) {
		double theta = 1; // adjustable constant to compare to box width / distance (lower value means
								// greater accuracy)
		if (BotLeft == null && BotRight == null && TopLeft == null && TopRight == null) {
			if (stars.size() == 1 && !stars.peek().checkUsed()) {
				calcForce(star, comX, comY, mass);
			}
		} else {
			double sd = (x1 - x0) / calcDistance(star, comX, comY);
			if (sd < theta) {
				calcForce(star, comX, comY, mass);
			} else {
				if (TopLeft != null) {
					TopLeft.netForce(star);
				}
				if (TopRight != null) {
					TopRight.netForce(star);
				}
				if (BotLeft != null) {
					BotLeft.netForce(star);
				}
				if (BotRight != null) {
					BotRight.netForce(star);
				}
			}
		}
	}

	private double calcDistance(Star star0, double x, double y) {
		double dx = star0.getX() - x;
		double dy = star0.getY() - y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	private void calcForce(Star star0, double x, double y, double mass) {
		double G = 6.67e-11;
		double soften = 1e+2; //weakens gravity as r approaches 0
		double dx = x - star0.getX();
		double dy = y - star0.getY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		double force = (G * star0.getMass() * mass) / (dist * dist + soften);
		double xForce = force * dx / dist;
		double yForce = force * dy / dist;
		star0.addForce(xForce, yForce);
	}
}