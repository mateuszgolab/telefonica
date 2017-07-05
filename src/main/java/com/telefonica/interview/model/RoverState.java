package com.telefonica.interview.model;

public class RoverState {

    private int x;
    private int y;
    private Direction direction;
    private Obstacle obstacle;

    public RoverState(int x, int y, Direction direction, Obstacle obstacle) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.obstacle = obstacle;
    }

    public RoverState(int x, int y, Direction direction) {
        this(x, y, direction, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

}
