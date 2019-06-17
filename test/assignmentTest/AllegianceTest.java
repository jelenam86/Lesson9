package assignmentTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import gameOfThrones.Allegiance;
import gameOfThrones.CerseiLannister;
import gameOfThrones.Character;
import gameOfThrones.DaenerysStormborn;
import gameOfThrones.JonSnow;
import gameOfThrones.TyrionLannister;

class AllegianceTest {

	@Test
	void testGetAllAllegiance() {
		Character one = new Character("Bran Stark", "Stark");
		Character two = new Character("Jaime Lannister", "Lannister");
		JonSnow jon = new JonSnow();
		DaenerysStormborn danny = new DaenerysStormborn();
		TyrionLannister tyrion = new TyrionLannister();
		CerseiLannister cersei = new CerseiLannister();

		List<String> houses = new ArrayList<String>();
		houses.add("Stark");
		houses.add("Lannister");
		houses.add("Targaryen");
		Collections.sort(houses);

		assertEquals(houses, Allegiance.getAllAllegiance());
	}
}