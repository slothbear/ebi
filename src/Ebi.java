import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Ebi {
	private static Robot ROBOT;

	public static void main(String[] args)
		throws AWTException, InterruptedException, IOException {

		ROBOT = new Robot();
		List<Enchantment> enchantments = new ArrayList<Enchantment>();
		new PrintWriter("ebi_rows.html").close();

		while (true) {
			String chestID = getChestID();
			if (chestID.isEmpty()) {
				writeHTML(enchantments);
				writeCSV(enchantments);
				System.exit(4);
			}
			scanChest(enchantments, chestID);
		}
	}

	private static void scanChest(List<Enchantment> enchantments,
		String chestID) throws InterruptedException, IOException {
		TimeUnit.SECONDS.sleep(10); // Wait for human to arrange UI.

		for (int chestRow = 0; chestRow < 6; chestRow++) {
			int bookY = 155 + (chestRow * 64);
			for (int chestColumn = 0; chestColumn < 9; chestColumn++) {
				int bookX = 470 + (chestColumn * 64);
				BufferedImage popup = captureInfoPopup(bookX, bookY,
					chestColumn == 8);
				stowImageFile(chestRow, chestColumn, popup);

				enchantments.addAll(
					Chunker.getEnchantments(popup, chestID,
						chestRow, chestColumn));
			}
		}
//		ROBOT.keyPress(KeyEvent.VK_ESCAPE);

//		Distiller.distill(enchantments);
//		removeDuplicates(enchantments);
	}

	private static void writeHTML(List<Enchantment> enchantments)
		throws IOException {
		FileWriter writer = new FileWriter("ebi_rows.html", true /* append */);
		for (Enchantment e : enchantments) {
			writer.write(e.toHTMLrow());
		}
		writer.close();
	}

	private static void writeCSV(List<Enchantment> enchantments)
		throws IOException {
		FileWriter writer = new FileWriter("ebi.csv", true /* append */);
		for (Enchantment e : enchantments) {
			writer.write(e.toCSVrow());
		}
		writer.close();
	}

	private static void stowImageFile(int row, int column, BufferedImage popup)
		throws IOException {
		String enchFile = "popups/" + (row + 1) + "x" + (column + 1) + ".png";
		ImageIO.write(popup, "png", new File(enchFile));
	}

	private static BufferedImage captureInfoPopup(int x, int y,
		boolean lastColumn) throws InterruptedException, IOException {

		ROBOT.mouseMove(x, y);
		TimeUnit.MILLISECONDS.sleep(100);
		return getPopupImage(getPopupRect(x, y, lastColumn));
	}

	private static BufferedImage getPopupImage(Rectangle popupRect)
		throws IOException {
		BufferedImage src = ROBOT.createScreenCapture(popupRect);
		FilteredImageSource fis = new FilteredImageSource(
			src.getSource(), new EnchantmentColorFilter());

		Image toolkitImage = Toolkit.getDefaultToolkit().createImage(fis);
		BufferedImage bimage = new BufferedImage(toolkitImage.getWidth(null),
			toolkitImage.getHeight(null), BufferedImage.TYPE_INT_RGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(toolkitImage, 0, 0, null);
		bGr.dispose();

		return bimage;
	}

	private static Rectangle getPopupRect(int x, int y, boolean lastColumn) {
		final int CHUNK_WIDTH = 440;
		int popupX = lastColumn ? x - 515 : x + 35;
		return new Rectangle(popupX, y - 5, CHUNK_WIDTH, 245);
	}

	private static String getChestID() {
		String id = JOptionPane.showInputDialog(null,
			"Enter the chest ID to associate with these enchantments.\n" +
				"You have 10 seconds after pressing OK to open your " +
				"Large Chest of Enchanted Books in full screen.",
			"Ebi",
			JOptionPane.INFORMATION_MESSAGE);
		if (id == null) {
			id = "";
		}
		return id;
	}

	public static <T> List<T> removeDuplicates(List<T> list) {
		Set<T> set = new LinkedHashSet<>();
		set.addAll(list);
		list.clear();
		list.addAll(set);
		return list;
	}


}
