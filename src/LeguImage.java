import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LeguImage {

	public static void main(String[] args) throws IOException {
		BufferedImage popup;
		BufferedImage enchantmentTextImage;
		String userDir = System.getProperty("user.dir");
		String fileName = userDir + "/captures/1x5.png";
		popup = ImageIO.read(new File(fileName));
		
		for (int textRow = 0; textRow < 7; textRow++) {
			enchantmentTextImage = popup.getSubimage(0, 35 * textRow, 425, 35);
			ImageIcon icon = new ImageIcon(enchantmentTextImage);
			JOptionPane.showMessageDialog(null, 
					"very fine enchantment image", 
					"Ebi",
					JOptionPane.INFORMATION_MESSAGE,
					icon);
		}
		
	}

}
