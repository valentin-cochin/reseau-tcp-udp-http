package com.cda.sujets.suivi.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Auxiliaire implements Runnable {
	private final Socket socketClient;

	public Auxiliaire(Socket sc) throws IOException {
		this.socketClient = sc;
	}

	@Override
	public void run() {
		try {
			OutputStream os = socketClient.getOutputStream();
			InputStream is = socketClient.getInputStream();

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

			socketClient.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.socketClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
