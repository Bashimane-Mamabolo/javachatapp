package org.bashimane.chatapp.client;

import java.io.*;
import java.net.*;

public class ChatClient {

    private Socket clientSocket;
    private BufferedReader clientInput;
    private PrintWriter messageToServer;
    private BufferedReader messageFromServer;

    public static void main(String[] args) {
        try {
            ChatClient client = new ChatClient("localhost", 6888);
            client.start();
        } catch (IOException e) {
            System.err.println("Error starting client: " + e.getMessage());
        }
    }

    public ChatClient(String address, int port) throws IOException {
        try {
            clientSocket = new Socket(address, port);
            System.out.println("Connected to the chat server");

            clientInput = new BufferedReader(new InputStreamReader(System.in));
            messageToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            messageFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException u) {
            throw new IOException("Host unknown: " + u.getMessage(), u);
        } catch (IOException i) {
            throw new IOException("Error initializing client: " + i.getMessage(), i);
        }
    }

    public void start() {
        try {
            String line = "";
            while (!line.equalsIgnoreCase("exit")) {
                line = clientInput.readLine();
                if (line != null) {
                    messageToServer.println(line);
                    String response = messageFromServer.readLine();
                    if (response != null) {
                        System.out.println(response);
                    } else {
                        System.out.println("Server closed connection");
                        break;
                    }
                }
            }
        } catch (IOException i) {
            System.err.println("Error during communication: " + i.getMessage());
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
            if (clientInput != null) {
                clientInput.close();
            }
            if (messageToServer != null) {
                messageToServer.close();
            }
            if (messageFromServer != null) {
                messageFromServer.close();
            }
        } catch (IOException i) {
            System.err.println("Error closing resources: " + i.getMessage());
        }
    }
}
