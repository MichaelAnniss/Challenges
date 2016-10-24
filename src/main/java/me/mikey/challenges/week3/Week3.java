package me.mikey.challenges.week3;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Michael on 22/10/2016.
 */
public class Week3 {
	private static Week3 instance;

	private Timer timer;
	private Spirograph currentSpirograph;
	private Week3MainWindow mainWindow;
	private Week3SliderWindow sliderWindow;

	private Week3() {}

	private void start() {
		this.currentSpirograph = new Spirograph(125, 5, 135, 10, 0.01);
		this.timer = new Timer();
		this.mainWindow = new Week3MainWindow();
		this.sliderWindow = new Week3SliderWindow();
		this.timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				mainWindow.repaint();
			}
		}, 20, 20);
	}

	public void setCurrentSpirograph(Spirograph currentSpirograph) {
		this.currentSpirograph = currentSpirograph;
	}

	public Spirograph getCurrentSpirograph() {
		return currentSpirograph;
	}

	public static void main(String[] args) {
		instance = new Week3();
		instance.start();
	}

	public static Week3 getInstance() {
		return instance;
	}
}
