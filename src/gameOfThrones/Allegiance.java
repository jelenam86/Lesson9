package gameOfThrones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Allegiance {

	private String allegiance;
	private static List<String> allAllegiance = new ArrayList<String>();

	public String getAllegiance() {
		return allegiance;
	}

	public void setAllegiance(String house) {
		this.allegiance = house;
	}

	public static List<String> getAllAllegiance() {
		return allAllegiance;
	}

	public static void addAllAllegiance(String allegiance) {
		if (!allAllegiance.contains(allegiance)) {
			allAllegiance.add(allegiance);
			Collections.sort(allAllegiance);
		}
	}
}