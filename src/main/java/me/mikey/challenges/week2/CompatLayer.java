package me.mikey.challenges.week2;

import me.mikey.challenges.week2.listeners.Week2KeyListener;
import me.mikey.challenges.week2.listeners.Week2MouseListener;
import me.mikey.challenges.week2.tools.FollowMouseTool;
import me.mikey.challenges.week2.tools.ScrollingTextTool;
import me.mikey.challenges.week2.util.Position;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Michael on 17/10/16.
 */
public abstract class CompatLayer {
    private static CompatLayer instance;
    protected static final long FRAME_PERIOD = 100;

    protected boolean drawBoundingBoxes;
    protected Timer timer;
    protected Graphics graphics;

    public void setup(final Graphics graphics) {
        this.graphics = graphics;

        //Create timer instance and retrieve Graphics instance
        this.timer = new java.util.Timer(true);

        //Initialise components
        ComponentManager.getInstance().init();

        //Start paint timer
        startTimer();

        //Register tools
        ToolManager.getInstance().registerTool(new ScrollingTextTool());
        ToolManager.getInstance().registerTool(new FollowMouseTool());
    }

    public boolean isDrawBoundingBoxes() {
        return drawBoundingBoxes;
    }

    public void setDrawBoundingBoxes(boolean drawBoundingBoxes) {
        this.drawBoundingBoxes = drawBoundingBoxes;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract Position getAdaptedMousePosition();

    public abstract void startTimer();

    public static CompatLayer getInstance() {
        return instance;
    }

    public static void setInstance(CompatLayer layer) {
        instance = layer;
    }
}
