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
		String fileName = userDir + "/captures/1x2.png";
		popup = ImageIO.read(new File(fileName));
		
		
		ImageIcon fullIcon = new ImageIcon(popup);

		JOptionPane.showMessageDialog(null, 
				"the full enchanted book information image", 
				"Ebi",
				JOptionPane.INFORMATION_MESSAGE,
				fullIcon);
		

		for (int textRow = 0; textRow < 7; textRow++) {
			enchantmentTextImage = popup.getSubimage(0, 35 * textRow, 425, 35);
			
			int count = 0;
			
			int[] px = popup.getRGB(0, 35*textRow, 425, 35, null, 0, 425);
			
			for (int xx=0; xx < px.length; xx++ ) {
				if(px[xx] != -16777216) {
					count++;
				}
			}
	
			System.out.println("white count(" + textRow + "): " + count);
			if (count == 0) {
				continue;
			}
			
			ImageIcon icon = new ImageIcon(enchantmentTextImage);

			JOptionPane.showMessageDialog(null, 
					"very fine enchantment image", 
					"Ebi",
					JOptionPane.INFORMATION_MESSAGE,
					icon);
		}

	}

}
