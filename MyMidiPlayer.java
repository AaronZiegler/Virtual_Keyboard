// Java program showing the implementation of a simple record
import javax.sound.midi.*;
import java.util.*;


public class MyMidiPlayer {
	
	

	public static void main(String[] args){
		
		Vector synthInfos;
		MidiDevice device;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		Sequencer sequencer;
		Transmitter trans;
		Synthesizer synth;
		Receiver receiver;
		Scanner scan;
		
		
		//finds and opens all midi devices availible to be recieved
		for(int i = 0; i < infos.length; i++){
			System.out.println("INFOS " + infos[i]);
			try{
				device = MidiSystem.getMidiDevice(infos[i]);
				if (!device.isOpen()){
					try {
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
//https://docs.oracle.com/javase/tutorial/sound/MIDI-seq-methods.html
				    // ShortMessage myMsg = new ShortMessage();
		// 		     // Start playing the note Middle C (60),
		// 		     // moderately loud (velocity = 93).
				     // myMsg.setMessage(ShortMessage.NOTE_ON, 0, 61, 93);
				     // long timeStamp = 1;
				     // MidiEvent rcvr = new MidiEvent(myMsg, timeStamp);
		//
					 sequencer = MidiSystem.getSequencer();
					 trans = device.getTransmitter();
		// 			 synth = MidiSystem.getSynthesizer();
					 receiver = sequencer.getReceiver();
					 trans.setReceiver(receiver);
		//
		//
					 sequencer.open();
		// 			 synth.open();
			 
					 Sequence sequence = new Sequence(Sequence.PPQ, 20);
			 
					 Track track = sequence.createTrack();
			 
					 sequencer.setSequence(sequence);
					 sequencer.setTempoInBPM(100);
					 sequencer.recordEnable(track, -1);
					 sequencer.startRecording();
					 
					 scan = new Scanner(System.in);
					 System.out.println("Done?");
					 String word = scan.next();
					 if(word.equals("y")){
						 sequencer.stopRecording();
						 System.out.println(MidiSystem.getMidiFileTypes(sequence));
						 System.out.println(MidiSystem.isFileTypeSupported(0, sequence));
						 sequencer.start();
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

