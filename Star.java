import java.awt.Graphics;
import java.lang.Math;

public class Star {
	public static final int RADIUS = 500;
	
	private double mass, xPos, yPos, xVel, yVel, xForce, yForce;
	private boolean used = false;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + xPos);
		result = (int) (prime * result + yPos);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Star star = (Star) obj;
		if (xPos == star.getX() && yPos == star.getY()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Star(double xPos, double yPos, double xVel, double yVel, double mass) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = xVel;
		this.yVel = yVel;
		this.mass = mass;
	}
	
	public void usage(boolean b) {
		used = b;
	}
	
	public boolean checkUsed() {
		return used;
	}
	
	public String toString() {
		return mass + ", " + xPos + ", " + yPos + ", " + xVel + ", " + yVel + ", " + xForce + ", " + yForce;
	}
	
	public void move() {
		double speed = 1e+2;
		xPos += xVel * speed;
		yPos += yVel * speed;
		xVel += xForce / mass * speed;
		yVel += yForce / mass * speed;
	}
	
	public void plot(Graphics g, double scale) {
		if (xPos / scale <= 1 && yPos / scale <= 1 & xPos / scale >= -1 && yPos/ scale >= -1) {
			g.fillOval((int) Math.round(xPos * RADIUS / scale + RADIUS),(int) Math.round(RADIUS - yPos * RADIUS / scale), 3, 3);
		} else {
//			System.out.println("Outside of Plot: " + "(" + xPos + ", " + yPos + ")");
		}
		xForce = 0;
		yForce = 0;
	}
	
	public double getMass() {
		return mass;
	}
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}
	
	public double getXVel() {
		return xVel;
	}
	
	public double getYVel() {
		return yVel;
	} 
	
	public void addForce(double xForce, double yForce) {
		this.xForce += xForce;
		this.yForce += yForce;
	}
	
}
