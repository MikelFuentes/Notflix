package database;

import java.util.ArrayList;

public class Visual {
	int id;
	String nombre;
	String año;
	String director;
	String archi;
	String imagen;
	ArrayList<Integer> id_tag;

	public Visual(int id, String nombre, String año, String director, String archi, String imagen, ArrayList<Integer> id_tags) {
		this.id = id;
		this.nombre = nombre;
		this.año = año;
		this.director = director;
		this.archi = archi;
		this.imagen = imagen;
		this.id_tag = id_tags;
		
	}

	public String getNombre() {
		return nombre;
	}

	public String getDirector() {
		return director;
	}

	public String getArchi() {
		return archi;
	}

	public String getImagen() {
		return imagen;
	}
	
}
