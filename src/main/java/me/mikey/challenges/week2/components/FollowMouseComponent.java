package me.mikey.challenges.week2.components;

import me.mikey.challenges.week2.*;
import me.mikey.challenges.week2.util.AABB;
import me.mikey.challenges.week2.util.Position;

import java.awt.*;

/**
 * Created by Michael on 15/10/16.
 */
public class FollowMouseComponent extends GUIComponent {

    private String text;
    private int speed;
    private int width;
    private int height;

    public FollowMouseComponent(String text, Position pos, int speed) {
        super(pos);
        this.setText(text);
        this.speed = speed;
    }

    public void setText(String text) {
        this.text = text;
        this.width = Week2.getInstance().getGraphics().getFontMetrics().stringWidth(text);
        this.height = Week2.getInstance().getGraphics().getFontMetrics().getHeight();
    }

    //Update the position to move towards the pointer
    @Override
    public void tick() {
        Position pos = Week2.getInstance().getAdaptedMousePosition();
        tickForMouse(pos);
    }

    //Separated to a different method for unit testing
    public void tickForMouse(Position target) {
        Position initialPos = this.pos.clone();

        //Try and move towards the targetX and targetY positions
        this.pos.tickTowards(target, this.speed);

        //Update first then check for collisions
        updateBoundingBox();

        //Check for collisions
        if(this.boundingBox != null && isCollisionEnabled()) {
            for (GUIComponent component : ComponentManager.getInstance().getComponents()) {
                if (component != this && component.isCollisionEnabled() && component.getBoundingBox() != null) {
                    if(component.getBoundingBox().intersectsWith(this.boundingBox)) {
                        //reset position
                        this.pos = initialPos;
                        break;
                    }
                }
            }
        }

        //Color based on distance
        int distance = this.pos.distance(target);

        if(distance == 0) {
            this.color = Color.GREEN;
        } else if(distance < 100) {
            this.color = Color.BLUE;
        } else {
            this.color = Color.RED;
        }
    }

    @Override
    public void redraw(Graphics g) {
        g.drawString(this.text, pos.getX(), pos.getY());
        updateBoundingBox();
    }

    @Override
    public void updateBoundingBox() {
        this.boundingBox = new AABB(
                Position.from(this.pos.getX(), this.pos.getY() - height),
                Position.from(this.pos.getX() + width, this.pos.getY())
        );
    }
}
