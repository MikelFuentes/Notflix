package database;

import gui.Error;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

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

	public void meter_peli (String nom, String dir, String ruta_archivo, String ruta_imagen, ArrayList<Integer> id_tags, String anio){
		String statement = "INSERT INTO peliculas (nombre, anyo, director, archivo, imagen) VALUES ('?','?','?','?','?');";
		
		
//		statement = statement.replace("z",nom);
//		statement = statement.replace("x",dir);
//		statement = statement.replace("ra",ruta_archivo);
//		statement = statement.replace("ri",ruta_imagen);
//		statement = statement.replace("xx",año);
//		System.out.println(statement);


		try{
			PreparedStatement state = con.prepareStatement(statement);
			state.setString(1, nom);
			state.setString(2, anio);
			state.setString(3, dir);
			state.setString(4, ruta_archivo);
			state.setString(5, ruta_imagen);
			
			System.out.println(state);
//			state.executeUpdate();

//			String query = "SELECT nombre from peliculas where nombre = '"+ nom +"' ;";
//			state = con.prepareStatement(query);
//			ResultSet id_pelicula = state.executeQuery();
//			String  id_peli = id_pelicula.getString("id_peli");
//
//			
//
//			for (int i = 0; i<id_tags.size(); i++) {
//				String statement_tags = "insert into tags_peliculas(id_pelis, id_tags) values ('id_pog', 'id_tog')";//aqui
//				statement_tags = statement_tags.replace("id_pog", id_peli);											//aqui
//				String statement_tag = statement_tags.replace("id_tog", id_tags.get(i).toString());
//				state = con.prepareStatement(statement_tag); 														//TODO  USAR CON INTERROGANTES
//				state.executeUpdate();
//			}
//			state.executeUpdate();
//			state = con.prepareStatement(statement_tags);
//			state.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new Error("El programa se ha ido a cagar");
			e.printStackTrace();
		}
	}

	public ArrayList buscar2 (String d) {
		String qwer = "SELECT * from peliculas where nombre LIKE 'd' ;";
		qwer = qwer.replace("d", d);
		
		System.out.println(qwer);
		Pelicula peli_datos;
		ArrayList<Pelicula> resul = new ArrayList<>();
		try {
			PreparedStatement sta = con.prepareStatement(qwer);
			ResultSet set = sta.executeQuery();
			while(set.next()) {
			
			//	peli_datos = new Pelicula(set.getInt("id_peli"), set.getString("nombre"), set.getString("anyo"), set.getString("director"), set.getString("archivo"), set.getString("imagen"));
//				resul.add(peli_datos);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resul;
		
	}
	public ArrayList buscartaCla (String z) {
		System.out.println("me llama");
		String qwer = "SELECT * from tags where nombre_tag LIKE 'z' ;";
		qwer = qwer.replace("z", z);
		
		Tag tag_datos;
		ArrayList<Tag> resul = new ArrayList<>();
		try {
			PreparedStatement sta = con.prepareStatement(qwer);
			ResultSet set = sta.executeQuery();
			while (set.next()){
				tag_datos = new Tag(set.getString("nombre_tag"), set.getInt("id_tag"));
				
				resul.add(tag_datos);
				
			}
		} catch (Exception e) {
		
		}
		return resul;
	}
	
}
