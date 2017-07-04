package com.telefonica.interview.api;

import com.telefonica.interview.MarsExplorable;
import com.telefonica.interview.model.Command;
import com.telefonica.interview.model.Direction;
import com.telefonica.interview.model.State;

import static com.telefonica.interview.model.Direction.*;

public class MarsExplorationAPI implements MarsExplorable {


    public State send(Command[] commands, State initialState) {

        State state = initialState;
        for (Command command : commands) {
            state = send(command, state);
        }

        return state;
    }


    private State send(final Command command, final State state) {

        switch (command) {
            case LEFT:
                return left(state);
            case RIGHT:
                return right(state);
            case FORWARD:
                return forward(state);
            case BACKWARD:
                return backward(state);
            default:
                return state;
        }

    }

    private State left(final State state) {
        Direction newDirection = rotateLeft(state);
        return new State(state.getX(), state.getY(), newDirection);
    }

    private State right(State state) {
        Direction newDirection = rotateRight(state);
        return new State(state.getX(), state.getY(), newDirection);
    }

    private State forward(State state) {
        return move(state, 1);
    }

    private State backward(State state) {
        return move(state, -1);
    }

    private State move(State state, int move) {

        switch (state.getDirection()) {
            case E:
                return new State(state.getX() + move, state.getY(), state.getDirection());
            case W:
                return new State(state.getX() - move, state.getY(), state.getDirection());
            case N:
                return new State(state.getX(), state.getY() + move, state.getDirection());
            case S:
                return new State(state.getX(), state.getY() - move, state.getDirection());
            default:
                return state;
        }
    }

    private Direction rotateLeft(State state) {

        switch (state.getDirection()) {
            case E:
                return N;
            case W:
                return S;
            case S:
                return E;
            case N:
                return W;
            default:
                return state.getDirection();
        }
    }

    private Direction rotateRight(State state) {

        switch (state.getDirection()) {
            case E:
                return S;
            case W:
                return N;
            case S:
                return W;
            case N:
                return E;
            default:
                return state.getDirection();
        }
    }

}
