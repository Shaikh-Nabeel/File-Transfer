
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Shaikh Nabs
 */

public class Peer {
   
    Peer(MulticastSocket socket, InetAddress group, int groupPort , OnMessageQuery messageByPeer){
        Thread t1 = new Thread(new ReadThreadMessages(socket, group,groupPort,messageByPeer));
        t1.start();       
    }
    
    Peer( int receiverPort, OnMessageQuery messageByPeer, String folderLocator){
        Thread t2  = new Thread(new ReadThreadFile(receiverPort, messageByPeer,folderLocator));
        t2.start();
    }
}

//import javax.swing.JOptionPane;

class ReadThreadFile implements Runnable{
    private BufferedOutputStream bos;
    private BufferedInputStream bin;
    private DataInputStream dis;
    private final String folderLocator;
    private ServerSocket ss = null;
    private Socket socket = null;
    private OnMessageQuery messageByPeer;
    private final int receiptionPort;
    
    ReadThreadFile(int receiption_Port, OnMessageQuery mssgbypeer,String folderLocator){
        this.receiptionPort = receiption_Port;
        this.messageByPeer = mssgbypeer;
        this.folderLocator = folderLocator;
        
    }
    
    
    @Override
    public void run(){
        try{
            ss = new ServerSocket(receiptionPort);
            while(!ChatAndShare.finished){
            socket = ss.accept();
            dis = new DataInputStream(socket.getInputStream());
            
            String nameOfSender = dis.readUTF();
            String nameOfFile = dis.readUTF();
            int lengthOfFile = dis.readInt();
         
            messageByPeer.onAnyMessageQuery("------------------------------------------------------");
            messageByPeer.onAnyMessageQuery("Receiving File "+nameOfFile+"("+lengthOfFile+" bytes) from -> "+nameOfSender);

            String dir = folderLocator + nameOfFile;
            System.out.println(dir);
            FileOutputStream fileToBeReceived = new FileOutputStream(dir); 
            bos = new BufferedOutputStream(fileToBeReceived);
            bin =new BufferedInputStream(socket.getInputStream());
            int b;
            while((b = bin.read()) != -1){
                bos.write(b);
            }
            
            messageByPeer.onAnyMessageQuery("File Received From -> "+nameOfSender);
            messageByPeer.onAnyMessageQuery("------------------------------------------------------");
            
            socket.close();
            dis.close();
            bos.close();
            bin.close();
            fileToBeReceived.close();
            }
        }
        catch(IOException e){
            System.out.println("Error "+ e);
        }
    }
}

class ReadThreadMessages implements Runnable{
        private final MulticastSocket socket;
	private final InetAddress groupIPAddress;
	private final int groupPort;
	private static final int MAX_LEN = 1000; 
        private final OnMessageQuery messageByPeer;
        
	ReadThreadMessages(MulticastSocket socket,InetAddress group,int port, OnMessageQuery mssgbypeer) 
	{ 
		this.socket = socket; 
		this.groupIPAddress = group; 
		this.groupPort = port; 
                this.messageByPeer = mssgbypeer;
	} 
	
	@Override
	public void run() 
	{ 
            
		while(!ChatAndShare.finished) 
		{ 
                    byte[] buffer = new byte[ReadThreadMessages.MAX_LEN]; 
                    DatagramPacket datagram = new
                    DatagramPacket(buffer,buffer.length,groupIPAddress,groupPort);
                    String message2;
                               
			try
			{ 
			socket.receive(datagram); 
			message2 = new
			String(buffer,0,datagram.getLength());
			if(!message2.startsWith(ChatAndShare.myName)) 
				messageByPeer.onAnyMessageQuery(message2);     
			} 
			catch(IOException e) 
			{ 
                            System.out.println("Socket closed!"); 
			} 
		} 
	} 
}
