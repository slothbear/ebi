import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class ChunkerTest {
//	private static final String CHEST_SLOT = "4x2";

	public static void main(String[] args) throws IOException {
		BufferedImage popup;
		List<Enchantment> enchantments = new ArrayList<Enchantment>();

		// Read all the .png in a folder.

		File folder = new File(System.getProperty("user.dir") +
			"/popups/");
		File[] pngs = folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isFile()
					&& file.getName().toLowerCase().endsWith(".png")
					&& file.getName().length() == 7;
			}
		});

		for (File png : pngs) {
			String fileName = png.getName();
			int row = Integer.parseInt(fileName.substring(0, 1));
			int column = Integer.parseInt(fileName.substring(2, 3));

			popup = ImageIO.read(png);
			enchantments.addAll(
				Chunker.getEnchantments(popup, "Sid",
					row, column));
		}
		Distiller.distill(enchantments);

		System.out.println("enchantments:");
		System.out.println(Arrays.toString(enchantments.toArray()));
		System.out.println("spells:");
		System.out.println(Arrays.toString(Distiller.spells.toArray()));

		Enchantment.dump(Distiller.spells);

		System.out.println(enchantments.size() + " enchantments");
		System.out.println(Distiller.spells.size() + " spells");
	}

}
