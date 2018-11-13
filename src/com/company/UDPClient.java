package com.company;

import java.io.*;
import java.net.*;
import java.util.InputMismatchException;

public class UDPClient {
    private byte[] message;
    private InetAddress address;
    private int port;
    private DatagramSocket socket;

    public UDPClient(byte[] message, InetAddress address, int port ) throws SocketException{
        this.message = message;
        this.address = address;
        this.port = port;
        this.socket = new DatagramSocket();
    }

    public void send() throws IOException{
        DatagramPacket request = new DatagramPacket(this.message, this.message.length, this.address, this.port);
        socket.send(request);
        System.out.println("------ CLIENT ------");
        System.out.println("Message Sent");
    }

    public void  receive() throws IOException{
        byte[] buffer = new byte[1000];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        socket.receive(reply);
        System.out.println("------ CLIENT ------");
        System.out.println("Message received");
        System.out.println("Reply: " + new String(reply.getData()));
    }

    public DatagramSocket getSocket(){
        return this.socket;
    }

    public static void main(String[] args){
        UDPClient client = null;

        try{
            client = new UDPClient(args[0].getBytes(), InetAddress.getByName(args[1]), Integer.parseInt(args[2]));
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
