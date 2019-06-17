package gameOfThrones;

import java.util.Arrays;
import java.util.List;

public class DaenerysStormborn extends Character {

	private List<String> dataForThisCharacter = Arrays
			.asList(ResourceFile.getDataForThisCharacterFromTheMeta(getCharacterName()).split(", "));

	public DaenerysStormborn() {
		super("Daenerys Stormborn");
		setAllegiance(dataForThisCharacter.get(1));
		setMessagesFile(dataForThisCharacter.get(2));
		Allegiance.addAllAllegiance(dataForThisCharacter.get(1).split("\\s+")[1]);
	}
}