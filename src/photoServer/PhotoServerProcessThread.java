package photoServer;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ui.HanbatPhotoShop_SERVER;

public class PhotoServerProcessThread extends Thread {
    private String nickname = null;
    private Socket socket = null;
    ArrayList<DataOutputStream> listWriters = null;
    boolean isThread = true;
    final String token = "@@##:";			// 토큰 정보

    public PhotoServerProcessThread(Socket socket, ArrayList<DataOutputStream> listWriters) {
        this.socket = socket;
        this.listWriters = listWriters;
    }

    @Override
    public void run() {
        try {
            BufferedReader buffereedReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            DataOutputStream printWriter = new DataOutputStream(socket.getOutputStream());

            while(isThread) {
                String request = buffereedReader.readLine();

                if( request == null) {
                    consoleLog("클라이언트로부터 연결 끊김");
                    doQuit(printWriter);
                    break;
                }

                String[] tokens = request.split(token);
                if("join".equals(tokens[0])) {
                    doJoin(tokens[1], printWriter);
                }
                else if("message".equals(tokens[0])) {
                    doMessage(tokens[1]);
                    HanbatPhotoShop_SERVER.chatLeading(this.nickname + ":" + tokens[1]);
                    
                }
                else if("quit".equals(tokens[0])) {
                    doQuit(printWriter);
                }
            }
            
            System.out.println("실행 종료");
        }
        catch(IOException e) {
            consoleLog(this.nickname + "님이 채팅방을 나갔습니다.");
        }
    }

    private void doQuit(DataOutputStream writer) {
        removeWriter(writer);
        
        String tokens = "message" + token;
        String data = this.nickname + "님이 퇴장했습니다.";
        broadcast(tokens + data, null);
    }

    private void removeWriter(DataOutputStream writer) {
        synchronized (listWriters) {
            listWriters.remove(writer);
        }
    }

    private void doMessage(String data) {
        String toknes = "message" + token;
        broadcast(toknes + this.nickname + ":" + data, null);
    }

    private void doJoin(String nickname, DataOutputStream writer) {
        this.nickname = nickname;
        String tokens = "message" + token;
        String data = nickname + "님이 입장하였습니다.";
        
        broadcast(tokens + data, null);

        // writer pool에 저장
        addWriter(writer);
    }

    private void addWriter(DataOutputStream writer) {
        synchronized (listWriters) {
            listWriters.add(writer);
        }
    }

    
    private void broadcast(String data, BufferedImage data2) {
        synchronized (listWriters) {
            for(DataOutputStream writer : listWriters) {
            	String[] tokens = data.split(token);
            	if("message".equals(tokens[0])) {
            		try {
						writer.writeUTF("message" + token);
	            		writer.flush();
	            		
	            		writer.writeUTF(tokens[1]);
	            		writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

                } else if ("bufferedimage".equals(tokens[0])) {
            		int filesize;
            		ByteArrayOutputStream bScrn = new ByteArrayOutputStream();
            		byte[] imgByte = bScrn.toByteArray();
            		try {
						writer.writeUTF("bufferedimage" + token);
	            		writer.flush();

	            		
	            		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            		ImageIO.write(data2, "jpg", baos);
	            		
	            		baos.flush();
	            		byte[] imageInByte = baos.toByteArray();
	            		
	            		writer.writeInt(imageInByte.length);
	            		writer.flush();
	            		writer.write(imageInByte);
	            		writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        }
    }

    private void consoleLog(String log) {
        System.out.println(log);
    }
    
    public void stopThread() {
    	isThread = false;
    }

	public void drawSend(BufferedImage data) {
		String tokens = "bufferedimage" + token;
		broadcast(tokens, data);
	}

	public void textSend(String text) {
        String toknes = "message" + token;
        broadcast(toknes + "Admin" + ":" + text, null);
	}
}