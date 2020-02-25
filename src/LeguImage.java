import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LeguImage {
	
	private static final String CHEST_SLOT = "1x1";

	public static void main(String[] args) throws IOException {
		String userDir = System.getProperty("user.dir");
		String fileName = userDir + "/captures/" + CHEST_SLOT + ".png";
		popup = ImageIO.read(new File(fileName));
		BufferedImage popup = ImageIO.read(new File(fileName));
		JOptionPane.showMessageDialog(null, 
				"the full enchanted book information image", 
				"Ebi",
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(popup));

		for (int textRow = 0; textRow < 7; textRow++) {
			enchantmentTextImage = popup.getSubimage(0, 35 * textRow, 425, 35);
			BufferedImage enchantmentTextImage = popup.getSubimage(0, 35 * textRow, 425, 35);

			int[] pixels = enchantmentTextImage.getRGB(0, 0, 425, 35, null, 0, 425);

			int count = whitePixelCount(pixels);
			System.out.println("white pixels (" + textRow + "):" + count);
			if (count == 0) {
				continue;
			}

			JOptionPane.showMessageDialog(null, 
					"enchantment #" + (textRow+1) +
						"\nwhite pixels: " + count,
					"Ebi",
					JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon(enchantmentTextImage));
		}

	}

	private static int whitePixelCount(int[] pixels) {
		final int WHITE_PIXEL = -6645094 ;
		int count = 0;
		
		for (int pixel : pixels) {
			count += (pixel == WHITE_PIXEL) ? 1 : 0;
		}		
		return count;
	}

}
