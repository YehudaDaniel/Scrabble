package test;

import server.ClientHandler;
import server.MainTrain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class testClientHandler {
    public static void main(String[] args) {
        // Create a test input string
        String inputString = "Hello, World!";

        // Convert the input string to an input stream
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

        // Create a test output stream
        OutputStream outputStream = new ByteArrayOutputStream();

        // Create an instance of your client handler implementation
        ClientHandler handler = (ClientHandler) new MainTrain.ClientHandler1();

        // Call the handleClient method with the test input and output streams
        handler.handleClient(inputStream, outputStream);

        // Get the output from the output stream
        String output = outputStream.toString();

        // Print the output
        System.out.println("Output: " + output);

        // Close the client handler
        handler.close();
    }
}
