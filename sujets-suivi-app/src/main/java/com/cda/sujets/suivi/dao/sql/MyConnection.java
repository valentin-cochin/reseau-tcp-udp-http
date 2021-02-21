package com.cda.sujets.suivi.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
	private static Connection connexion = null;

	private MyConnection() {
		try {
			Properties appProps = new Properties();
			appProps.load(MyConnection.class.getResourceAsStream("/db.properties"));
			connexion = DriverManager.getConnection(
					appProps.getProperty("url").trim(), 
					appProps.getProperty("username").trim(),
					appProps.getProperty("password").trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (connexion == null) {
			new MyConnection();
		}
		return connexion;
	}

	public static void stop() {
		if (connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
