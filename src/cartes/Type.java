package cartes;

public enum Type {
	FEU("rouge", "vert", "prioritaire"), ESSENCE("essence", "panne d'essence", "citerne"),
	CREVAISON("crevaison", "roue de secours", "increvable"), ACCIDENT("accident", "reparations", "as du volant");

	private String attaque;
	private String parade;
	private String botte;

	private Type(String attaque, String parade, String botte) {
		this.attaque = attaque;
		this.parade = parade;
		this.botte = botte;
	}

	public String getAttaque() {
		return attaque;
	}

	public String getParade() {
		return parade;
	}

	public String getBotte() {
		return botte;
	}

}
