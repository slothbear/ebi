import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class Ebi {
	private static Robot ROBOT;

	public static void main(String[] args)
		throws AWTException, InterruptedException, IOException {

		ROBOT = new Robot();
		List<Enchantment> enchantments = new ArrayList<Enchantment>();

		String chestID = getChestID();
		if (chestID.isEmpty()) {
			return;
		}
		TimeUnit.SECONDS.sleep(10); // Wait for human to arrange UI.

		for (int chestRow = 0; chestRow < 6; chestRow++) {
			int bookY = 155 + (chestRow * 64);
			for (int chestColumn = 0; chestColumn < 9; chestColumn++) {
				int bookX = 470 + (chestColumn * 64);
				BufferedImage popup = captureInfoPopup(bookX, bookY,
					chestColumn == 8);
				enchantments.addAll(
					Chunker.getEnchantments(popup, chestID,
						chestRow, chestColumn));
			}
		}
		ROBOT.keyPress(KeyEvent.VK_ESCAPE);

		System.out.println(enchantments);
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

}
