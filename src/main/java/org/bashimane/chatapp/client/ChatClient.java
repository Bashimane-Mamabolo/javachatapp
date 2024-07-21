package org.bashimane.chatapp.client;

import java.io.*;
import java.net.*;
import java.util.function.Consumer;

public class ChatClient {

    private Socket clientSocket;
//    private BufferedReader clientInput;
    private PrintWriter messageToServer;
    private BufferedReader messageFromServer;
    private Consumer<String> onMessageReceived;

    public static void main(String[] args) {
        try {
            ChatClient client = new ChatClient("localhost", 6888, System.out::println);
            client.startClient();

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String userMessage;
            while ((userMessage = userInput.readLine()) != null) {
                client.sendMessage(userMessage);
                if (userMessage.equalsIgnoreCase("exit")) {
                    break;
                }
            }
            client.closeResources();
        } catch (IOException e) {
            System.err.println("Error starting client: " + e.getMessage());
        }
    }

    public ChatClient(String serverAddress, int serverPort, Consumer<String> onMessageReceived ) throws IOException {

        this.clientSocket = new Socket(serverAddress, serverPort);
        this.messageFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.messageToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        this.onMessageReceived = onMessageReceived;
        System.out.println("Connected to the chat server");

    }

    public void sendMessage(String msg) {
        messageToServer.println(msg);
    }

    public void startClient() {
        new Thread(() -> {
            try {
                String line;
                while ((line = messageFromServer.readLine()) != null) {
                    onMessageReceived.accept(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeResources();
            }
        }).start();
    }

    private void closeResources() {
        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
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
