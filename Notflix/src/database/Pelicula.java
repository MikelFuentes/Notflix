package database;

import java.util.ArrayList;

public class Pelicula {
	int id;
	String nombre;
	String año;
	String director;
	String archi;
	String imagen;


	public Pelicula(int id, String nombre, String año, String director, String archi, String imagen){
		this.id = id;
		this.nombre = nombre;
		this.año = año;
		this.director = director;
		this.archi = archi;
		this.imagen = imagen;
		
		
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

	@Override
	public String toString() {
		return "Pelicula [nombre=" + nombre + "]";
	}
	
	
	
}

