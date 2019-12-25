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

	public Connection getCon() {
		return con;
	}

	/**
	 * Crea la conexión con la base de datos
	 */
	public void Crear() {
		try {
			String url = "jdbc:sqlite:src/database/Database"; //TODO AÑADIR O QUITAR "Notflix/"
			System.out.println("antessss");
			this.con = DriverManager.getConnection(url);
			System.out.println(this.con);
		} catch (SQLException e) {
			new Error("no se ha podido conectar ");
		}
	}
	
	/**
	 * @deprecated 
	 * @param d
	 * @param campo Campo en el que se quiere buscar (nombre, director, anyo)
	 * @return
	 */
	public ArrayList<String> buscar (String d, String campo) {
		String qwer = "SELECT nombre from peliculas where ? LIKE ? ;";
		ArrayList<String> resul = new ArrayList<>();
//		System.out.println(qwer);
		try {
			PreparedStatement sta = con.prepareStatement(qwer);
			sta.setString(1,campo);
			sta.setString(2,d);
//            System.out.println(sta);
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



	/** Mete peliculas en la base de datos
	 * @param nom Nombre de la pelicula
	 * @param dir Director de la pelicula
	 * @param ruta_archivo Ruta del archivo de la pelicula
	 * @param ruta_imagen Ruta de la imagfen de la pelicula
	 * @param id_tags Array con las id de la pelicula
	 * @param anio Año en el que salió la pelicula
	 */
	public void meter_peli (String nom, String dir, String ruta_archivo, String ruta_imagen, ArrayList<Integer> id_tags, String anio, String var, String tipo){
		String statement = "INSERT INTO peliculas (nombre, anyo, director, archivo, imagen, "+ tipo +") VALUES (?,?,?,?,?,?);";
		
		
//		statement = statement.replace("z",nom);
//		statement = statement.replace("x",dir);
//		statement = statement.replace("ra",ruta_archivo);
//		statement = statement.replace("ri",ruta_imagen);
//		statement = statement.replace("xx",año);
//		System.out.println(statement);


		try{
			PreparedStatement state = con.prepareStatement(statement);
//			state.setString(1, tipo);
			state.setString(1, nom);
			state.setString(2, anio);
			state.setString(3, dir);
			state.setString(4, ruta_archivo);
			state.setString(5, ruta_imagen);
			state.setString(6, var);
			

			System.out.println(state.toString());
			state.executeUpdate();													// Introduzco un nuevo elemento en la tabla de peliculas
			state.close();

			String query = "SELECT id_peli from peliculas where nombre = ? ;";		// Saco el Id de la Pelicula que acabo de introducir
			PreparedStatement state2 = con.prepareStatement(query);
			state2.setString(1, nom);

			ResultSet id_pelicula = state2.executeQuery();							//
			int  id_peli = id_pelicula.getInt("id_peli");
			state2.close();
//			System.out.println(id_peli);
			//////////////////////////////////////////////////
//			String prue = "Select * from tags_peliculas";
//			PreparedStatement stateprue = con.prepareStatement(prue);
//			ResultSet resulPrue = stateprue.executeQuery();
//			while (resulPrue.next()) {
//				String nomTag = resulPrue.getString("id_pelis");
//				String iddtag = resulPrue.getString("id_tags");
//				System.out.println(nomTag + "-->" + iddtag);
//				
//			}

			for (int i = 0; i<id_tags.size(); i++) {  																	// Recorro el Arraylist de tags que he pasado y los meto en la
				String statement_tags = "insert into tags_peliculas(id_pelis, id_tags) values (?,?)";					// tabla intermedia tags_peliculas junto a la id de la pelicul
				PreparedStatement state3 = con.prepareStatement(statement_tags);										// que acabo de meter
				state3.setInt(1, id_peli);
				state3.setInt(2, id_tags.get(i));
				System.out.println(i);
//				statement_tags = statement_tags.replace("id_pog", id_peli);
//				String statement_tag = statement_tags.replace("id_tog", id_tags.get(i).toString());
				state3.executeUpdate();
				
				
				
			}
			
//			resulPrue = stateprue.executeQuery();
//			System.out.println(resulPrue);
//			while (resulPrue.next()) {
//				String nomTag = resulPrue.getString("id_pelis");
//				String iddtag = resulPrue.getString("id_tags");
//				System.out.println(nomTag + "-->" + iddtag);
//				
//			}
//			System.out.println();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new Error("Error. No se ha podido guardar la película.");
			e.printStackTrace();
		}
	}
	

	
	/** Hace una busqueda en la base de datos que devuelve una clase con toda la información de la pelicula
	 * @param d Es elmento que quiers buscar
	 * @return Devuelve un Array list con las peliculas que ha encontrado
	 */
	public ArrayList<Visual> buscar2 (String d) { 					
		String qwer = "SELECT *  from peliculas where nombre LIKE ?;";
		ArrayList<Visual> resul = new ArrayList<>();
		
		System.out.println(qwer);
		try {
			System.out.println(con);
			PreparedStatement sta = con.prepareStatement(qwer); //TODO arreglar nullpoiter
			sta.setString(1, d);								//ns como lo has hecho en el de arriba, pero creo que pasa lo mismo aqui
			ResultSet set = sta.executeQuery();
			
			
			
			while(set.next()) {
				String qwer2 = "SELECT * from tags where id_tag in (SELECT id_tags from tags_peliculas where id_pelis = ?);";
				
				PreparedStatement sta2 = con.prepareStatement(qwer2);
				
				sta2.setInt(1, set.getInt("id_peli"));
				
				ResultSet set2 = sta2.executeQuery();
				ArrayList<Tag> listaTags = new ArrayList<Tag>();
//				System.out.println("NUE");
				while(set2.next()) {
					
					Tag unTag = new Tag(set2.getString("nombre_tag"), set2.getInt("id_tag"));
//					System.out.println(set2.getString("nombre_tag") + set2.getInt("id_tag"));
					listaTags.add(unTag);
//					System.out.println(listaTags);
				}
				
						
				
				
				if(set.getString("tema") == null) {
					
					String qwer3 = "SELECT * from idioma where id_idioma =" + set.getInt("idioma")+ ";" ;
					PreparedStatement sta3 = con.prepareStatement(qwer3);
					ResultSet set3 = sta3.executeQuery();
					Idioma idioma = new Idioma(set3.getString("id_idioma"), set3.getString("nombre_idioma"));
					
					Pelicula peli_datos = new Pelicula(set.getInt("id_peli"), set.getString("nombre"), set.getString("anyo"), set.getString("director"), set.getString("archivo"), set.getString("imagen"), listaTags, idioma);
					
					resul.add(peli_datos);
					
					System.out.println("le meto:" + listaTags + " y me guarda dentro:"+ resul.get(0).getTags() + resul.get(0).getNombre() + "\n");
					
				}else if(set.getString("idioma") == null) {

					
					String qwer3 = "SELECT * from tema where id_tema =" + set.getInt("tema")+ ";" ;
					PreparedStatement sta3 = con.prepareStatement(qwer3);
					ResultSet set3 = sta3.executeQuery();
					Tema tema = new Tema(set3.getString("id_idioma"), set3.getString("nombre_idioma"));					
					
					Documental documental_datos = new Documental(set.getInt("id_peli"), set.getString("nombre"), set.getString("anyo"), set.getString("director"), set.getString("archivo"), set.getString("imagen"), listaTags, tema);
					
					resul.add(documental_datos);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("resul0 fue fue:" + resul.get(0).getTags() + resul.get(0).getNombre() );
//		System.out.println(resul.get(1).getTags());
//		//System.out.println(resul.get(2).getTags());
		return resul;
	}
	/**
	 * @param tg Nombre del tag que se quiere buscar
	 * @return Devuelve los nombres de las peliculas con el tag
	 */

	public ArrayList<String> buscarPorUnTag(String tg){ //TODO comprobar si funciona, que me da un nullpointerexception en buscar2 y no me deja probar nada
		String st1 = "select  id_tag from tags where nombre_tag like ?";
		String st2 = "select nombre from tags_peliculas where id_tags = ?";
		ArrayList<String> resul = new ArrayList<>();
		try{
			PreparedStatement state = con.prepareStatement(st1);
			state.setString(1,tg);
			ResultSet set1 = state.executeQuery();
			int tagId = set1.getInt(1);
			state = con.prepareStatement(st2);
			state.setInt(1,tagId);
			ResultSet set2 = state.executeQuery();
			while(set2.next()){
				resul.add(set2.getString("nombre"));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return resul;
		
	}
	
	public ArrayList buscartaClaTa (String z)  {
		ResultSet set = null;
		String qwer = "SELECT * from tags where nombre_tag LIKE ? ;";
		try {
			PreparedStatement state = con.prepareStatement(qwer);
			state.setString(1, z);
			set = state.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		ArrayList<Tag> resul = new ArrayList<>();
		try {
			while (set.next()){
				String nomTag = set.getString("nombre_tag");
				int idTag = set.getInt("id_tag");
				Tag unRes = new Tag(nomTag, idTag);
				resul.add(unRes);
			}
		//ss
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return resul;
	}
	
	public ArrayList buscartaClaIdioma (String z)  {
		ResultSet set = null;
		String qwer = "SELECT * from idioma where nombre_idioma LIKE ? ;";
		try {
			PreparedStatement state = con.prepareStatement(qwer);
			state.setString(1, z);
			set = state.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		ArrayList<Idioma> resul = new ArrayList<>();
		try {
			while (set.next()){
				String nomTag = set.getString("nombre_idioma");
				String idTag = set.getString("id_idioma");
				Idioma unRes = new Idioma(idTag, nomTag);
				resul.add(unRes);
			}
		//ss
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return resul;
	}
	
	public ArrayList buscartaClaTem (String z)  {
		ResultSet set = null;
		String qwer = "SELECT * from tema where nombre_tema LIKE ? ;";
		try {
			PreparedStatement state = con.prepareStatement(qwer);
			state.setString(1, z);
			set = state.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		ArrayList<Idioma> resul = new ArrayList<>();
		try {
			while (set.next()){
				String nomTag = set.getString("nombre_tema");
				System.out.println(nomTag);
				String idTag = set.getString("id_tema");
				Idioma unRes = new Idioma(idTag, nomTag);
				resul.add(unRes);
			}
		//ss
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return resul;
	}
	
	public ArrayList cogerTagsNoSeleccionados (int z)  {
		ResultSet set = null;
		String qwer = "SELECT * from tags where id_tag not in (SELECT id_tags from tags_peliculas where id_pelis = ?);";
		System.out.println(z);
		try {
			PreparedStatement state = con.prepareStatement(qwer);
			state.setInt(1, z);
			set = state.executeQuery();
		} catch (Exception e) {
			
		}
		ArrayList<Tag> resul = new ArrayList<>();
		try {
			while(set.next()) {
				
				String nomTag = set.getString("nombre_tag");
				int idTag = set.getInt("id_tag");
				Tag tagTemp = new Tag(nomTag, idTag);
				resul.add(tagTemp);
			}
		} catch (Exception e) {
			
		}
		
		return resul;
		
	}
	
	public void Editar_peli (String nom, String dir, String ruta_archivo, String ruta_imagen, ArrayList<Integer> id_tags, String anio, String var, String tipo, int id_peli){
		String statement = "UPDATE peliculas SET nombre = ?, anyo = ? , director = ?, archivo = ?, imagen = ?, "+ tipo +" = ? where id_peli = " + id_peli + ";";

		try{
			PreparedStatement state = con.prepareStatement(statement);
			state.setString(1, nom);
			state.setString(2, anio);
			state.setString(3, dir);
			state.setString(4, ruta_archivo);
			state.setString(5, ruta_imagen);
			state.setString(6, var);

			System.out.println(state.toString());
			state.executeUpdate();													// Introduzco un nuevo elemento en la tabla de peliculas
			state.close();

			for (int i = 0; i<id_tags.size(); i++) {  																	// Recorro el Arraylist de tags que he pasado y los meto en la
				String statement_tags = "insert into tags_peliculas(id_pelis, id_tags) values (?,?)";					// tabla intermedia tags_peliculas junto a la id de la pelicul
				PreparedStatement state3 = con.prepareStatement(statement_tags);										// que acabo de meter
				state3.setInt(1, id_peli);
				state3.setInt(2, id_tags.get(i));
				System.out.println(i);

				state3.executeUpdate();	
				
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new Error("Error. No se ha podido guardar la película.");
			e.printStackTrace();
		}
	}
	
	
	
}
