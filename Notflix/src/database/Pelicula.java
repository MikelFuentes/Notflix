package database;

import java.util.ArrayList;

public class Pelicula extends Visual{
	String idioma;

	public Pelicula(int id, String nombre, String año, String director, String archi, String imagen,
			ArrayList<Tag> tags , String idioma) {
		super(id, nombre, año, director, archi, imagen, tags);
		// TODO Auto-generated constructor stub
		this.idioma = idioma;
	}

	public String getIdioma() {
		return idioma;
	}
	
	
	
}

