package me.mikey.challenges.week3;

/**
 * Created by Michael on 22/10/2016.
 */
public class Spirograph {
	private int fixedCircleRadius;
	private int movingCircleRadius;
	private int centreOffset;
	private int maxIters;
	private double increment;

	private int lastX = -1;
	private int lastY = -1;

	public Spirograph(int fixedCircleRadius, int movingCircleRadius, int centreOffset, int maxIters, double increment) {
		this.fixedCircleRadius = fixedCircleRadius;
		this.movingCircleRadius = movingCircleRadius;
		this.centreOffset = centreOffset;
		this.maxIters = maxIters;
		this.increment = increment;
	}

	public int getLastX() {
		return lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public int getNextX(double t, int centreX) {
		this.lastX = (int) ((this.fixedCircleRadius + this.movingCircleRadius) * Math.cos(t) -
						(this.movingCircleRadius + this.centreOffset) * Math.cos(
								((this.fixedCircleRadius + this.movingCircleRadius)/this.movingCircleRadius) * t
						)) + centreX;
		return this.lastX;
	}

	public int getNextY(double t, int centreY) {
		this.lastY = (int) ((this.fixedCircleRadius + this.movingCircleRadius) * Math.sin(t) -
						(this.movingCircleRadius + this.centreOffset) * Math.sin(
								((this.fixedCircleRadius + this.movingCircleRadius)/this.movingCircleRadius) * t
						)) + centreY;
		return this.lastY;
	}

	public int getMaxIters() {
		return maxIters;
	}

	public double getIncrement() {
		return increment;
	}

	public int getFixedCircleRadius() {
		return fixedCircleRadius;
	}

	public int getMovingCircleRadius() {
		return movingCircleRadius;
	}

	public int getCentreOffset() {
		return centreOffset;
	}

	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
	}
}
