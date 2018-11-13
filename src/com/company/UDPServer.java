package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;

public class UDPServer extends JFrame{

    private DatagramSocket socket;
    private DatagramPacket request;
    private DatagramPacket reply;
    private int port;
    static GraphicsConfiguration gc;
    private JFrame frame;
    private boolean running;

    public UDPServer(int port) throws SocketException{
        this.port = port;
        this.socket = new DatagramSocket(this.port);
        this.running = true;
    }

    public DatagramSocket getSocket(){
        return this.socket;
    }

    public boolean isRunning(){
        return this.running;
    }

    public void setRunning(boolean bool){
        this.running = bool;
    }

    public int getPort(){
        return this.port;
    }

    public void listen() throws IOException{
        while(true){
            byte[] buffer = new byte[1000];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            this.request = request;
            this.socket.receive(request);
            DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
            this.reply = reply;
            this.socket.send(reply);
            addEntry();
        }
    }

    public void createGUI(){

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame(gc);
        frame.setLayout(new GridLayout(0,2));
        frame.setTitle("SERVER");

        JLabel portLabel = new JLabel();
        portLabel.setText("<html><h2>Listening on Port Number: " + this.port + "</h2></html>");
        portLabel.setBounds(10,10,100,100);
        frame.getContentPane().add(portLabel);

        JLabel name = new JLabel();
        name.setText("<html><h3>Group 2: Marco Tran</h3></html>");
        name.setBounds(10,10,100,100);
        frame.getContentPane().add(name);

        frame.setSize(dim.width/3,dim.height/3);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void addEntry(){
        JLabel packetLabel = new JLabel();
        packetLabel.setText("<html><p>REQUEST: " + new String(this.request.getData()) +"</p></html>");
        this.frame.getContentPane().add(packetLabel);
    }

    public static void main(String[] args){

        UDPServer server = null;
        try{
            server = new UDPServer(Integer.parseInt(args[0]));
            server.createGUI();
            System.out.println("------ SERVER ------");
            System.out.println("Server listening on port: " + server.getPort());
            while(server.isRunning()){
                server.listen();
            }
        }
        catch (NumberFormatException e){
            System.out.println("Invalid Port, use integers\n" + e.getMessage());
        }
        catch (SocketException e){
            System.out.println("Socket\n" + e.getMessage());
        }

        catch (IOException e){
            System.out.println("IO\n" + e.getMessage());
        }
        finally {
            try{
                if(server.getSocket() != null){
                    server.getSocket().close();
                }
            }
            catch(NullPointerException e){

            }
        }
    }
}
