package com.cda.reseau.serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurApp {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8080);
		
		System.out.println("serveur : en attente de connexion ...");
		Socket sc = ss.accept();
		
		System.out.println("serveur : connexion établie !");
		OutputStream os = sc.getOutputStream();
		
		os.write(99);
		
		InputStream is = sc.getInputStream();
		int y = is.read();
		
		System.out.println("serveur : y : "+(char)y);
		
		ss.close();
	}

}
