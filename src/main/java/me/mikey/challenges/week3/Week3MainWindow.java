package me.mikey.challenges.week3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;

/**
 * Created by Michael on 22/10/2016.
 */
public class Week3MainWindow extends JFrame {
	private Image offScreenBuffer;

	public Week3MainWindow() {
		setVisible(true);
		setSize(800, 600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	@Override
	public void paint(Graphics gr) {
		if (offScreenBuffer == null || offScreenBuffer.getWidth(this) != getWidth() ||
				offScreenBuffer.getHeight(this) != getHeight())
		{
			offScreenBuffer = this.createImage(getWidth(), getHeight());
		}

		Graphics g = offScreenBuffer.getGraphics();

		//Clear the buffer
		g.clearRect(0, 0, getWidth(), getHeight());

		//Anti aliasing on
		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
		);
		((Graphics2D) g).setRenderingHints(hints);

		Spirograph spirograph = Week3.getInstance().getCurrentSpirograph();

		for(double t = 0; t < spirograph.getMaxIters(); t += spirograph.getIncrement()) {
			int lastX = spirograph.getLastX();
			int lastY = spirograph.getLastY();
			int nextX = spirograph.getNextX(t, getWidth() / 2);
			int nextY = spirograph.getNextY(t, getHeight() / 2);

			if(lastX == -1 || lastY == -1) {
				continue;
			}

			g.drawLine(lastX, lastY, nextX, nextY);
		}

		spirograph.setLastX(-1);
		spirograph.setLastY(-1);

		g.dispose();
		gr.drawImage(offScreenBuffer, 0, 0, this);
		gr.dispose();
	}
}
