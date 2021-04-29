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
import java.awt.Canvas;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.sound.midi.*;
import java.util.HashMap;

public class Keyboard extends JFrame implements KeyListener{
	
	JLabel label; //create area to receive key inputs
	private Synthesizer synthesizer; //create synthsiver object to play sounds
	private final MidiChannel[] midiChannels; //create midiChannels to find sounds to play
    private final Instrument[] instruments; //
	private int instrumentIndex = 0;
	private HashMap<Integer, Integer> mapNotes = new HashMap<Integer, Integer>();
		
	public Keyboard(){
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
            System.exit(1);
        }   
		
	    mapNotes.put(KeyEvent.VK_A, 60);
	    mapNotes.put(KeyEvent.VK_W, 61);
        mapNotes.put(KeyEvent.VK_S, 62);
        mapNotes.put(KeyEvent.VK_E, 63);
        mapNotes.put(KeyEvent.VK_D, 64);
        mapNotes.put(KeyEvent.VK_F, 65);
        mapNotes.put(KeyEvent.VK_T, 66);
        mapNotes.put(KeyEvent.VK_G, 67);
	    mapNotes.put(KeyEvent.VK_Y, 68);
	    mapNotes.put(KeyEvent.VK_H, 69);
        mapNotes.put(KeyEvent.VK_U, 70);
        mapNotes.put(KeyEvent.VK_J, 71);
        mapNotes.put(KeyEvent.VK_K, 72);
        mapNotes.put(KeyEvent.VK_O, 73);
        mapNotes.put(KeyEvent.VK_L, 74);
        mapNotes.put(KeyEvent.VK_P, 75);
		mapNotes.put(KeyEvent.VK_SEMICOLON, 76);
		
		
		this.midiChannels = synthesizer.getChannels();
        Soundbank bank = synthesizer.getDefaultSoundbank();

        synthesizer.loadAllInstruments(bank);


	    this.instruments = synthesizer.getAvailableInstruments();
        synthesizer.loadAllInstruments(synthesizer.getDefaultSoundbank());
        synthesizer.getChannels()[0].programChange(instrumentIndex);

	    System.out.println("[STATE] MIDI channels: " + midiChannels.length);
	    System.out.println("[STATE] Instruments: " + instruments.length);
		
	}
	
	private void init() {   
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
		   int keyCode = e.getExtendedKeyCode();
		   int noteNumber = -1;


		   if(mapNotes.containsKey(keyCode)){
		       noteNumber = mapNotes.get(keyCode);
		   }else{
		       switch (keyCode) {
		           case KeyEvent.VK_LEFT: {
		               if (instrumentIndex == 0){
		                  instrumentIndex = instruments.length - 1;
		               }else{
		                  instrumentIndex--;
		               }
		               break;
		           }
		           case KeyEvent.VK_RIGHT:{
		               if (instrumentIndex == instruments.length - 1){
		                   instrumentIndex = 0;
		               } else {
		                   instrumentIndex++;
		               }
		               break;
		           }
		       }
		       synthesizer.getChannels()[0].programChange(instrumentIndex);
		       System.out.println("Switched to " + instruments[instrumentIndex].getName());
		   }

		   if (noteNumber != -1) {
			   	  midiChannels[0].noteOn(noteNumber, 600);
		   }
       }

       @Override
       public void keyReleased(KeyEvent e) {
		   int keyCode = e.getExtendedKeyCode();
		   int noteNumber = -1;
		   
		   if(mapNotes.containsKey(keyCode)){
		       noteNumber = mapNotes.get(keyCode);
			   
		   }
		   
		   if (noteNumber != -1){
		   	   midiChannels[0].noteOff(noteNumber, 600);
		   }
       }
	   

	
	public static void main(String[] args){
		Keyboard keyboard = new Keyboard();
		keyboard.init();
	}
}
