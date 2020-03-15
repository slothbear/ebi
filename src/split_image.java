import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class split_image {
	private static final int BLACK_COLOR = new Color(0, 0, 0).getRGB();

	public static void main(String[] args) throws IOException {

		String fileName = System.getProperty("user.dir") +
			"/captures/single_chunk.png";
		BufferedImage image = ImageIO.read(new File(fileName));

		BufferedImage chunk = strip(image);
		showImage(chunk, "stripped");
		BufferedImage[] pieces = split(chunk);

		BufferedImage name = null;
		BufferedImage level = null;

//		BufferedImage lastPiece = pieces[pieces.length - 1];
//		if (isLevel(lastPiece)) {
//			level = lastPiece;
//			BufferedImage[] allButLast = Arrays.copyOfRange(pieces, 0,
//				pieces.length - 1);
//			name = (BufferedImage) join(allButLast);
//		} else {
//			level = null;
//			name = (BufferedImage) join(pieces);
//		}
//
//		System.out.println(name);
//		System.out.println(level);

	}

	private static Object join(BufferedImage[] copyOfRange) {

		return null;
	}

	private static boolean isLevel(BufferedImage lastPiece) {

		return false;
	}

	private static BufferedImage[] split(BufferedImage chunk) {

		return null;
	}

	private static BufferedImage strip(BufferedImage chunk) {
		int column = 0;
		while (column <= chunk.getWidth() - 1 && allBlack(chunk, column)) {
			column++;
		}
		int firstTextColumn = column;

		column = chunk.getWidth() - 1;
		while (column >= 0 && allBlack(chunk, column)) {
			column--;
		}
		int lastTextColumn = column;

		return chunk.getSubimage(firstTextColumn, 0,
			lastTextColumn - firstTextColumn + 1, chunk.getHeight());
	}

	private static boolean allBlack(BufferedImage image, int x) {
		int height = image.getHeight();
		int[] pixels = image.getRGB(x, 0, 1, height, null, 0, 1);
		for (int y = 0; y < height; y++) {
			if (pixels[y] != BLACK_COLOR) {
				return false;
			}
		}
		return true;
	}

	private static void showImage(BufferedImage popup, String message) {
		JOptionPane.showMessageDialog(null, message,
			"Ebi", JOptionPane.INFORMATION_MESSAGE,
			new ImageIcon(popup));
	}

}