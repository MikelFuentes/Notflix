package database;

public class Tag {
	String nombre_tag;
	int id_tag;
	
	
	public Tag(String nombre_tag, int id_tag) {
		this.nombre_tag = nombre_tag;
		this.id_tag = id_tag;
	}


	public int getId_tag() {
		return id_tag;
	}


	public String getNombre_tag() {
		return nombre_tag;
	}


	@Override
	public String toString() {
		return "Tag [nombre_tag=" + nombre_tag + "]";
	}


	
	
}
