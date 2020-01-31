package com.twu.biblioteca.lib;

public class OutputWriterWrapper {
    public void writeStringln(String content) {
        System.out.println(content);
    }

    public void writeString(String content) {
        System.out.print(content);
    }
}
