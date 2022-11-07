package treasureHunter;

import java.io.Serializable;

public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	private String name;
	private double balance;
	//private Item item;
	
	public Player(String name) {
		this.name = name;
		this.balance = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void deductBalance(float balance) {
		this.balance -= balance;
	}
	
	public void accreditBalance(float balance) {
		this.balance += balance;
	}
	
	public boolean canBuyUpgrade(float price) {
		if(getBalance() >= price) {
			return true;
		}else {
			System.out.println("Dinero insuficiente\n");
			return false;
		}
	}
		
	@Override
	public String toString() {
		return "Player [name=" + name + ", balance=" + balance + "]";
	}
	
}
