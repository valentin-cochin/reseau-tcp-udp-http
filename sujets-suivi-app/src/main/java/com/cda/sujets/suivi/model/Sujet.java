package com.cda.sujets.suivi.model;

import java.time.LocalDate;

public class Sujet {
	
	private int id;
	private LocalDate dateDecouverte;
	private SujetEtat etat;
	private String nom;

	public LocalDate getDateDecouverte() {
		return this.dateDecouverte;
	}

	public SujetEtat getEtat() {
		return this.etat;
	}

	public Sujet setDateDecouverte(LocalDate dateDecouverte) {
		this.dateDecouverte = dateDecouverte;
		return this;
	}

	public Sujet setEtat(SujetEtat etat) {
		this.etat = etat;
		return this;
	}

	public String getNom() {
		return nom;
	}

	public Sujet setNom(String nom) {
		this.nom = nom;
		return this;
	}

	public int getId() {
		return id;
	}

	public Sujet setId(int id) {
		this.id = id;
		return this;
	}

	@Override
	public String toString() {
		return "Sujet [id=" +  String.format("|%10d|", id) 
				+ ", dateDecouverte=" + String.format("|%10s|", dateDecouverte.toString())  
				+ ", etat=" + String.format("|%25s|", etat)  
				+ ", nom=" + String.format("|%20s|", nom.subSequence(0, Math.min(nom.length(), 20))) + "]";
	}
	
}
