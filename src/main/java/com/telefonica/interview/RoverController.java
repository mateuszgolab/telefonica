package com.telefonica.interview;

import com.telefonica.interview.model.RoverState;

public interface RoverController {

    RoverState send(char[] commands, RoverState initialRoverState);

}
