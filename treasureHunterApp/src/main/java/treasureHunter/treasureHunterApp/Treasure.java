package treasureHunter.treasureHunterApp;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;

public class Treasure implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final TreasureType type;
	private final Coordinate position;
	private final int rarity;
	private final int weight;
	private final int price;
	private final String nameImage;
	
	public Treasure(TreasureType type, Coordinate position, int weight, int rarity, int price, String nameImage) {
		super();
		this.type = type;
		this.position = position;
		this.rarity = rarity;
		this.weight = weight;
		this.price = price;
		this.nameImage = nameImage;
	}

	/*
	 * DEVUELVE EL TIPO DE TESORO
	 */
	public TreasureType getType() {
		return type;
	}

	/*
	 * DEVUELVE LA POSICION DEL TESORO
	 */
	public Coordinate getPosition() {
		return position;
	}

	/*
	 * DEVUELVE LA RAREZA DEL TESORO
	 */
	public int getRarity() {
		return rarity;
	}
	
	/*
	 * DEVUELVE EL PESO DEL TESORO
	 */
	public int getWeight() {
		return weight;
	}

	/*
	 * DEVUELVE EL PRECIO DEL TESORO
	 */
	public int getPrice() {
		return price;
	}

	public String getNameImage() {
		return nameImage;
	}
	
	public void draw(GraphicsContext graphics) {
		graphics.drawImage(View.images.get(nameImage), getPosition().getX(), getPosition().getY());
	}
	
	/*
	 * MODIFICA LA POSICION DEL TESORO CON LOS
	 * VALORES RECIBIDOS POR PARAMETRO
	 */
	public void setPosition(int i, int j) {
		position.setX(i);
		position.setY(j);
	}
	
	@Override
	public String toString() {
		return "Treasure [type=" + type + ", position=" + position + ", rarity=" + rarity + ", weight=" + weight
				+ ", price=" + price + "]";
	}
}
