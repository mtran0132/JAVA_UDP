# UDP Client Server Echo

Project for CECS 327

## Getting Started

You can download the whole Git Repo if you want the source code.  
All you really need is the "UDPClientServer" folder within the "out" folder.  

### Prerequisites

You need at least JDK 11  

## Running the program

Open up two command MS-DOS windows  
One for the server and one for the client  

### If you just downloaded the "production" folder:  
Navigate to the "UDPClientServer" using the command line  
``` cd \path\to\UDPClientServer\```

From here, in one terminal type in 
``` java com.company.UDPServer 127.0.0.1 6789```  
In other words  
``` java com.compant.UDPServer [local address] [port number]```  

In the second terminal type in  
``` java com.company.UDPClient "Hello World" 127.0.0.1 6789```  
In other words  
``` java com.company.UDPClient [message] [address] [port]```  

### If you downloaded the whole Git Repo

Navigate to the the production folder to find UDPClientServer  
``` cd \path\to\production\UDPClientServer```  

From here, in one terminal type in  
``` java com.company.UDPServer 127.0.0.1 6789```  
In other words  
``` java com.compant.UDPServer [local address] [port number]```  

In the second terminal type in  
``` java com.company.UDPClient "Hello World" 127.0.0.1 6789```  
In other words  
``` java com.company.UDPClient [message] [address] [port]```  

#### Using the GUI
You will interact with the GUI that says "Client".  
In the text box, you can type in a message to send to the server  
When you press send, look at the command line interfaces as well as the "Server" GUI to see changes.  

## Authors

Marco Tran  
