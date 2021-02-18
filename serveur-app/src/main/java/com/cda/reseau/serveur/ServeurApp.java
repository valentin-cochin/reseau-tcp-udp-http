package com.cda.reseau.serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurApp {

	public static void main(String[] args) throws IOException {
		try (ServerSocket ss = new ServerSocket(8080)) {

			while (true) {
				System.out.println("serveur : en attente de connexion ...");
				Socket sc = ss.accept();

				System.out.println("serveur : connexion établie !");
				OutputStream os = sc.getOutputStream();

				os.write("serveur : entrer un caractere > \n".getBytes());

				InputStream is = sc.getInputStream();
				int caractereRecu = is.read();
				System.out.println("caractere recu : " + (char) caractereRecu);

				os.write(("\nserveur : le caractere qui suit est > " + (char) (caractereRecu + 1) + "\n").getBytes());
				os.flush();
			}
			
		}

	}

}
