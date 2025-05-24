package org.example.sockets;

import org.example.sockets.client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000");
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("[ SERVER ] : New connection from " + socket);

                Thread client = new ClientHandler(socket);
                client.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}