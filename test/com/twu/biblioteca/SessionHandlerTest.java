package com.twu.biblioteca;

import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.CurrentUser;
import com.twu.biblioteca.models.User;
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

    List<User> users = new ArrayList<>();

    @Before
    public void buildCredentials() {
        User.deleteAll();

        User userOne = new User("xxx-xxxx", "password-one", "customer");
        User userTwo = new User("yyy-yyyy", "password-two", "librarian");

        users.add(userOne);
        users.add(userTwo);
    }

    @Test
    public void loginReturnTrueIfCorrectCredentials() throws IOException {
        OutputWriterWrapper outputWriter = Mockito.mock(OutputWriterWrapper.class);
        InputReaderWrapper inputReader = Mockito.mock(InputReaderWrapper.class);

        when(inputReader.readString()).thenReturn("xxx-xxxx").thenReturn("password-one");

        SessionHandler sessionHandler = new SessionHandler(this.users, inputReader, outputWriter);
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

        SessionHandler sessionHandler = new SessionHandler(this.users, inputReader, outputWriter);
        boolean successfulLogin = sessionHandler.login();

        verify(outputWriter, times(1)).writeString("Please input your username: ");
        verify(outputWriter, times(0)).writeString("Please input your password: ");
        assertEquals(successfulLogin, false);
    }

    @Test
    public void loginReturnsFalseIfNoPassword() throws IOException {
        OutputWriterWrapper outputWriter = Mockito.mock(OutputWriterWrapper.class);
        InputReaderWrapper inputReader = Mockito.mock(InputReaderWrapper.class);

        when(inputReader.readString()).thenReturn("xxx-xxxx").thenThrow(new IOException());

        SessionHandler sessionHandler = new SessionHandler(this.users, inputReader, outputWriter);
        boolean successfulLogin = sessionHandler.login();

        verify(outputWriter, times(1)).writeString("Please input your username: ");
        verify(outputWriter, times(1)).writeString("Please input your password: ");
        assertEquals(successfulLogin, false);
    }

    @Test
    public void loginSetsCurrentUser() throws IOException {
        User expectedCurrentUser = this.users.get(0);

        OutputWriterWrapper outputWriter = Mockito.mock(OutputWriterWrapper.class);
        InputReaderWrapper inputReader = Mockito.mock(InputReaderWrapper.class);

        when(inputReader.readString()).thenReturn(expectedCurrentUser.libraryId).thenReturn(expectedCurrentUser.password);

        SessionHandler sessionHandler = new SessionHandler(this.users, inputReader, outputWriter);
        sessionHandler.login();

        assertEquals(CurrentUser.get(), expectedCurrentUser);
    }
}
