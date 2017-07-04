package com.telefonica.interview;

import com.telefonica.interview.model.Command;
import com.telefonica.interview.model.State;

public interface MarsExplorable {

    State send(Command[] commands, State initialState);

}
