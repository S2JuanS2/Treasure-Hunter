package treasureHunter;

import java.io.Serializable;

public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private double balance;
	
	public Player(String name) {
		this.setName(name);
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

	public void setBalance(float balance) {
		this.balance += balance;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", balance=" + balance + "]";
	}
	
	public boolean canBuyUpgradeHook() {
		return(getBalance() >= Hook.COST_CHAIN);
	}

	public boolean canBuyFuel() {
		return(getBalance() >= Hook.FUEL_COST);
	}
}
