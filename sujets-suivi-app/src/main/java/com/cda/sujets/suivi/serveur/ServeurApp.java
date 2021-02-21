package com.cda.sujets.suivi.serveur;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class ServeurApp {

	private static final String SITE_ROOT = "mon-site";
	public static Path SITE_PATH;
	public static VelocityEngine VELOCITY_ENGINE;
	
	public static void main(String[] args) throws IOException {
		
		VELOCITY_ENGINE = new VelocityEngine();
		VELOCITY_ENGINE.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		VELOCITY_ENGINE.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		VELOCITY_ENGINE.init();
		
		String userHomePath = System.getProperty("user.home");
		
		SITE_PATH = Paths.get(userHomePath+File.separator+SITE_ROOT);
		
		System.out.println(SITE_PATH);
		
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
