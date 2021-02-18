package com.cda.reseau.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class ClientApp {

	public static void main(String[] args) throws IOException {
		Socket sc = new Socket();
		
		InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
		System.out.println("client : tentative de connexion");
		sc.connect(isa);
		
		Scanner clavierIn = new Scanner(System.in);
		InputStream is = sc.getInputStream();
		OutputStream os = sc.getOutputStream();
		
		// pour faciliter la lecture, passer par des buffer
		BufferedReader bfr = new BufferedReader(new InputStreamReader(is));

		String phraseRecue = bfr.readLine();
		System.out.print(phraseRecue);
		
		char caractereSaisie = clavierIn.next().charAt(0);

		os.write(caractereSaisie);
		bfr.readLine();
		phraseRecue = bfr.readLine();
		System.out.println(phraseRecue);
		
		sc.close();
		clavierIn.close();
		
	}

}
