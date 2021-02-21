package com.cda.sujets.suivi.serveur;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Auxiliaire implements Runnable {
	private final Socket socketClient;

	public Auxiliaire(Socket sc) throws IOException {
		this.socketClient = sc;
	}

	@Override
	public void run() {
		OutputStream os = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = socketClient.getInputStream();
			os = socketClient.getOutputStream();
			br = new BufferedReader(new InputStreamReader(is));

			String requeteLigne = br.readLine();
			if(requeteLigne == null || requeteLigne.isEmpty()) {
				this.repondre(
						os,
						HttpStatus.REQUETE_INCORRECTE,
						new ByteArrayInputStream(HttpStatus.REQUETE_INCORRECTE.getDescription().getBytes())
						);
			} else {
				System.out.println("premiere ligne : "+requeteLigne);

				String fichierCible = requeteLigne.split(" ")[1];
				
				fichierCible = "/".equals(fichierCible)?"/index.html":File.separator+fichierCible;
				System.out.println("> fichierCible : "+fichierCible);
				Path fichierCiblePath = Paths.get(ServeurApp.SITE_PATH.toString()+fichierCible);

				while ((requeteLigne = br.readLine()) != null) {
					System.out.println(requeteLigne);
					if (requeteLigne.isEmpty()) {
						break;
					}
				}

				this.repondre(os,
						HttpStatus.TOUT_VA_BIEN,
						Files.newInputStream(fichierCiblePath));
			}


		} catch(Exception e) {
			e.printStackTrace();
			if(os != null) {
				try {
					this.repondre(os, 
							HttpStatus.PROBLEME_TECHNIQUE, 
							new ByteArrayInputStream("ERROR".getBytes()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void repondre(OutputStream os, HttpStatus reponseStatus, InputStream reponseInput) throws IOException {
		os.write(("HTTP/1.1 "+reponseStatus.getCode()+" "+reponseStatus.getDescription()+"\n").getBytes());
		os.write(("Date: "+LocalDateTime.now()+"\n").getBytes());
		os.write(("Server: CDA SERVER"+"\n").getBytes());
		os.write(("Content-Type: text/html"+"\n").getBytes());
		os.write(("\n").getBytes());
		byte[] buffer = new byte[100];
		int nbOctetsLus;
		while((nbOctetsLus = reponseInput.read(buffer)) != -1) {
			os.write(buffer, 0, nbOctetsLus);
		}
	}

}
