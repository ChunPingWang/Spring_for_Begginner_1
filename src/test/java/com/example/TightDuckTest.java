package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TightDuckTest {

    @Test
    public void rubber_duck_can_not_fly(){
        Duck rubberDuck = new RubberDuck();
        assertEquals("No Fly", rubberDuck.performFly());
    }
}
