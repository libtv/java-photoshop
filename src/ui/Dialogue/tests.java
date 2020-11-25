package ui.Dialogue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class MenuAndColorChooserEx extends JFrame{
    JLabel label = new JLabel("Hello");
    MenuAndColorChooserEx(){
        this.setTitle("JColorChooser 예제");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Ravie",Font.ITALIC,30));
        this.add(label, BorderLayout.CENTER);
        createMenu();
        this.setSize(250, 200);
        this.setVisible(true);
    }
    
    void createMenu(){
        JMenuBar mb=new JMenuBar();
        JMenuItem colorMenuItem = new JMenuItem("Color");
        JMenu fileMenu = new JMenu("Text");
        
        colorMenuItem.addActionListener(new MenuActionListener());
        
        fileMenu.add(colorMenuItem);
        mb.add(fileMenu);
        this.setJMenuBar(mb);
    }
    
    class MenuActionListener implements ActionListener {
        JColorChooser chooser=new JColorChooser();
        @Override
        public void actionPerformed(ActionEvent ae) {
            String cmd=ae.getActionCommand();
            if(cmd.equals("Color")){
                Color selectedColor=chooser.showDialog(null,"Color",Color.YELLOW);
                if(selectedColor!=null)
                    label.setForeground(selectedColor);
            }
        }
    }
    
}
public class tests {
    public static void main(String[] args) {
        new MenuAndColorChooserEx();
    }
}