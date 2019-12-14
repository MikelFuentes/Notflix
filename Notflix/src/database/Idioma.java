package database;

public class Idioma {
	int idIdioma;
	String nombreIdioma;

	
	
	public Idioma(int idIdioma, String idioma) {
		this.idIdioma = idIdioma;
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
