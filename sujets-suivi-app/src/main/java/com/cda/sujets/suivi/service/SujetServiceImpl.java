package com.cda.sujets.suivi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cda.sujets.suivi.dao.ISujetDao;
import com.cda.sujets.suivi.dao.sql.SujetDaoImpl;
import com.cda.sujets.suivi.exception.SujetExisteDejaException;
import com.cda.sujets.suivi.exception.SujetIntrouvableException;
import com.cda.sujets.suivi.model.Sujet;
import com.cda.sujets.suivi.model.SujetEtat;

public class SujetServiceImpl implements ISujetService {

	private ISujetDao sujetDao;

	public SujetServiceImpl() {
		this.sujetDao = new SujetDaoImpl();
	}

	public Sujet ajouter(String sujetNom) throws SujetExisteDejaException {
		Optional<Sujet> sujetOpt = this.sujetDao.rechercherParNom(sujetNom);
		if (sujetOpt.isPresent()) {
			throw new SujetExisteDejaException();
		}
		return this.sujetDao.save(
				new Sujet()
				.setDateDecouverte(LocalDate.now())
				.setNom(sujetNom)
				.setEtat(SujetEtat.A_DECOUVRIR));
	}

	public Sujet chercherSujetParNom(String sujetNom) throws SujetIntrouvableException {
		Optional<Sujet> res = this.sujetDao.rechercherParNom(sujetNom);
		return res.orElseThrow(SujetIntrouvableException::new);
	}

	public void supprimerTousLesSujets() {
		this.sujetDao.removeAll();
	}

	public List<Sujet> chercherTousLesSujets() {
		return this.sujetDao.getAll().stream()
		.sorted((s1,s2) -> Integer.compare(s1.getEtat().getOrdreAffichage(), s2.getEtat().getOrdreAffichage()))
		.collect(Collectors.toList());
	}

	@Override
	public void modifierEtat(int id, SujetEtat nouvelEtat) throws SujetIntrouvableException {
		Optional<Sujet> res = this.sujetDao.findById(id);
		if(res.isEmpty()) {
			throw new SujetIntrouvableException();
		}
		this.sujetDao.update(res.get().setEtat(nouvelEtat));
	}

	@Override
	public Sujet chercherSujetParId(int id) throws SujetIntrouvableException {
		Optional<Sujet> res = this.sujetDao.findById(id);
		return res.orElseThrow(SujetIntrouvableException::new);
	}

	@Override
	public void modifierDate(int id, LocalDate nouvelleDate) throws SujetIntrouvableException {
		Optional<Sujet> res = this.sujetDao.findById(id);
		if(res.isEmpty()) {
			throw new SujetIntrouvableException();
		}
		this.sujetDao.update(res.get().setDateDecouverte(nouvelleDate));		
	}

}
