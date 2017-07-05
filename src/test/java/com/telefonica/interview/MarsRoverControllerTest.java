package com.telefonica.interview;

import com.telefonica.interview.api.MarsRoverController;
import com.telefonica.interview.model.Direction;
import com.telefonica.interview.model.Obstacle;
import com.telefonica.interview.model.RoverState;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.telefonica.interview.api.MarsRoverController.MARS_CIRCUIT;
import static com.telefonica.interview.model.Direction.E;
import static com.telefonica.interview.model.Direction.S;
import static com.telefonica.interview.model.Direction.W;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class MarsRoverControllerTest {

    private RoverController marsExplorer = new MarsRoverController();

    @Test
    public void testMoveForward() {

        //given
        RoverState initialRoverState = new RoverState(1, 1, Direction.N);
        char[] commands = new char[]{'f'};

        // when
        RoverState finalRoverState = marsExplorer.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(Direction.N));
        assertThat(finalRoverState.getX(), is(initialRoverState.getX()));
        assertThat(finalRoverState.getY(), is(initialRoverState.getY() + 1));
        assertNull(finalRoverState.getObstacle());

    }

    @Test
    public void testMoveBackward() {

        //given
        RoverState initialRoverState = new RoverState(2, 2, Direction.N);
        char[] commands = new char[]{'b'};

        // when
        RoverState finalRoverState = marsExplorer.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(Direction.N));
        assertThat(finalRoverState.getX(), is(initialRoverState.getX()));
        assertThat(finalRoverState.getY(), is(initialRoverState.getY() - 1));
        assertNull(finalRoverState.getObstacle());

    }

    @Test
    public void testTurnLeft() {

        //given
        RoverState initialRoverState = new RoverState(2, 2, Direction.N);
        char[] commands = new char[]{'l'};

        // when
        RoverState finalRoverState = marsExplorer.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(W));
        assertThat(finalRoverState.getX(), is(initialRoverState.getX()));
        assertThat(finalRoverState.getY(), is(initialRoverState.getY()));
        assertNull(finalRoverState.getObstacle());

    }

    @Test
    public void testTurnRight() {

        //given
        RoverState initialRoverState = new RoverState(1, 1, Direction.N);
        char[] commands = new char[]{'r'};

        // when
        RoverState finalRoverState = marsExplorer.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(E));
        assertThat(finalRoverState.getX(), is(initialRoverState.getX()));
        assertThat(finalRoverState.getY(), is(initialRoverState.getY()));
        assertNull(finalRoverState.getObstacle());

    }

    @Test
    public void testMakeCircle() {

        //given
        RoverState initialRoverState = new RoverState(1, 1, Direction.N);
        char[] commands = new char[]{'f', 'l', 'f', 'l', 'f', 'l', 'f', 'l'};

        // when
        RoverState finalRoverState = marsExplorer.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(initialRoverState.getDirection()));
        assertThat(finalRoverState.getX(), is(initialRoverState.getX()));
        assertThat(finalRoverState.getY(), is(initialRoverState.getY()));
        assertNull(finalRoverState.getObstacle());

    }

    @Test
    public void testMakeCircleBackwards() {

        //given
        RoverState initialRoverState = new RoverState(1, 1, Direction.N);
        char[] commands = new char[]{'b', 'r', 'b', 'r', 'b', 'r', 'b', 'r'};

        // when
        RoverState finalRoverState = marsExplorer.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(initialRoverState.getDirection()));
        assertThat(finalRoverState.getX(), is(initialRoverState.getX()));
        assertThat(finalRoverState.getY(), is(initialRoverState.getY()));
        assertNull(finalRoverState.getObstacle());

    }

    @Test
    public void testWrappingHorizontallyEastDirection() {


        //given
        RoverState initialRoverState = new RoverState(MARS_CIRCUIT - 1, 1, E);
        char[] commands = new char[]{'f', 'f'};

        // when
        RoverState finalRoverState = marsExplorer.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(initialRoverState.getDirection()));
        assertThat(finalRoverState.getX(), is(1));
        assertThat(finalRoverState.getY(), is(initialRoverState.getY()));
        assertNull(finalRoverState.getObstacle());

    }

    @Test
    public void testWrappingHorizontallyWestDirection() {


        //given
        RoverState initialRoverState = new RoverState(1, 1, W);
        char[] commands = new char[]{'f', 'f'};

        // when
        RoverState finalRoverState = marsExplorer.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(initialRoverState.getDirection()));
        assertThat(finalRoverState.getX(), is(MARS_CIRCUIT - 1));
        assertThat(finalRoverState.getY(), is(initialRoverState.getY()));
        assertNull(finalRoverState.getObstacle());

    }

    @Test
    public void testWrappingVerticallyNorthDirection() {

        //given
        RoverState initialRoverState = new RoverState(1, MARS_CIRCUIT - 1, Direction.N);
        char[] commands = new char[]{'f', 'f'};

        // when
        RoverState finalRoverState = marsExplorer.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(initialRoverState.getDirection()));
        assertThat(finalRoverState.getX(), is(initialRoverState.getX()));
        assertThat(finalRoverState.getY(), is(1));
        assertNull(finalRoverState.getObstacle());

    }

    @Test
    public void testWrappingVerticallySouthDirection() {

        //given
        RoverState initialRoverState = new RoverState(1, 1, Direction.S);
        char[] commands = new char[]{'f', 'f'};

        // when
        RoverState finalRoverState = marsExplorer.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(initialRoverState.getDirection()));
        assertThat(finalRoverState.getX(), is(initialRoverState.getX()));
        assertThat(finalRoverState.getY(), is(MARS_CIRCUIT - 1));
        assertNull(finalRoverState.getObstacle());

    }

    @Test
    public void testObstacleDetected() {

        //given
        Set<Obstacle> obstacles = new HashSet<>();
        obstacles.add(new Obstacle(2, 2));

        RoverState initialRoverState = new RoverState(1, 1, Direction.N);
        char[] commands = new char[]{'f', 'r', 'f', 'f', 'l'};

        // when
        MarsRoverController marsExplorerWithObstacles = new MarsRoverController(obstacles);
        RoverState finalRoverState = marsExplorerWithObstacles.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(E));
        assertThat(finalRoverState.getX(), is(1));
        assertThat(finalRoverState.getY(), is(2));
        assertNotNull(finalRoverState.getObstacle());
        assertThat(finalRoverState.getObstacle().getX(), is(2));
        assertThat(finalRoverState.getObstacle().getY(), is(2));

    }

    @Test
    public void testGridWithObstaclesOutsideOfPath() {

        //given
        Set<Obstacle> obstacles = new HashSet<>();
        obstacles.add(new Obstacle(2, 2));
        obstacles.add(new Obstacle(2, 5));

        RoverState initialRoverState = new RoverState(1, 1, Direction.N);
        char[] commands = new char[]{'f', 'f', 'r', 'f', 'f', 'r'};

        // when
        MarsRoverController marsExplorerWithObstacles = new MarsRoverController(obstacles);
        RoverState finalRoverState = marsExplorerWithObstacles.send(commands, initialRoverState);

        // then
        assertThat(finalRoverState.getDirection(), is(S));
        assertThat(finalRoverState.getX(), is(3));
        assertThat(finalRoverState.getY(), is(3));
        assertNull(finalRoverState.getObstacle());

    }

}