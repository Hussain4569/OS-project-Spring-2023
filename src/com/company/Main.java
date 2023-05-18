package com.company;

public class Main {
    public static void main(String[] args) {
        FrameTwo sf = new FrameTwo();
        FrameOne cf = new FrameOne();

        cf.startClient();
        sf.startClient();

        cf.setFrameTwo(sf);
        sf.setFrameOne(cf);
    }
}

//client cannot start before the server, or it will give exception and crash
