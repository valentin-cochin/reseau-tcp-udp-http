package com.cda.sujets.suivi.service;

import java.time.LocalDate;
import java.util.List;

import com.cda.sujets.suivi.exception.SujetExisteDejaException;
import com.cda.sujets.suivi.exception.SujetIntrouvableException;
import com.cda.sujets.suivi.model.Sujet;
import com.cda.sujets.suivi.model.SujetEtat;

public interface ISujetService {

	Sujet ajouter(String sujetNom) throws SujetExisteDejaException;

	Sujet chercherSujetParNom(String sujetNom) throws SujetIntrouvableException;

	void supprimerTousLesSujets();

	List<Sujet> chercherTousLesSujets();

	void modifierEtat(int id, SujetEtat vuMaisARevoir) throws SujetIntrouvableException;

	Sujet chercherSujetParId(int id) throws SujetIntrouvableException;

	void modifierDate(int id, LocalDate parse) throws SujetIntrouvableException;

}
