package photoServer;

import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class PhotoServer extends Thread {
	
	public String address;
    public int PORT;
    ServerSocket serverSocket = null;
    Socket socket;
    ArrayList<DataOutputStream> listWriters = new ArrayList<DataOutputStream>();
    PhotoServerProcessThread thd = null;
    boolean isThread = true;
    
    public PhotoServer(String address, int port) {
    	this.address = address;
    	this.PORT = port;
    	this.start();
    }

    public void run() {
    	try {
            // 1. 서버 소켓 생성
            serverSocket = new ServerSocket();

            // 2. 바인딩
            serverSocket.bind( new InetSocketAddress(address, PORT) );

            // 3. 요청 대기
            while(isThread) {
                socket = serverSocket.accept();
                thd = new PhotoServerProcessThread(socket, listWriters);
                thd.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if( serverSocket != null && !serverSocket.isClosed() ) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
    
    public void stopThread() {
    	
    	if (thd != null) {
    		thd.stopThread();
    	}
    	
    	isThread = false;
    	try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void drawSend(BufferedImage bi) {
    	thd.drawSend(bi);
    }

	public void textSend(String text) {
		thd.textSend(text);
		
	}
}
