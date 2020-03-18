import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class split_image {
	private static final int WORD_SPACE = 18;
	private static final Map<Integer, Integer> LEVEL_PIXELS;
	static {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(114, 1);
		map.put(228, 2);
		map.put(342, 3);
		map.put(249, 4);
		map.put(135, 5);
		LEVEL_PIXELS = Collections.unmodifiableMap(map);
	}

	private static final int BLACK_COLOR = new Color(0, 0, 0).getRGB();
	private static final int GRAY_LEVEL = 151;
	private static final int TEXT_COLOR = new Color(
		GRAY_LEVEL, GRAY_LEVEL, GRAY_LEVEL).getRGB();


	public static void main(String[] args) throws IOException {
		String fileName = System.getProperty("user.dir") +
			"/captures/single_chunk.png";
		BufferedImage image = ImageIO.read(new File(fileName));

		BufferedImage chunk = strip(image);
		List<BufferedImage> pieces = split(chunk);
		int lastPixelCount = textPixelCount(last(pieces));

		int namePixels = 0;
		int level = 0;

		// Call containsKey() first because get() cannot return an
		// autoboxed Integer/int that is null.
		if (LEVEL_PIXELS.containsKey(lastPixelCount)) {
			level = LEVEL_PIXELS.get(lastPixelCount);
			namePixels = textPixelCount(allButLast(pieces));
		} else {
			namePixels = textPixelCount(chunk);
		}

		System.out.println("namePixels: " + namePixels);
		System.out.println("level: " + level);
	}

	private static BufferedImage last(List<BufferedImage> pieces) {
		return pieces.get(pieces.size() - 1);
	}

	private static List<BufferedImage> allButLast(List<BufferedImage> pieces) {
		return pieces.subList(0, pieces.size() - 1);
	}

	private static List<BufferedImage> split(BufferedImage chunk) {
		int column = 0;
		int foundBlackColumns = 0;
		int startOfPiece = 0;

		int width = chunk.getWidth();
		List<BufferedImage> pieces = new ArrayList<BufferedImage>();

		while (column < width) {
			if (allBlack(chunk, column)) {
				foundBlackColumns++;
			} else {
				foundBlackColumns = 0;
			}

			if (foundBlackColumns == WORD_SPACE) {
				pieces.add(
					getPiece(chunk, startOfPiece, column - WORD_SPACE + 1));
				foundBlackColumns = 0;
				startOfPiece = column + 1;
			}
			column++;
		}
		pieces.add(getPiece(chunk, startOfPiece, column));

		return pieces;
	}

	private static BufferedImage getPiece(BufferedImage chunk,
		int start, int end) {
		return chunk.getSubimage(start, 0,
			end - start, chunk.getHeight());
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

	private static int textPixelCount(BufferedImage image) {
		int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(),
			null, 0, image.getWidth());

		int count = 0;
		for (int pixel : pixels) {
			count += (pixel == TEXT_COLOR) ? 1 : 0;
		}
		return count;
	}

	private static int textPixelCount(List<BufferedImage> pieces) {
		int count = 0;
		for (BufferedImage piece : pieces) {
			count += textPixelCount(piece);
		}
		return count;
	}

}
