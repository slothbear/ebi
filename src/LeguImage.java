import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LeguImage {
	private static final String CHEST_SLOT = "1x1";
	private static final int MAX_ENCHANTMENTS = 7;
	private static final int CHUNK_WIDTH = 425;
	private static final int CHUNK_HEIGHT = 35;
	private static final int BLACK_COLOR = new Color(0, 0, 0).getRGB();
	private static final int TEXT_COLOR = new Color(154, 154, 154).getRGB();

	public static void main(String[] args) throws IOException {
		String fileName = System.getProperty("user.dir") +
				"/captures/" + CHEST_SLOT + ".png";
		BufferedImage popup = ImageIO.read(new File(fileName));
		showImage(popup);

		for (int chunkLine = 0; chunkLine < MAX_ENCHANTMENTS; chunkLine++) {
			BufferedImage chunk = popup.getSubimage(0,
					CHUNK_HEIGHT * chunkLine, CHUNK_WIDTH, CHUNK_HEIGHT);
			int[] pixels = chunk.getRGB(0, 0, CHUNK_WIDTH, CHUNK_HEIGHT,
					null, 0, CHUNK_WIDTH);
			int count = textPixelCount(pixels);
			if (count == 0) {
				continue; // next chunk
			}

			int column = chunk.getWidth() - 1;
			while (column >= 0 && allBlack(chunk, column)) {
				column--;
			}

			JOptionPane.showMessageDialog(null,
					"enchantment #" + (chunkLine + 1) +
							"\ntext pixels: " + count + "\nfirst text: "
							+ column,
					"Ebi",
					JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon(chunk));
		}
	}

	private static boolean allBlack(BufferedImage image, int x) {
		int[] pixels = image.getRGB(x, 0, 1, CHUNK_HEIGHT, null, 0, 1);
		for (int y = 0; y < CHUNK_HEIGHT - 1; y++) {
			if (pixels[y] != BLACK_COLOR) {
				return false;
			}
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

	private static void showImage(BufferedImage popup) {
		JOptionPane.showMessageDialog(null,
				"full enchanted book list", "Ebi",
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(popup));
	}

}
