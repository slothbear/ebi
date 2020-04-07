import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class EnchantmentColorFilter extends RGBImageFilter {
	private static final int PICKAXE_GRAY_LEVEL = 151; // Minecraft 15.2
	@SuppressWarnings("unused")
	private static final int AXEPICK_GRAY_LEVEL = 154; // Minecraft 13.2
	private static final int GRAY_LEVEL = PICKAXE_GRAY_LEVEL;
//	private static final int CURSE_COLOR = new Color(248, 60, 69).getRGB();
	private static final int CURSE_COLOR = new Color(251, 57, 63).getRGB();
	private static int blackColor = Color.black.getRGB();
	private static int enchTextColor = new Color(
		GRAY_LEVEL, GRAY_LEVEL, GRAY_LEVEL).getRGB();

	public EnchantmentColorFilter() {
		canFilterIndexColorModel = false;
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		if (rgb == enchTextColor || rgb == CURSE_COLOR) {
			return enchTextColor;
		} else {
			return blackColor;
		}
	}
}
