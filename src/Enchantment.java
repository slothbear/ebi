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

	}

	@Override
	public String toString() {
		return "Enchantment [" + name + " "
			+ level + "  location=" + chestID + "/" + row + "x"
			+ column + "]";
	}

}
