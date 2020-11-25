package socketDAO;

import java.io.PrintWriter;

public class UserInfo {
	// 유저의 고유 정보는 PrintWriter의 소켓정보로 구현을 하겠음.
	// 유저는 크게 	1. 유저 정보 
	//			2. 유저 닉네임
	//			3. 유저 학번
	//			4. 유저 이메일
	// 로 구현 차후에 추가할 내용이 있으면 포함하겠음.
	
	PrintWriter 	printWriter;
	String 			user_name;
	int 			user_id;
	String 			user_email;
	
	public UserInfo(PrintWriter p, String n, int i, String e) {
		printWriter = p;
		user_name = n;
		user_id = i;
		user_email = e;
	}
	
	public PrintWriter getPrintWriter() {
		return printWriter;
	}
	
	public void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_email() {
		return user_email;
	}
	
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

}
