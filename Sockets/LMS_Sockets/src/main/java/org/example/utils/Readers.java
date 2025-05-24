package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Readers {
    public static String readUsername(BufferedReader reader, PrintWriter writer, String entity) throws IOException {
        writer.println("Enter " + entity + " name: ");
        return reader.readLine();
    }

    public static String readPassword(BufferedReader reader, PrintWriter writer, String entity) throws IOException {
        writer.println("Enter " + entity + " password: ");
        return reader.readLine();
    }
}
