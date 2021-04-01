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


public class Keyboard{
	
	
	public static void main(String[] args){
		
	}
	public void keyPressed(KeyEvent e) {
	    System.out.println("Press");
	    switch (e.getKeyCode()){
	        case KeyEvent.VK_A :
	            System.out.println("A");
	            break;
	    }
	}
}
