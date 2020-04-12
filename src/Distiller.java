import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class Distiller {
	static List<Enchantment> spells = new ArrayList<Enchantment>();
	private static List<String> options = new ArrayList<String>();

	private static Enchantment[] spellVault = new Enchantment[] {
		// @formatter:off (so each enchantment stays on one line)
		new Enchantment("Punch", 0, "", 0, 0, null, 101, 101, 636, 648),
		new Enchantment("Mending", 0, "", 0, 0, null, 129, 129, 969, 969),
		new Enchantment("Respiration", 0, "", 0, 0, null, 192, 192, 1293, 1293),
		new Enchantment("Punch", 0, "", 0, 0, null, 101, 101, 636, 648),
		new Enchantment("Loyalty", 0, "", 0, 0, null, 125, 126, 843, 846),
		new Enchantment("Channeling", 0, "", 0, 0, null, 181, 181, 1248, 1248),
		new Enchantment("Flame", 0, "", 0, 0, null, 90, 90, 624, 624),
		new Enchantment("Power", 0, "", 0, 0, null, 101, 101, 654, 654),
		new Enchantment("Sharpness", 0, "", 0, 0, null, 185, 185, 1200, 1200),
		new Enchantment("Loyalty", 0, "", 0, 0, null, 125, 126, 843, 846)
		// @formatter:on
	};
	private static List<Enchantment> spellChest = Arrays.asList(spellVault);

	static {
		spells.addAll(spellChest);

		options.add("Aqua Afifinity");
		options.add("Bane of Arthropods");
		options.add("Blast Protection");
		options.add("Channeling");
		options.add("Chopping");
		options.add("Curse of Binding");
		options.add("Curse of Vanishing");
		options.add("Depth Strider");
		options.add("Efficiency");
		options.add("Feather Falling");
		options.add("Fire Aspect");
		options.add("Fire Protection");
		options.add("Flame");
		options.add("Fortune");
		options.add("Frost Walker");
		options.add("Impaling");
		options.add("Infinity");
		options.add("Knockback");
		options.add("Looting");
		options.add("Loyalty");
		options.add("Luck of the Sea");
		options.add("Lure");
		options.add("Mending");
		options.add("Multishot");
		options.add("Piercing");
		options.add("Power");
		options.add("Projectile Protection");
		options.add("Protection");
		options.add("Punch");
		options.add("Quick Charge");
		options.add("Respiration");
		options.add("Riptide");
		options.add("Sharpness");
		options.add("Silk Touch");
		options.add("Smite");
		options.add("Soul Speed");
		options.add("Sweeping Edge");
		options.add("Thorns");
		options.add("Unbreaking");
	}

	public static void distill(List<Enchantment> enchantments)
		throws IOException {
		setDialogOptions();

		for (Enchantment enchantment : enchantments) {


			}
		}
	}

	private static String promptForName(Enchantment enchantment) {
		String choice = (String) JOptionPane.showInputDialog(null,
			"Which enchantment is this?\n",
			"Ebi",
			JOptionPane.QUESTION_MESSAGE,
			new ImageIcon(enchantment.image),
			options.toArray(),
			options.get(0));

		if (choice == null || choice.isEmpty()) {
			JOptionPane.showMessageDialog(null,
				"Quitting: CANCEL or nothing selected");
			System.exit(12);
		}
		return choice;
	}

	private static void setDialogOptions() {
		UIManager.put("OptionPane.background",
			new ColorUIResource(Color.black));
		UIManager.put("Panel.background", new ColorUIResource(Color.black));
		UIManager.put("OptionPane.minimumSize", new Dimension(600, 400));
		UIManager.put("OptionPane.messageForeground", Color.red);
	}

}
