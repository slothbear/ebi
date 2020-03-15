import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class split_image {

	public static void main(String[] args) throws IOException {
		String fileName = System.getProperty("user.dir") +
			"/captures/single_chunk.png";
		BufferedImage chunk = ImageIO.read(new File(fileName));
		BufferedImage name = null;
		BufferedImage level = null;

		chunk = strip(chunk);
		BufferedImage[] pieces = split(chunk);

		BufferedImage lastPiece = pieces[pieces.length - 1];
		if (isLevel(lastPiece)) {
			level = lastPiece;
			BufferedImage[] allButLast = Arrays.copyOfRange(pieces, 0,
				pieces.length - 1);
			name = (BufferedImage) join(allButLast);
		} else {
			level = null;
			name = (BufferedImage) join(pieces);
		}

		System.out.println(name);
		System.out.println(level);

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

		return null;
	}

}
