package com.company;

import java.net.*;
import java.io.*;

public class Server {
    public boolean clientConnected = false;
    //initialize socket and input stream
    private Socket		 socket = null;
    private ServerSocket server = null;
    private DataInputStream in	 = null;


    public void createConnection(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");
        } catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public void closeConnection() {
        System.out.println("Closing connection");

        // close connection
        try {
            socket.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void acceptClient() {
        try {
            socket = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client accepted");

        // takes input from the client socket
        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientConnected = true;
    }

    public String readMessage() {
      // reads message from client until "Over" is sent
        String line = "";
        try {
            line = in.readUTF();
            System.out.println(line);

        } catch(IOException i) {
            System.out.println(i);
        }
        return line;
    }
}
