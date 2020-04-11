import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

public class Disenchant {
	private static Robot ROBOT;

	public static void main(String[] args)
		throws InterruptedException, AWTException {
		ROBOT = new Robot();
		ROBOT.setAutoDelay(50);
		TimeUnit.SECONDS.sleep(10); // Wait for human to arrange UI.

		for (int chestRow = 0; chestRow < 3; chestRow++) {
			int y = 484 + (chestRow * 64);
			for (int chestColumn = 0; chestColumn < 9; chestColumn++) {
				int x = 470 + (chestColumn * 64);
				disenchant(x, y);
			}
		}
	}

	private static void disenchant(int x, int y) {
		click(x, y); 		// inventory slot
		click(610, 252); 	// input, top slot
		click(894, 306); 	// output
		click(x, y); 		// put back in inventory
	}

	private static void click(int x, int y) {
		ROBOT.mouseMove(x, y);
		ROBOT.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		ROBOT.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}


}
