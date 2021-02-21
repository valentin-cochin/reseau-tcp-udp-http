package com.cda.sujets.suivi.serveur;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServeurApp {

	private static final String SITE_ROOT = "mon-site";
	public static Path SITE_PATH;
	
	public static void main(String[] args) throws IOException {
		
		String userHomePath = System.getProperty("user.home");
		
		SITE_PATH = Paths.get(userHomePath+File.separator+SITE_ROOT);
		
		System.out.println(SITE_PATH);
		
		ExecutorService poolDeThread = Executors.newFixedThreadPool(10);
		try (ServerSocket ss = new ServerSocket(8080)) {

			while (true) {
				System.out.println("serveur : en attente de connexion ...");
				Socket sc = ss.accept();
				System.out.println("serveur : connexion établie !");
				poolDeThread.execute(new Auxiliaire(sc));
			}

		}

	}

}
