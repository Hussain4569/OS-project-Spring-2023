package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class FrameTwo extends JFrame implements ActionListener{
    private Server server = new Server();
    private Client client = new Client();
    private FrameOne frameOne;

    private Container c;
    private JLabel title;
    private JLabel title2;
    private JLabel title3;
    private JTextField sSender;
    private JTextArea sReceive;
    private JButton send;

    public FrameTwo(){

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                server.closeConnection();
                client.closeConnection();
                System.exit(0);
            }
        });

        server.createConnection(5000); // to receive

        setTitle("Frame Two");
        setBounds(800,90,550,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c=getContentPane();
        c.setLayout(null);

        title=new JLabel("Frame Two");
        title.setFont(new Font("Arial",Font.PLAIN,30));
        title.setSize(300,30);
        title.setLocation(180,30);
        c.add(title);

        title2=new JLabel("Write message here");
        title2.setFont(new Font("Arial",Font.PLAIN,20));
        title2.setSize(300,25);
        title2.setLocation(100,85);
        c.add(title2);

        sSender=new JTextField();
        sSender.setFont(new Font("Arial",Font.PLAIN,15));
        sSender.setSize(300,100);
        sSender.setLocation(100,130);
        c.add(sSender);

        sReceive=new JTextArea();
        sReceive.setFont(new Font("Arial",Font.PLAIN,15));
        sReceive.setSize(300,200);
        sReceive.setLocation(100,340);
        sReceive.setLineWrap(true);
        sReceive.setEditable(false);
        c.add(sReceive);

        title3=new JLabel("Message received");
        title3.setFont(new Font("Arial",Font.PLAIN,20));
        title3.setSize(300,25);
        title3.setLocation(100,300);
        c.add(title3);

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
            String output=sSender.getText();
            System.out.println(output);
            client.sendMessage(output);
            frameOne.recieveMessage();
            sSender.setText("");
        }



    }

    public void setFrameOne(JFrame frameOne) {
        this.frameOne = (FrameOne) frameOne;
    }

    private void addTextInTextFeild(String text){
        sReceive.setText(sReceive.getText()+"\n"+text);
    }

    public void receiveMessage(){
        if (!server.clientConnected) {
            server.acceptClient();
        }
        String message = server.readMessage();
        addTextInTextFeild(message);
    }

    public void startClient(){
        client.createConnection("127.0.0.1", 6000);
    }
}

