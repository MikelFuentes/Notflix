package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sesion {
	Connection con;
	
	public void Crear() {
		try {
			String url = "jdbc:sqlite:src/database/Database"; //TODO AÑADIR O QUITAR NOTFIX
			System.out.println("antessss");
			this.con = DriverManager.getConnection(url);
			System.out.println(this.con);
		} catch (SQLException e) {
			new Error("no se ha podido conectar ");
		}
	}

	public ArrayList<String> buscar (String d) {
		String qwer = "SELECT nombre from peliculas where nombre LIKE 'd' ;";
		ArrayList<String> resul = new ArrayList<>();
		qwer = qwer.replace("d", d);
		System.out.println(qwer);
		try {
			PreparedStatement sta = con.prepareStatement(qwer);
			ResultSet set = sta.executeQuery();
			while(set.next()) {
				
				resul.add(set.getString("nombre"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resul;
		
	}

	
}
