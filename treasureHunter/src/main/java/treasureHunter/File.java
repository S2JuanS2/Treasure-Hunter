package treasureHunter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public abstract class File {
	
	public static final String FILE_PLAYER = "datosPlayer.txt";
	public static final String FILE_HOOK = "datosHook.txt";
	public static final String FILE_TREASURE = "datosTreasure.txt";

	/*
	 * DEVUELVE EL ESTADO DE UN JUGADOR LEIDO DESDE UN ARCHIVO DE TEXTO
	 */
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
	
	/*
	 * RECIBE EL ESTADO DE UN JUGADOR Y LO GUARDA EN UN ARCHIVO DE TEXTO
	 */
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
	
	/*
	 * DEVUELVE EL ESTADO DEL GANCHO LEIDO DESDE UN ARCHIVO DE TEXTO
	 */
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
	
	/*
	 * RECIBE EL ESTADO DE UN GANCHO Y LO GUARDA EN UN ARCHIVO DE TEXTO
	 */
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
	
	/*
	 * DEVUELVE EL ESTADO DE UNA LISTA DE TESOROS LEIDO DESDE UN ARCHIVO DE TEXTO
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Treasure> treasureListUpload() {
		
		ArrayList<Treasure> treasureState = null;
		FileInputStream fichero = null;
		
		try {
			fichero = new FileInputStream(FILE_TREASURE);
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
			treasureState = (ArrayList<Treasure>)objectInput.readObject();
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
		return treasureState;
	}
	
	/*
	 * RECIBE EL ESTADO DE UNA LISTA DE TESOROS Y LO GUARDA EN UN ARCHIVO DE TEXTO
	 */
	public static void TreasureListSave(ArrayList<Treasure> treasureState) {
				
		FileOutputStream fichero = null;
		try {
			fichero = new FileOutputStream(FILE_TREASURE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ObjectOutputStream red = null;
		try {
			red = new ObjectOutputStream(fichero);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			red.writeObject(treasureState);
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
