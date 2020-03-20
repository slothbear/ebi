import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class Ebi {
	private static Robot ROBOT;

	public static void main(String[] args)
		throws AWTException, InterruptedException {

		ROBOT = new Robot();
		List<Enchantment> enchantments = new ArrayList<Enchantment>();

		String chestID = getChestID();
		if (chestID.isEmpty()) {
			return;
		}
		TimeUnit.SECONDS.sleep(10); // Wait for human to arrange UI.

		for (int chestRow = 0; chestRow < 6; chestRow++) {
			int bookY = 155 + (chestRow * 64);
			for (int chestColumn = 0; chestColumn < 9; chestColumn++) {
				int bookX = 470 + (chestColumn * 64);
				BufferedImage popup = captureInfoPopup(bookX, bookY,
					chestColumn == 8);
				enchantments.addAll(getEnchantments(
					popup, chestID, chestRow, chestColumn));
			}
		}
		ROBOT.keyPress(KeyEvent.VK_ESCAPE);
		System.out.println(enchantments);

	}

	private static BufferedImage captureInfoPopup(int x, int y,
		boolean lastColumn) throws InterruptedException, IOException {

		ROBOT.mouseMove(x, y);
		TimeUnit.MILLISECONDS.sleep(100);
		return getPopupImage(getPopupRect(x, y, lastColumn));

		return null;
	}

	private static List<Enchantment> getEnchantments(
		BufferedImage popup, String chestID, int row, int column) {

		return new ArrayList<Enchantment>();
	}

	private static String getChestID() {
		String id = JOptionPane.showInputDialog(null,
			"Enter the chest ID to associate with these enchantments.\n" +
				"You have 10 seconds after pressing OK to open your " +
				"Large Chest in full screen.",
			"Ebi",
			JOptionPane.INFORMATION_MESSAGE);
		if (id == null) {
			id = "";
		}
		return id;
	}

}
