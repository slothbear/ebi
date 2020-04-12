import java.io.IOException;

import org.junit.jupiter.api.Test;

class EnchantmentTest {

//	@Test
	void testNewSpell() throws IOException {
		Enchantment e1 = new Enchantment("Lure", 5, "ch1", 1, 5, null, 123,
			450);
		Enchantment spell = new Enchantment("Lure");
		spell.merge(e1);
		System.out.println(spell);
	}

//	@Test
	void testExistingSpell() throws IOException {
		Enchantment e1 = new Enchantment("Mending", 4, "ch1", 2, 7, null, 123,
			450);
		Enchantment spell = new Enchantment("Mending", 3, "ch1", 3, 9, null,
			124, 450);
		spell.merge(e1);
		System.out.println(spell);
	}

	@Test
	void testEquality() throws IOException {
		Enchantment e1 = new Enchantment("Efficiency", 3, "ch1", 2, 8, null,
			171, 1161);
		Enchantment e2 = new Enchantment("Efficiency", 5, "ch2", 6, 1, null,
			171, 1173);
		e1.merge(e2);
		System.out.println(e1);

		Enchantment e3 = new Enchantment("Efficiency", 5, "ch2", 6, 1, null,
			171, 1167);
		e1.merge(e3);
		System.out.println(e1);

		assert (e1.equals(e2));
	}

}
