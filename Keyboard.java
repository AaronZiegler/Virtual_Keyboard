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
    private final Instrument[] instruments;
	private int instrumentIndex = 0;
	private HashMap<Integer, Integer> mapNotes = new HashMap<Integer, Integer>(); //hashMap to hold the values of thekey's pressed
		
		
	public Keyboard(){//creates a synth that will play the sounds
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
            System.exit(1);
        }   
		
		//hashMap for each key on the keyboard and corresponding sound
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
		
		//sets the channel for synth to play from
		this.midiChannels = synthesizer.getChannels();
        Soundbank bank = synthesizer.getDefaultSoundbank();

		//getting and setting the instrument to play
        synthesizer.loadAllInstruments(bank);


	    this.instruments = synthesizer.getAvailableInstruments();
        synthesizer.loadAllInstruments(synthesizer.getDefaultSoundbank());
        synthesizer.getChannels()[0].programChange(instrumentIndex);

	    System.out.println("[STATE] MIDI channels: " + midiChannels.length);
	    System.out.println("[STATE] Instruments: " + instruments.length);
		
	}
	
	//initialization of the Keyboard class which will create the input window for the Key presses
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

		   //checks to see if key press is to play a note of to switch an instrument
		   if(mapNotes.containsKey(keyCode)){
			   //sets note value
		       noteNumber = mapNotes.get(keyCode);
		   }else{
			   //cycles through instruments
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
			   //sets instrument value and prints current instument
		       synthesizer.getChannels()[0].programChange(instrumentIndex);
		       System.out.println("Switched to " + instruments[instrumentIndex].getName());
		   }

		   if (noteNumber != -1) {
			   //plays current note
			   midiChannels[0].noteOn(noteNumber, 600);
		   }
       }

       @Override
       public void keyReleased(KeyEvent e) {
		   int keyCode = e.getExtendedKeyCode();
		   int noteNumber = -1;
		   
		   //stops the note from being played
		   if(mapNotes.containsKey(keyCode)){
		       noteNumber = mapNotes.get(keyCode);
			   
		   }
		   
		   if (noteNumber != -1){
		   	   midiChannels[0].noteOff(noteNumber, 600);
		   }
       }
	   

	
	public static void main(String[] args){
		//creates keyboard object and jFrame input area
		Keyboard keyboard = new Keyboard();
		keyboard.init();
	}
}
