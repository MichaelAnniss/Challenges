package me.mikey.challenges.week2.util;

/**
 * Created by Michael on 16/10/16.
 */
public class AABB {
    private Position min;
    private Position max;

    //Take two positions and sort out min/max from there
    public AABB(Position a, Position b) {
        this.min = Position.from(
                Math.min(a.getX(), b.getX()),
                Math.min(a.getY(), b.getY())
        );

        this.max = Position.from(
                Math.max(a.getX(), b.getX()),
                Math.max(a.getY(), b.getY())
        );
    }

    public boolean intersectsWith(AABB aabb) {
        return aabb.getMax().getX() > this.min.getX() && aabb.getMax().getY() > this.min.getY() &&
                aabb.getMin().getX() < this.max.getX() && aabb.getMin().getY() < this.max.getY();
    }

    public boolean inside(Position pos) {
        return pos.getX() > this.min.getX() && pos.getX() < this.max.getX() &&
                pos.getY() > this.min.getY() && pos.getY() < this.max.getY();
    }

    public int getHeight() {
        return Math.abs(max.getY() - min.getY());
    }

    public int getWidth() {
        return Math.abs(max.getX() - min.getX());
    }

    public Position getMin() {
        return min;
    }

    public Position getMax() {
        return max;
    }
}
