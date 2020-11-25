package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

class ColorAction implements ActionListener {
	Color color;
	ColorAction() {
		color = null;
	}
	
    JColorChooser chooser=new JColorChooser();
    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmd=ae.getActionCommand();
        if(cmd.equals("Color")){
            Color selectedColor = chooser.showDialog(null,"Color",Color.YELLOW);
            if(selectedColor!=null)
            	color = selectedColor;
        }
    }
}