package oleg.podolyan.ammodpsu.domain.lex;

public enum  ClothesType {

	UNIFORM("Обмундирування"), FOOTWEAR("Взуття"), UNDERWEAR("Білизна"), AMMUNITION("Спорядження"), OTHER("");

	private final String value;

	ClothesType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String [] values = {"Обмундирування", "Взуття", "Білизна", "Спорядження"};

	public static ClothesType of(String value){
		switch (value) {
			case "Обмундирування" : return UNIFORM;
			case "Взуття" : return FOOTWEAR;
			case "Білизна" : return UNDERWEAR;
			case "Спорядження" : return AMMUNITION;
			default: return OTHER;
		}
	}
}
