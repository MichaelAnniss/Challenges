package me.mikey.challenges.week2;

import me.mikey.challenges.week2.listeners.Week2KeyListener;
import me.mikey.challenges.week2.listeners.Week2MouseListener;
import me.mikey.challenges.week2.util.Position;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

/**
 * Created by Michael on 17/10/16.
 */
public class Week2Application extends JFrame {
    private Image offScreenBuffer;

    private Week2Application() {
        setVisible(true);
        setSize(640, 480);
        CompatLayer.setInstance(new ApplicationLayer(this));
        System.out.println(getGraphics());
        CompatLayer.getInstance().setup(getGraphics());

        //Register listeners
        addMouseListener(new Week2MouseListener());
        addKeyListener(new Week2KeyListener());
    }

    @Override
    public void paint(Graphics g) {
        //Utilise an off screen buffer to remove flashing
        if (offScreenBuffer == null || offScreenBuffer.getWidth(this) != getWidth() ||
                offScreenBuffer.getHeight(this) != getHeight())
        {
            offScreenBuffer = this.createImage(getWidth(), getHeight());
        }

        Graphics offScreenGraphics = offScreenBuffer.getGraphics();

        //Clear screen and reset colour
        offScreenGraphics.setColor(Color.WHITE);
        offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());
        offScreenGraphics.setColor(Color.BLACK);

        //Let ComponentManager do all the work
        ComponentManager.getInstance().paint(offScreenGraphics);

        //Write the name of the tool in the corner of the screen
        ToolManager.getInstance().paint(offScreenGraphics);

        //Clear the screen
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        //Draw the buffer to the main applet
        g.drawImage(offScreenBuffer, 0, 0, this);
    }

    public static void main(String[] args) {
        new Week2Application();
    }

    private class ApplicationLayer extends CompatLayer {
        private Week2Application application;

        private ApplicationLayer(Week2Application application) {
            this.application = application;
        }

        @Override
        public int getWidth() {
            return application.getWidth();
        }

        @Override
        public int getHeight() {
            return application.getHeight();
        }

        @Override
        public Position getAdaptedMousePosition() {
            //Shift the location to account for the position of the applet on the screen (the MouseInfo location is relative
            // to the top right of the screen, the x and y needs to be relative to the top left of the applet)
            PointerInfo info = MouseInfo.getPointerInfo();
            Point point = info.getLocation();
            Point screenPos = application.getLocationOnScreen();
            point.translate((int) -screenPos.getX(), (int) -screenPos.getY());
            return Position.from((int) point.getX(), (int) point.getY());
        }

        @Override
        public void startTimer() {
            this.timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    paint(graphics);
                }
            }, 0, FRAME_PERIOD);
        }
    }
}
