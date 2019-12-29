package database;

import java.util.ArrayList;

public class Pelicula extends Visual{
	Idioma idioma;

	public Pelicula(int id, String nombre, String año, String director, String archi, String imagen,
			ArrayList<Tag> tags , Idioma idioma) {
		super(id, nombre, año, director, archi, imagen, tags);
		// TODO Auto-generated constructor stub
		this.idioma = idioma;
	}

	public Idioma getIdioma() {
		return idioma;
	}
	
	
	
}

