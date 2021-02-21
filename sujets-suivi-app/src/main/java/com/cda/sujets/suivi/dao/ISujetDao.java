package com.cda.sujets.suivi.dao;

import java.util.Optional;

import com.cda.sujets.suivi.model.Sujet;

public interface ISujetDao extends IDao<Sujet> {
	Optional<Sujet> rechercherParNom(String nom);
}
