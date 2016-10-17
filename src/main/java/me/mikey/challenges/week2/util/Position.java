package me.mikey.challenges.week2.util;

/**
 * Created by Michael on 16/10/16.
 */
public class Position implements Cloneable {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Pythagoras
    public int distance(Position pos) {
        return (int) Math.sqrt(
                Math.pow(pos.x - this.x, 2) +
                Math.pow(pos.y - this.y, 2)
        );
    }

    //To move this position slightly towards the given pos at the given speed
    public void tickTowards(Position pos, int speed) {
        if(this.x > pos.x) {
            this.x -= speed;

            //check if overshot
            if(this.x < pos.x) {
                this.x = pos.x;
            }
        }
        else if(this.x < pos.x) {
            this.x += speed;

            if(this.x > pos.x) {
                this.x = pos.x;
            }
        }

        if(this.y > pos.y) {
            this.y -= speed;

            if(this.y < pos.y) {
                this.y = pos.y;
            }
        }
        else if(this.y < pos.y) {
            this.y += speed;

            if(this.y > pos.y) {
                this.y = pos.y;
            }
        }
    }

    // To keep this position within the bounds of the window
    public void bounds(int width, int height, Position initial) {
        if(x > width)
            x = initial.getX();
        if(x < 0)
            x = width;

        if(y > height)
            y = initial.getY();
        if(y < 0)
            y = height;
    }

    public Position addX(int x) {
        this.x += x;
        return this;
    }

    public Position addY(int y) {
        this.y += y;
        return this;
    }

    @Override
    public Position clone() {
        return Position.from(this.x, this.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Position from(int x, int y) {
        return new Position(x, y);
    }
}
