import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LeguImage {
	private static final String CHEST_SLOT = "1x1";
	private static final int BLACK_COLOR = new Color(0,0,0).getRGB();
	private static final int TEXT_COLOR = new Color(154,154,154).getRGB();

	public static void main(String[] args) throws IOException {
		String fileName = System.getProperty("user.dir") +
				"/captures/" + CHEST_SLOT + ".png";
		BufferedImage popup = ImageIO.read(new File(fileName));
		JOptionPane.showMessageDialog(null,
				"full enchanted book list",
				"Ebi",
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(popup));

		for (int chunk = 0; chunk < 7; chunk++) {
			BufferedImage enchantmentTextImage = popup.getSubimage(0, 35 * chunk, 425, 35);
			int[] pixels = enchantmentTextImage.getRGB(0, 0, 425, 35, null, 0, 425);
			int count = textPixelCount(pixels);
			if (count == 0) {
				continue; // next chunk
			}

			int column = enchantmentTextImage.getWidth()-1;
			while (column >= 0 && allBlack(enchantmentTextImage, column)) {
				column--;
				}

			JOptionPane.showMessageDialog(null,
					"enchantment #" + (chunk+1) +
						"\ntext pixels: " + count + "\nfirst text: " + column,
					"Ebi",
					JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon(enchantmentTextImage));
		}
	}

	private static boolean allBlack(BufferedImage image, int x) {
		int[] pixels = image.getRGB(x, 0, 1, 35, null, 0, 1);
		for (int y = 0; y<34; y++) {
			if (pixels[y] != BLACK_COLOR)
			return false;
		}
		return true;
	}

	private static int textPixelCount(int[] pixels) {
		int count = 0;
		for (int pixel : pixels) {
			count += (pixel == TEXT_COLOR) ? 1 : 0;
		}
		return count;
	}

}
