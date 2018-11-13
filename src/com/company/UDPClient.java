package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.InputMismatchException;

public class UDPClient {
    private byte[] message;
    private InetAddress address;
    private int port;
    private DatagramSocket socket;
    static JFrame frame;
    static GraphicsConfiguration gc;

    public UDPClient(byte[] message, InetAddress address, int port ) throws SocketException{
        this.message = message;
        this.address = address;
        this.port = port;
        this.socket = new DatagramSocket();
    }

    public void send() throws IOException{
        DatagramPacket request = new DatagramPacket(this.message, this.message.length, this.address, this.port);
        this.socket.send(request);
        System.out.println("------ CLIENT ------");
        System.out.println("Message Sent");
    }

    public void  receive() throws IOException{
        byte[] buffer = new byte[1000];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        this.socket.receive(reply);
        System.out.println("------ CLIENT ------");
        System.out.println("Message received from server");
        System.out.println("Reply: " + new String(reply.getData()));
    }

    public byte[] getMessage() {
        return message;
    }

    public DatagramSocket getSocket(){
        return this.socket;
    }

    public void createGUI(){

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame(gc);
        frame.setLayout(new GridLayout(6,2));
        frame.setTitle("CLIENT");

        JLabel portLabel = new JLabel();
        portLabel.setText("<html><h2>Using Port Number: " + this.port + "</h2></html>");
        portLabel.setBounds(10,10,100,100);
        frame.getContentPane().add(portLabel);

        JLabel messageLabel = new JLabel();
        messageLabel.setText("<html><p>Enter message to send: </p></html>");
        frame.getContentPane().add(messageLabel);

        JTextField messageField = new JTextField();
        messageField.setBounds(10,10,100,100);
        frame.getContentPane().add(messageField);

        JButton send = new JButton("SEND");
        send.setBounds(100,100,140,40);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    if(!messageField.getText().isEmpty()) {
                        UDPClient temp = new UDPClient(messageField.getText().getBytes(), address, port);
                        temp.send();
                        temp.receive();
                        messageField.setText("");
                    }
                }
                catch (NumberFormatException nfe){ }
                catch (UnknownHostException uke){ }
                catch (SocketException se){ }
                catch (IOException ioe){ }
                finally{
                    try{
                        if(getSocket() != null){
                            getSocket().close();
                        }
                    }
                    catch(NullPointerException npe){ }
                }
            }
        });
        frame.getContentPane().add(send);

        frame.setSize(dim.width/3,dim.height/3);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        UDPClient client = null;
        try{
            client = new UDPClient(args[0].getBytes(), InetAddress.getByName(args[1]), Integer.parseInt(args[2]));
            client.createGUI();
            client.send();
            client.receive();
        }
        catch (NumberFormatException e){
            System.out.println("Invalid Port, use integers\n" + e.getMessage());
        }
        catch (UnknownHostException e){
            System.out.println("Invalid Address\n" + e.getMessage());
        }
        catch (SocketException e){
            System.out.println("Socket\n" + e.getMessage());
        }
        catch (IOException e){
            System.out.println("IO\n" + e.getMessage());
        }

        finally {
            try{
                if(client.getSocket() != null){
                    client.getSocket().close();
                }
            }
            catch(NullPointerException e){

            }
        }
    }
}
