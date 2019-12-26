package database;

public class Idioma {
	int idIdioma;
	String nombreIdioma;

	
	
	public Idioma(int i, String idioma) {
		this.idIdioma = i;
		this.nombreIdioma = idioma;		
	}



	public int getIdIdioma() {
		return idIdioma;
	}



	public String getNombreIdioma() {
		return nombreIdioma;
	}



	@Override
	public String toString() {
		return nombreIdioma;
	}
}
