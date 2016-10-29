package me.mikey.challenges.week3;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Michael on 24/10/2016.
 */
public class Week3Test {
	@Mock
	private Graphics graphics;

	@Mock
	private Week3MainWindow mainWindow;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
/*
	@Test
	public void testInitialisation() {
		Spirograph spirograph = new Spirograph(10, 10, 10, 10, 1);
		Week3 week3 = mock(Week3.class);
		//Week3.setInstance(week3);

		//Set up calls
		when(mainWindow.getHeight()).thenReturn(600);
		when(mainWindow.getWidth()).thenReturn(800);
		when(week3.getCurrentSpirograph()).thenReturn(spirograph);

		mainWindow.paint(graphics);

		//Some sort of image was drawn (could expand this to choose exactly what line is drawn based on spirograph patterns
		verify(graphics, times(1)).clearRect(eq(0), eq(0), eq(600), eq(800));
		verify(graphics, times(1)).drawImage(any(Image.class), eq(0), eq(0), any(Week3MainWindow.class));

		//Verify the Graphics instance was disposed of properly
		verify(graphics, times(1)).dispose();
		mainWindow.paint(graphics);
	}
*/
	@Test
	public void testSpirograph() {
		Spirograph spirograph = new Spirograph(10, 11, 12, 100, 0.1);

		assertEquals(spirograph.getFixedCircleRadius(), 10);
		assertEquals(spirograph.getMovingCircleRadius(), 11);
		assertEquals(spirograph.getCentreOffset(), 12);
		assertEquals(spirograph.getMaxIters(), 100);
		assertTrue(spirograph.getIncrement() == 0.1);
	}


}
