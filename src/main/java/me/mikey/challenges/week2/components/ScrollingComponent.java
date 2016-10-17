package me.mikey.challenges.week2.components;

import me.mikey.challenges.week2.*;
import me.mikey.challenges.week2.util.AABB;
import me.mikey.challenges.week2.util.Position;

import java.awt.*;

/**
 * Created by Michael on 15/10/16.
 */
public class ScrollingComponent extends GUIComponent {

    //Screen position
    private String text;
    private int speed;
    private TextDirection dir;

    private int width;
    private int height;

    public ScrollingComponent(String text, Position pos, int speed, TextDirection dir) {
        super(pos);
        this.setText(text);
        this.speed = speed;
        this.dir = dir;
    }

    public void setText(String text) {
        this.text = text;
        this.width = CompatLayer.getInstance().getGraphics().getFontMetrics().stringWidth(this.text);
        this.height = CompatLayer.getInstance().getGraphics().getFontMetrics().getHeight();
    }

    //update the coordinates
    @Override
    public void tick() {
        Position posBefore = this.pos.clone();

        switch(this.dir) {
            case LEFT_TO_RIGHT:
                pos.addX(this.speed);
                break;
            case RIGHT_TO_LEFT:
                pos.addX(-this.speed);
                break;
            case TOP_TO_BOTTOM:
                pos.addY(this.speed);
                break;
            case BOTTOM_TO_TOP:
                pos.addY(-this.speed);
                break;
        }

        pos.bounds(CompatLayer.getInstance().getWidth(), CompatLayer.getInstance().getHeight(), this.initPos);
        updateBoundingBox();

        if(this.boundingBox != null && this.isCollisionEnabled()) {
            for (GUIComponent component : ComponentManager.getInstance().getComponents()) {
                if (component.isCollisionEnabled() && component.getBoundingBox() != null &&
                        component != this && component.getBoundingBox().intersectsWith(this.boundingBox)) {
                    pos = posBefore;
                }
            }
        }
    }

    @Override
    public void redraw(Graphics g) {
        g.drawString(this.text, this.pos.getX(), this.pos.getY());
        updateBoundingBox();
    }

    @Override
    public void updateBoundingBox() {
        this.boundingBox = new AABB(
                Position.from(this.pos.getX(), this.pos.getY() - height),
                Position.from(this.pos.getX() + width, this.pos.getY())
        );
    }

    public enum TextDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT,
        TOP_TO_BOTTOM,
        BOTTOM_TO_TOP
    }
}
