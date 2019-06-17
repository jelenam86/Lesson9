package assignmentTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import gameOfThrones.ResourceFile;
import gameOfThrones.Character;

class ResourceFileTest {

	@Test
	void testMakeCharactersFromTheFile() {
		List<Character> characters = ResourceFile.makeCharactersFromTheFile();

		assertEquals(characters.get(0).getCharacterName(), "Daenerys Stormborn");
		assertEquals(characters.get(0).getAllegiance(), "House Targaryen");
		assertEquals(characters.get(0).getMessagesFile(), "messages82387561293.txt");

		assertEquals(characters.get(1).getCharacterName(), "Jon Snow");
		assertEquals(characters.get(1).getAllegiance(), "House Stark");
		assertEquals(characters.get(1).getMessagesFile(), "messages2094721612573.txt");

		assertEquals(characters.get(2).getCharacterName(), "Tyrion Lannister");
		assertEquals(characters.get(2).getAllegiance(), "House Lannister");
		assertEquals(characters.get(2).getMessagesFile(), "messages9287658288370.txt");

		assertEquals(characters.get(3).getCharacterName(), "Cersei Lannister");
		assertEquals(characters.get(3).getAllegiance(), "House Lannister");
		assertEquals(characters.get(3).getMessagesFile(), "messages22274950573636.txt");

		assertEquals(4, characters.size());
	}

	@Test
	void testGetDataForThisCharacterFromTheMeta() {
		assertEquals("Daenerys Stormborn, House Targaryen, messages82387561293.txt",
				ResourceFile.getDataForThisCharacterFromTheMeta("Daenerys Stormborn"));
		assertEquals("Jon Snow, House Stark, messages2094721612573.txt",
				ResourceFile.getDataForThisCharacterFromTheMeta("Jon Snow"));
		assertEquals("Tyrion Lannister, House Lannister, messages9287658288370.txt",
				ResourceFile.getDataForThisCharacterFromTheMeta("Tyrion Lannister"));
		assertEquals("Cersei Lannister, House Lannister, messages22274950573636.txt",
				ResourceFile.getDataForThisCharacterFromTheMeta("Cersei Lannister"));
	}
}