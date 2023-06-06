package test;

import server.BookScrabbleHandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class testBookScrabbleHandler {
    public static void main(String[] args) {
        // Create a sample input string
        String input = "Q Book1,Book2,Book3\n";

        // Set up the input and output streams
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Redirect the System.out to the output stream
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        // Create an instance of the BookScrabbleHandler
        BookScrabbleHandler handler = new BookScrabbleHandler();

        // Handle the client with the sample input and output streams
        handler.handleClient(inputStream, outputStream);

        // Restore the original System.out
        System.setOut(originalOut);

        // Get the output string
        String output = outputStream.toString();

        // Print the output
        System.out.println("Output: " + output);
    }
}
