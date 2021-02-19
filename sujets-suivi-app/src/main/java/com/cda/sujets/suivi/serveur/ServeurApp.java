package com.cda.sujets.suivi.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServeurApp {

	public static void main(String[] args) throws IOException {
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
