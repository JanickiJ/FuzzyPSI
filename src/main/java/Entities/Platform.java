package Entities;

import javafx.scene.paint.Color;

public class Platform {
    private final double x;
    private final double y;
    private final double height;
    private final double weight;
    private final Color color;

    public Platform(double x, double y, double height, double weight) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.weight = weight;
        this.color = Color.BLACK;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }
}
