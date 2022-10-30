package tpAlgo3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class SaveAndLoadGame {
	
	static final String FILE_PLAYER = "datosPlayer.txt";
	static final String FILE_HOOK = "datosHook.txt";

	public static Player playerUpload() {
		
		Player playerState = null;
		FileInputStream fichero = null;
		
		try {
			fichero = new FileInputStream(FILE_PLAYER);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ObjectInputStream objectInput = null;
		
		try {
			objectInput = new ObjectInputStream(fichero);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			playerState = (Player)objectInput.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			objectInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playerState;
	}
	
	
	public static void playerSave(Player playerStateToSave) {
				
		FileOutputStream fichero = null;
		try {
			fichero = new FileOutputStream(FILE_PLAYER);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ObjectOutputStream tuberia = null;
		try {
			tuberia = new ObjectOutputStream(fichero);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			tuberia.writeObject(playerStateToSave);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Hook hookUpload() {
		
		Hook hookState = null;
		FileInputStream fichero = null;
		
		try {
			fichero = new FileInputStream(FILE_HOOK);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ObjectInputStream objectInput = null;
		
		try {
			objectInput = new ObjectInputStream(fichero);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			hookState = (Hook)objectInput.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			objectInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hookState;
	}
	
	
	public static void hookSave(Hook hookStateToSave) {
				
		FileOutputStream fichero = null;
		try {
			fichero = new FileOutputStream(FILE_HOOK);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ObjectOutputStream tuberia = null;
		try {
			tuberia = new ObjectOutputStream(fichero);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			tuberia.writeObject(hookStateToSave);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
