package com.luxoft;

import java.io.IOException;

public class EntryPoint {
    public static void main(String args[]) throws IOException {
        LogParser logParser = new LogParser();
        logParser.process("", "");
    }
}
