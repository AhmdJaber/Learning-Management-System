package org.example.sockets.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("[ " + socket.getLocalPort() + " socket ] : connected");

            Scanner scanner = new Scanner(System.in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            while(true){
                readData(socket, reader);
                writeData(socket, writer, scanner);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeData(Socket socket, PrintWriter writer, Scanner scanner){
        while(socket.isConnected()){
            String data = scanner.nextLine();
            writer.println(data);
            writer.flush();
        }
    }

    public static void readData(Socket socket, BufferedReader reader){
        new Thread(() -> {
            String data;

            while(socket.isConnected()){
                try{
                    data = reader.readLine();
                    System.out.println(data);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}