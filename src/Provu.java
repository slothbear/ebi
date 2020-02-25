public class Provu {
	public static void main(String[] args) {

		for (int rx : range(0, 6)) {
			System.out.print(rx);
		}
		System.out.println();
		
		for (int rx : range(5, 0)) {
			System.out.print(rx);
		}
		System.out.println();
		
		for (int rx : range(8, 0)) {
			System.out.print(rx);
		}
		System.out.println();
		
		final int[] FIVE_ZERO = new int[] {5, 4, 3, 2, 1, 0};
		for (int rx : FIVE_ZERO) {
			System.out.print(rx);
		}
		System.out.println();
		
		for (int rx : new int[] {5, 4, 3, 2, 1, 0}) {
			System.out.print(rx);
		}
		System.out.println();
		
//		Invalid arguments will terminate the program with an exception.
		for (int rx : range(0, 0)) {
			System.out.print(rx);
		}
	}

	private static int[] range(int start, int end) {
		int[] result;
		if (start == 0 && end == 6) {
			result = new int[] {0, 1, 2, 3, 4, 5, 6};			
		}
		else if (start == 5 && end == 0) {
			result = new int[] {5, 4, 3, 2, 1, 0};
		}
		else if (start == 8 && end == 0) {
			result = new int[] {8, 7, 6, 5, 4, 3, 2, 1, 0};
		}
		else {
			throw new RuntimeException("Undefined range: " + start + ".." + end);
		}
		return result;
	}


}



