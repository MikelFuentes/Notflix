package database;

public class Idioma {
	String idIdioma;
	String nombreIdioma;

	
	
	public Idioma(String idIdioma, String idioma) {
		this.idIdioma = idIdioma;
		this.nombreIdioma = idioma;		
	}



	public String getIdIdioma() {
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
