package com.cda.sujets.suivi.exception;

import java.sql.SQLException;

public class ErreurTechnique extends RuntimeException {

	public ErreurTechnique(SQLException e) {
		super(e);
	}

	private static final long serialVersionUID = 1L;

}
