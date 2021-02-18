package com.cda.reseau.serveur.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class ServeurApp {

	public static void main(String[] args) throws IOException {
		try (DatagramSocket ss = new DatagramSocket(8080)) {

			byte[] msg = new byte[50];
			DatagramPacket in = new DatagramPacket(msg,50);
			
			while (true) {
				System.out.println("serveur : en attente de connexion ...");
				ss.receive(in);

				System.out.println("serveur : connexion établie !");

				System.out.println("caracteres recus : " + new String(msg));
				
				Arrays.fill(msg, (byte)0);
			}
			
		}

	}

}
