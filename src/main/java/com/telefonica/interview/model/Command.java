package com.telefonica.interview.model;

public enum Command {

    FORWARD('f'),
    BACKWARD('b'),
    LEFT('l'),
    RIGHT('r');


    private char character;

    Command(char c) {
        this.character = c;
    }


}
