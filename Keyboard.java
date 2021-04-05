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
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Keyboard extends JFrame implements KeyListener{
	
	JLabel label;
	
	public Keyboard(String s){
		super(s);
		JPanel p = new JPanel();
        label = new JLabel("Keyboard");
		p.add(label);
        add(p);
        addKeyListener(this);
        setSize(10, 10);
        setVisible(true);
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	 	//Not needed
	}
	
    @Override
       public void keyPressed(KeyEvent e) {
		   switch(e.getKeyCode()){
			   case KeyEvent.VK_A :
			       System.out.println("C");
				   break;
			   case KeyEvent.VK_W :
				   System.out.println("C#/Db");
				   break;
			   case KeyEvent.VK_S :
				   System.out.println("D");
				   break;
			   case KeyEvent.VK_E :
				   System.out.println("D#/Eb");
				   break;
			   case KeyEvent.VK_D :
				   System.out.println("E");
				   break;
			   case KeyEvent.VK_F :
				   System.out.println("F");
				   break;
			   case KeyEvent.VK_T :
				   System.out.println("F#/Gb");
				   break;
			   case KeyEvent.VK_G :
				   System.out.println("G");
				   break;
			   case KeyEvent.VK_Y :
			       System.out.println("G#/Ab");
				   break;
			   case KeyEvent.VK_H :
				   System.out.println("A");
				   break;
			   case KeyEvent.VK_U :
				   System.out.println("A#/Bb");
				   break;
			   case KeyEvent.VK_J :
				   System.out.println("B");
				   break;
			   case KeyEvent.VK_K :
				   System.out.println("C_high");
				   break;
			   case KeyEvent.VK_O :
				   System.out.println("C#/Db_high");
				   break;
			   case KeyEvent.VK_L :
				   System.out.println("D_high");
				   break;
			   case KeyEvent.VK_P :
				   System.out.println("D#/Eb_high");
				   break;
			   }

       }

       @Override
       public void keyReleased(KeyEvent e) {
		   
		   switch(e.getKeyCode()){
			   case KeyEvent.VK_A :
			       System.out.println("C Release");
				   break;
			   case KeyEvent.VK_W :
				   System.out.println("C#/Db Release");
				   break;
			   case KeyEvent.VK_S :
				   System.out.println("D Release");
				   break;
			   case KeyEvent.VK_E :
				   System.out.println("D#/Eb Release");
				   break;
			   case KeyEvent.VK_D :
				   System.out.println("E Release");
				   break;
			   case KeyEvent.VK_F :
				   System.out.println("F Release");
				   break;
			   case KeyEvent.VK_T :
				   System.out.println("F#/Gb Release");
				   break;
			   case KeyEvent.VK_G :
				   System.out.println("G Release");
				   break;
			   case KeyEvent.VK_Y :
			       System.out.println("G#/Ab Release");
				   break;
			   case KeyEvent.VK_H :
				   System.out.println("A Release");
				   break;
			   case KeyEvent.VK_U :
				   System.out.println("A#/Bb Release");
				   break;
			   case KeyEvent.VK_J :
				   System.out.println("B Release");
				   break;
			   case KeyEvent.VK_K :
				   System.out.println("C_high Release");
				   break;
			   case KeyEvent.VK_O :
				   System.out.println("C#/Db_high Release");
				   break;
			   case KeyEvent.VK_L :
				   System.out.println("D_high Release");
				   break;
			   case KeyEvent.VK_P :
				   System.out.println("D#/Eb_high Release");
				   break;
		   }
		   
      
       }
	
	public static void main(String[] args){
		new Keyboard("Key Listener Test");
		
	}
}
