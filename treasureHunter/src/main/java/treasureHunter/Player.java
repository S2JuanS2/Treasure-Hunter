package treasureHunter;

import java.io.Serializable;

public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	static final float FUEL_COST = 80;
	static final float COST_UPGRADE = 100;
	
	public static final String NO_MONEY = "Dinero insuficiente\n";
	
	private String name;
	private double balance;
	
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
	
	public boolean canBuyUpgradeHook() {
		if(getBalance() >= COST_UPGRADE) {
			return true;
		}else {
			System.out.println(NO_MONEY);
			return false;
		}
	}
	
	public boolean canBuyFuel() {
		if(getBalance() >= FUEL_COST) {
			return true;
		}else {
			System.out.println(NO_MONEY);
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", balance=" + balance + "]";
	}
	
}
