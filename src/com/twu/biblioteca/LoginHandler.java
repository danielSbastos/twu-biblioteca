package com.twu.biblioteca;

import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginHandler {

    private List<Map> credentials;
    private InputReaderWrapper inputReaderWrapper;
    private OutputWriterWrapper outputWriterWrapper;

    public LoginHandler(List<Map> credentials)
    {
        this.credentials = credentials;
    }
    public  LoginHandler(
            List<Map> credentials,
            InputReaderWrapper inputReaderWrapper,
            OutputWriterWrapper outputWriterWrapper
    )
    {
        this.credentials = credentials;
        this.inputReaderWrapper = inputReaderWrapper;
        this.outputWriterWrapper = outputWriterWrapper;
    }

    public boolean validateCredentials(String username, String password)
    {
        for (Map<String, String> map: this.credentials) {
            if (map.get("username").equals(username) && map.get("password").equals(password))
                return true;
        }

        return false;
    }

    public Map<String, String> promptCredential() throws IOException {
        this.outputWriterWrapper.writeString("Please input your username: ");
        String username = this.inputReaderWrapper.readString();

        this.outputWriterWrapper.writeString("Please input your password: ");
        String password = this.inputReaderWrapper.readString();

        Map<String, String> credential = new HashMap<>();
        credential.put("username", username);
        credential.put("password", password);

        return credential;
    }
}
