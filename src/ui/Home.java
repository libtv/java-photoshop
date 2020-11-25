package ui;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.Dialog.ModalityType;
import java.awt.Color;
import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;

import chatClient.ChatClientSocket;
import ui.Login_State.LoginState;
import ui.Login_State.Login_state;

public class Home extends javax.swing.JFrame {
	static String nickname;
	private Login login = new Login();
	private PhotoRoom photoroom = null;
	private Students students = null;
	private JDialog dialog;

	public Home() {
		login_state.setLoginState(Off);
		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jLabel13 = new javax.swing.JLabel();
		exit = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jPanel5 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jPanel6 = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();

		//회원추가 add jLabel
		jLabel_login = new javax.swing.JLabel();


		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		setPreferredSize(new java.awt.Dimension(860, 550));
		setVisible(true);

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setMinimumSize(new java.awt.Dimension(860, 600));
		jPanel1.setPreferredSize(new java.awt.Dimension(854, 600));
		jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jPanel2.setBackground(new java.awt.Color(45, 118, 232));
		jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				jPanel2MouseDragged(evt);
			}
		});
		jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jPanel2MousePressed(evt);
			}
		});
		jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
		jLabel13.setForeground(new java.awt.Color(255, 255, 255));
		jLabel13.setText("designed by KeepToo, The Git remote repository and copyrights belong to \"libtv11\"");
		jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

		exit.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
		exit.setForeground(new java.awt.Color(255, 255, 255));
		exit.setText("X");
		// exit 버튼 구현 및 메소드 출현
		exit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				exitMousePressed(evt);
			}
		});
		jPanel2.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(838, 0, 22, -1));

		// 로고 라벨 추가
		jLabel15.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
		jLabel15.setForeground(new java.awt.Color(255, 255, 255));
		jLabel15.setText("HANBAT SIMPLE PHOTOSHOP");
		jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

		// 로그인 닉네임 추가
		jLabel_login.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
		jLabel_login.setForeground(new java.awt.Color(255, 255, 255));
		jLabel_login.setText("Please log in");
		jPanel2.add(jLabel_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));

		// 로고 아이콘 추가
		jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("./images/navigation.png"))); // NOI18N
		jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 90, 100));

		jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 240));

		jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("./images/download_52px.png"))); // NOI18N


		jPanel4.setBackground(java.awt.SystemColor.controlHighlight);
		jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				jPanel4MouseEntered(evt);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				jPanel4MouseExited(evt);
			}
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jPanel4MousePressed(evt);
			}
		});

		jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(45, 118, 232));
		jLabel3.setText("Login");

		jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("./images/home_48px.png"))); // NOI18N

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup()
						.addGap(46, 46, 46)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
								.addComponent(jLabel3)
								.addComponent(jLabel4))
						.addContainerGap(46, Short.MAX_VALUE))
				);
		jPanel4Layout.setVerticalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup()
						.addGap(22, 22, 22)
						.addComponent(jLabel4)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel3)
						.addContainerGap(29, Short.MAX_VALUE))
				);

		jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 140, 120));

		jPanel5.setBackground(java.awt.SystemColor.controlHighlight);
		jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				jPanel5MouseEntered(evt);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				jPanel5MouseExited(evt);
			}
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jPanel5MousePressed(evt);
			}
		});

		jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabel5.setForeground(new java.awt.Color(45, 118, 232));
		jLabel5.setText("Students");

		jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("./images/group_52px.png"))); // NOI18N

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(
				jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup()
						.addGap(46, 46, 46)
						.addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
								.addComponent(jLabel5)
								.addComponent(jLabel6))
						.addContainerGap(37, Short.MAX_VALUE))
				);
		jPanel5Layout.setVerticalGroup(
				jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup()
						.addGap(22, 22, 22)
						.addComponent(jLabel6)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel5)
						.addContainerGap(25, Short.MAX_VALUE))
				);

		jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 140, 120));

		jPanel6.setBackground(java.awt.SystemColor.controlHighlight);
		jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				jPanel6MouseEntered(evt);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				jPanel6MouseExited(evt);
			}
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jPanel6MousePressed(evt);
			}
		});

		jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabel7.setForeground(new java.awt.Color(45, 118, 232));
		jLabel7.setText("Room");

		jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("./images/classroom_48px.png"))); // NOI18N

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(
				jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup()
						.addGap(46, 46, 46)
						.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
								.addComponent(jLabel7)
								.addComponent(jLabel8))
						.addContainerGap(35, Short.MAX_VALUE))
				);
		jPanel6Layout.setVerticalGroup(
				jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup()
						.addGap(22, 22, 22)
						.addComponent(jLabel8)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel7)
						.addContainerGap(29, Short.MAX_VALUE))
				);

		jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 140, 120));


		jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabel11.setForeground(new java.awt.Color(45, 118, 232));
		jLabel11.setText("Classes");

		jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("./images/list_64px.png")));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
						.addGap(0))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(Alignment.LEADING)
				.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 589, Short.MAX_VALUE)
				);
		getContentPane().setLayout(layout);
		pack();

	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

	}// </editor-fold>//GEN-END:initComponents

	private void exitMousePressed(java.awt.event.MouseEvent evt) {
		try {
			if (nickname != null && ChatClientSocket.getChatClientSocket(nickname).getSocket() != null) {
				ChatClientSocket.getChatClientSocket(nickname).quitSocket();
			} else {
				System.exit(0);
			}
		} catch(Exception e) {
			System.exit(0);
		}
	}//GEN-LAST:event_exitMousePressed

	private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
		// TODO add your handling code here:
		setColor(jPanel4);
	}

	private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {
		resetColor(jPanel4);
	}

	int xx,xy;
	private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {

		xx = evt.getX();
		xy = evt.getY();
	}

	private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xx, y - xy);  
	}

	private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {
		setColor(jPanel5);
	}

	private void jPanel5MouseExited(java.awt.event.MouseEvent evt) {
		resetColor(jPanel5);
	}

	private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {
		setColor(jPanel6);
	}

	private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {
		resetColor(jPanel6);
	}
	
	//로그인버튼 클릭시에 
	private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {
		
		if (login_state.getLoginState() == Off) {
			login_state.setLoginState(On);
		} else if (login_state.getLoginState() == On) {
			login_state.setLoginState(Off);
		}
		login_state.Login();
	}

	public static Window windowForComponent (Component c) {
		if (c instanceof Window) 
			return (Window)c;

		return SwingUtilities.windowForComponent(c);
	}

	public void login_openTableAction() {
		dialog = new JDialog(null, "Login",
				ModalityType.APPLICATION_MODAL);
		dialog.setUndecorated(true);
		dialog.getContentPane().add(login);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		//로그인 된 상태여서 여기서 모든것을 처리해주면됌
		// 로그인이 된 상태이기 때문에
		jLabel_login.setText(login.getNickname());
		nickname = login.getNickname();
		jPanel2.add(jLabel_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(860 - jLabel_login.getMinimumSize().width - 10, 50, -1, -1));
		
		// 님이 입장하였습니다. doJoin 을 실행시키게 함.
		ChatClientSocket.getChatClientSocket(nickname);
		
		// 로그인이 된 상태에서 nickname 주고 받기
		photoroom = new PhotoRoom(nickname);
		students = new Students(photoroom);
		students.showing(nickname);
	}
	
	public void logout_openTableAction() {
		ChatClientSocket.getChatClientSocket(nickname).quitSocket();
		nickname = null;
	}
	
	private void students_openTableAction() {
		dialog = new JDialog(null, "Students",
				ModalityType.APPLICATION_MODAL);
		dialog.setUndecorated(false);
		dialog.getContentPane().add(students);
		dialog.pack();
		dialog.setVisible(true);
	}

	//로그인 후에 채팅방에 들어갈 시에
	private void jPanel5MousePressed(java.awt.event.MouseEvent evt) {
		try {
			if (nickname != null && ChatClientSocket.getChatClientSocket(nickname).getSocket() != null) {
				students_openTableAction();
			} else { // 로그인하지 않았을 경우 혹은 소켓이 없을 경우에!!
				JOptionPane.showMessageDialog(null, "Socket environment unstable or not logged in. Please try again. \nnickname :" + nickname , "error", JOptionPane.WARNING_MESSAGE);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Socket environment unstable or not logged in. Please try again. \nnickname :" + nickname , "error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void photoroom_openTableAction() {
		dialog = new JDialog(null, "PhotoRoom",
				ModalityType.APPLICATION_MODAL);
		dialog.setUndecorated(false);
		dialog.getContentPane().add(photoroom);
		dialog.pack();
		dialog.setVisible(true);
	}
	
	
	// 로그인 후에 포토룸에 들어갈 시에
	private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {
		try {
			if (nickname != null && ChatClientSocket.getChatClientSocket(nickname).getSocket() != null) {
				photoroom_openTableAction();
			} else { // 로그인하지 않았을 경우 혹은 소켓이 없을 경우에!!
				JOptionPane.showMessageDialog(null, "Socket environment unstable or not logged in. Please try again. \nnickname :" + nickname , "error", JOptionPane.WARNING_MESSAGE);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Socket environment unstable or not logged in. Please try again. \nnickname :" + nickname , "error", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void setColor(JPanel panel) {
		panel.setBackground(new java.awt.Color(197, 197, 197));
	}

	public void resetColor(JPanel panel) {
		panel.setBackground(new java.awt.Color(240,240,240));
	}

	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Home();
			}
		});
	}

	private javax.swing.JLabel exit;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	//add
	private javax.swing.JLabel jLabel_login;
	
	private Login_state login_state = new Login_state(this);
	private LoginState Off = new ui.Login_State.Off();
	private LoginState On = new ui.Login_State.On();
}
