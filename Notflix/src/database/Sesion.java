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
		String statement = "INSERT INTO peliculas (nombre, anyo, director, archivo, imagen) VALUES (?,?,?,?,?);";
		
		
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
            state.executeUpdate();													// Introduzco un nuevo elemento en la tabla de peliculas
            state.close();

            String query = "SELECT id_peli from peliculas where nombre = ? ;";		// Saco el Id de la Pelicula que acabo de introducir
            PreparedStatement state2 = con.prepareStatement(query);
            state2.setString(1, nom);

            ResultSet id_pelicula = state2.executeQuery();							//
            int  id_peli = id_pelicula.getInt("id_peli");
            state2.close();
//			System.out.println(id_peli);


            for (int i = 0; i<id_tags.size(); i++) {  																	// Recorro el Arraylist de tags que he pasado y los meto en la
                String statement_tags = "insert into tags_peliculas(id_pelis, id_tags) values (?,?)";	// tabla intermedia tags_peliculas junto a la id de la pelicul
                PreparedStatement state3 = con.prepareStatement(statement_tags);										// que acabo de meter
                state3.setInt(1, id_peli);
                state3.setInt(2, id_tags.get(i));

//				statement_tags = statement_tags.replace("id_pog", id_peli);
//				String statement_tag = statement_tags.replace("id_tog", id_tags.get(i).toString());
                state3.executeUpdate();
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            new Error("El programa se ha ido a cagar"); 				// TODO POR AMOR DE CRISTO HAY QUE CAMBIAR ESTO <--------------------
            e.printStackTrace();
        }
    }


	public ArrayList buscar2 (String d) {
		String qwer = "SELECT peliculas.*, id_tags  from peliculas, tags_peliculas where peliculas.nombre LIKE ?;";
		try {
			PreparedStatement sta = con.prepareStatement(qwer);
			sta.setString(1, d);
			ResultSet set = sta.executeQuery();
			while(set.next()) {
			
//				Pelicula peli_datos = new Pelicula(set.getInt("id_peli"), set.getString("nombre"), set.getString("anyo"), set.getString("director"), set.getString("archivo"), set.getString("imagen"));
//				kresul.add(peli_datos);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println(qwer);
		Pelicula peli_datos;
		ArrayList<Pelicula> resul = new ArrayList<>();
		
		
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
