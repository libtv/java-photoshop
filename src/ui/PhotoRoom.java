package ui;

import java.awt.Dialog.ModalityType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import chatClient.ChatClientSocket;
import keeptoo.Drag;
import socketDAO.RoomInfo;

public class PhotoRoom extends javax.swing.JPanel {
	final static String token = "@@##:";						// 토큰열 짜르기
	String nickname;
	private JDialog dialog;
	private CreateRoom createroom = new CreateRoom();
	
    public PhotoRoom(String nickname) {
    	this.nickname = nickname;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        kButton1 = new keeptoo.KButton();
        kButton2 = new keeptoo.KButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(45, 118, 232));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("./images/navigation.png"))); // NOI18N
        jLabel15.setText(" Photo Room");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 40, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 140));

        kGradientPanel1.setkEndColor(new java.awt.Color(204, 0, 204));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);				//리스트의 단일 선택 모드 변경

        jScrollPane3.setViewportView(jList2);

        kGradientPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 260));

        kButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kButton1.setkBorderRadius(40);
        kButton1.setkEndColor(new java.awt.Color(0, 204, 204));
        kButton1.setkStartColor(new java.awt.Color(204, 0, 240));
        kButton1.setLabel("Enter");
        kButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                kButton1MousePressed(evt);
            }
        });
        kGradientPanel1.add(kButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 180, 40));

        kButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kButton2.setkBorderRadius(40);
        kButton2.setkEndColor(new java.awt.Color(0, 204, 204));
        kButton2.setkStartColor(new java.awt.Color(204, 0, 240));
        kButton2.setLabel("Create Room");
        kButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                kButton2MousePressed(evt);
            }
        });
        kGradientPanel1.add(kButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 180, 40));

        add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 670, 340));
    }// </editor-fold>//GEN-END:initComponents

    private void kButton2MousePressed(java.awt.event.MouseEvent evt) {
		dialog = new JDialog(null, "Students",
				ModalityType.APPLICATION_MODAL);
		dialog.setUndecorated(false);
		dialog.getContentPane().add(createroom);
		dialog.pack();
		dialog.setVisible(true);
		
		// 후에 처리할 것들
		int portnum = createroom.getPortNum();
		String title = createroom.getTitle();
		RoomInfo roominfo;
		// 소켓 만들고 처리할 예정!!
		try {
			roominfo = new RoomInfo(InetAddress.getLocalHost().getHostAddress(),
					portnum, title);
			ChatClientSocket.getChatClientSocket(nickname).createRoom(roominfo);
			ServerPhotoshop_Open(roominfo); // 포토샵 열기
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
    }
    
    private void kButton1MousePressed(java.awt.event.MouseEvent evt) {
		String source = (String) jList2.getSelectedValue();
		String[] token = source.split("@@!");
		HanbatPhotoShop_CLIENT photoshop_client = new HanbatPhotoShop_CLIENT(nickname, token[0], Integer.parseInt(token[1]));
    	JDialog dialog = new JDialog(null, "Students",
				ModalityType.APPLICATION_MODAL);
    	dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		dialog.getContentPane().add(photoshop_client);
		dialog.setResizable(false);
		dialog.pack();
		dialog.setVisible(true);
		
    }

	private void ServerPhotoshop_Open(RoomInfo roominfo) {
		HanbatPhotoShop_SERVER photoshop_sever = new HanbatPhotoShop_SERVER(nickname, roominfo);
    	JDialog dialog = new JDialog(null, "Students",
				ModalityType.APPLICATION_MODAL);
    	dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		dialog.getContentPane().add(photoshop_sever);
		dialog.setResizable(false);
		dialog.pack();
		dialog.setVisible(true);
		
		// 포토샵 끄면
		photoshop_sever.stopThread();
    }
    
    // 리스트 바꾸는 함수. 
    public void setData(DefaultListModel model) {
    	jList2.removeAll();
    	jList2.setModel(model);
    }
    


    private javax.swing.JLabel jLabel15;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private keeptoo.KButton kButton1;
    private keeptoo.KButton kButton2;
    private keeptoo.KGradientPanel kGradientPanel1;
}
