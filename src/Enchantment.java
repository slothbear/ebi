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
	int nameWidth = 0;

	private static AtomicInteger unique = new AtomicInteger();

	public Enchantment(int namePixels, int level,
		String chestID, int row, int column, BufferedImage image, int nameWidth)
		throws IOException {

		this.nameWidth = nameWidth;
		this.namePixels = namePixels;
		this.level = level;
		this.chestID = chestID;
		this.row = row;
		this.column = column;
		this.image = image;
		this.id = String.valueOf(unique.getAndIncrement());

		ImageIO.write(image, "png", new File(chunkFilename()));
	}


	private String chunkFilename() {
		return "chunks/" +
			chestID + "-" +
			row + "x" + column + "-" +
			id + ".png";
	}


	@Override
	public String toString() {
		return "Enchantment [" + name + " "
			+ level + "  location=" + chestID + "-" + row + "x"
			+ column + "]";
	}

	public String toHTMLrow() {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>\n");
		sb.append("  <td>" + nameWidth + "</td>\n");
		sb.append("  <td>" + namePixels + "</td>\n");
		sb.append("  <td><img src='" + chunkFilename() + "'</img></td>\n");
		sb.append("  <td>" + level + "</td>\n");
		sb.append("  <td>" + name + "</td>\n");
		sb.append("  <td>" + chestID + "</td>\n");
		sb.append("  <td>" + (row + 1) + "</td>\n");
		sb.append("  <td>" + (column + 1) + "</td>\n");
		String crc = chestID + "-" + (row + 1) + "x" + (column + 1);
		sb.append("  <td>" + crc + "</td>\n");
		sb.append("</tr>\n\n");
		return sb.toString();
	}

	public String toCSVrow() {
		StringBuilder sb = new StringBuilder();
		String crc = chestID + "-" + (row + 1) + "x" + (column + 1);

		sb.append(nameWidth);
		sb.append(",");
		sb.append(namePixels);
		sb.append(",");
		sb.append(level);
		sb.append(",");
		sb.append(name);
		sb.append(",");
		sb.append(chestID);
		sb.append(",");
		sb.append(row + 1);
		sb.append(",");
		sb.append(column + 1);
		sb.append(",");
		sb.append(crc);
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + namePixels;
		result = prime * result + nameWidth;
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
		if (namePixels != other.namePixels) {
			return false;
		}
		if (nameWidth != other.nameWidth) {
			return false;
		}
		return true;
	}

}
