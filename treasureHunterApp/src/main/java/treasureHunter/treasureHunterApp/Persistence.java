package treasureHunter.treasureHunterApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public abstract class Persistence {
	
	public static final String FILE_PLAYER = "datosPlayer.txt";
	public static final String FILE_HOOK = "datosHook.txt";
	public static final String FILE_TREASURE = "datosTreasure.txt";

	/*
	 * DEVUELVE EL ESTADO DE UN JUGADOR LEIDO DESDE UN ARCHIVO DE TEXTO
	 */
	public static Player playerUpload() throws IOException, ClassNotFoundException {
				
		FileInputStream fichero = new FileInputStream(FILE_PLAYER);
		ObjectInputStream objectInput = new ObjectInputStream(fichero);
		Player playerState = (Player)objectInput.readObject();
		objectInput.close();
		
		return playerState;
	}
	
	/*
	 * RECIBE EL ESTADO DE UN JUGADOR Y LO GUARDA EN UN ARCHIVO DE TEXTO
	 */
	public static void playerSave(Player playerStateToSave) throws IOException {
				
		FileOutputStream fichero = new FileOutputStream(FILE_PLAYER);
		ObjectOutputStream ous = new ObjectOutputStream(fichero);
		ous.writeObject(playerStateToSave);
		fichero.close();
	}
	
	/*
	 * DEVUELVE EL ESTADO DEL GANCHO LEIDO DESDE UN ARCHIVO DE TEXTO
	 */
	public static Hook hookUpload() throws IOException, ClassNotFoundException {
		
		FileInputStream fichero = new FileInputStream(FILE_HOOK);		
		ObjectInputStream objectInput = new ObjectInputStream(fichero);	
		Hook hookState = (Hook)objectInput.readObject();	
		objectInput.close();
		
		return hookState;
	}
	
	/*
	 * RECIBE EL ESTADO DE UN GANCHO Y LO GUARDA EN UN ARCHIVO DE TEXTO
	 */
	public static void hookSave(Hook hookStateToSave) throws IOException {
				
		FileOutputStream fichero = new FileOutputStream(FILE_HOOK);
		ObjectOutputStream ous = new ObjectOutputStream(fichero);
		ous.writeObject(hookStateToSave);
		fichero.close();
	}
	
	/*
	 * DEVUELVE EL ESTADO DE UNA LISTA DE TESOROS LEIDO DESDE UN ARCHIVO DE TEXTO
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Treasure> treasureListUpload() throws IOException, ClassNotFoundException {
				
		FileInputStream fichero = new FileInputStream(FILE_TREASURE);	
		ObjectInputStream objectInput = new ObjectInputStream(fichero);	
		ArrayList<Treasure> treasureState = (ArrayList<Treasure>)objectInput.readObject();	
		objectInput.close();
			
		return treasureState;
	}
	
	/*
	 * RECIBE EL ESTADO DE UNA LISTA DE TESOROS Y LO GUARDA EN UN ARCHIVO DE TEXTO
	 */
	public static void TreasureListSave(ArrayList<Treasure> treasureState) throws IOException {
				
		FileOutputStream fichero = new FileOutputStream(FILE_TREASURE);
		ObjectOutputStream ous = new ObjectOutputStream(fichero);
		ous.writeObject(treasureState);
		fichero.close();
	}
}
