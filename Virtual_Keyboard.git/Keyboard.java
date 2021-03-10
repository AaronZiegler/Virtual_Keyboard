/* Aaron Ziegler
* Keyboard
* A local keyboard program to play
* Key guide: A-K, C-B
* Have effects
* maybe some recording
* Have some UI
*/

import doodlepad.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Keyboard{
	
	
	
	public static void main(String[] args){
		MKeyListener keyPress = new MKeyListener();
	}
}

class MKeyListener extends KeyAdapter {
	@Override
    public void keyPressed(KeyEvent event) {
		char ch = event.getKeyChar();
		
	  	if (ch == 'a' ||ch == 'b'||ch == 'c' ) {
		  	System.out.println(event.getKeyChar());
	  	}
		
	  	if (event.getKeyCode() == KeyEvent.VK_HOME) {
			System.out.println("Key codes: " + event.getKeyCode());
		}
	}
}