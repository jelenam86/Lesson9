package gameOfThrones.differentMain;

import javax.swing.JOptionPane;

import gameOfThrones.Character;
import gameOfThrones.ResourceFile;

/*
 * Make characters directly from the got_meta_data.txt
 */

public class Main1 {

	public static void main(String[] args) {

		ResourceFile.makeCharactersFromTheFile();
		Character danny = Character.findCharacterByName("Daenerys Stormborn");

		Character.printAllMessages(danny);

		JOptionPane.showMessageDialog(null, Character.getStatisticMessagesInformation());

		System.out.println();
		Character.printCharacterWhoHasTheMostTypeDisposition("happy");
		Character.printCharacterWhoHasTheMostTypeDisposition("sad");

		System.out.println();
		System.out.println("Tyron has more positive dispositon: "
				+ Character.findCharacterByName("Tyrion Lannister").isDispositionPositive());

		System.out.println();
		System.out.println(
				"Jon loves Daenerys more: " + Character.lovingCouple(Character.findCharacterByName("Jon Snow"), danny));
	}
}