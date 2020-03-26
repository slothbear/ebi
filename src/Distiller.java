import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class Distiller {
	private static Map<Integer, String> pixelMap = new HashMap<Integer, String>();
	private static List<String> options = new ArrayList<String>();
	static {
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

	public static void distill(List<Enchantment> enchantments) {
		setDialogOptions();

		for (Enchantment enchantment : enchantments) {
			if (!enchantment.name.isEmpty()) {
				continue;
			}

			if (pixelMap.containsKey(enchantment.namePixels)) {
				enchantment.name = pixelMap.get(enchantment.namePixels);
			} else {
				String choice = (String) JOptionPane.showInputDialog(null,
					"Which enchantment is this?\n",
					"Ebi",
					JOptionPane.QUESTION_MESSAGE,
					new ImageIcon(enchantment.image),
					options.toArray(),
					options.get(0));

				enchantment.name = choice;
				pixelMap.put(enchantment.namePixels, choice);
			}
		}
	}

	private static void setDialogOptions() {
		UIManager.put("OptionPane.background",
			new ColorUIResource(Color.black));
		UIManager.put("Panel.background", new ColorUIResource(Color.black));
		UIManager.put("OptionPane.minimumSize", new Dimension(600, 400));
		UIManager.put("OptionPane.messageForeground", Color.red);
	}

}
