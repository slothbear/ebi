import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LeguImage {
	
	private static final String CHEST_SLOT = "1x1";

	public static void main(String[] args) throws IOException {
		BufferedImage popup;
		BufferedImage enchantmentTextImage;
		String userDir = System.getProperty("user.dir");
		String fileName = userDir + "/captures/" + CHEST_SLOT + ".png";
		popup = ImageIO.read(new File(fileName));

		JOptionPane.showMessageDialog(null, 
				"the full enchanted book information image", 
				"Ebi",
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(popup));

		for (int textRow = 0; textRow < 7; textRow++) {
			enchantmentTextImage = popup.getSubimage(0, 35 * textRow, 425, 35);
			
			int[] px = enchantmentTextImage.getRGB(0, 0, 425, 35, null, 0, 425);
			
			int count = whitePixelCount(px);

			System.out.println("white count(" + textRow + "): " + count);
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

	private static int whitePixelCount(int[] px) {
		final int WHITE_PIXEL = -16777216;
		int count = 0;
		for (int xx=0; xx < px.length; xx++ ) {
			if(px[xx] != WHITE_PIXEL) {
				count++;
			}
		}
		return count;
	}

}
