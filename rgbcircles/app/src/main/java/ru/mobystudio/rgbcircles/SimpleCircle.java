package ru.mobystudio.rgbcircles;

/**
 * Created by molst on 10.07.2017.
 */

public class SimpleCircle {
    protected int x, y, radius, color;
    public SimpleCircle(int x1, int y1, int radius1){
        x = x1;
        y = y1;
        radius = radius1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }
    public void setColor(int col){
        this.color = col;
    }

    public SimpleCircle getCircleArea() {
        return new SimpleCircle(x-10, y-10, radius*3);
    }

    public boolean isIntersect(SimpleCircle circle) {
        return radius + circle.radius >= Math.sqrt(Math.pow(x-circle.getX(),2)+Math.pow(y-circle.getY(),2));
    }


}
