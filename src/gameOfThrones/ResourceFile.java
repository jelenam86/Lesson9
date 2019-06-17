package gameOfThrones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.curiousworks.lesson9.FileHelper;

public class ResourceFile {

	private static List<String> getListAboutCharactersFromTheFile() {
		List<String> data = Arrays.asList(FileHelper.loadMetaData().split("\n"));
		List<String> characters = new ArrayList<String>();
		for (int i = 1; i < data.size(); i++) {
			characters.add(data.get(i));
		}
		return characters;
	}

	public static List<Character> makeCharactersFromTheFile() {
		List<Character> characters = new ArrayList<Character>();
		List<String> dataForThisCharacter;
		for (String oneOfThem : getListAboutCharactersFromTheFile()) {
			dataForThisCharacter = Arrays.asList(getDataForThisCharacterFromTheMeta(oneOfThem).split(", "));
			Character character = new Character(dataForThisCharacter.get(0));
			character.setAllegiance(dataForThisCharacter.get(1));
			character.setMessagesFile(dataForThisCharacter.get(2));
			Allegiance.addAllAllegiance(dataForThisCharacter.get(1).split("\\s+")[1]);
			characters.add(character);
		}
		return characters;
	}

	public static String getDataForThisCharacterFromTheMeta(String characterName) {
		String line = "There is no such character in got_meta_data.txt.";
		ListIterator<String> iteratorMetaData = getListAboutCharactersFromTheFile().listIterator();
		while (iteratorMetaData.hasNext()) {
			String lineFromTheMeta = iteratorMetaData.next();
			if (lineFromTheMeta.contains(characterName))
				line = lineFromTheMeta;
		}
		return line;
	}

	/*
	 * Permanent - add it to file got_meta_data.txt if it doesn't already exist and
	 * create message file if character is completely new
	 */
	public static void makeCharacterPermanent(String characterName, String allegiance, String messagesFile) {
		if (allegiance.contains(" "))
			allegiance = allegiance.substring("House ".length());
		if (!getDataForThisCharacterFromTheMeta(characterName)
				.equals("There is no such character in got_meta_data.txt.")) {
			System.err.println("Character " + characterName + " already exist as permanent.");
			return;
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("resource/got_meta_data.txt", true));
			if (new File("resource/message_logs/" + messagesFile).isFile()) {
				String file = "messages" + messagesFile.substring("message".length());
				File f1 = new File("resource/message_logs/" + messagesFile);
				File f2 = new File("resource/message_logs/" + file);
				f1.renameTo(f2);
				writer.write(characterName + ", House " + allegiance + ", " + file + "\n");
			} else {
				writer.write(characterName + ", House " + allegiance + ", " + messagesFile + "\n");
				BufferedWriter writerFile = new BufferedWriter(new FileWriter("resource/message_logs/" + messagesFile));
				writerFile.write(characterName.split("\\s+")[0].toUpperCase() + "\n");
				writerFile.close();
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Exception occoured" + e);
		}
	}
}