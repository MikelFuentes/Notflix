package database;

import java.util.ArrayList;

public class Documental extends Visual {
	Tema tema;
	
	public Documental(int id, String nombre, String año, String director, String archi, String imagen,
			ArrayList<Tag> tags, Tema tema) {
		super(id, nombre, año, director, archi, imagen, tags);
		// TODO Auto-generated constructor stub
		this.tema = tema;
	}

	public Tema getTema() {
		return tema;
	}
	
	
	
}
