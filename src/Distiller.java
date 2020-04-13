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
		new Enchantment("Aqua Afifinity", 0, "spell", 0, 0, null, 220, 220, 1491, 1494),
		new Enchantment("Bane of Arthropods", 0, "spell", 0, 0, null, 350, 350, 2136, 2136),
		new Enchantment("Blast Protection", 0, "spell", 0, 0, null, 279, 279, 1737, 1737),
		new Enchantment("Channeling", 0, "spell", 0, 0, null, 181, 181, 1248, 1248),
		new Enchantment("Curse of Binding", 0, "spell", 0, 0, null, 287, 287, 1773, 1773),
		new Enchantment("Curse of Vanishing", 0, "spell", 0, 0, null, 328, 328, 1941, 1941),
		new Enchantment("Depth Strider", 0, "spell", 0, 0, null, 234, 234, 1506, 1506),
		new Enchantment("Efficiency", 0, "spell", 0, 0, null, 171, 171, 1173, 1173),
		new Enchantment("Feather Falling", 0, "spell", 0, 0, null, 262, 262, 1686, 1686),
		new Enchantment("Fire Aspect", 0, "spell", 0, 0, null, 199, 199, 1248, 1257),
		new Enchantment("Fire Protection", 0, "spell", 0, 0, null, 262, 262, 1548, 1548),
		new Enchantment("Flame", 0, "spell", 0, 0, null, 91, 91, 630, 630),
		new Enchantment("Fortune", 0, "spell", 0, 0, null, 136, 136, 834, 837),
		new Enchantment("Frost Walker", 0, "spell", 0, 0, null, 220, 220, 1314, 1314),
		new Enchantment("Impaling", 0, "spell", 0, 0, null, 133, 133, 966, 966),
		new Enchantment("Infinity", 0, "spell", 0, 0, null, 119, 119, 837, 837),
		new Enchantment("Knockback", 0, "spell", 0, 0, null, 178, 178, 1155, 1155),
		new Enchantment("Looting", 0, "spell", 0, 0, null, 122, 122, 801, 801),
		new Enchantment("Loyalty", 0, "spell", 0, 0, null, 126, 126, 843, 843),
		new Enchantment("Luck of the Sea", 0, "spell", 0, 0, null, 276, 276, 1515, 1515),
		new Enchantment("Lure", 0, "spell", 0, 0, null, 80, 80, 480, 480),
		new Enchantment("Mending", 0, "spell", 0, 0, null, 129, 129, 969, 969),
		new Enchantment("Multishot", 0, "spell", 0, 0, null, 146, 147, 993, 1011),
		new Enchantment("Piercing", 0, "spell", 0, 0, null, 136, 136, 903, 912),
		new Enchantment("Power", 0, "spell", 0, 0, null, 101, 101, 648, 654),
		new Enchantment("Protection", 0, "spell", 0, 0, null, 178, 178, 1107, 1107),
		new Enchantment("Punch", 0, "spell", 0, 0, null, 101, 101, 645, 645),
		new Enchantment("Quick Charge", 0, "spell", 0, 0, null, 224, 224, 1392, 1392),
		new Enchantment("Respiration", 0, "spell", 0, 0, null, 192, 192, 1299, 1299),
		new Enchantment("Riptide", 0, "spell", 0, 0, null, 108, 108, 852, 855),
		new Enchantment("Sharpness", 0, "spell", 0, 0, null, 185, 185, 1188, 1200),
		new Enchantment("Silk Touch", 0, "spell", 0, 0, null, 171, 171, 1008, 1008),
		new Enchantment("Smite", 0, "spell", 0, 0, null, 80, 80, 579, 585),
		new Enchantment("Sweeping Edge", 0, "spell", 0, 0, null, 248, 248, 1764, 1782),
		new Enchantment("Thorns", 0, "spell", 0, 0, null, 122, 122, 729, 729),
		new Enchantment("Unbreaking", 0, "spell", 0, 0, null, 188, 188, 1290, 1299),

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
			int index = spells.indexOf(enchantment);

			if (index != -1) { // found existing spell for this enchantment
				Enchantment spell = spells.get(index);
				enchantment.name = spell.name;
			} else {  // no spell found
				enchantment.name = promptForName(enchantment);
				Enchantment spell = getSpell(enchantment.name);
				spell.merge(enchantment);
			}
		}
	}

	private static Enchantment getSpell(String name) {
		for (Enchantment spell : spells) {
			if (spell.name.equals(name)) {
				return spell;
			}
		}
		Enchantment newSpell = new Enchantment(name);
		spells.add(newSpell);
		return newSpell;
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
