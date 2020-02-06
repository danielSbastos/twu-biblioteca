package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.lib.SystemWrapper;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;


public class QuitTest {

    @Test
    public void actionQuitApplication()
    {
        SystemWrapper systemMock = Mockito.mock(SystemWrapper.class);

        Quit quit = new Quit(1, "Quit system", systemMock);
        quit.action();

        verify(systemMock, times(1)).exit();
    }
}
