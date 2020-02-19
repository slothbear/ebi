import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class Ebi {

	public static void main(String[] args) throws AWTException, InterruptedException {
		Robot robot = new Robot();
		
		String chestID = JOptionPane.showInputDialog("[e] Enter chest ID to associate with this collection of books:");		
		System.out.println(chestID);
		
//		Give time to rearrange chest UI for capture.
		TimeUnit.SECONDS.sleep(10);
		
//		Capture book enchantments moving from bottom to top,
//		right to left. This cursor movement doesn't interfere with
//		redrawing the item information popup.
		
		for (int chestRow = 5; chestRow > -1; chestRow--) {
	        int bookY = 155 + (chestRow * 64);
	        for (int chestColumn = 8; chestColumn > -1; chestColumn--) {
	          int bookX = 470 + (chestColumn * 64);
	          robot.mouseMove(bookX, bookY);
	          TimeUnit.MILLISECONDS.sleep(100);
	        }
		}
	} // main()
}
