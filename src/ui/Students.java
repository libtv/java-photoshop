package ui;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javax.swing.DefaultListModel;

import chatClient.ChatClientSocket;

public class Students extends javax.swing.JPanel {
	final static String token = "@@##:";						// 토큰열 짜르기
	String nickname;
	PhotoRoom photoroom;
	
    public Students(PhotoRoom photoroom) {
        initComponents();
        this.photoroom = photoroom;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(45, 118, 232));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("./images/group_52px_1.png"))); // NOI18N
        jLabel15.setText(" Students");
        
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 40, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 140));

        kGradientPanel1.setkEndColor(new java.awt.Color(204, 0, 204));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        kGradientPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 220, 340));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        
        jScrollPane2.setViewportView(jTextArea1);

        kGradientPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 340));

        jTextField1.setText("jTextField1");
        kGradientPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 900, 60));
        jTextField1.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                char keyCode = e.getKeyChar();
                if (keyCode == KeyEvent.VK_ENTER) {
                	ChatClientSocket.getChatClientSocket(nickname).sendMassage(jTextField1.getText());
                }
            }
        });

        add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 890, 400));
    }
    
    
    private class ChatClientReceiveThread extends Thread {
        Socket socket = null;

        ChatClientReceiveThread(Socket socket){
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                while(true) {
                    String msg = br.readLine();
                    
                    String[] tokens = msg.split(token);
                    if("join".equals(tokens[0])) {
                    	String[] st = tokens[1].split(":##@@");
                    	jList1.removeAll();
                    	DefaultListModel model = new DefaultListModel();
                    	for(String s : st) {
                    		model.addElement(s);
                    	}
                    	jList1.setModel(model);
                    }
                    else if("quit".equals(tokens[0])) {
                    	String[] st = tokens[1].split(":##@@");
                    	jList1.removeAll();
                    	DefaultListModel model = new DefaultListModel();
                    	for(String s : st) {
                    		model.addElement(s);
                    	}
                    	jList1.setModel(model);
                    }
                    else if("message".equals(tokens[0])) {
                    	jTextArea1.append(tokens[1]);
                    	jTextArea1.append("\n");
                    }
                    else if("showroom".equals(tokens[0])) {
                    	DefaultListModel model = new DefaultListModel();
                    	try {
                        	String[] st = tokens[1].split(":##@@");
                        	for(String s : st) {
                        		model.addElement(s);
                        		System.out.println(s);
                        	}
                        	photoroom.setData(model);
                    	} catch (Exception e) {
                    		photoroom.setData(model);
                    	}

                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void showing(String nickname) {
    	new ChatClientReceiveThread(ChatClientSocket.getChatClientSocket(nickname).getSocket()).start();	// 스레드로 처리하기
    	this.nickname = nickname;
    }

    private javax.swing.JLabel jLabel15;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private keeptoo.KGradientPanel kGradientPanel1;
}
