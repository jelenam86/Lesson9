package gameOfThrones.differentMain.application;

enum ImagePath {
	danny("Daenerys Stormborn", "resource/images/danny.jpg"), jon("Jon Snow", "resource/images/jon.jpg"),
	tyrion("Tyrion Lannister", "resource/images/tyrion.jpeg"), cersei("Cersei Lannister", "resource/images/cersei.png");

	private final String name;
	private final String path;

	private ImagePath(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public static String getPath(String name) {
		String path = "";
		for(ImagePath p : ImagePath.values()) {
			if(name.equals(p.name))
				path = p.path;
		}
		return path;
	}
}