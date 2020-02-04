package com.twu.biblioteca;

import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SessionHandlerTest {

    List<Map> credentials;

    @Before
    public void buildCredentials() {
        Map credential1 = new HashMap<String, String>();
        credential1.put("username", "matheus");
        credential1.put("password", "qwerty");
        Map credential2 = new HashMap<String, String>();
        credential2.put("username", "daniel");
        credential2.put("password", "zxcvbn");

        this.credentials = new ArrayList<>();
        this.credentials.add(credential1);
        this.credentials.add(credential2);
    }

    @Test
    public void loginReturnTrueIfCorrectCredentials() throws IOException {
        OutputWriterWrapper outputWriter = Mockito.mock(OutputWriterWrapper.class);
        InputReaderWrapper inputReader = Mockito.mock(InputReaderWrapper.class);

        when(inputReader.readString()).thenReturn("matheus").thenReturn("qwerty");

        SessionHandler sessionHandler = new SessionHandler(this.credentials, inputReader, outputWriter);
        boolean successfulLogin = sessionHandler.login();

        verify(outputWriter, times(1)).writeString("Please input your username: ");
        verify(outputWriter, times(1)).writeString("Please input your password: ");
        assertEquals(successfulLogin, true);
    }

    @Test
    public void loginReturnsFalseIfNoUsername() throws IOException {
        OutputWriterWrapper outputWriter = Mockito.mock(OutputWriterWrapper.class);
        InputReaderWrapper inputReader = Mockito.mock(InputReaderWrapper.class);

        when(inputReader.readString()).thenThrow(new IOException());

        SessionHandler sessionHandler = new SessionHandler(this.credentials, inputReader, outputWriter);
        boolean successfulLogin = sessionHandler.login();

        verify(outputWriter, times(1)).writeString("Please input your username: ");
        verify(outputWriter, times(0)).writeString("Please input your password: ");
        assertEquals(successfulLogin, false);
    }

    @Test
    public void loginReturnsFalseIfNoPassword() throws IOException {
        OutputWriterWrapper outputWriter = Mockito.mock(OutputWriterWrapper.class);
        InputReaderWrapper inputReader = Mockito.mock(InputReaderWrapper.class);

        when(inputReader.readString()).thenReturn("matheus").thenThrow(new IOException());

        SessionHandler sessionHandler = new SessionHandler(this.credentials, inputReader, outputWriter);
        boolean successfulLogin = sessionHandler.login();

        verify(outputWriter, times(1)).writeString("Please input your username: ");
        verify(outputWriter, times(1)).writeString("Please input your password: ");
        assertEquals(successfulLogin, false);
    }
}
