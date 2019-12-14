package database;

public class Tema {
	String idTema;
	String nombreTema;

	
	
	public Tema(String idTema, String Tema) {
		this.idTema = idTema;
		this.nombreTema = Tema;		
	}



	public String getIdTema() {
		return idTema;
	}



	public String getNombreTema() {
		return nombreTema;
	}



	@Override
	public String toString() {
		return nombreTema;
	}
}
