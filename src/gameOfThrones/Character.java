package gameOfThrones;

import java.util.ArrayList;
import java.util.List;

public class Character implements Comparable<Character> {

	private String characterName;
	private Allegiance allegiance = new Allegiance();
	private MessagesFile messagesFile = new MessagesFile();
	private static List<Character> allCharacters = new ArrayList<Character>();

	public Character(String characterName) {
		this.characterName = characterName;
		allCharacters.add(this);
	}

	public Character(String characterName, String allegiance) {
		this.characterName = characterName;
		String firstName = characterName.split("\\s+")[0];
		this.allegiance.setAllegiance("House " + allegiance);
		if (!this.messagesFile.checkDoesAppropriateFileAlreadyExist(firstName))
			this.messagesFile.createMessagesFile(firstName.toUpperCase());
		allCharacters.add(this);
		Allegiance.addAllAllegiance(allegiance);
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getAllegiance() {
		return allegiance.getAllegiance();
	}

	public void setAllegiance(String allegiance) {
		this.allegiance.setAllegiance(allegiance);
	}

	public String getMessagesFile() {
		return messagesFile.getMessagesFile();
	}

	public void setMessagesFile(String messagesFile) {
		this.messagesFile.setMessagesFile(messagesFile);
	}

	public static List<Character> getAllCharacters() {
		return allCharacters;
	}

	public static Character findCharacterByName(String characterName) {
		Character characterFound = null;
		for (Character character : getAllCharacters()) {
			if (character.getCharacterName().equals(characterName)) {
				characterFound = character;
			}
		}
		return characterFound;
	}

	public void printSentMessages() {
		this.messagesFile.printSentMessages();
	}

	public static void printRecievedMessages(Character character) {
		MessagesFile.printRecievedMessages(character);
	}

	public static void printAllMessages(Character character) {
		character.printSentMessages();
		Character.printRecievedMessages(character);
	}

	public int getNumberOfSentMessages() {
		return this.messagesFile.getNumberOfSentMessages();
	}

	public static int getNumberOfRecievedMessages(Character character) {
		return MessagesFile.getNumberOfRecievedMessages(character);
	}

	public void addMessage(String message) {
		this.messagesFile.addMessageToTheMessagesFile(message);
	}

	public boolean isDispositionPositive() {
		if (this.messagesFile.countNumberOfEmoticonsByFile("happy") > this.messagesFile
				.countNumberOfEmoticonsByFile("sad"))
			return true;
		return false;
	}

	public static Character characterWhoHasTheMostTypeOfDisposition(String typeOfDisposition) {
		Character character = getAllCharacters().get(0);
		for (int i = 0; i < getAllCharacters().size(); i++) {
			if (character.compareTo(getAllCharacters().get(i), typeOfDisposition) < 0)
				character = getAllCharacters().get(i);
		}
		return character;
	}

	public static void printCharacterWhoHasTheMostTypeDisposition(String typeOfDisposition) {
		System.out.println("Character who has the most " + typeOfDisposition + " disposition is: "
				+ Character.characterWhoHasTheMostTypeOfDisposition(typeOfDisposition).getCharacterName());

	}

	/**
	 * 
	 * @param c1
	 * @param c2
	 * @return true only if c1 loves more c2 then vice versa
	 */
	public static boolean lovingCouple(Character c1, Character c2) {
		if (c1.compareTo(c2) > 0)
			return true;
		return false;
	}

	/**
	 * compare only "loving"
	 */
	@Override
	public int compareTo(Character character) {
		if (this.messagesFile.countNumberOfEmoticonsByMessage("loving", character) > character.messagesFile
				.countNumberOfEmoticonsByMessage("loving", this))
			return 1;
		else if (this.messagesFile.countNumberOfEmoticonsByMessage("loving", character) > character.messagesFile
				.countNumberOfEmoticonsByMessage("loving", this))
			return 0;
		else
			return -1;
	}

	// To compare disposition with other character
	public int compareTo(Character character, String typeOfDisposition) {
		if (this.messagesFile.countNumberOfEmoticonsByFile(typeOfDisposition) > character.messagesFile
				.countNumberOfEmoticonsByFile(typeOfDisposition))
			return 1;
		else if (this.messagesFile.countNumberOfEmoticonsByFile(typeOfDisposition) == character.messagesFile
				.countNumberOfEmoticonsByFile(typeOfDisposition))
			return 0;
		else
			return -1;
	}

	public static String getStatisticMessagesInformation() {
		String numberOfMessagesThatEachCharacterHasSent = "";
		int numberOfAllSentMessages = 0;
		for (Character c : getAllCharacters()) {
			numberOfAllSentMessages += c.getNumberOfSentMessages();
			numberOfMessagesThatEachCharacterHasSent += c.getCharacterName() + " has sent "
					+ c.getNumberOfSentMessages() + " messages.\n";
			if (getAllCharacters().indexOf(c) == getAllCharacters().size() - 1)
				numberOfMessagesThatEachCharacterHasSent += "\nSum of all sent messages is: " + numberOfAllSentMessages;
		}
		return numberOfMessagesThatEachCharacterHasSent;
	}
}