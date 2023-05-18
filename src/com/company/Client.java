package com.company;

import java.io.*;
import java.net.*;

public class Client {
    // initialize socket and input output streams
    private Socket socket = null;
    private DataOutputStream out = null;

    public void createConnection(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // sends output to the socket
            out = new DataOutputStream(
                    socket.getOutputStream());
        }
        catch (UnknownHostException u) {
            System.out.println(u);
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }

    public void closeConnection() {
        try {
            out.close();
            socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }

    public void sendMessage(String msg) {
        // send message to server
        try {
            out.writeUTF(msg);
        }
        catch (IOException i) {
            System.out.println(i);
        }

    }
}

