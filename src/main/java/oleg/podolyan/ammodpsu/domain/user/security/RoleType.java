package oleg.podolyan.ammodpsu.domain.user.security;

public enum RoleType {

	ADMIN("ROLE_ADMIN"), MANAGER("ROLE_MANAGER"), USER("ROLE_USER");

	private final String value;

	RoleType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
