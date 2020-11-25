package chatClient;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import socketDAO.RoomInfo;


public class ChatClientSocket {
	private static final String SERVER_IP = "192.168.0.10"; 	// 호스트 주소
	private static final int SERVER_PORT = 1234;				// 호스트 포트 번호
	final static String token = "@@##:";						// 토큰열 짜르기
	static Socket socket = new Socket();					// 소켓
	static PrintWriter pw;
	private static ChatClientSocket chatClientSocket = null;

	// 싱글톤 패턴
	// 앞으로 이것으로만 객체를 불러옵니다.
	public static ChatClientSocket getChatClientSocket(String name){
		if (chatClientSocket == null) {
			// Printer 인스턴스 생성
			chatClientSocket = new ChatClientSocket(name);
		}
		return chatClientSocket;
	}

	public Socket getSocket() {
		return socket;
	}

	private ChatClientSocket(String name) {
		try {
			socket.connect( new InetSocketAddress(SERVER_IP, SERVER_PORT) );
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
			String request = "join" + token + name + "\r\n";
			System.out.println(request);
			pw.println(request);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void quitSocket() {
		String request = "quit" + token + "\r\n";
		pw.println(request);
		System.exit(0);
	}
	
	public void logoutSocket() {
		String request = "quit" + token + "\r\n";
		pw.println(request);
	}

	public void sendMassage(String message) {
		String request = "message" + token + message + "\r\n";
		pw.println(request); 
	}
	
	public void createRoom(RoomInfo roomInfo) {
		String request = "createroom" + token + roomInfo.toString() + "\r\n";
		pw.println(request); 
	}
	
	public void removeRoom(RoomInfo roomInfo) {
		String request = "removeroom" + token + "\r\n";
		pw.println(request); 
	}
}