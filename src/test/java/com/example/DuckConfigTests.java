package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class) // JUnit4與Spring5為 @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= DuckConfig.class)
public class DuckConfigTests {

    @Autowired
    Duck redheadDuck;

    @Autowired
    QuackBehavior quack;
    @Test
    public void beans_should_not_be_null_test(){
        assertNotNull(redheadDuck);
        assertNotNull(quack);
    }

    @Test
    public void duck_display_test(){
        assertEquals("RedheadDuck", redheadDuck.display());
    }

    @Test
    public void redheads_sound_quack_test(){
        assertEquals("Quack", redheadDuck.performQuack());
    }
}
