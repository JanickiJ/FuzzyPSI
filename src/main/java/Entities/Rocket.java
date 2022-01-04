package Entities;

import javafx.scene.paint.Color;

import static java.lang.Math.min;

public class Rocket {
    private double x;
    private double max_x;
    private double y;
    private double max_y;
    private double vx;
    private double vy;
    private double ax;
    private double ay;
    private double height;
    private double weight;
    private Color color;

    public Rocket(double x, double y, double max_x, double max_y) {
        this.x = x;
        this.y = y;
        this.color = Color.RED;
        this.ax = 0;
        this.ay = 3;
        this.vx = 0;
        this.vy = 0;
        this.weight = 40;
        this.height = 40;
        this.max_x = max_x;
        this.max_y = max_y;
    }

    public void move() {
        double t = 0.5;
        if (y != max_y) {
            x = min(x + vx * t + 0.5 * ax * t * t, max_x);
        }
        y = min(y + vy * t + 0.5 * ay * t * t, max_y);
        vx = min(vx + ax * t, 35);
        vy = min(vy + ay * t, 10);

    }

    public double getAx() {
        return ax;
    }

    public double getAy() {
        return ay;
    }

    public Color getColor() {
        return color;
    }

    public void setAx(double ax) {
        if (!Double.isNaN(ax)) {
            this.ax = ax;
        }
    }

    public void setAy(double ay) {
        if (!Double.isNaN(ay)) {
            this.ay = ay;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }
}
