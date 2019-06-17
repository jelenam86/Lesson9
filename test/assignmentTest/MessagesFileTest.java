package assignmentTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import gameOfThrones.Character;
import gameOfThrones.MessagesFile;

class MessagesFileTest {

	@Test
	void testCheckDoesAppropriateFileAlreadyExist() {
		Character ned = new Character("Ned Stark", "Stark");

		MessagesFile mf = new MessagesFile();
		assertTrue(mf.checkDoesAppropriateFileAlreadyExist("Jon Snow".split("\\s+")[0]));
		assertTrue(mf.checkDoesAppropriateFileAlreadyExist(ned.getCharacterName().split("\\s+")[0]));
		assertTrue(mf.checkDoesAppropriateFileAlreadyExist("Dany"));
		assertTrue(mf.checkDoesAppropriateFileAlreadyExist("Tyrion Lannister".split("\\s+")[0]));

		assertFalse(mf.checkDoesAppropriateFileAlreadyExist("Varys"));
	}
}