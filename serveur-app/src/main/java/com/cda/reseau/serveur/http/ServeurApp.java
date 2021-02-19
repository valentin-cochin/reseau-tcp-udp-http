package com.cda.reseau.serveur.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServeurApp {

	public static void main(String[] args) throws IOException {
		try (ServerSocket ss = new ServerSocket(8080)) {

			while (true) {
				System.out.println("serveur : en attente de connexion ...");
				Socket sc = ss.accept();

				System.out.println("serveur : connexion établie !");
				OutputStream os = sc.getOutputStream();
				InputStream is = sc.getInputStream();

				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				PrintWriter prw = new PrintWriter(os, true);

				String s;
				while ((s = br.readLine()) != null) {
					System.out.println(s);
					if (s.isEmpty()) {
						break;
					}
				}
				
				System.out.println("debut ecriture reponse");
				prw.println("HTTP/1.1 200 OK");
				prw.println("Date: Fri, 31 Dec 2021 23:59:59 GMT");
				prw.println("Server: CDA SERVER");
				prw.println("Content-Type: text/html");
				prw.println("");
				prw.println("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <title>CDA tcp/udp/http</title>\r\n"
						+ "    <style>\r\n"
						+ "        p{\r\n"
						+ "            background-color: red;\r\n"
						+ "            font-size: larger;\r\n"
						+ "        }\r\n"
						+ "    </style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <p>Hello CDA !</p>\r\n"
						+ "</body>\r\n"
						+ "</html>");
				
				prw.close();
				br.close();
				
				sc.close();
			}

		}

	}

}
