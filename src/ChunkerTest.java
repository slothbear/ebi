import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;

public class ChunkerTest {
//	private static final String CHEST_SLOT = "4x2";

	public static void main(String[] args) throws IOException {
		BufferedImage popup;
		List<Enchantment> enchantments = new ArrayList<Enchantment>();

// Read a single popup.
//		String fileName = System.getProperty("user.dir") +
//			"/popups/" + CHEST_SLOT + ".png";
//		popup = ImageIO.read(new File(fileName));
//		enchantments = Chunker.getEnchantments(popup, "unu", 4, 2);
//		for (Enchantment e : enchantments) {
//			System.out.println(e);
//		}

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

		List<Enchantment> dedup = removeDuplicates(enchantments);

		for (Enchantment e : dedup) {
			System.out.println(e.getTableRow());
		}

		writeProperties(Distiller.pixelMap);
	}

	private static void writeProperties(Map<Integer, String> pixelMap)
		throws IOException {
		Properties props = new Properties();

		for (Map.Entry<Integer, String> entry : pixelMap.entrySet()) {
			String key = entry.getKey().toString();
			props.setProperty(key, entry.getValue());
		}

		FileOutputStream out = new FileOutputStream("ebi.properties");
		props.store(out, "Ebi: Enchanted Book index");
		out.close();

	}

	public static <T> List<T> removeDuplicates(List<T> list) {
		Set<T> set = new LinkedHashSet<>();
		set.addAll(list);
		list.clear();
		list.addAll(set);
		return list;
	}

}
