package chatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import socketDAO.RoomInfo;
import socketDAO.UserInfo;

// 일반 채팅방 Source

public class ChatServerProcessThread_save extends Thread{
    /* 객체 직렬화를 위한 타입을 선언한다.
     * Type 1. join
     * Type 2. message
     * Type 3. quit
     * Type 4. showroom
     */
    final int N_JOIN = 1;
    final int N_MASSAGE = 2;
    final int N_QUIT = 3;
    final int N_SHOWROOM = 4;
    
    private Socket socket = null;			// 소켓 정보
    ArrayList<UserInfo> userlist = null;	// 유저 리스트
    UserInfo userinfo = null;				// 유저 정보
    final String token = "@@##:";			// 토큰 정보
    ArrayList<RoomInfo> roomlist = null;	// 룸 정보
    RoomInfo roominfo = null;				// 룸 방장 정보

    
    //static으로 받은 listWriters를 통해 모든 공유가 가능해서 여기서 처리해도 됨
    public ChatServerProcessThread_save(Socket socket, ArrayList<UserInfo> userlist, ArrayList<RoomInfo> roomlist) { 
        this.socket = socket;
        this.userlist = userlist;
        this.roomlist = roomlist;
    }

    @Override
    public void run() {
        try {
            BufferedReader buffereedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            while(true) {
                String request = buffereedReader.readLine();
                if( request == null ) {
                    consoleLog("클라이언트로부터 연결 끊김");
                    doQuit(userinfo);
                    break;
                }
                
                String[] tokens = request.split(token);
                if("join".equals(tokens[0])) {
                    doJoin(tokens[1], printWriter);
                    doRoom(roomlist);
                } else if("message".equals(tokens[0])) {
                    doMessage(tokens[1]);
                } else if("quit".equals(tokens[0])) {
                    doQuit(userinfo);
                    doRoom(roomlist);
                } else if ("createroom".equals(tokens[0])) {
                	// 방이 만들어졌으면 
                	doCreateRoom(tokens[1]);
                } else if ("removeroom".equals(tokens[0])) {
                	// 방이 만들어졌으면 
                	doRemoveRoom(roominfo);
                }
            }
        }
        catch(IOException e) {
            consoleLog(userinfo.getUser_name() + "님이 채팅방을 나갔습니다.");
        }
    }
    
    private void doCreateRoom(String roominfomation) {
    	
    	String[] tokens = roominfomation.split("@@!");
    	roominfo = new RoomInfo(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
    	
        synchronized (roomlist) {
        	roomlist.add(roominfo);
        	broadcast("roomlist", N_SHOWROOM);
        }
    }
    
    private void doRemoveRoom(RoomInfo room) {
        synchronized (roomlist) {
        	roomlist.remove(room);
        	broadcast("roomlist", N_SHOWROOM);
        }
        
    }

    private void doRoom(ArrayList<RoomInfo> roomlist2) {
    	broadcast("roomlist", N_SHOWROOM);
	}

	private void doQuit(UserInfo user) {
        removeWriter(user);

        String data = user.getUser_name() + "님이 퇴장했습니다.";
        broadcast(data, N_MASSAGE);
    }

    private void removeWriter(UserInfo user) {
        synchronized (userlist) {
        	userlist.remove(user);
        	broadcast("userlist", N_QUIT);
        }
    }

    private void doMessage(String data) {
        broadcast(userinfo.getUser_name() + ":" + data, N_MASSAGE);
    }

    private void doJoin(String nickname, PrintWriter writer) {
    	userinfo = new UserInfo(writer, nickname, 0, null);

        String data = nickname + "님이 입장하였습니다.";
        broadcast(data, N_MASSAGE);
        addWriter(userinfo);
    }

    private void addWriter(UserInfo userinfo) {
        synchronized (userlist) {
        	userlist.add(userinfo);
        	broadcast("userlist", N_JOIN);
        }
    }

    private void broadcast(String data, int type) {
        synchronized (userlist) {
        	String tok = "";
        	
        	Iterator<UserInfo> iterator = userlist.iterator();
        	
        	if (type == N_MASSAGE) {
        		tok = "message" + token;
        		while(iterator.hasNext()) {
        			UserInfo user = iterator.next();
            		user.getPrintWriter().println(tok + data);
            		user.getPrintWriter().flush();
            	}
        	} else if (type == N_JOIN) {
        		tok = "join" + token;
        		data = getUser();
        		while(iterator.hasNext()) {
        			UserInfo user = iterator.next();
            		user.getPrintWriter().println(tok + data);
            		user.getPrintWriter().flush();
            	}
        	} else if (type == N_QUIT) {
        		tok = "quit" + token;
        		data = getUser();
        		while(iterator.hasNext()) {
        			UserInfo user = iterator.next();
            		user.getPrintWriter().println(tok + data);
            		user.getPrintWriter().flush();
            	}        		
        	//채팅룸 보여주기
        	} else if (type == N_SHOWROOM) {
        		tok = "showroom" + token;
        		data = getRoom();
        		while(iterator.hasNext()) {
        			UserInfo user = iterator.next();
            		user.getPrintWriter().println(tok + data);
            		user.getPrintWriter().flush();
            	}
        	}
        }
    }

    private String getUser() {
		StringBuilder st= new StringBuilder();
		for(UserInfo user : userlist) {
			st.append(user.getUser_name());
			st.append(":##@@");
		}
		return st.toString();
	}

	private void consoleLog(String log) {
        System.out.println(log);
    }
	
	
	//여기서부터 시작하자
	private String getRoom() {
		StringBuilder st= new StringBuilder();
		for(RoomInfo room : roomlist) {
			st.append(room.getAdminIP());
			st.append("@@!");
			st.append(room.getAdminPort());
			st.append("@@!");
			st.append(room.getRoomName());
			st.append(":##@@");
		}
		return st.toString();
	}
   
}