package com.telefonica.interview.model;

public class State {

    private int x;
    private int y;
    private Direction direction;

    public State(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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

}
