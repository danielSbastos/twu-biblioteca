package com.twu.biblioteca.models.options;

import com.twu.biblioteca.Menu;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.SystemWrapper;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class QuitTest {

    @Test
    public void actionQuitApplication()
    {
        SystemWrapper systemMock = Mockito.mock(SystemWrapper.class);

        Quit quit = new Quit(systemMock);
        quit.action();

        verify(systemMock, times(1)).exit();
    }
}
