package tpAlgo3;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class Sound {

	public static final String HOOK_SOUND = "hook.wav";
	public static final String TREASURE_COLLISION_SOUND = "treasure.wav";
	public static final String BOUGTH_SOUND = "buy.wav";
	public static final String ERROR_MONEY_SOUND = "errorMoney.wav";
	public static final String MOVE_HOOK_SOUND = "moveHook.wav";
	public static final String NO_FUEL_SOUND = "noFuel.wav";
	public static final String DEFEAT_SOUND = "defeat.wav";
	public static final String PLAY_SOUND = "play.wav";
	
	static void playSound(String sound) {
		
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        
		}catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			Interactions.screen.print("Error al reproducir el sonido.");
		}
	}
}
