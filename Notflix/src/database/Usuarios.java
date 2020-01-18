package database;

public class Usuarios {
	int id;
	String nombre;
	
	public Usuarios(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	

	public String toString() {
		return this.nombre;
	}

}
