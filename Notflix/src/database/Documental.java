package database;

import java.util.ArrayList;

public class Documental extends Visual {
	String tema;
	
	public Documental(int id, String nombre, String año, String director, String archi, String imagen,
			ArrayList<Tag> tags, String tema) {
		super(id, nombre, año, director, archi, imagen, tags);
		// TODO Auto-generated constructor stub
		this.tema = tema;
	}

	public String getTema() {
		return tema;
	}
	
	
	
}
