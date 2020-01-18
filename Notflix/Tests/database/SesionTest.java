package database;

import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Before;

public class SesionTest {
	private Sesion sesion;
	private Connection conexion;
	
	@Before
	public void init() {
		sesion = new Sesion();
		sesion.Crear();
		conexion = sesion.getCon();
		
	}
	
//	@Test
//    public void getCon() {
//    	
//    }

//    @Test
//    public void crear() {
//    	
//    }

    @Test
    public void buscar() {
    	ArrayList<Tag> tags = new ArrayList<Tag>();
    	tags.add(new Tag("terror", 1));
    	tags.add(new Tag("accion", 2));
    	Idioma idioma = new Idioma(1, "Castellano");
    	Pelicula pelicula = new Pelicula(1, "titanic", "1999", "Mikel", "./peliculas/titanic.mp4", "http://foto.com/titanic", tags, idioma);
    	Pelicula pelicula_act = (Pelicula) sesion.buscar2("%").get(0);
    	assertEquals(pelicula.getNombre(), pelicula_act.getNombre());
    	assertEquals(pelicula.getArchi(), pelicula_act.getArchi());
    	assertEquals(pelicula.getAño(), pelicula_act.getAño());
    	assertEquals(pelicula.getDirector(), pelicula_act.getDirector());
    	assertEquals(pelicula.getIdioma().getIdIdioma(), pelicula_act.getIdioma().getIdIdioma());
    	assertEquals(pelicula.getImagen(), pelicula_act.getImagen());
    	assertEquals(pelicula.getTags().get(0).getNombre_tag(), pelicula_act.getTags().get(0).getNombre_tag());
    	
    }

    @Test
    public void meter_peli() {
    }

    @Test
    public void buscar2() {
    }

    @Test
    public void buscarPorUnTag() {
    }

    @Test
    public void buscartaClaTa() {
    }
}