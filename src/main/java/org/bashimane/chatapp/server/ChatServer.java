package org.bashimane.chatapp.server;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {

    private static final List<ClientChatHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        int portNumber = 6888;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {  // Port number 68888
            System.out.println("Server started. Waiting for clients...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                ClientChatHandler clientHandler = new ClientChatHandler(clientSocket, clients);
                clients.add(clientHandler);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        }
    }
}
