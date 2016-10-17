package me.mikey.challenges.week2;

import me.mikey.challenges.week2.util.AABB;
import me.mikey.challenges.week2.util.Position;

import java.awt.*;

/**
 * Created by Michael on 15/10/16.
 */
public abstract class GUIComponent {

    //The current position of the component
    protected Position pos;

    //The original position of the component (for resetting later)
    protected Position initPos;

    //The color to use for this component
    protected Color color = Color.BLACK;

    //The bounding box of this component (calculated at runtime)
    protected AABB boundingBox;

    //Should this component collide with other components?
    protected boolean collisionEnabled = true;

    public GUIComponent(Position pos) {
        this.pos = pos;
        this.initPos = pos.clone();
    }

    //Called each time the component needs to update (purely for updating position)
    public abstract void tick();

    //Called each time the component needs to update
    public abstract void redraw(Graphics g);

    //Update the bounding box for this component
    public abstract void updateBoundingBox();

    //Retrieve the color to use for this component
    public Color getColor() {
        return color;
    }

    //Getters/setters

    public boolean isCollisionEnabled() {
        return collisionEnabled;
    }

    public void setCollisionEnabled(boolean collision) {
        this.collisionEnabled = collision;
    }

    public AABB getBoundingBox() {
        return boundingBox;
    }
}
