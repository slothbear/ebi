import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LeguImage {

	private static final String CHEST_SLOT = "1x1";

	public static void main(String[] args) throws IOException {
		String fileName = System.getProperty("user.dir") +
				"/captures/" + CHEST_SLOT + ".png";
		BufferedImage popup = ImageIO.read(new File(fileName));
		JOptionPane.showMessageDialog(null,
				"full enchanted book list",
				"Ebi",
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(popup));

		for (int textRow = 0; textRow < 7; textRow++) {
			BufferedImage enchantmentTextImage = popup.getSubimage(0, 35 * textRow, 425, 35);

			int[] pixels = enchantmentTextImage.getRGB(0, 0, 425, 35, null, 0, 425);

			int count = textPixelCount(pixels);
			System.out.println("text pixels (" + textRow + "):" + count);
			if (count == 0) {
				continue;
			}

			int column = enchantmentTextImage.getWidth()-1;
			while (column >= 0 && allBlack(enchantmentTextImage, column)) {
				column--;
				}

			JOptionPane.showMessageDialog(null,
					"enchantment #" + (textRow+1) +
						"\ntext pixels: " + count + "\nfirst text: " + column,
					"Ebi",
					JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon(enchantmentTextImage));
		}
	}

	private static boolean allBlack(BufferedImage image, int x) {
		return false;
	}

	private static int textPixelCount(int[] pixels) {
		final int TEXT_COLOR = -6645094 ;
		int count = 0;

		for (int pixel : pixels) {
			count += (pixel == TEXT_COLOR) ? 1 : 0;
		}
		return count;
	}

}
