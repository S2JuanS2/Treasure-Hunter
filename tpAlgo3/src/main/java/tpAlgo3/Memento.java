package tpAlgo3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Memento {

	private Player playerState;
	private Hook hookState;
	
	public FileInputStream input;
	public FileOutputStream output;
	public File fileInput, fileOutput;
	
	
	public Memento(Player playerStateToSave, Hook hookStateToSave) {

		this.playerState = playerStateToSave;
		this.hookState = hookStateToSave;
	}


	public Player getPlayerState() {
		return playerState;
	}


	public Hook getHookState() {
		return hookState;
	}


	public Player playerUpload() {
		
		FileInputStream fichero = null;
		
		try {
			fichero = new FileInputStream("datosPlayer.txt");
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
			this.playerState = (Player)objectInput.readObject();
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
	
	
	public void playerSave(Player playerStateToSave) {
		
		
		this.playerState = playerStateToSave;
		
		FileOutputStream fichero = null;
		try {
			fichero = new FileOutputStream("datosPlayer.txt");
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
			tuberia.writeObject(playerState);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Hook HookUpload() {
		
		FileInputStream fichero = null;
		
		try {
			fichero = new FileInputStream("datosHook.txt");
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
			this.hookState = (Hook)objectInput.readObject();
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
	
	
	public void hookSave(Hook hookStateToSave) {
		
		
		this.hookState = hookStateToSave;
		
		FileOutputStream fichero = null;
		try {
			fichero = new FileOutputStream("datosHook.txt");
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
			tuberia.writeObject(hookState);
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
