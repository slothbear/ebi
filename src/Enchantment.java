import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;

public class Enchantment {
	String name = "";
	int namePixels = 0;
	int level = 0;
	String chestID = "";
	int row = 0;
	int column = 0;
	BufferedImage image;
	String id = "";

	private static AtomicInteger unique = new AtomicInteger();

	public Enchantment(int namePixels, int level,
		String chestID, int row, int column, BufferedImage image)
		throws IOException {

		this.namePixels = namePixels;
		this.level = level;
		this.chestID = chestID;
		this.row = row;
		this.column = column;
		this.image = image;
		this.id = String.valueOf(unique.getAndIncrement());

		ImageIO.write(image, "png",
			new File(
				"captures/" + chestID + "-" + row + "x" + column + "-" + id
					+ ".png"));
	}


	@Override
	public String toString() {
		return "Enchantment [" + name + " "
			+ level + "  location=" + chestID + "/" + row + "x"
			+ column + "]";
	}

	public String getTableRow() {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>\n");
		sb.append("  <td>" + level + "</td>\n");
		sb.append("  <td>" + name + "</td>\n");
		sb.append(
			"  <td><img src='" + "captures/Sid-" + row + "x" + column + "-" + id
				+ ".png'</img>\n");
//		sb.append("  <td>" + namePixels + "</td>\n");
		sb.append("  <td>" + row + "</td>\n");
		sb.append("  <td>" + column + "</td>\n");
		sb.append("</tr>\n");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + level;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + namePixels;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Enchantment other = (Enchantment) obj;
		if (level != other.level) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (namePixels != other.namePixels) {
			return false;
		}
		return true;
	}

}
