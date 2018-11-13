# UDP Client Server Echo

Project for CECS 327

## Getting Started

You can download the whole Git Repo if you want the source code.  
All you really need is the "UDPClientServer" folder within the "out" folder.  

### Prerequisites

You need at least JDK 11  

#### Installing JDK 11 for Windows  
[JDK 11 Download](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)  
Once you run and install, you can check in your cmd by  
```java -version```  
You should get: ``` java version "11.0.1" 2018-10-16```  
If you dont, then you'll probably have to change your environment variables.  

On Windows 10, you press the "Windows" button  
1. Type in "advanced system settings"  
2. Click on the "View Advanced System Settings"  
3. Click on Environment Variables  
4. Under System Variables, if you already have "JAVA_HOME" then you can edit that one, if not just make a new one  
5. For the name, put in ```JAVA_HOME```  
6. For the value, put in the path to the newly installed JDK, mine is under ```D:\Program Files\Java\JDK-11.0.1```  
7. Accept those changes  
8. Under System Variables, edit the "Path" variable  
9. Click "New" and then type in ```%JAVA_HOME%\bin```  
10. Open another cmd and check again.   

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


