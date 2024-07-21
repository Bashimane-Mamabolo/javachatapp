package org.bashimane.chatapp.server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    private static final List<ClientChatHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(6888)) {  // Port number 68888
            System.out.println("Server started. Waiting for clients...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                ClientChatHandler clientThread = new ClientChatHandler(clientSocket, clients);
                clients.add(clientThread);
                Thread task = new Thread(clientThread);
                task.start();
            }
        }
    }
}
