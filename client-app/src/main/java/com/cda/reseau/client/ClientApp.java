package com.cda.reseau.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientApp {

	public static void main(String[] args) throws IOException {
		Socket sc = new Socket();
		
		InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
		System.out.println("client : tentative de connexion");
		sc.connect(isa);
		
		InputStream is = sc.getInputStream();
		int x = is.read();
		
		System.out.println("client : x = "+(char)x);
		
		OutputStream os = sc.getOutputStream();
		os.write(x+1);
		
		sc.close();
		
	}

}
