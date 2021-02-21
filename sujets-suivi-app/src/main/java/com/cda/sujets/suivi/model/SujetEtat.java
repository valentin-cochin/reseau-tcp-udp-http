package com.cda.sujets.suivi.model;

public enum SujetEtat {
	A_DECOUVRIR(1),
	VU_MAIS_A_REVOIR(0),
	COMPRIS_A_APPROFONDIR(2);
	
	private final int ordreAffichage;
	
	SujetEtat(int i) {
		this.ordreAffichage = i;
	}

	public static SujetEtat valueOf(int id) {
		return SujetEtat.values()[id-1];
	}

	public int getOrdreAffichage() {
		return ordreAffichage;
	}

}
