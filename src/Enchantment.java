public class Enchantment {
	String name;
	int namePixels;
	int level;
	String chestID;
	int row;
	int column;


	public Enchantment(int namePixels, int level,
		String chestID, int row, int column) {

		this.namePixels = namePixels;
		this.level = level;
		this.chestID = chestID;
		this.row = row;
		this.column = column;
	}

	@Override
	public String toString() {
		return "Enchantment [name=" + name + ", namePixels=" + namePixels
			+ ", level=" + level + ", chestID=" + chestID + ", row=" + row
			+ ", column=" + column + "]";
	}



}
