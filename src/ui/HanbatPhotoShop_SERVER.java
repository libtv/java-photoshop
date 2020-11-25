package ui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.ShortLookupTable;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import chatClient.ChatClientSocket;
import keeptoo.Drag;
import keeptoo.KGradientPanel;
import photoServer.PhotoServer;
import socketDAO.RoomInfo;
import ui.abstract_Factory.AbstractFactory;
import ui.abstract_Factory.Brush_Factory;
import ui.abstract_Factory.Circle_Factory;
import ui.abstract_Factory.DisplayFactory;
import ui.abstract_Factory.Displays;
import ui.abstract_Factory.Dog_Factory;
import ui.abstract_Factory.Line_Factory;
import ui.abstract_Factory.Rect_Factory;
/**
 *
 * @author libtv
 */
public class HanbatPhotoShop_SERVER extends javax.swing.JPanel implements ActionListener {
	
    public HanbatPhotoShop_SERVER(String nickname, RoomInfo roominfo) {
    	this.nickname = nickname;
    	this.roominfo = roominfo;
        initComponents();
        ps = new PhotoServer(roominfo.getAdminIP(), roominfo.getAdminPort());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        kGradientPanel1 = new javax.swing.JPanel();
        btn_exit = new javax.swing.JLabel();
        kGradientPanel3 = new DrawPanel();
        kGradientPanel2 = new javax.swing.JPanel();
        //new javax.swing.ImageIcon(getClass().getResource("./images/open.png"))
        jButton1 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/open.png")));
        jButton2 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/dog.png")));
        jButton3 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/grayscale.png")));
        jButton4 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/undo.png")));
        jButton5 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/line.png")));
        jButton6 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/brush.png")));
        jButton7 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/rectangle.png")));
        jButton8 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/edge.png")));
        jButton9 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/color.png")));
        jButton10 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/lookup.png")));
        jButton11 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/distinction.png")));
        jButton12 = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("./images/distinction.png")));
        // 엣지 컬러 룩업 디스틴트
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField2 = new javax.swing.JTextField();
        

        setBackground(new java.awt.Color(102, 102, 102));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setBackground(new java.awt.Color(102, 102, 102));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_exit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_exit.setForeground(new java.awt.Color(255, 255, 255));
        btn_exit.setText("X");
        btn_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_exitMouseClicked(evt);
            }
        });
        kGradientPanel1.add(btn_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, -1, -1));

        kGradientPanel3.setBackground(new java.awt.Color(153, 153, 153));
        kGradientPanel1.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 930, 710));

        kGradientPanel2.setBackground(new java.awt.Color(102, 102, 102));
        kGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // x = + 70 / y = + 30
        jButton1.setActionCommand("Load");
        jButton1.setToolTipText("이미지 불러오기");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setOpaque(false);
        jButton1.addActionListener(this);
        kGradientPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 30, -1));
        
        jButton2.setActionCommand("Dod");
        jButton2.setToolTipText("돋보기");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setOpaque(false);
        jButton2.addActionListener(this);
        kGradientPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 100, -1));
        
        jButton3.setActionCommand("Gray");
        jButton3.setToolTipText("그레이 스케일(grayscale)");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.setOpaque(false);
        jButton3.addActionListener(this);
        kGradientPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 30, -1));
        
        jButton4.setActionCommand("Undo");
        jButton4.setToolTipText("되돌리기");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusPainted(false);
        jButton4.setOpaque(false);
        jButton4.addActionListener(this);
        kGradientPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 100, -1));
        
        jButton5.setActionCommand("Line");
        jButton5.setToolTipText("선 그리기");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
        jButton5.setOpaque(false);
        jButton5.addActionListener(this);
        kGradientPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 30, -1));
        
        jButton6.setActionCommand("Brush");
        jButton6.setToolTipText("브러쉬 그리기");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusPainted(false);
        jButton6.setOpaque(false);
        jButton6.addActionListener(this);
        kGradientPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 100, -1));
        
        jButton7.setActionCommand("Rect");
        jButton7.setToolTipText("사각형 그리기");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        jButton7.setOpaque(false);
        jButton7.addActionListener(this);
        kGradientPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 30, -1));
        
        jButton8.setActionCommand("Edge");
        jButton8.setToolTipText("Edge Detection");
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setFocusPainted(false);
        jButton8.setOpaque(false);
        jButton8.addActionListener(this);
        kGradientPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 100, -1));
        
        jButton9.setActionCommand("Color");
        jButton9.setToolTipText("Color 보기");
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setFocusPainted(false);
        jButton9.setOpaque(false);
        jButton9.addActionListener(this);
        kGradientPanel2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 30, -1));

        jButton10.setActionCommand("Lookup");
        jButton10.setToolTipText("Lookup");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setFocusPainted(false);
        jButton10.setOpaque(false);
        jButton10.addActionListener(this);
        kGradientPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 100, -1));
        
        jButton11.setActionCommand("Distinct");
        jButton11.setToolTipText("Distinct");
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setFocusPainted(false);
        jButton11.setOpaque(false);
        jButton11.addActionListener(this);
        kGradientPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 30, -1));
        
        jButton12.setActionCommand("Circle");
        jButton12.setToolTipText("원 그리기");
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.setFocusPainted(false);
        jButton12.setOpaque(false);
        jButton12.addActionListener(this);
        kGradientPanel2.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 100, -1));

        kGradientPanel1.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 710));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        kGradientPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 1030, 60));

        jTextField2.setText("jTextField2");
        jTextField2.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                char keyCode = e.getKeyChar();
                if (keyCode == KeyEvent.VK_ENTER) {
                	ps.textSend(jTextField2.getText());
                    jTextArea1.append("Admin : " + jTextField2.getText());
                    jTextArea1.append("\n");
                    jTextField2.setText("");
                }
            }
        });
        kGradientPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 770, 1030, 30));

        add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 800));
    }

    private void btn_exitMouseClicked(java.awt.event.MouseEvent evt) {
    	ChatClientSocket.getChatClientSocket(nickname).removeRoom(roominfo);
    	okButtonAction();
    }
    
	private void okButtonAction() {

	    Window win = SwingUtilities.getWindowAncestor(this);
	    if (win != null) {
	       win.dispose();
	    }
	} 
	
	public void stopThread() {
		ps.stopThread();
	}  

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton myButton = (JButton)e.getSource();
		String temp = myButton.getActionCommand();
		AbstractFactory abstractFactory = null;
		
		switch(temp) {
			case "Load":
				LoadImage();
				break;
			case "Gray":
				GrayImage();
				break;
			case "Undo":
				UndoImage();
				break;
			case "Edge":
				EdgeImage();
				break;
			case "Lookup":
				LookupImage();
				break;
			case "Distinct":
				DistinctImage();
				break;
			case "Color":
	            Color selectedColor = JColorChooser.showDialog(null,"Color",Color.YELLOW);
	            if(selectedColor!=null)
	            	color = selectedColor;
				break;
			case "Dod":
				abstractFactory = new Dog_Factory(expand, this);
				break;
			case "Line":
				abstractFactory = new Line_Factory(line, this);
				break;
			case "Brush":
				abstractFactory = new Brush_Factory(brush, this);
				break;
			case "Rect":
				abstractFactory = new Rect_Factory(rect, this);
				break;
			case "Circle":
				abstractFactory = new Circle_Factory(circle, this);
				break;
		}
		if(abstractFactory != null) {
			Displays factory = DisplayFactory.getProduct(abstractFactory);
			factory.print();
		}
	}


	/**
	***  PhotoShop Source
	***  create Kim-Jun-Ho 
	*	 hanbat Univ. 20177115
	**/
	
	
	// JPanel Class
	class DrawPanel extends JPanel {		
		private static final long serialVersionUID = 1L;
		Point startP = null;
		Point endP = null;
		
		DrawPanel(){			
			addMouseMotionListener(new MouseAdapter() {
				
				@Override
				public void mouseMoved(MouseEvent e) {
					mx = e.getX();
					my = e.getY();
					if(expand == true) {
						repaint();
					}
				}
				
				@Override
				public void mouseDragged(MouseEvent e) {
					if (brush) {
						mx = e.getX();
						my = e.getY();
						
						moving();
						drawSend(photoshop_Image);
						repaint();
					}
					
					if (rect || line || circle) {
						mx = e.getX();
						my = e.getY();
						repaint();
					}

				}

				private void moving() {
					Graphics g = photoshop_Image.getGraphics();
			    	if (brush) {
				    	g.setColor(color);
				    	g.fillOval(mx-10, my-10, 20, 20);
				    	g.dispose();
			    	}

				}
			});
			
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mousePressed(MouseEvent e) {
					if (brush || line || circle) {
						imageUndo.push(deepCopy(photoshop_Image)); 	// 원본 이미지 저장
					}
					startP = e.getPoint();
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {
					endP = e.getPoint();
					if (line) {
						_last = true;
						repaint();
						drawSend(photoshop_Image);
					}
					
					if (rect) {
						mx = e.getX();
						my = e.getY();
						
						_last = true;
						repaint();
						drawSend(photoshop_Image);
					}
					
					if (circle) {
						mx = e.getX();
						my = e.getY();
						_last = true;
						repaint();
						drawSend(photoshop_Image);
					}
					
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// 이미지를 다시 그리는 것
			Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(photoshop_Image, 0, 0, null);
			
			if(expand == true) {
				if(mx>25 && my>25 && mx+25<w && my+25<h) {
					BufferedImage temp = photoshop_Image.getSubimage(mx-25, my-25, 50, 50);
					Image image = temp.getScaledInstance(expwidth, expwidth, Image.SCALE_SMOOTH);
					expcircle = new Ellipse2D.Float(mx - expwidth/2, my - expwidth/2, expwidth, expwidth);
					g2.setClip(expcircle);
					g2.drawImage(image, mx-expwidth/2, my-expwidth/2, expwidth, expwidth, null);
				}
			}
			
			if (line) {
				g.setColor(color);
				g.drawLine(startP.x, startP.y, mx, my);	
				g.dispose();
				
				if (_last) {
					g = photoshop_Image.getGraphics();
					g.setColor(color);
					g.drawLine(startP.x, startP.y, endP.x, endP.y);	
					_last = false;
					g.dispose();
				}
			}

	    	if (rect) {
	    		g.setColor(color);
	    		g.drawRect(startP.x, startP.y, Math.abs(startP.x-mx), Math.abs(startP.y-my));
	    		g.dispose();
	    		if (_last) {
		    		g = photoshop_Image.getGraphics();
		    		g.setColor(color);
		    		g.drawRect(startP.x, startP.y, Math.abs(startP.x-mx), Math.abs(startP.y-my));
		    		_last = false;
		    		g.dispose();
	    		}
	    	}
	    	
	    	if (circle) {
	    		g.setColor(color);
	    		g.fillOval(startP.x, startP.y, Math.abs(startP.x-mx), Math.abs(startP.y-my));
	    		g.dispose();
	    		if (_last) {
		    		g = photoshop_Image.getGraphics();
		    		g.setColor(color);
		    		g.fillOval(startP.x, startP.y, Math.abs(startP.x-mx), Math.abs(startP.y-my));
		    		_last = false;
		    		g.dispose();
	    		}
	    	}
			
		}		
	}
	
	private void UndoImage() {
		if(imageUndo.isEmpty() == false) {
			photoshop_Image = deepCopy(imageUndo.pop());
			Graphics2D draw = (Graphics2D)kGradientPanel3.getGraphics();	// 패널을 알아내서
			draw.drawImage(photoshop_Image, 0, 0, null);	
		}
	}
	
	private void EdgeImage() {
		imageUndo.push(deepCopy(photoshop_Image)); 							// Image save
		
		float[] sharpen = new float[] {
			     0.0f, -1.0f, 0.0f,
			    -1.0f, 5.0f, -1.0f,
			     0.0f, -1.0f, 0.0f
			};
		Kernel kernel = new Kernel(3, 3, sharpen);
		ConvolveOp op = new ConvolveOp(kernel);
		photoshop_Image = op.filter(photoshop_Image, null);
		double[][] ar = toArray(photoshop_Image); 
		double[][] filterBlur = 
			{{0.088 , 0.107 , 0.088}, 
			 {0.107 , 0.214 , 0.107},
	   	   	 {0.088 , 0.107 , 0.088}}; 
		ar = convolution(ar, filterBlur); 
		double[][] filterEdge = 
			{ { -1, -1, -1 }, 
			  { -1,  8, -1  }, 
			  { -1, -1, -1  }}; 
		ar = convolution(ar, filterEdge); 
		ar = arrayInColorBound(ar); 
		ar = arrayColorInverse(ar); 
		photoshop_Image = deepCopy(toImage(ar)); 

		Graphics2D g = (Graphics2D)kGradientPanel3.getGraphics();
		g.drawImage(photoshop_Image, 0, 0, null);
	}
	
	public static BufferedImage toImage(double[][] ar) { 
		BufferedImage output = new BufferedImage(ar[0].length, ar.length, BufferedImage.TYPE_INT_BGR); 
		for (int y = 0; y < ar.length; y++) { 
			for (int x = 0; x < ar[0].length; x++) { 
				output.setRGB(x, y, new Color((int) ar[y][x], (int) ar[y][x], (int) ar[y][x]).getRGB()); 
			} 
		} 
		return output; 
	} 
	
	public double[][] convolution(double map[][], double[][] filter){

		double output[][] = new double[map.length][map[0].length];
		for (int y = 0; y < map.length; y++) { 
			for (int x = 0; x < map[y].length; x++) { 
				for (int i = 0; i < filter.length; i++) { 
					for (int j = 0; j < filter[i].length; j++) { 
						try { 
							output[y][x] += map[y - i + 1][x - j + 1] * filter[i][j]; 
						} catch (ArrayIndexOutOfBoundsException e) { 
						}
					}
				}
			}
		}
		return output;
	}
	
	public double[][] arrayInColorBound(double[][] ar){
		for (int i = 0; i < ar.length; i++) { 
			for (int j = 0; j < ar[i].length; j++) { 
				ar[i][j] = Math.max(0, ar[i][j]); 
				ar[i][j] = Math.min(225, ar[i][j]); 
			} 
		} 
		return ar; 
	}
	
	public static double[][] arrayColorInverse(double[][] ar) { 
		for (int i = 0; i < ar.length; i++) { 
			for (int j = 0; j < ar[i].length; j++) { 
				ar[i][j] = 255 - ar[i][j]; 
			} 
		} 
		return ar; 
	} 
	
	public double[][] toArray(BufferedImage bi) {
		double [][] output = new double[bi.getHeight()][bi.getWidth()];
		for(int y = 0; y < bi.getHeight(); y++) {
			for(int x = 0; x < bi.getWidth(); x++) {
				Color c = new Color(bi.getRGB(x, y));
				output [y][x] = (c.getRed() + c.getBlue() + c.getGreen())/3;
			}
		}
		return output;
	}
	
	// 이미지 복사하기 메소드
	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	// 이미지 그레이스케일
	private void GrayImage() {
		imageUndo.push(deepCopy(photoshop_Image)); 							// Image save
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				Color c = new Color(photoshop_Image.getRGB(j, i));
				int red = (int)(c.getRed() * 0.299);
				int green = (int)(c.getGreen() * 0.587);
				int blue = (int)(c.getBlue() * 0.114);
				Color newColor = new Color(red+green+blue, red+green+blue, red+green+blue);
				photoshop_Image.setRGB(j, i, newColor.getRGB());
			}
		}
		Graphics2D draw = (Graphics2D)kGradientPanel3.getGraphics();
		draw.drawImage(photoshop_Image, 0, 0, null);
	}
	
	private void DistinctImage() {
		// TODO Auto-generated method stub
		imageUndo.push(deepCopy(photoshop_Image));
		float[] sharpen = new float[] {
			     0.0f, -1.0f, 0.0f,
			    -1.0f, 5.0f, -1.0f,
			     0.0f, -1.0f, 0.0f
			};
			Kernel kernel = new Kernel(3, 3, sharpen);
			ConvolveOp op = new ConvolveOp(kernel);
			photoshop_Image = op.filter(photoshop_Image, null);
			Graphics2D draw = (Graphics2D)kGradientPanel3.getGraphics();
			draw.drawImage(photoshop_Image, 0, 0, null);
	}

	private void LookupImage() {
		// TODO Auto-generated method stub
		imageUndo.push(deepCopy(photoshop_Image));
		short[] data = new short[256];
		for (short i = 0; i < 256; i++) {
		    data[i] = (short) (255 - i);
		}

		LookupTable lookupTable = new ShortLookupTable(0, data);
		LookupOp op = new LookupOp(lookupTable, null);
		photoshop_Image = op.filter(photoshop_Image, null);
		Graphics2D draw = (Graphics2D)kGradientPanel3.getGraphics();
		draw.drawImage(photoshop_Image, 0, 0, null);
	}

	
	// 이미지 Load 메소드
	private void LoadImage() {
		JFileChooser chooser = new JFileChooser();
		String filePath;
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
		chooser.setFileFilter(filter);
		int ret = chooser.showOpenDialog(null);
		if(ret!= JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//파일 경로 알아내서
		filePath = chooser.getSelectedFile().getPath();
		try {
			
			BufferedImage image;														// 저장된 이미지			
			File input = new File(filePath);											// 파일 이미지를 불러와서
			image = ImageIO.read(input);												// 버퍼 이미지에 저장하고
			// 이미지 resize 하기
			w = kGradientPanel3.getWidth();
			h = kGradientPanel3.getHeight();
			resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			photoshop_Image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);		// 새로운 이미지를 만들어서 rgb 형태로 만들고
			
			// photoshop_Image 에서는 그리기
			Graphics g = photoshop_Image.getGraphics();								
			g.drawImage(resizeImage, 0, 0, null);
			g.dispose();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Graphics2D draw = (Graphics2D)kGradientPanel3.getGraphics();					// 패널을 알아내서
		draw.drawImage(photoshop_Image, 0, 0, null);									// 패널에 그리기
		imageUndo.push(deepCopy(photoshop_Image)); 										// 원본 이미지 저장
		drawSend(photoshop_Image);
	}
	
	// 이미지를 변경하면 서버소켓에서 클라이언트에게 보내는 메소드
	public void drawSend(BufferedImage bi) {
		ps.drawSend(bi);
	}

	// 채팅 읽어오기 메소드
	public static void chatLeading(String str) {
        jTextArea1.append(str);
        jTextArea1.append("\n");
	}
	
	
	/* 
	 * 
	 * 모든 변수 설정하기
	 * 
	*/
	
    private javax.swing.JLabel btn_exit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private JPanel kGradientPanel1;
    private JPanel kGradientPanel2;
    private DrawPanel kGradientPanel3;
    
	int w = 560;
	int h = 720;
	int mx;
	int my;
	int expwidth = 100;
	BufferedImage photoshop_Image;
	Stack<BufferedImage> imageUndo = new Stack<BufferedImage>();				// image undo Memento 패턴
	Image resizeImage;															// 이미지 변경
	Ellipse2D.Float expcircle;
	int patch = 3;
	public static boolean expand = false;
	public static boolean line = false;
	public static boolean brush = false;
	public static boolean rect = false;
	public static boolean circle = false;
	public static boolean _last = false;
	
	String nickname;
	RoomInfo roominfo;
	PhotoServer ps;
	Color color = new Color(255, 255, 255);
}
