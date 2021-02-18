package com.cda.reseau.client.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;


public class ClientApp {

	public static void main(String[] args) throws IOException {
		InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
		
		String phrase = null;
		DatagramPacket dp = null;
		
		try(
				Scanner clavierIn = new Scanner(System.in);
				DatagramSocket ds = new DatagramSocket();
				){
			while(true) {
				System.out.print("\nsaisissez une phrase > ");
				phrase = clavierIn.nextLine();
				dp = new DatagramPacket(phrase.getBytes(), phrase.length(),isa);
				ds.send(dp);
				System.out.println("packet envoyé");
			}
			
		}
		
	}

}
