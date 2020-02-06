package com.twu.biblioteca;

import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.CurrentUser;
import com.twu.biblioteca.models.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionHandler {

    private List<User> users;
    private InputReaderWrapper inputReaderWrapper;
    private OutputWriterWrapper outputWriterWrapper;

    public SessionHandler(
            List<User> users,
            InputReaderWrapper inputReaderWrapper,
            OutputWriterWrapper outputWriterWrapper
    ) {
        this.users = users;
        this.inputReaderWrapper = inputReaderWrapper;
        this.outputWriterWrapper = outputWriterWrapper;
    }

    public boolean login() {
        User loggedInUser = null;
        try {
            Map<String, String> credential = this.promptCredential();
            loggedInUser = User.findByLibraryIdAndPassword(credential.get("libraryId"), credential.get("password"));
            CurrentUser.set(loggedInUser);
        } catch (IOException e) {}

        if (loggedInUser == null)
            System.err.println("Invalid username or password");

        return loggedInUser != null;
    }

    private Map<String, String> promptCredential() throws IOException {
        this.outputWriterWrapper.writeString("Please input your username: ");
        String username = this.inputReaderWrapper.readString();

        this.outputWriterWrapper.writeString("Please input your password: ");
        String password = this.inputReaderWrapper.readString();

        Map<String, String> credential = new HashMap<>();
        credential.put("libraryId", username);
        credential.put("password", password);

        return credential;
    }
}
