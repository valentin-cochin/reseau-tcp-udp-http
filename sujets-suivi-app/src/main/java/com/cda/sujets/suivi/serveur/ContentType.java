package com.cda.sujets.suivi.serveur;

public enum ContentType {
	TEXT_HTML("text/html"),
	TEXT_CSS("text/css");
	
	private final String value;
	
	private ContentType(String v) {
		this.value = v;
	}

	public String getValue() {
		return value;
	}
	
}
