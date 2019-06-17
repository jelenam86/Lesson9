package gameOfThrones.differentMain;

import java.util.List;

import javax.swing.JOptionPane;

import gameOfThrones.Character;
import gameOfThrones.ResourceFile;

/*
 * ***Add new characters to the realm.***
 * For each of them, the app makes temporarily messages text file, which will be deleted when JVM is done.
 * None of them will be added to the got_meta_data.txt
 * However, adding is possible and in that case, messages text files won't be deleted.
 * Therefore, adding is toggled.
 */

public class Main3 {

	public static void main(String[] args) {

		List<Character> characters = ResourceFile.makeCharactersFromTheFile();
		characters.add(new Character("Ned Stark", "Stark"));
		characters.add(new Character("Brienne of Tarth", "Tarth"));
		characters.add(new Character("Olenna Tyrell", "Tyrell"));
		characters.add(new Character("Samwell Tarly", "Tarly"));
		characters.add(new Character("Petyr Baelish", "Bealish"));
		characters.add(new Character("Arya Stark", "Stark"));

		System.out.println("Ned Strak: " + Character.findCharacterByName("Ned Stark").getMessagesFile());

		Character.printCharacterWhoHasTheMostTypeDisposition("happy");
		Character.printCharacterWhoHasTheMostTypeDisposition("sad");
		JOptionPane.showMessageDialog(null, "Statistic before:\n" + Character.getStatisticMessagesInformation());

		Character.findCharacterByName("Arya Stark")
				.addMessage("Jaqen H'ghar,\"A girl is Arya Stark of Winterfell, and I'm going home.😊\"");
		Character.findCharacterByName("Arya Stark").addMessage(
				"Sansa Stark,\"I was never going to be as good a lady as you. So I had to be something else. I never could have survived what you survived.😢😢😢\"");
		Character.findCharacterByName("Samwell Tarly")
				.addMessage("Tyrion Lannister, \"I don't believe you're mentioned...😄😄😄😄😄😄😄😄😄\"");
		Character.findCharacterByName("Olenna Tyrell").addMessage(
				"Cersei Lannister, \"I wonder if you're the worst person I've ever met.👿👿👿👿👿👿👿👿👿👿👿\"");
		Character.findCharacterByName("Petyr Baelish").addMessage(
				"Varys, \"Chaos isn’t a pit. Chaos is a ladder. Many who try to climb it fail, and never get to try again. The fall breaks them. And some are given a chance to climb, but refuse. They cling to the realm, or the gods, or love... illusions. Only the ladder is real. The climb is all there is. \"");

		Character.printCharacterWhoHasTheMostTypeDisposition("happy");
		Character.printCharacterWhoHasTheMostTypeDisposition("sad");

		Character.findCharacterByName("Olenna Tyrell").printSentMessages();
		System.out.println(Character.findCharacterByName("Olenna Tyrell")
				.compareTo(Character.findCharacterByName("Cersei Lannister"), "sad"));

		JOptionPane.showMessageDialog(null, "Statistic after:\n" + Character.getStatisticMessagesInformation());

//		Character arya = Character.findCharacterByName("Arya Stark");
//		ResourceFile.makeCharacterPermanent(arya.getCharacterName(), arya.getAllegiance(), arya.getMessagesFile());
//		ResourceFile.makeCharacterPermanent("Sansa Stark", "Stark", "sansaFile");
	}
}