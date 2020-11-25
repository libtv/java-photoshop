package socketDAO;

public class RoomInfo {
	// RoomInfo는 여러가지의 방을 출력하는 클래스임
	// 서버에서는 방 목록을 지원하고 클라이언트가 방을 만드는 구조로 선택함
	// 따라서 클라이언트의 IP를 이용하여 만들기 떄문에 IP와 소켓 넘버가 필요함.
	
	String 			adminIP;		// 방 아이피
	int 			adminPort;		// 방 포트번호
	String 			roomName;		// 방이름
	
	public RoomInfo(String adminIP, int adminPort, String roomName) {
		this.adminIP = adminIP;
		this.adminPort = adminPort;
		this.roomName = roomName;
	}
	
	public String getAdminIP() {
		return adminIP;
	}

	public void setAdminIP(String adminIP) {
		this.adminIP = adminIP;
	}

	public int getAdminPort() {
		return adminPort;
	}

	public void setAdminPort(int adminPort) {
		this.adminPort = adminPort;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public String toString() {
		StringBuilder st= new StringBuilder();
		st.append(this.adminIP);
		st.append("@@!");
		st.append(this.adminPort);
		st.append("@@!");
		st.append(this.roomName);
		return st.toString();
		
	}
}
