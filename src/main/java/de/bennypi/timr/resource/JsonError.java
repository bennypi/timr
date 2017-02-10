package de.bennypi.timr.resource;

public class JsonError {

	private String type;
	private String message;

	public JsonError(String type, String message) {
		this.type = type;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

}
