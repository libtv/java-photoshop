package chatServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import socketDAO.RoomInfo;
import socketDAO.UserInfo;

public class ChatServer {
	static final int SERVER_PORT = 1234;
	static ServerSocket server = null;
	static ArrayList<UserInfo> userlist = new ArrayList<UserInfo>();
	static ArrayList<RoomInfo> roomlist = new ArrayList<RoomInfo>();
	
	public static void main(String args[]) {
		try {
			server = new ServerSocket();
			String localHostAddress = InetAddress.getLocalHost().getHostAddress();
			server.bind(new InetSocketAddress(localHostAddress, SERVER_PORT));
			System.out.println("[server] binding! \naddress:" + localHostAddress + ", port:" + SERVER_PORT);
			
			while(true) {
		        Socket socket = server.accept();
		        new ChatServerProcessThread(socket, userlist, roomlist).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}