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
		new Enchantment("Channeling", 0, "", 0, 0, null, 181, 181, 1248, 1248),
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
