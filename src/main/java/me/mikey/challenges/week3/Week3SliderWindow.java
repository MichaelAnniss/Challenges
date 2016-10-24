package me.mikey.challenges.week3;

import javax.swing.*;

/**
 * Created by Michael on 22/10/2016.
 */
public class Week3SliderWindow extends JFrame {
	private JLabel fixedRadiusLabel = new JLabel("Fixed Radius (125)");
	private JLabel movingRadiusLabel = new JLabel("Moving Radius (5)");
	private JLabel centreOffsetLabel = new JLabel("Centre Offset (135)");
	private JLabel maxItersLabel = new JLabel("Max Iterations (10)");
	private JLabel incrementLabel = new JLabel("Increment (0.01)");

	private JSlider fixedRadiusSlider = new JSlider(1, 400, 125);
	private JSlider movingRadiusSlider = new JSlider(1, 400, 5);
	private JSlider centreOffsetSlider = new JSlider(1, 400, 135);
	private JSlider maxItersSlider = new JSlider(10, 300, 10);
	private JSlider incrementSlider = new JSlider(10, 1000, 10);

	public Week3SliderWindow() {
		setSize(100, 600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		fixedRadiusSlider.addChangeListener(e -> redraw());
		movingRadiusSlider.addChangeListener(e -> redraw());
		centreOffsetSlider.addChangeListener(e -> redraw());
		maxItersSlider.addChangeListener(e -> redraw());
		incrementSlider.addChangeListener(e -> redraw());

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		getContentPane().add(fixedRadiusLabel);
		getContentPane().add(fixedRadiusSlider);

		getContentPane().add(movingRadiusLabel);
		getContentPane().add(movingRadiusSlider);

		getContentPane().add(centreOffsetLabel);
		getContentPane().add(centreOffsetSlider);

		getContentPane().add(maxItersLabel);
		getContentPane().add(maxItersSlider);

		getContentPane().add(incrementLabel);
		getContentPane().add(incrementSlider);

		setVisible(true);
		pack();
	}

	public void redraw() {
		Spirograph spirograph = new Spirograph(fixedRadiusSlider.getValue(), movingRadiusSlider.getValue(), centreOffsetSlider.getValue(), maxItersSlider.getValue(), (double) (incrementSlider.getValue()) / 1000.0);
		Week3.getInstance().setCurrentSpirograph(spirograph);

		fixedRadiusLabel.setText("Fixed Radius (" + fixedRadiusSlider.getValue() + ")");
		movingRadiusLabel.setText("Moving Radius (" + movingRadiusSlider.getValue() + ")");
		centreOffsetLabel.setText("Centre Offset (" + centreOffsetSlider.getValue() + ")");
		maxItersLabel.setText("Max Iterations (" + maxItersSlider.getValue() + ")");
		incrementLabel.setText("Increment (" + ((double) incrementSlider.getValue() / 1000.0) + ")");
	}
}
