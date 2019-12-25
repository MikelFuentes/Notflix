package database;

import java.util.ArrayList;

public class Visual {
	int id;
	String nombre;
	String año;
	String director;
	String archi;
	String imagen;
	ArrayList<Tag> tags = new ArrayList<Tag>();


	public Visual(int id, String nombre, String año, String director, String archi, String imagen, ArrayList<Tag> tags){
		this.id = id;
		this.nombre = nombre;
		this.año = año;
		this.director = director;
		this.archi = archi;
		this.imagen = imagen;
		this.tags = tags;
		
	}

	public String getAño() {
		return año;
	}

	public int getId() {
		return id;
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

	public ArrayList<Tag> getTags() {
		System.out.println("mis tags son" + tags+ "mi nombre es" + this.nombre);
		return tags;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
