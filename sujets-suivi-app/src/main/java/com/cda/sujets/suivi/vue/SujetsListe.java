package com.cda.sujets.suivi.vue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.cda.sujets.suivi.serveur.ServeurApp;
import com.cda.sujets.suivi.service.ISujetService;
import com.cda.sujets.suivi.service.SujetServiceImpl;

public class SujetsListe implements VueDynamique {

	private ISujetService sujetService = new SujetServiceImpl();

	@Override
	public InputStream getContenu() {
		Template aTemplate = ServeurApp.VELOCITY_ENGINE.getTemplate("template/sujets-listing.html");
	    
		VelocityContext context = new VelocityContext();
		context.put("sujets", this.sujetService.chercherTousLesSujets());
		    
		StringWriter writer = new StringWriter();
		aTemplate.merge( context, writer );
		
		return new ByteArrayInputStream(writer.getBuffer().toString().getBytes());
	}

}
