/* Aaron Ziegler
* Keyboard
* A local keyboard program to play
* Key guide: A-K, C-B
* Have effects
* maybe some recording
* Have some UI
*/

import java.util.Scanner;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;



public class KeyListenerExample extends Frame implements KeyListener{
	
	Label l;
	TextArea area;
		
	public KeyListenerExample(){
		
		l = new Label();
		l.setBounds(20,50,100,20);
		area = new TextArea();
		area.setBounds(20,80,300,300);
		area.addKeyListener(this);
		
		add(l);add(area);
		setSize(400,400);
		setLayout(null);
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent e){
		l.setText("Key Pressed");
	}
	public void keyReseased(KeyEvent e){
		l.setText("Key Reseased");
	}
	public void keyTyped(KeyEvent e){
		l.setText("KeyTyped");
	}
	
	public static void main(String[] args){
		new KeyListener();
	}
	
}
