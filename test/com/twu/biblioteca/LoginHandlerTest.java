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

public class LoginHandlerTest {

    List<Map> credentials;

    @Before
    public void buildCredentials()
    {
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
    public void validReturnsTrue() {
        LoginHandler loginHandler = new LoginHandler(this.credentials);
        boolean isValidCredential = loginHandler.validateCredentials("matheus", "qwerty");

        assertEquals(isValidCredential, true);
    }

    @Test
    public void validReturnsFalseForWrongPassword() {
        LoginHandler loginHandler = new LoginHandler(this.credentials);
        boolean isValidCredential = loginHandler.validateCredentials("matheus", "<wrong-password>");

        assertEquals(isValidCredential, false);
    }

    @Test
    public void validReturnsFalseForWrongUsername() {
        LoginHandler loginHandler = new LoginHandler(this.credentials);
        boolean isValidCredential = loginHandler.validateCredentials("<wrong-username>", "qwerty");

        assertEquals(isValidCredential, false);
    }

    @Test
    public void validReturnsFalseForWrongUsernameAndPassword() {
        LoginHandler loginHandler = new LoginHandler(this.credentials);
        boolean isValidCredential = loginHandler.validateCredentials("<wrong-username>", "<wrong-password>");

        assertEquals(isValidCredential, false);
    }

    @Test
    public void promptCredentialReturnCredentials() throws IOException {
        OutputWriterWrapper outputWriter = Mockito.mock(OutputWriterWrapper.class);
        InputReaderWrapper inputReader = Mockito.mock(InputReaderWrapper.class);

        when(inputReader.readString()).thenReturn("matheus").thenReturn("qwerty");

        LoginHandler loginHandler = new LoginHandler(this.credentials, inputReader, outputWriter);
        Map<String, String> credential = loginHandler.promptCredential();

        verify(outputWriter, times(1)).writeString("Please input your username: ");
        verify(outputWriter, times(1)).writeString("Please input your password: ");
        assertEquals(credential.get("username"), "matheus");
        assertEquals(credential.get("password"), "qwerty");
    }

    @Test(expected = IOException.class)
    public void promptCredentialRaisesErrorIfNoUsername() throws IOException {
        OutputWriterWrapper outputWriter = Mockito.mock(OutputWriterWrapper.class);
        InputReaderWrapper inputReader = Mockito.mock(InputReaderWrapper.class);

        when(inputReader.readString()).thenThrow(new IOException());

        LoginHandler loginHandler = new LoginHandler(this.credentials, inputReader, outputWriter);
        loginHandler.promptCredential();

        verify(outputWriter, times(1)).writeString("Please input your username: ");
        verify(outputWriter, times(0)).writeString("Please input your password: ");
    }

    @Test(expected = IOException.class)
    public void promptCredentialRaisesErrorIfNoPassword() throws IOException {
        OutputWriterWrapper outputWriter = Mockito.mock(OutputWriterWrapper.class);
        InputReaderWrapper inputReader = Mockito.mock(InputReaderWrapper.class);

        when(inputReader.readString()).thenReturn("matheus").thenThrow(new IOException());

        LoginHandler loginHandler = new LoginHandler(this.credentials, inputReader, outputWriter);
        loginHandler.promptCredential();

        verify(outputWriter, times(1)).writeString("Please input your username: ");
        verify(outputWriter, times(1)).writeString("Please input your password: ");
    }
}
