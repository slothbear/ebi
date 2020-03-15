import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LeguImage {
	private static final String CHEST_SLOT = "6x9";
	private static final int MAX_ENCHANTMENTS = 7;
	private static final int CHUNK_WIDTH = 440;
	private static final int CHUNK_HEIGHT = 35;
	private static final int MIN_TEXT_PIXELS = 100;

	private static final int BLACK_COLOR = new Color(0, 0, 0).getRGB();
	private static final int GRAY_LEVEL = 151;
	private static final int TEXT_COLOR = new Color(
		GRAY_LEVEL, GRAY_LEVEL, GRAY_LEVEL).getRGB();

	public static void main(String[] args) throws IOException {
		String fileName = System.getProperty("user.dir") +
			"/captures/" + CHEST_SLOT + ".png";
		BufferedImage popup = ImageIO.read(new File(fileName));

		for (int chunkLine = 0; chunkLine < MAX_ENCHANTMENTS; chunkLine++) {
			BufferedImage chunk = popup.getSubimage(0,
				CHUNK_HEIGHT * chunkLine, CHUNK_WIDTH, CHUNK_HEIGHT);
			int chunkPixelCount = textPixelCount(chunk);
			if (chunkPixelCount < MIN_TEXT_PIXELS) {
				continue; // next chunk
			}

			showImage(chunk, "enchantment #" + (chunkLine + 1) +
				"\ntext pixels: " + chunkPixelCount);

			int column = chunk.getWidth() - 1;
			while (column >= 0 && allBlack(chunk, column)) {
				column--;
			}

			int finalLevelColumn = column;
			int foundBlackColumns = 0;
			int WORD_SPACE = 18;
			while (column >= 0 && foundBlackColumns < WORD_SPACE) {
				column--;
				if (allBlack(chunk, column)) {
					foundBlackColumns++;
				} else {
					foundBlackColumns = 0;
				}
			}

			BufferedImage level = chunk.getSubimage(column + WORD_SPACE, 0,
				finalLevelColumn - column - WORD_SPACE + 1, chunk.getHeight());
			showImage(level, "level\ntext pixels: " + textPixelCount(level));

			column = 0;
			while (column <= finalLevelColumn - 18 && allBlack(chunk, column)) {
				column++;
			}
			int firstNameColumn = column;

			BufferedImage name = chunk.getSubimage(firstNameColumn, 0,
				finalLevelColumn - firstNameColumn - 18 - level.getWidth() + 1,
				chunk.getHeight());
			showImage(name, "name\ntext pixels: " + textPixelCount(name));
		}
	}

	private static void colorColumn(BufferedImage image, int x, Color color) {
		int height = image.getHeight();
		int rgb = color.getRGB();
		for (int y = 0; y < height; y++) {
			image.setRGB(x, y, rgb);
		}
	}

	private static boolean allBlack(BufferedImage image, int x) {
		int height = image.getHeight();
		int[] pixels = image.getRGB(x, 0, 1, height, null, 0, 1);
		for (int y = 0; y < height - 1; y++) {
			if (pixels[y] != BLACK_COLOR) {
				return false;
			}
		}
		return true;
	}

	private static int textPixelCount(BufferedImage image) {
		int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(),
			null, 0, image.getWidth());

		int count = 0;
		for (int pixel : pixels) {
			count += (pixel == TEXT_COLOR) ? 1 : 0;
		}
		return count;
	}

	private static void showImage(BufferedImage popup, String message) {
		JOptionPane.showMessageDialog(null, message,
			"Ebi", JOptionPane.INFORMATION_MESSAGE,
			new ImageIcon(popup));
	}

}
