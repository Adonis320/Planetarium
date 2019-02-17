import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.*;
import javax.sound.sampled.*;
 
/**
 * Ce code permet de lire un fichier au format wav.
 * Il implémente LineListener pour écouter les évènements
 */

public class AudioPlayer implements LineListener {
    
	//Les attributs 
    private boolean playCompleted;
    private boolean loop; 
    private final int START;
    private Clip audioClip; 
    
    /**
     * Le constructeur
     */
    public AudioPlayer(){
		playCompleted = false;
		loop = false;
		START = 0;
		audioClip=null;
	}
    
    /**
     * Pour charger la musique
     * @params le nom de la musique à lire
     */
    public void load(String songName){ 
    	//~ String audioFilePath = "./audiofiles/" + songName + ".wav";
    	String audioFilePath = songName + ".wav";
    	File audioFile = new File(audioFilePath);
    	
    	
		
			try {
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				
				audioClip = (Clip) AudioSystem.getLine(info);
				audioClip.addLineListener(this);
				audioClip.open(audioStream);
			}
				catch (UnsupportedAudioFileException ex) {
					ex.printStackTrace();
				}
				catch (LineUnavailableException ex) {
					ex.printStackTrace();
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
	
	 /**
	 * Pour jouer la musique
	 */
    public void play(){ // Function plays the sound file
		while(audioClip.getMicrosecondPosition()<=audioClip.getMicrosecondLength()){
			if(audioClip.isOpen()  && !(audioClip.isActive())){
				if(audioClip.getMicrosecondPosition() == audioClip.getMicrosecondLength()){
					audioClip.setFramePosition(START);	
				}
				audioClip.start();
			}
		}
   }
     /**
	 * Pour arrêter la musique
	 */
   public void stop(){ // This closes the file and triggers playCompleted boolean
	   audioClip.close();
	   playCompleted = true;
   }
   
    /**
	 * Pour arrêter la musique
	 */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP) {
        	if (audioClip.getMicrosecondLength() == audioClip.getMicrosecondPosition()){
        		if(loop) {
        			play();
        		}
        	}  
        }
    }

}
