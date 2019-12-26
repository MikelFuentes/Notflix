package database;

public class Tema {
	int idTema;
	String nombreTema;

	
	
	public Tema(int idTema, String Tema) {
		this.idTema = idTema;
		this.nombreTema = Tema;		
	}



	public int getIdTema() {
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
