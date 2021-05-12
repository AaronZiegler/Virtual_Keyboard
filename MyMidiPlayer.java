/* Aaron Ziegler
*  Midi player class created to play sounds from a midi input
*  still work in progress, linking issues with getting the keyboard to work play sounds
*  currently able to connect to a midi Device, working on getting it to play, abt. 60% there
*
*
*/

import javax.sound.midi.*;
import java.util.*;


public class MyMidiPlayer {
	public static void main(String[] args){
		
		//init variables for a Midi system
		Vector synthInfos;// info on synth, or digital sound/instrument
		MidiDevice device;// init of a midi device, native to computer or ported
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo(); // array info on midi device
		Sequencer sequencer; // sequencer which takes in midi data
		Transmitter trans; // transmiter which transfers midid data to a synth
		Synthesizer synth; // synth which plays midi data
		Receiver receiver; // receives midi data for a synth to play
		Scanner scan; // scanner for user input, such as finished recording or activating effects(not implemented)
		
		
		//finds and opens all midi devices availible to be recieved
		//*need to edit to be a midi device with a transmitter and receiver, or some way to select a specific midi device, currently, it opens all availble devices*
		for(int i = 0; i < infos.length; i++){
			System.out.println("INFOS " + infos[i]);
			try{
				device = MidiSystem.getMidiDevice(infos[i]);
				if (!device.isOpen()){
					try {
						//opens an unopened device for playing and prints info on device to make ure it is the correct device
						device.open();
						
					    System.out.println("\nDevice: ");
					    System.out.println("Name: " + device.getDeviceInfo().getName());
		                System.out.println("Vendor: " + device.getDeviceInfo().getVendor());
   	                    System.out.println("Version: " + device.getDeviceInfo().getVersion());
		                System.out.println("Description: " + device.getDeviceInfo().getDescription());
	                    System.out.println("Transmitters: " + device.getMaxTransmitters());
	                    System.out.println("Receivers: " + device.getMaxReceivers());
						
						
					}catch(MidiUnavailableException e){
						System.out.println("No Device Found to Open");
					}
				}
				try{
//read this for when broken, like rn: https://docs.oracle.com/javase/tutorial/sound/MIDI-seq-methods.html

// following commented lines can work to play a note, just as a test
				     /*ShortMessage myMsg = new ShortMessage();
		 		     Start playing the note Middle C (60),
		 		     moderately loud (velocity = 93).
				     myMsg.setMessage(ShortMessage.NOTE_ON, 0, 61, 93);
				     long timeStamp = 1;
				     MidiEvent rcvr = new MidiEvent(myMsg, timeStamp);*/
					 
					 //will create and open a sequencer to prepare to recording
					 sequencer = MidiSystem.getSequencer();
					 trans = device.getTransmitter(); //sets the transmitter device, or the midi device we are recording from
		 			 //synth = MidiSystem.getSynthesizer();
					 receiver = sequencer.getReceiver();
					 trans.setReceiver(receiver);
		
		
					 sequencer.open();
		 			 //synth.open();
			 
			 		 //set parameters for the sequence that is being recorded to
					 Sequence sequence = new Sequence(Sequence.PPQ, 20);
			 		 //create a track to be recorded to
					 Track track = sequence.createTrack();
			 		 //Here there should be a file created that will store the data, but i have not created it yet, also need to look into how to create a midi file, shouldnt be too hard
					 
					 //sets which sequence and track to record to, then starts the recording
					 sequencer.setSequence(sequence);
					 sequencer.setTempoInBPM(100);
					 sequencer.recordEnable(track, -1);
					 sequencer.startRecording();
					 
					 //checks if user is done recording, stops recording and will wrtie the recording to a file(not implemented)
					 scan = new Scanner(System.in);
					 System.out.println("Done?");
					 String word = scan.next();
					 if(word.equals("y")){
						 sequencer.stopRecording();
						 System.out.println(MidiSystem.getMidiFileTypes(sequence));
						 System.out.println(MidiSystem.isFileTypeSupported(0, sequence));
						 sequencer.start();
						 //Here would be MidiEvent.write(File...), but need to work out how the file is going to work
					 }
					 
					 
					 
			 
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}catch(MidiUnavailableException e){
				System.out.println("No Device Found");
			}
		}
		
		
		//takes in the midi data that will play the notes in sequence
		
		// System.out.println("Enter the number of notes to be played: ");
// 		Scanner in = new Scanner(System.in);
// 		int numOfNotes = in.nextInt();
//
// 		MyMidiPlayer player = new MyMidiPlayer();
// 		player.setUpPlayer(numOfNotes);
	}
	//these methods were copied from an online source to use as reference
	public void setUpPlayer(int numOfNotes)
	{

		try {

			// A static method of MidiSystem that returns
			// a sequencer instance.
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();

			// Creating a sequence.
			Sequence sequence = new Sequence(Sequence.PPQ, 4);

			// PPQ(Pulse per ticks) is used to specify timing
			// type and 4 is the timing resolution.

			// Creating a track on our sequence upon which
			// MIDI events would be placed
			Track track = sequence.createTrack();

				// Adding some events to the track
				for (int i = 5; i < (4 * numOfNotes) + 5; i += 4)
			{

				// Add Note On event
				track.add(makeEvent(144, 1, i, 100, i));

				// Add Note Off event
				track.add(makeEvent(128, 1, i, 100, i + 2));
			}

			// Setting our sequence so that the sequencer can
			// run it on synthesizer
			sequencer.setSequence(sequence);

			// Specifies the beat rate in beats per minute.
			sequencer.setTempoInBPM(220);

			// Sequencer starts to play notes
			sequencer.start();

			while (true) {

				// Exit the program when sequencer has stopped playing.
				if (!sequencer.isRunning()) {
					sequencer.close();
					System.exit(1);
				}
			}
		}
		catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	public MidiEvent makeEvent(int command, int channel,
							int note, int velocity, int tick)
	{

		MidiEvent event = null;

		try {

			// ShortMessage stores a note as command type, channel,
			// instrument it has to be played on and its speed.
			ShortMessage a = new ShortMessage();
			a.setMessage(command, channel, note, velocity);

			// A midi event is comprised of a short message(representing
			// a note) and the tick at which that note has to be played
			event = new MidiEvent(a, tick);
		}
		catch (Exception ex) {

			ex.printStackTrace();
		}
		return event;
	}
}

