import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class EbiSpike {
	private static final int CHUNK_WIDTH = 440;
	private static Robot robot;

	public static void main(String[] args)
		throws AWTException, InterruptedException, IOException {
		BufferedImage popup;
		robot = new Robot();

		String chestID = JOptionPane.showInputDialog(
			"Enter chest ID to associate with this collection of books:");
		System.out.println(chestID);

//		Give human time to rearrange chest UI for capture.
		TimeUnit.SECONDS.sleep(10);

		for (int chestRow = 0; chestRow < 6; chestRow++) {
			int bookY = 155 + (chestRow * 64);
			for (int chestColumn = 0; chestColumn < 9; chestColumn++) {
				int bookX = 470 + (chestColumn * 64);
				robot.mouseMove(bookX, bookY);
				TimeUnit.MILLISECONDS.sleep(100);
				popup = captureInfoPopup(bookX, bookY, chestColumn == 8);
				stowImageFile(chestRow, chestColumn, popup);
			}
		}
		robot.keyPress(KeyEvent.VK_ESCAPE);
	}

	private static void stowImageFile(int row, int column, BufferedImage popup)
		throws IOException {
		String enchFile = "captures/" + (row + 1) + "x" + (column + 1) + ".png";
		ImageIO.write(popup, "png", new File(enchFile));
	}

	private static BufferedImage captureInfoPopup(int x, int y,
		boolean lastColumn)
		throws IOException {
		Rectangle popupRect = getPopupRect(x, y, lastColumn);
		BufferedImage popup = getPopupImage(popupRect);
		return popup;
	}

	private static BufferedImage getPopupImage(Rectangle popupRect)
		throws IOException {
		BufferedImage src = robot.createScreenCapture(popupRect);

		String popupFile = "captures/popup.png";
		ImageIO.write(src, "png", new File(popupFile));

		ImageFilter colorfilter = new LocalEnchantmentColorFilter();
		FilteredImageSource fis = new FilteredImageSource(src.getSource(),
			colorfilter);

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
		Rectangle popupRect;
		if (lastColumn) {
			// Details pop up to the *left* of the cursor.
			popupRect = new Rectangle(x - 515, y - 5, CHUNK_WIDTH, 245);
		} else {
			// Details pop up to the right of the cursor.
			popupRect = new Rectangle(x + 35, y - 5, CHUNK_WIDTH, 245);
		}
		return popupRect;
	}
}


class LocalEnchantmentColorFilter extends RGBImageFilter {
	@SuppressWarnings("unused")
	private static final int PICKAXE_GRAY_LEVEL = 151; // Minecraft 15.2
	private static final int AXEPICK_GRAY_LEVEL = 154; // Minecraft 13.2
	private static final int GRAY_LEVEL = AXEPICK_GRAY_LEVEL;
	private static int blackColor = Color.black.getRGB();
	private static int enchTextColor = new Color(
		GRAY_LEVEL, GRAY_LEVEL, GRAY_LEVEL).getRGB();

	public LocalEnchantmentColorFilter() {
		canFilterIndexColorModel = false;
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		if (rgb == enchTextColor) {
			return rgb;
		} else {
			return blackColor;
		}
	}
}
