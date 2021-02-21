package com.cda.sujets.suivi.serveur;

public enum HttpStatus {
	TOUT_VA_BIEN(200,"ok"),
	PROBLEME_TECHNIQUE(500,"erreur serveur"),
	FICHIER_INTROUVABLE(404,"ce fichier n'existe pas"),
	REQUETE_INCORRECTE(400,"mauvaise requete");
	
	private final int code;
	private final String description;

	private HttpStatus(int c, String d) {
		this.code = c;
		this.description = d;
	}
	
	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
