import javax.swing.JOptionPane;

public class Ebi {

	public static void main(String[] args) {
		String chestID = getChestID();
		if (chestID.isEmpty()) {
			return;
		}
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
