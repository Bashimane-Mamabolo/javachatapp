#   Java Chatting Application

Welcome to the Chat Application! This project consists of a server and a client, 
with both console and GUI clients available. The server manages the communication between multiple clients,
allowing them to send messages to each other in real time.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)

## Features

- Real-time messaging between clients
- Console-based client for quick testing
- Graphical User Interface (GUI) client for enhanced user experience
- Broadcast messages to all connected clients
- Display timestamps for each message
- Notify users when a client joins or leaves the chat

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Git (optional, for cloning the repository)

### Steps

1. **Clone the Repository** (optional):

   ```bash
   git clone https://github.com/Bashimane-Mamabolo/javachatapp.git
   cd javachatapp
   ```

2. **Compile the Project**:

   Navigate to the project directory and run the following commands:

   ```bash
   javac -d out src/org/bashimane/chatapp/server/*.java 
   javac -d out src/org/bashimane/chatapp/client/*.java 
   javac -d out src/org/bashimane/chatapp/clientgui/*.java
   ```

3. **Run the Server**:

   ```bash
   java -cp out org.bashimane.chatapp.server.ChatServer
   ```

4. **Run the Console Client**:

   ```bash
   java -cp out org.bashimane.chatapp.client.ChatClient
   ```

5. **Run the GUI Client**:

   ```bash
   java -cp out org.bashimane.chatapp.clientgui.ChatClientGUI
   ```

## Usage

### Console Client

After starting the server, you can run the console client as described above. 
Enter your messages in the console. To exit the chat, type `exit`.

### GUI Client

Run the GUI client as described above. Enter your name when prompted. 
Type your messages in the text field at the bottom and press Enter to send them. 
Click the "Exit" button to leave the chat.

## Troubleshooting

### Common Issues

- **Server Port Already in Use**:
    - If you encounter an error indicating that the server port is already in use, 
    - try changing the port number in the `ChatServer` class.

- **Unable to Connect to Server**:
    - Ensure the server is running and the correct IP address and port are specified in the client.
    - Check your firewall settings to ensure the port is open.

- **Messages Not Being Broadcasted**:
    - Verify that the client is connected by checking the server logs.
    - Ensure the message format is correct and the connection is stable.

### Logs

Check the server logs for detailed error messages and status updates. 
Logs are printed to the console and include information about client connections, 
disconnections, and message broadcasts.

## Contributing

Contributions are welcome! If you have any ideas or improvements, 
feel free to open an issue or submit a pull request.

### Steps to Contribute

1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add some feature'`)
5. Push to the branch (`git push origin feature-branch`)
6. Open a pull request

