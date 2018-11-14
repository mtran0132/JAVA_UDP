/*
Created By Group 2
Marco Tran
A command line program that launches a GUI
 */

package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class UDPServer extends JFrame{

    private DatagramSocket socket;
    private DatagramPacket request;
    private int port;
    static GraphicsConfiguration gc;
    private JFrame frame;
    private InetAddress address;

    // Constructor
    // Given an address and port
    // Create a new socket
    public UDPServer(InetAddress address, int port) throws SocketException{
        this.port = port;
        this.address = address;
        this.socket = new DatagramSocket(port, address);
    }

    // Returns the socket of the Server
    public DatagramSocket getSocket(){
        return this.socket;
    }

    // Returns the port of the Server
    public int getPort(){
        return this.port;
    }

    // Using a socket, the server listens for incoming messages on a specific port
    public void listen() throws IOException{
        while(true){
            byte[] buffer = new byte[1000];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);

            // Receives a DatagramPacket from this socket
            // This method blocks until a Datagram is received
            this.socket.receive(request);
            this.request = request;

            System.out.println("Received from Client: " + request.getAddress());
            System.out.println("Message: " + new String(request.getData()));

            // Server crafts a reply
            DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
            // Sends the reply
            // Using the information from the packet
            this.socket.send(reply);
            addEntry();
        }
    }

    // Creates a dynamic GUI
    // Centered and a third of your screen
    public void createGUI(){

        // Get the dimensions of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame(gc);
        frame.setLayout(new GridLayout(0,2));
        frame.setTitle("SERVER");

        // Creating Labels
        JLabel portLabel = new JLabel();
        portLabel.setText("<html><h2>" + this.address.getHostAddress() +"<br>Listening on Port Number: " + this.port + "</h2></html>");
        portLabel.setBounds(10,10,100,100);

        //Creating Labels
        JLabel name = new JLabel();
        name.setText("<html><h3>Group 2: Marco Tran</h3></html>");
        name.setBounds(10,10,100,100);

        // Add the Labels
        frame.getContentPane().add(portLabel);
        frame.getContentPane().add(name);

        // Set the GUI to a third of your screen
        frame.setSize(dim.width/3,dim.height/3);
        // Center the GUI
        frame.setLocationRelativeTo(null);
        // Stop the program once you exit the GUI
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Display the GUI
        frame.setVisible(true);
    }

    // Adds an entry to the GUI everytime a request comes in.
    public void addEntry(){
        JLabel packetLabel = new JLabel();
        packetLabel.setText("<html><p>REQUEST: " + new String(this.request.getData()) +"</p></html>");
        this.frame.getContentPane().add(packetLabel);
    }

    // ENTRY POINT
    public static void main(String[] args){

        UDPServer server = null;

        try{
            // Create a server with command line arguments
            // Internet Address, Port Number
            server = new UDPServer(InetAddress.getByName(args[0]),Integer.parseInt(args[1]));

            // Makes the GUI popup
            server.createGUI();
            System.out.println("------ SERVER ------");
            System.out.println("Server listening on port: " + server.getPort());

            // Server starts listening
            server.listen();
        }

        // Fail cases taken care of(?)
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
            catch(NullPointerException e){ }
        }
    }
}
