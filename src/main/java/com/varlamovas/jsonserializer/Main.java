package com.varlamovas.jsonserializer;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        String pathToFile = Paths.get("").resolve( "src/test/resources/Test.json").toAbsolutePath().toString();
        System.out.println(pathToFile);
        Reader reader = new FileReader(pathToFile);
        PushbackReader pushBackReader = new PushbackReader(reader);
        System.out.println("read");
        char c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("unread");
        pushBackReader.unread(c);
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("unread");
        pushBackReader.unread(c);
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("unread");
        pushBackReader.unread(c);
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        System.out.println("read");
        c = (char) pushBackReader.read();
        System.out.println(c);

        Set<String> endedChars = new HashSet<String>() {
            {
                add(" ");
                add("\n");
                add("\t");
                add("\r");
                add("]");
                add("}");
                add(",");
            }
        };

        System.out.println(endedChars.contains("\r"));


        System.out.println(Boolean.parseBoolean(""));

    }
}
