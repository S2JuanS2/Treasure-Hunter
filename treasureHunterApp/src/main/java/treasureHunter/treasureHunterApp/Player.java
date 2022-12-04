package treasureHunter.treasureHunterApp;

import java.io.Serializable;

public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	private String name;
	private int balance;
	private Treasure treasure;
	
	public Player(String name) {
		this.name = name;
		this.balance = 0;
		this.treasure = null;
	}

	/*
	 * DEVUELVE EL NOMBRE DEL JUGADOR
	 */
	public String getName() {
		return name;
	}

	/*
	 * MODIFICA EL NOMBRE DEL JUGADOR
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * DEVUELVE EL BALANCE DEL JUGADOR
	 */
	public int getBalance() {
		return balance;
	}

	public Treasure getTreasure() {
		return treasure;
	}

	public void setTreasure(Treasure treasure) {
		this.treasure = treasure;
	}
	
	/*
	 * REDUCE EL BALANCE DEL JUGADOR CON LA CANTIDAD RECIBIDA
	 * POR PARAMETRO
	 */
	public void deductBalance(int price) {
		this.balance -= price;
	}
	
	/*
	 * AUMENTA EL BALANCE DEL JUGADOR CON LA CANTIDAD RECIBIDA
	 * POR PARAMETRO
	 */
	public void accreditBalance(int price) {
		this.balance += price;
	}
	
}
