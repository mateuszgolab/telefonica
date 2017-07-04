package com.telefonica.interview;

import com.telefonica.interview.api.MarsExplorationAPI;
import com.telefonica.interview.model.Command;
import com.telefonica.interview.model.Direction;
import com.telefonica.interview.model.State;
import org.junit.Test;

import static com.telefonica.interview.model.Command.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MarsExplorationAPITest {

    private MarsExplorable marsExplorer = new MarsExplorationAPI();

    @Test
    public void testMoveForward() {

        //given
        State state = new State(1, 1, Direction.N);
        Command[] commands = new Command[]{FORWARD};

        // when
        State result = marsExplorer.send(commands, state);

        // then
        assertThat(result.getDirection(), is(Direction.N));
        assertThat(result.getX(), is(state.getX()));
        assertThat(result.getY(), is(state.getY() + 1));

    }

    @Test
    public void testMoveBackward() {

        //given
        State state = new State(1, 1, Direction.N);
        Command[] commands = new Command[]{BACKWARD};

        // when
        State result = marsExplorer.send(commands, state);

        // then
        assertThat(result.getDirection(), is(Direction.N));
        assertThat(result.getX(), is(state.getX()));
        assertThat(result.getY(), is(state.getY() - 1));

    }

    @Test
    public void testTurnLeft() {

        //given
        State state = new State(1, 1, Direction.N);
        Command[] commands = new Command[]{LEFT};

        // when
        State result = marsExplorer.send(commands, state);

        // then
        assertThat(result.getDirection(), is(Direction.W));
        assertThat(result.getX(), is(state.getX()));
        assertThat(result.getY(), is(state.getY()));

    }

    @Test
    public void testTurnRight() {

        //given
        State state = new State(1, 1, Direction.N);
        Command[] commands = new Command[]{RIGHT};

        // when
        State result = marsExplorer.send(commands, state);

        // then
        assertThat(result.getDirection(), is(Direction.E));
        assertThat(result.getX(), is(state.getX()));
        assertThat(result.getY(), is(state.getY()));

    }

    @Test
    public void testMakeCircle() {

        //given
        State state = new State(1, 1, Direction.N);
        Command[] commands = new Command[]{FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT};

        // when
        State result = marsExplorer.send(commands, state);

        // then
        assertThat(result.getDirection(), is(state.getDirection()));
        assertThat(result.getX(), is(state.getX()));
        assertThat(result.getY(), is(state.getY()));

    }

    @Test
    public void testMakeCircleBackwards() {

        //given
        State state = new State(1, 1, Direction.N);
        Command[] commands = new Command[]{BACKWARD, RIGHT, BACKWARD, RIGHT, BACKWARD, RIGHT, BACKWARD, RIGHT};

        // when
        State result = marsExplorer.send(commands, state);

        // then
        assertThat(result.getDirection(), is(state.getDirection()));
        assertThat(result.getX(), is(state.getX()));
        assertThat(result.getY(), is(state.getY()));

    }

}