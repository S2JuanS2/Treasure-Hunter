package treasureHunter;

import java.io.Serializable;

public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	private String name;
	private int balance;
	//private Item item;
	
	public Player(String name) {
		this.name = name;
		this.balance = 0;
	}

	/*
	 * DEVUELVE EL NOMBRE DEL JUGADOR
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * DEVUELVE EL BALANCE DEL JUGADOR
	 */
	public int getBalance() {
		return balance;
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
			
	@Override
	public String toString() {
		return "Player [name=" + name + ", balance=" + balance + "]";
	}
	
}
