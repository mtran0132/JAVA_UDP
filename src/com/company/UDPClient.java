/*
Created By Group 2
Marco Tran
A command line program that launches a GUI
 */
package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class UDPClient {
    private byte[] message;
    private InetAddress address;
    private int port;
    private DatagramSocket socket;
    static JFrame frame;
    static GraphicsConfiguration gc;

    // Creates a new Client Object with a message, address and port
    public UDPClient(byte[] message, InetAddress address, int port ) throws SocketException{
        this.message = message;
        this.address = address;
        this.port = port;
        this.socket = new DatagramSocket();
    }

    // Returns the client's socket
    public DatagramSocket getSocket(){
        return this.socket;
    }

    // Sends the message to the server

    public void send() throws IOException{

        if(!this.socket.isClosed()){
            // Creates the packet
            DatagramPacket request = new DatagramPacket(this.message, this.message.length, this.address, this.port);
            // Sends the packet
            // Information is within the packet
            this.socket.send(request);

            // Console output
            System.out.println("------ CLIENT ------");
            System.out.println("Message Sent");
        }
    }
    // Receives message from server

    public void  receive() throws IOException{
        if(!this.socket.isClosed()){

            byte[] buffer = new byte[1000];

            // Creates the reply
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            // Receives the reply
            this.socket.receive(reply);

            // Console output
            System.out.println("------ CLIENT ------");
            System.out.println("Message received from server");

            // Displays what the server sent back
            System.out.println("Reply: " + new String(reply.getData()));
        }
    }

    // Creates a GUI for user to input messages to send over
    // Centered and a third of your screen
    public void createGUI(){

        // Gets the screens dimensions
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame(gc);
        frame.setLayout(new GridLayout(6,2));
        frame.setTitle("CLIENT");

        // Create Labels
        JLabel portLabel = new JLabel();
        portLabel.setText("<html><h2>" + this.address.getHostAddress() + "<br>Listening on Port Number: " + this.port + "</h2></html>");
        portLabel.setBounds(10,10,100,100);

        // Create Labels
        JLabel messageLabel = new JLabel();
        messageLabel.setText("<html><p>Enter message to send: </p></html>");

        // Create the text field for the user to input
        JTextField messageField = new JTextField();
        messageField.setBounds(10,10,100,100);

        // Add the labels to GUI
        frame.getContentPane().add(portLabel);
        frame.getContentPane().add(messageLabel);
        frame.getContentPane().add(messageField);

        // Create the button to send
        JButton send = new JButton("SEND");
        send.setBounds(100,100,120,40);

        // Add an actionlister to the button
        // Whenever the button is pressed, do this anonymous function
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{

                    // Prevents empty messages to be sent
                    if(!messageField.getText().isEmpty()) {
                        UDPClient temp = new UDPClient(messageField.getText().getBytes(), address, port);
                        temp.send();
                        temp.receive();
                        // Clears the text box
                        messageField.setText("");
                    }
                }

                // Just catching my exceptions
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

        // Add the button to the GUI
        frame.getContentPane().add(send);

        // Set the size to a third of your screen
        frame.setSize(dim.width/3,dim.height/3);
        // Center the GUI
        frame.setLocationRelativeTo(null);
        // Exit the program when the GUI closes
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Display the GUI
        frame.setVisible(true);
    }

    // ENTRY POINT
    public static void main(String[] args){
        UDPClient client = null;
        try{
            // Creates a client object
            // (message, address, port)
            client = new UDPClient(args[0].getBytes(), InetAddress.getByName(args[1]), Integer.parseInt(args[2]));

            // Creates the GUI
            client.createGUI();
            // Send the message
            client.send();
            // Receive the reply
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
            catch(NullPointerException e){ }
        }
    }
}
