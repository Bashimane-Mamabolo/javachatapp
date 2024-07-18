package org.bashimane.chatapp.client;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 6888)) {   // Connect to server on port 68888
        }
        System.out.println("Connected to server.");
        // Additional code to handle communication will be added later
    }
}
