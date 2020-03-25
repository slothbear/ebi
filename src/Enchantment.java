import java.awt.image.BufferedImage;

public class Enchantment {
	String name = "";
	int namePixels = 0;
	int level = 0;
	String chestID = "";
	int row = 0;
	int column = 0;
	BufferedImage image;

	public Enchantment(int namePixels, int level,
		String chestID, int row, int column, BufferedImage image) {

		this.namePixels = namePixels;
		this.level = level;
		this.chestID = chestID;
		this.row = row;
		this.column = column;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Enchantment [name=" + name + ", namePixels=" + namePixels
			+ ", level=" + level + ", chestID=" + chestID + ", row=" + row
			+ ", column=" + column + "]";
	}



}
