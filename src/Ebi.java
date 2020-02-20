import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Ebi {
	
	private static Robot robot;

	public static void main(String[] args) throws AWTException, InterruptedException, IOException {
		BufferedImage popup;
		robot = new Robot();
		
		String chestID = JOptionPane.showInputDialog("Enter chest ID to associate with this collection of books:");		
		System.out.println(chestID);
		
//		Give human time to rearrange chest UI for capture.
		TimeUnit.SECONDS.sleep(10);
		
//		Capture book enchantments moving from bottom to top,
//		right to left. This cursor movement doesn't interfere with
//		redrawing the item information popup.
		
		for (int chestRow = 5; chestRow > -1; chestRow--) {
	        int bookY = 155 + (chestRow * 64);
	        for (int chestColumn = 8; chestColumn > -1; chestColumn--) {
	          int bookX = 470 + (chestColumn * 64);
	          robot.mouseMove(bookX, bookY);
	          TimeUnit.MILLISECONDS.sleep(100);
	          popup = captureInfoPopup(bookX, bookY, chestColumn);
	          stowImageFile(chestRow, chestColumn, popup);	          
	        }
		}
	} // main()

	private static void stowImageFile(int row, int column, BufferedImage popup) throws IOException {
        String enchFile = "captures/" + (row+1) + "x" + (column+1) + ".png";
        ImageIO.write(popup, "png", new File(enchFile));
	}

	private static BufferedImage captureInfoPopup(int x, int y, int column) {
		Rectangle popupRect = getPopupRect(x, y, column);
		BufferedImage popup = getPopupImage(popupRect);
		return popup;
	}

	private static BufferedImage getPopupImage(Rectangle popupRect) {
        BufferedImage src = robot.createScreenCapture(popupRect);
        ImageFilter colorfilter = new EnchantmentColorFilter();
	      FilteredImageSource fis = new FilteredImageSource(src.getSource(), colorfilter);

	      Image toolkitImage = Toolkit.getDefaultToolkit().createImage(fis);
	      BufferedImage bimage = new BufferedImage(toolkitImage.getWidth(null), toolkitImage.getHeight(null), BufferedImage.TYPE_INT_RGB);

	      // Draw the image on to the buffered image
	      Graphics2D bGr = bimage.createGraphics();
	      bGr.drawImage(toolkitImage, 0, 0, null);
	      bGr.dispose();

	      return bimage;
	}

	private static Rectangle getPopupRect(int x, int y, int column) {
		Rectangle popupRect;
		if (column == 8) {
            // Details pop up to the *left* of the cursor.
        	popupRect = new Rectangle(x - 515, y - 5, 425, 245);
          }
          else {
            // Details pop up to the right of the cursor.
        	  popupRect = new Rectangle(x + 35, y - 5, 425, 245);
          }
        return popupRect;
	}
}


class EnchantmentColorFilter extends RGBImageFilter {
	private static int blackColor = Color.black.getRGB();
	private static int enchTextColor = new Color(154, 154, 154).getRGB();

	public EnchantmentColorFilter() {
		canFilterIndexColorModel = false;
	}

	public int filterRGB(int x, int y, int rgb) {
		if (rgb == enchTextColor) {
			return rgb;
		}
		else {
			return blackColor;
		}
	}
}
