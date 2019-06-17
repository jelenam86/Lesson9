package assignmentTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.curiousworks.lesson9.FileHelper;
import org.junit.jupiter.api.Test;

import gameOfThrones.CerseiLannister;
import gameOfThrones.Character;
import gameOfThrones.DaenerysStormborn;
import gameOfThrones.JonSnow;
import gameOfThrones.TyrionLannister;

class CharacterTest {

	@Test
	void testGetCharacterName() {
		Character one = new Character("Bran Stark", "Stark");
		Character two = new Character("Jaime Lannister", "Lannister");
		JonSnow jon = new JonSnow();
		DaenerysStormborn dany = new DaenerysStormborn();
		TyrionLannister tyrion = new TyrionLannister();
		CerseiLannister cersei = new CerseiLannister();

		assertEquals("Bran Stark", one.getCharacterName());
		assertEquals("Jaime Lannister", two.getCharacterName());
		assertEquals("Jon Snow", jon.getCharacterName());
		assertEquals("Daenerys Stormborn", dany.getCharacterName());
		assertEquals("Tyrion Lannister", tyrion.getCharacterName());
		assertEquals("Cersei Lannister", cersei.getCharacterName());
	}

	@Test
	void testGetAllegiance() {
		Character one = new Character("Bran Stark", "Stark");
		Character two = new Character("Jaime Lannister", "Lannister");
		JonSnow jon = new JonSnow();
		DaenerysStormborn dany = new DaenerysStormborn();
		TyrionLannister tyrion = new TyrionLannister();
		CerseiLannister cersei = new CerseiLannister();

		assertEquals("House Stark", one.getAllegiance());
		assertEquals("House Lannister", two.getAllegiance());
		assertEquals("House Stark", jon.getAllegiance());
		assertEquals("House Targaryen", dany.getAllegiance());
		assertEquals("House Lannister", tyrion.getAllegiance());
		assertEquals("House Lannister", cersei.getAllegiance());
	}

	@Test
	void testGetMessagesFile() {
		JonSnow jon = new JonSnow();
		DaenerysStormborn dany = new DaenerysStormborn();
		TyrionLannister tyrion = new TyrionLannister();
		CerseiLannister cersei = new CerseiLannister();

		assertEquals("messages2094721612573.txt", jon.getMessagesFile());
		assertEquals("messages82387561293.txt", dany.getMessagesFile());
		assertEquals("messages9287658288370.txt", tyrion.getMessagesFile());
		assertEquals("messages22274950573636.txt", cersei.getMessagesFile());
	}

	@Test
	void testGetAllCharacters() {
		Character one = new Character("Bran Stark", "Stark");
		Character two = new Character("Jaime Lannister", "Lannister");
		JonSnow jon = new JonSnow();
		DaenerysStormborn dany = new DaenerysStormborn();
		TyrionLannister tyrion = new TyrionLannister();
		CerseiLannister cersei = new CerseiLannister();

		List<Character> characters = new ArrayList<Character>();
		characters.add(one);
		characters.add(two);
		characters.add(jon);
		characters.add(dany);
		characters.add(tyrion);
		characters.add(cersei);
		assertEquals(characters, Character.getAllCharacters());
	}

	@Test
	void testFindCharacterByName() {
		Character one = new Character("Bran Stark", "Stark");
		Character two = new Character("Jaime Lannister", "Lannister");
		JonSnow jon = new JonSnow();
		DaenerysStormborn dany = new DaenerysStormborn();
		TyrionLannister tyrion = new TyrionLannister();
		CerseiLannister cersei = new CerseiLannister();

		assertEquals(one, Character.findCharacterByName("Bran Stark"));
		assertEquals(two, Character.findCharacterByName("Jaime Lannister"));
		assertEquals(jon, Character.findCharacterByName("Jon Snow"));
		assertEquals(dany, Character.findCharacterByName("Daenerys Stormborn"));
		assertEquals(tyrion, Character.findCharacterByName("Tyrion Lannister"));
		assertEquals(cersei, Character.findCharacterByName("Cersei Lannister"));
		assertEquals(null, Character.findCharacterByName("Varys"));
	}

	// test with exact number and with helper method
	// -1 of size for the first line (line with name)
	@Test
	void testGetNumberOfSentMessages() {
		Character one = new Character("Bran Stark", "Stark");
		Character two = new Character("Jaime Lannister", "Lannister");
		JonSnow jon = new JonSnow();
		DaenerysStormborn dany = new DaenerysStormborn();
		TyrionLannister tyrion = new TyrionLannister();
		CerseiLannister cersei = new CerseiLannister();

		assertEquals(0, one.getNumberOfSentMessages());
		assertEquals(FileHelper.loadMessages(two.getMessagesFile()).size() - 1, two.getNumberOfSentMessages());
		assertEquals(FileHelper.loadMessages(jon.getMessagesFile()).size() - 1, jon.getNumberOfSentMessages());
		assertEquals(FileHelper.loadMessages(dany.getMessagesFile()).size() - 1, dany.getNumberOfSentMessages());
		assertEquals(15, tyrion.getNumberOfSentMessages());
		assertEquals(10, cersei.getNumberOfSentMessages());
	}

	@Test
	static void testGetNumberOfRecievedMessages() {
		Character one = new Character("Bran Stark", "Stark");
		Character two = new Character("Jaime Lannister", "Lannister");
		JonSnow jon = new JonSnow();
		DaenerysStormborn dany = new DaenerysStormborn();
		TyrionLannister tyrion = new TyrionLannister();
		CerseiLannister cersei = new CerseiLannister();

		assertEquals(1, Character.getNumberOfRecievedMessages(one));
		assertEquals(2, Character.getNumberOfRecievedMessages(two));
		assertEquals(12, Character.getNumberOfRecievedMessages(jon));
		assertEquals(11, Character.getNumberOfRecievedMessages(dany));
		assertEquals(13, Character.getNumberOfRecievedMessages(tyrion));
		assertEquals(9, Character.getNumberOfRecievedMessages(cersei));
	}

	@Test
	void testIsDispositionPositive() {
		JonSnow jon = new JonSnow();
		DaenerysStormborn dany = new DaenerysStormborn();
		TyrionLannister tyrion = new TyrionLannister();
		CerseiLannister cersei = new CerseiLannister();

		assertTrue(tyrion.isDispositionPositive());
		assertTrue(dany.isDispositionPositive());

		assertFalse(jon.isDispositionPositive()); // equal number of happy and sad emojis
		assertFalse(cersei.isDispositionPositive());
	}

	@Test
	void testLovingCouple() {
		JonSnow jon = new JonSnow();
		DaenerysStormborn dany = new DaenerysStormborn();

		assertTrue(Character.lovingCouple(jon, dany));
		assertFalse(Character.lovingCouple(dany, jon));
	}
}
