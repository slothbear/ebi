import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class ChunkerTest {
	private static final String CHEST_SLOT = "4x2";

	public static void main(String[] args) throws IOException {
		BufferedImage popup;
		List<Enchantment> enchantments;

		String fileName = System.getProperty("user.dir") +
			"/captures/" + CHEST_SLOT + ".png";
		popup = ImageIO.read(new File(fileName));
		enchantments = Chunker.getEnchantments(popup, "unu", 4, 2);
		for (Enchantment e : enchantments) {
			System.out.println(e);
		}

		File folder = new File(System.getProperty("user.dir") +
			"/captures/");
		File[] pngs = folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isFile()
					&& file.getName().toLowerCase().endsWith(".png");
			}
		});
		for (File png : pngs) {
			popup = ImageIO.read(png);
			enchantments = Chunker.getEnchantments(popup, "du", 4, 2);
			for (Enchantment e : enchantments) {
				System.out.println(e);
			}
		}
	}
}
