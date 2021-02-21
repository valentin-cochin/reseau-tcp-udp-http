package com.cda.sujets.suivi.vue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.cda.sujets.suivi.service.ISujetService;
import com.cda.sujets.suivi.service.SujetServiceImpl;

public class SujetsListe implements VueDynamique {

	private ISujetService sujetService = new SujetServiceImpl();

	@Override
	public InputStream getContenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html lang=\"en\">");
		sb.append("<head>");
		sb.append("    <meta charset=\"UTF-8\">");
		sb.append("    <title>sujets listing</title>");
		sb.append("    <style>");
		sb.append("    </style>");
		sb.append("    <link rel=\"stylesheet\" href=\"style.css\">");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("    <table>");
		sb.append("        <caption>liste des sujets</caption>");
		sb.append("        <thead>");
		sb.append("            <tr>");
		sb.append("                <th>id</th>");
		sb.append("                <th>titre</th>");
		sb.append("                <th>etat</th>");
		sb.append("                <th>date</th>");
		sb.append("            </tr>");
		sb.append("        </thead>");
		sb.append("        <tbody>");
		
		this.sujetService.chercherTousLesSujets().forEach(s->{
			sb.append("            <tr>");
			sb.append("                <td>"+s.getId()+"</td>");
			sb.append("                <td>"+s.getNom()+"</td>");
			sb.append("                <td>"+s.getEtat()+"</td>");
			sb.append("                <td>"+s.getDateDecouverte()+"</td>");
			sb.append("            </tr>");
		});
		
		sb.append("        </tbody>");
		sb.append("    </table>");
		sb.append("</body>");
		sb.append("");
		sb.append("</html>");
		return new ByteArrayInputStream(sb.toString().getBytes());
	}

}
