package gameOfThrones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.curiousworks.lesson9.FileHelper;

public class MessagesFile {

	private String messagesFile;
	private static final String[] positiveDispositionEmoticons = { "😄", "🙂", "😊", "😍" };
	private static final String[] negativeDispositionEmoticons = { "😭", "😢", "😞", "👿" };
	private static final String[] lovingDispositionEmoticons = { "😍", "😘" };

	public String getMessagesFile() {
		return messagesFile;
	}

	public void setMessagesFile(String messagesFile) {
		this.messagesFile = messagesFile;
	}

	private static List<String> getAllFilenames() {
		List<String> names = new ArrayList<String>();
		File[] files = new File("resource/message_logs/").listFiles();
		for (File file : files) {
			if (file.isFile())
				names.add(file.getName());
		}
		return names;
	}

	public boolean checkDoesAppropriateFileAlreadyExist(String charactersFirstName) {
		for (String line : getAllFilenames()) {
			for (String name : FileHelper.loadMessages(line)) {
				try {
					String beginningOfTheLine = name.substring(0, charactersFirstName.length());
					if (beginningOfTheLine.equals(charactersFirstName.toUpperCase())) {
						this.messagesFile = line;
						return true;
					}
				} catch (StringIndexOutOfBoundsException e) {
				}
			}
		}
		return false;
	}

	public void printSentMessages() {
		List<String> messages = FileHelper.loadMessages(getMessagesFile());
		if (messages.size() > 1) {
			messages.remove(0);
			for (String message : messages) {
				if (!(message.isEmpty() || message.trim().equals("") || message.trim().equals("\n"))) {
					System.out.println("Message to " + message);
				}
			}
		} else
			System.out.println("No sent messages...");
	}

	public static void printRecievedMessages(Character character) {
		for (Character c : Character.getAllCharacters()) {
			for (String line : FileHelper.loadMessages(c.getMessagesFile())) {
				try {
					String beginningOfTheLine = line.substring(0,
							character.getCharacterName().split("\\s+")[0].length());
					if (beginningOfTheLine.equals(character.getCharacterName().split("\\s+")[0])) {
						System.out.println("Message from " + c.getCharacterName() + ", "
								+ line.substring(character.getCharacterName().length() + 2));
					}
				} catch (StringIndexOutOfBoundsException e) {
					continue;
				}
			}
		}
	}

	public int getNumberOfSentMessages() {
		int numberOfSentMessages = 0;
		List<String> messages = FileHelper.loadMessages(getMessagesFile());
		messages.remove(0);
		for (String message : messages) {
			if (!(message.isEmpty() || message.trim().equals("") || message.trim().equals("\n")))
				numberOfSentMessages++;
		}
		return numberOfSentMessages;
	}

	public static int getNumberOfRecievedMessages(Character character) {
		int numberOfRecievedMessages = 0;
		for (Character c : Character.getAllCharacters()) {
			for (String line : FileHelper.loadMessages(c.getMessagesFile())) {
				try {
					String beginningOfTheLine = line.substring(0,
							character.getCharacterName().split("\\s+")[0].length());
					if (beginningOfTheLine.equals(character.getCharacterName().split("\\s+")[0])) {
						numberOfRecievedMessages++;
					}
				} catch (StringIndexOutOfBoundsException e) {
					continue;
				}
			}
		}
		return numberOfRecievedMessages;
	}

	public void createMessagesFile(String characterName) {
		try {
			File directorium = new File("resource/message_logs/");
			File temp = File.createTempFile("message", ".txt", directorium);
			BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
			writer.write(characterName + "\n");
			writer.close();
			setMessagesFile(temp.getName());
			temp.deleteOnExit();
		} catch (IOException e) {
			System.out.println("Exception occurred" + e);
		}
	}

	public void addMessageToTheMessagesFile(String message) {
		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter("resource/message_logs/" + this.messagesFile, true));
			writer.write(message + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("Exception occoured" + e);
		}
	}

	private static String[] chooseEmoticons(String typeOfDisposition) {
		ArrayList<String> toBeArray = new ArrayList<String>();
		if (typeOfDisposition.equals("happy"))
			toBeArray.addAll(Arrays.asList(positiveDispositionEmoticons));
		else if (typeOfDisposition.equals("sad"))
			toBeArray.addAll(Arrays.asList(negativeDispositionEmoticons));
		else
			toBeArray.addAll(Arrays.asList(lovingDispositionEmoticons));
		return toBeArray.toArray(new String[toBeArray.size()]);
	}

	/**
	 * 
	 * @param typeOfEmoticons - enter "happy", "sad" or "loving"
	 * @return number of emojis in file
	 */
	public int countNumberOfEmoticonsByFile(String typeOfEmoticons) {
		int number = 0;
		String[] array = chooseEmoticons(typeOfEmoticons);
		for (String message : FileHelper.loadMessages(getMessagesFile())) {
			for (String emoji : array) {
				while (message.contains(emoji)) {
					number++;
					int index = message.indexOf(emoji);
					message = message.substring(0, index) + message.substring(index + emoji.length());
				}
			}
		}
		return number;
	}

	// Message to the character; count sad, happy or loving emoticons sent to that
	// character
	public int countNumberOfEmoticonsByMessage(String typeOfEmoticons, Character character) {
		int number = 0;
		String[] array = chooseEmoticons(typeOfEmoticons);
		for (String line : FileHelper.loadMessages(getMessagesFile())) {
			try {
				String beginningOfTheLine = line.substring(0, character.getCharacterName().length());
				if (beginningOfTheLine.equals(character.getCharacterName())) {
					for (String emoji : array) {
						while (line.contains(emoji)) {
							number++;
							int index = line.indexOf(emoji);
							line = line.substring(0, index) + line.substring(index + emoji.length());
						}
					}
				}
			} catch (StringIndexOutOfBoundsException e) {
				continue;
			}
		}
		return number;
	}
}