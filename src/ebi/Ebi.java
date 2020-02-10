package ebi;

import javax.swing.JOptionPane;

public class Ebi {

	public static void main(String[] args) {
		String chestID = JOptionPane.showInputDialog("Enter chest ID to associate with this collection of books:");
		System.out.println(chestID);
	}
}
