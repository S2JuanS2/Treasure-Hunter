package tpAlgo3;

import java.io.Serializable;

public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private double balance;
	private int strengh;
	private int resistance;
	
	public Player(String name) {
		super();
		this.setName(name);
		this.balance = 0;
		this.strengh = 0;
		this.resistance = 0;
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

	public int getStrengh() {
		return strengh;
	}

	public void setStrengh(int strengh) {
		this.strengh = strengh;
	}
	
	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", balance=" + balance + ", strengh=" + strengh + ", resistance=" + resistance
				+ "]";
	}
	
	
}
