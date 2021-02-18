package com.cda.reseau.client.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientApp {
	public static void main(String[] args) throws IOException {
		Socket sc = new Socket();
		String machine = "172.31.87.132";
		machine = "www.google.fr";
		String fichier = "/";

		InetSocketAddress isa = new InetSocketAddress(machine, 80);
		System.out.println("client : tentative de connexion");
		sc.connect(isa);
		System.out.println("connexion reussie");

		InputStream is = sc.getInputStream();
		OutputStream os = sc.getOutputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		PrintWriter prw = new PrintWriter(os,true);
		prw.println("GET " + fichier + " HTTP/1.1");
		prw.println("Host: " + machine);
		prw.println("User-Agent: Simple Http Client");
		prw.println("Accept: text/html");
		prw.println("Accept-Language: en-US");
		prw.println("Connection: close");
		prw.println();

		int charactere = 0;
		do {
			charactere = br.read();
			System.out.print((char)charactere);
		} while (charactere != -1);
		
		System.out.println("fin lecture");
		sc.close();
	}
}
