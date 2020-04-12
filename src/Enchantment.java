import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;

public class Enchantment {
	String name = "";
	int level = 0;

	String chestID = "";
	int row = 0;
	int column = 0;

	BufferedImage image;
	String id = "";

	int minNameWidth = Integer.MAX_VALUE;
	int maxNameWidth = 0;
	int minNamePixels = Integer.MAX_VALUE;
	int maxNamePixels = 0;

	private static AtomicInteger unique = new AtomicInteger();

	// Enchantments created with just a name are called Spells.
	// They contain different values for min/max width and pixels.
	public Enchantment(String name) {
		this.name = name;
		this.chestID = "spell";
	}

	public Enchantment(String name, int level,
		String chestID, int row, int column,
		BufferedImage image,
		int minNameWidth, int maxNameWidth,
		int minNamePixels, int maxNamePixels) {

		this.name = name;
		this.minNameWidth = minNameWidth;
		this.maxNameWidth = maxNameWidth;
		this.minNamePixels = minNamePixels;
		this.maxNamePixels = maxNamePixels;
		this.level = level;
		this.chestID = chestID;
		this.row = row;
		this.column = column;
		this.image = image;
		this.id = String.valueOf(unique.getAndIncrement());
	}


	public Enchantment(String name, int level,
		String chestID, int row, int column,
		BufferedImage image,
		int nameWidth, int namePixels) throws IOException {

		this(name, level, chestID, row, column, image,
			nameWidth, nameWidth, namePixels, namePixels);

		ImageIO.write(image, "png", new File(chunkFilename()));
	}


	private String chunkFilename() {
		return "chunks/" +
			chestID + "-" +
			row + "x" + column + "-" +
			id + ".png";
	}

	public String toHTMLrow() {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>\n");
		sb.append("  <td>" + minNameWidth + "</td>\n");
		sb.append("  <td>" + minNamePixels + "</td>\n");
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

	@Override
	public String toString() {
		StringBuffer width = new StringBuffer();
		width.append(minNameWidth);
		if (minNameWidth != maxNameWidth) {
			width.append("-");
			width.append(maxNameWidth);
		}
		StringBuffer pixels = new StringBuffer();
		pixels.append(minNamePixels);
		if (minNamePixels != maxNamePixels) {
			pixels.append("-");
			pixels.append(maxNamePixels);
		}
		StringBuffer chest = new StringBuffer();
		if (chestID == "" || chestID == "spell") {
			chest.append("spell");
		} else {
			chest.append(chestID + "-" + row + "x" + column);
		}

		String romanLevel = "";
		switch (level) {
		case 1:
			romanLevel = "I";
			break;
		case 2:
			romanLevel = "II";
			break;
		case 3:
			romanLevel = "III";
			break;
		case 4:
			romanLevel = "IV";
			break;
		case 5:
			romanLevel = "V";
			break;
		}

		return name + " " + romanLevel + ": " +
			chest +
			" [" +
			width + ", " + pixels +
			"] " +
			"ID" + id +
			"\n";
	}


	public String toCSVrow() {
		StringBuilder sb = new StringBuilder();
		String crc = chestID + "-" + (row + 1) + "x" + (column + 1);

		sb.append(minNameWidth);
		sb.append(",");
		sb.append(minNamePixels);
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
		result = prime * result + maxNamePixels;
		result = prime * result + maxNameWidth;
		result = prime * result + minNamePixels;
		result = prime * result + minNameWidth;
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

		if (hasOverlap(this, (Enchantment) obj)) {
			return true;
		}

		return false;
	}

	private static boolean hasOverlap(Enchantment e1, Enchantment e2) {
		boolean widthOverlaps = !(e1.maxNameWidth < e2.minNameWidth)
			&& !(e1.minNameWidth > e2.maxNameWidth);

		boolean pixelOverlaps = !(e1.maxNamePixels < e2.minNamePixels)
			&& !(e1.minNamePixels > e2.maxNamePixels);

		return widthOverlaps && pixelOverlaps;
	}

	public static void dump(List<Enchantment> enchantments) {
		System.out.println("<---");
		for (Enchantment enchantment : enchantments) {
			System.out.println(
				"new Enchantment(" +
					"\"" + enchantment.name + "\", " +
					enchantment.level + ", " +
					"\"" + enchantment.chestID + "\", " +
					enchantment.row + ", " +
					enchantment.column + ", null, " +
					enchantment.minNameWidth + ", " +
					enchantment.maxNameWidth + ", " +
					enchantment.minNamePixels + ", " +
					enchantment.maxNamePixels +
					"),");
		}
		System.out.println("--->");
	}
}
