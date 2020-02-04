package com.twu.biblioteca.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO: Create IOWrapper
public class InputReaderWrapper {
    private BufferedReader reader;

    public InputReaderWrapper() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public int readInt() throws IOException, NumberFormatException {
        return Integer.parseInt(this.reader.readLine());
    }

    public String readString() throws IOException {
        return this.reader.readLine();
    }
}
