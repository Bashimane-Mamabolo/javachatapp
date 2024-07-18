package org.bashimane.chatapp.server;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(6888)) {  // Port number 68888
            System.out.println("Server started. Waiting for clients...");
            Socket clientSocket = serverSocket.accept();
        }
        System.out.println("Client connected.");
        // Additional code to handle communication will be added later
    }
}
