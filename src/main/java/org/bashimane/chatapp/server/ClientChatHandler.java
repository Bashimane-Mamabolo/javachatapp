package org.bashimane.chatapp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientChatHandler implements Runnable {

    private static final Logger logger = Logger.getLogger(ClientChatHandler.class.getName());

    private Socket clientSocket;
    private List<ClientChatHandler> connectedClients;
    private PrintWriter outToClient;
    private BufferedReader inFromClient;
    private String username;

    public ClientChatHandler(Socket socket, List<ClientChatHandler> clients) throws IOException {
        this.clientSocket = socket;
        this.connectedClients = clients;
        this.outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
        this.inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        try {
//            requestUsername();
            String inputLine;
            while ((inputLine = inFromClient.readLine()) != null) {
                broadcastMessage(inputLine);
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Client disconnected or an error occurred: " + ex.getMessage(), ex);
        } finally {
            closeConnections();
        }
    }

//    private void requestUsername() throws IOException {
//        outToClient.println("Enter your username: ");
//        username = inFromClient.readLine();
//        broadcastMessage("User " + username + " has joined the chat.");
//    }

    private void broadcastMessage(String message) {
        for (ClientChatHandler client : connectedClients) {
            client.outToClient.println(message);
        }
    }

    private void closeConnections() {
        try {
            inFromClient.close();
            outToClient.close();
            clientSocket.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "An error occurred while closing connections: " + ex.getMessage(), ex);
        } finally {
            connectedClients.remove(this);
//            broadcastMessage("User " + username + " has left the chat.");
            broadcastMessage("A user has left the chat.");
            logger.info("Client disconnected: " + clientSocket);
        }
    }
}
