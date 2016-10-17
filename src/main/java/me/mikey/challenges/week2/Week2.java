package me.mikey.challenges.week2;

import me.mikey.challenges.week2.listeners.Week2KeyListener;
import me.mikey.challenges.week2.listeners.Week2MouseListener;
import me.mikey.challenges.week2.tools.FollowMouseTool;
import me.mikey.challenges.week2.tools.PlacementTool;
import me.mikey.challenges.week2.tools.ScrollingTextTool;
import me.mikey.challenges.week2.util.Position;

import java.applet.Applet;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

// <applet code = "Week2" width = 640 height = 480> </applet>
public class Week2 extends Applet {
    private static final long FRAME_PERIOD = 100;
    private static Week2 instance;

    private Timer timer;
    private boolean drawBoundingBoxes = true;
    private Image offScreenBuffer;

    @Override
    public void start() {
        //Set singleton instance
        instance = this;

        //Create timer instance and retrieve Graphics instance
        final Graphics graphics = getGraphics();
        this.timer = new Timer(true);

        //Initialise components
        ComponentManager.getInstance().init();

        //Start paint timer
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                paint(graphics);
            }
        }, 0, FRAME_PERIOD);

        //Register listeners
        addMouseListener(new Week2MouseListener());
        addKeyListener(new Week2KeyListener());

        //Register tools
        ToolManager.getInstance().registerTool(new ScrollingTextTool());
        ToolManager.getInstance().registerTool(new FollowMouseTool());
    }

    @Override
    public void stop() {
        timer.cancel();
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

    public boolean isDrawBoundingBoxes() {
        return drawBoundingBoxes;
    }

    public void setDrawBoundingBoxes(boolean drawBoundingBoxes) {
        this.drawBoundingBoxes = drawBoundingBoxes;
    }

    public Position getAdaptedMousePosition() {
        //Shift the location to account for the position of the applet on the screen (the MouseInfo location is relative
        // to the top right of the screen, the x and y needs to be relative to the top left of the applet)
        PointerInfo info = MouseInfo.getPointerInfo();
        Point point = info.getLocation();
        Point screenPos = Week2.getInstance().getLocationOnScreen();
        point.translate((int) -screenPos.getX(), (int) -screenPos.getY());
        return Position.from((int) point.getX(), (int) point.getY());
    }

    public static Week2 getInstance() {
        return instance;
    }
}
