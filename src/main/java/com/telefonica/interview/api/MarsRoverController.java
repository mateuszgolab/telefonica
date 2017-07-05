package com.telefonica.interview.api;

import com.telefonica.interview.RoverController;
import com.telefonica.interview.model.Direction;
import com.telefonica.interview.model.Obstacle;
import com.telefonica.interview.model.RoverState;

import java.util.HashSet;
import java.util.Set;

import static com.telefonica.interview.model.Direction.*;

public class MarsRoverController implements RoverController {

    public static final int MARS_CIRCUIT = 21344000;
    private Set<Obstacle> obstacles;

    public MarsRoverController(Set<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public MarsRoverController() {
        this.obstacles = new HashSet<>();
    }


    @Override
    public RoverState send(final char[] commands, final RoverState initialRoverState) {

        RoverState roverState = initialRoverState;
        for (char command : commands) {
            roverState = send(command, roverState);
            if (roverState.getObstacle() != null) {
                break;
            }
        }

        return roverState;
    }


    private RoverState send(final char command, final RoverState roverState) {

        switch (command) {
            case 'l':
                return left(roverState);
            case 'r':
                return right(roverState);
            case 'f':
                return forward(roverState);
            case 'b':
                return backward(roverState);
            default:
                return roverState;
        }

    }

    private RoverState left(final RoverState roverState) {
        Direction newDirection = rotateLeft(roverState);
        return new RoverState(roverState.getX(), roverState.getY(), newDirection);
    }

    private RoverState right(RoverState roverState) {
        Direction newDirection = rotateRight(roverState);
        return new RoverState(roverState.getX(), roverState.getY(), newDirection);
    }

    private RoverState forward(RoverState roverState) {
        return move(roverState, 1);
    }

    private RoverState backward(RoverState roverState) {
        return move(roverState, -1);
    }

    private RoverState move(RoverState roverState, int move) {

        Obstacle potentialObstacle;

        switch (roverState.getDirection()) {
            case E:
                potentialObstacle = new Obstacle((roverState.getX() + move + MARS_CIRCUIT) % MARS_CIRCUIT, roverState.getY());
                break;
            case W:
                potentialObstacle = new Obstacle((roverState.getX() - move + MARS_CIRCUIT) % MARS_CIRCUIT, roverState.getY());
                break;
            case N:
                potentialObstacle = new Obstacle(roverState.getX(), (roverState.getY() + move + MARS_CIRCUIT) % MARS_CIRCUIT);
                break;
            case S:
                potentialObstacle = new Obstacle(roverState.getX(), (roverState.getY() - move + MARS_CIRCUIT) % MARS_CIRCUIT);
                break;
            default:
                return roverState;
        }

        if (obstacles.contains(potentialObstacle)) {
            return new RoverState(roverState.getX(), roverState.getY(), roverState.getDirection(), potentialObstacle);
        } else {
            // not an obstacle so making a move
            return new RoverState(potentialObstacle.getX(), potentialObstacle.getY(), roverState.getDirection());
        }

    }

    private Direction rotateLeft(RoverState roverState) {

        switch (roverState.getDirection()) {
            case E:
                return N;
            case W:
                return S;
            case S:
                return E;
            case N:
                return W;
            default:
                return roverState.getDirection();
        }
    }

    private Direction rotateRight(RoverState roverState) {

        switch (roverState.getDirection()) {
            case E:
                return S;
            case W:
                return N;
            case S:
                return W;
            case N:
                return E;
            default:
                return roverState.getDirection();
        }
    }

}
