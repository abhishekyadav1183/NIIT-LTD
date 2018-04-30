
package niit.ltd;

import com.sun.corba.se.spi.activation.Server;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class NIITLTD {

    public static void main(String[] args) throws IOException
    {
         int portNumber=10000;
        ServerSocket serverSocket=null;
        
        try
        {
            serverSocket=new ServerSocket(portNumber);
       }
        catch(IOException e){}
      while(true)
        {
            ClientWorker w;
            try
            {
                w=new ClientWorker(serverSocket.accept());
                Thread t=new Thread(w);
                t.start();
            }
            catch(IOException e){}
    }
    
}
    
}

class ClientWorker implements Runnable
{
  
      
    private Socket client;
    public ClientWorker(Socket client)
    {
        this.client=client;
    }
    
    public void run()
    {
       
        try
        {
         
        DataInputStream in=new DataInputStream(client.getInputStream());  
         String line="";
        while(true)
    {
        try{
            
           line= in.readUTF();
           
           if(line.equals("shutdown"))
{
   
  Runtime runtime = Runtime.getRuntime();
    Process proc = runtime.exec("shutdown -s -t 0");
    System.exit(0);   
}
        }
        catch(IOException e){}
                
            }
                
        }
        catch(IOException e){}
  }
   
}

