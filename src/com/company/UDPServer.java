package com.company;

import java.io.*;
import java.net.*;
import java.util.InputMismatchException;

public class UDPServer {

    private DatagramSocket socket;
    private int port;

    public UDPServer(int port) throws SocketException{
        this.port = port;
        this.socket = new DatagramSocket(this.port);
    }

    public DatagramSocket getSocket(){
        return this.socket;
    }

    public int getPort(){
        return this.port;
    }

    public void listen() throws IOException{
        while(true){
            byte[] buffer = new byte[1000];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            this.socket.receive(request);
            DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
            this.socket.send(reply);
        }
    }

    public static void main(String[] args){
        UDPServer server = null;
        try{
            server = new UDPServer(Integer.parseInt(args[0]));
            System.out.println("------ SERVER ------");
            System.out.println("Server listening on port: " + server.getPort());
            while(true){
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
