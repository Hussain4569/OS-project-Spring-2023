package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class FrameOne extends JFrame implements ActionListener{
    private Client client = new Client();
    private Server server = new Server();
    private FrameTwo frameTwo;

    private Container c;
    private JLabel title;
    private JLabel title2;
    private JLabel title3;
    private JTextField cSender;
    private JTextArea cReceive;
    private JButton send;

    public FrameOne(){

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                server.closeConnection();
                client.closeConnection();
                System.exit(0);
            }
        });

        //sockets code
        server.createConnection(6000); // to receive

        setTitle("Frame One");
        setBounds(100,90,550,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c=getContentPane();
        c.setLayout(null);


        title=new JLabel("Frame One");
        title.setFont(new Font("Arial",Font.PLAIN,30));
        title.setSize(300,30);
        title.setLocation(180,30);
        c.add(title);

        title2=new JLabel("Write message here");
        title2.setFont(new Font("Arial",Font.PLAIN,20));
        title2.setSize(300,25);
        title2.setLocation(100,85);
        c.add(title2);

        cSender=new JTextField();
        cSender.setFont(new Font("Arial",Font.PLAIN,15));
        cSender.setSize(300,100);
        cSender.setLocation(100,130);
        c.add(cSender);

        title3=new JLabel("Message received");
        title3.setFont(new Font("Arial",Font.PLAIN,20));
        title3.setSize(300,25);
        title3.setLocation(100,300);
        c.add(title3);

        cReceive=new JTextArea();
        cReceive.setFont(new Font("Arial",Font.PLAIN,15));
        cReceive.setSize(300,200);
        cReceive.setLocation(100,340);
        cReceive.setLineWrap(true);
        cReceive.setEditable(false);
        c.add(cReceive);

        send=new JButton("Send");
        send.setFont(new Font("Arial",Font.PLAIN,18));
        send.setSize(100,20);
        send.setLocation(200,250);
        send.addActionListener(this);
        c.add(send);

        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==send){
            String output=cSender.getText();
            System.out.println(output);
            client.sendMessage(output);
            frameTwo.receiveMessage();
            cSender.setText("");
        }

    }

    public void setFrameTwo(JFrame frameTwo) {
        this.frameTwo = (FrameTwo) frameTwo;
    }

    private void addTextInTextFeild(String text){
        cReceive.setText(cReceive.getText() + "\n" + text);
    }

    public void recieveMessage(){
        if (server != null && !server.clientConnected) {
            server.acceptClient();
        }
        String message = server.readMessage();
        addTextInTextFeild(message);
    }

    public void startClient() {
        client.createConnection("127.0.0.1", 5000);
    }

}


