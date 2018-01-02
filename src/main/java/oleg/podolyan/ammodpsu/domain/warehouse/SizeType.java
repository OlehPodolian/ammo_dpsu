package oleg.podolyan.ammodpsu.domain.warehouse;

public enum SizeType {

	BODY("одяг"), COLLAR(""), HEAD("головний убір"), FOOT("взуття"), OTHER("");

	private final String value;

	SizeType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String [] values = {"одяг", "взуття", "головний убір", "комір"};

	public static SizeType of(String value){
		switch (value) {
			case "одяг": return BODY;
			case "взуття": return FOOT;
			case "головний убір": return HEAD;
			case "комір" : return COLLAR;
			default: return OTHER;
		}
	}
}
