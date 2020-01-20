package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MenuTest {

    @Test
    public void getOptionsReturnOptions()
    {
        String[] options = {"Option1", "Option2"};
        Menu menu = new Menu(options);

        String[] expectedOptions = menu.getOptions();

        assertEquals(expectedOptions, options);
    }
}
