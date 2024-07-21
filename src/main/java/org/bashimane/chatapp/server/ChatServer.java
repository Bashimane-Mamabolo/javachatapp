package org.bashimane.chatapp.server;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {

    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());
    private static final List<ClientChatHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        int portNumber = 6888;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {  // Port number 68888
            logger.info("Server started. Waiting for clients...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Client connected: " + clientSocket);

                ClientChatHandler clientHandler = new ClientChatHandler(clientSocket, clients);
                clients.add(clientHandler);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        } catch (IOException ex){
            logger.log(Level.SEVERE, "An error occurred while starting the server: " + ex.getMessage(), ex);
        }
    }
}
