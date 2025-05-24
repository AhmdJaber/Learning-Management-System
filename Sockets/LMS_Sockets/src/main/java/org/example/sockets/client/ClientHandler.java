package org.example.sockets.client;

import org.example.controller.ClientController;
import org.example.database.MySQLDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class ClientHandler extends Thread{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    Connection conn;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.conn = MySQLDatabase.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            writer.println("Client is running on the thread: " + Thread.currentThread());
            ClientController.makeAction(reader, writer, conn);
        } catch (IOException | SQLException e) {
            closeResources(conn, reader, writer);
            e.printStackTrace();
        }
    }

    private void closeResources(Connection conn, BufferedReader reader, PrintWriter writer){
        try{
            conn.close();
            reader.close();
            writer.close();
        } catch (SQLException | IOException ex){
            ex.printStackTrace();
        }
    }
}
