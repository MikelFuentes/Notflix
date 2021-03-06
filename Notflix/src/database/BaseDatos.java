package database;

import java.sql.*;
//import org.sqlite.SQLiteConfig;

public class BaseDatos {
    public static void crearBD() {

        try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:Notflix/src/database/Database");  //TODO AÑADIR O QUITAR "Notflix/"
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(100);

//        ahora hacemos la tabla y cambios que queramos en ella con statement.executeUpdate(action)
//        para meter datos, en cambio, hacemos int res = statement.executeUpdate(data) sout(res)

//            statement.executeUpdate("DROP TABLE IF EXISTS peliculas");
//            statement.executeUpdate("DROP TABLE IF EXISTS tags");
//            statement.executeUpdate("DROP TABLE IF EXISTS usuarios");
//            statement.executeUpdate("DROP TABLE IF EXISTS tags_peliculas");
//            statement.executeUpdate("DROP TABLE IF EXISTS tema");
//            statement.executeUpdate("DROP TABLE IF EXISTS idioma");
//            statement.executeUpdate("DROP TABLE IF EXISTS usuario_peli");
            

//            statement.executeUpdate("create table tags(" +
//                    "id_tag int not null auto_increment," +
//                    " nombre_tag varchar(150) not null)," +
//
//                    "PRIMARY key(id_tag)" +
//                    ");");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS tags ("
            		+ "id_tag INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            		+ "nombre_tag varchar(150) NOT NULL"
            		+ ");");
            System.out.println("pasooo");
            
            ResultSet resul = statement.executeQuery("SELECT id_tag from tags;");
            int res = 0;
            System.out.println(resul.next());
            if (resul.next() == false) {
            	res = statement.executeUpdate("insert into tags (nombre_tag) values ('terror')");
                System.out.println("1");
                res = statement.executeUpdate("insert into tags (nombre_tag) values ('accion')");
                System.out.println("2");
                res = statement.executeUpdate("insert into tags (nombre_tag) values ('romance')");
                System.out.println("3");
                res = statement.executeUpdate("insert into tags (nombre_tag) values ('suspense')");
                System.out.println("4");
                
                System.out.println("Creado tags^^^^^^^^^^");
			}
            

//            statement.executeUpdate("create table peliculas(" +
//                    "id_peli int NOT NULL AUTO_INCREMENT,\n" +
//                    "nombre VARCHAR(100) NOT NULL,\n" +
//                    "fecha VARCHAR(100) NOT NULL,\n" +
//                    "director VARCHAR(100) NOT NULL,\n" +
//                    "archivo VARCHAR(100) NOT NULL,\n" +
//                    "link VARCHAR(120) NOT NULL,\n" +
//                    "fk_tags int NOT NULL," +
//
//                    "PRIMARY KEY (id_peli),\n" +
//                    "FOREIGN KEY (fk_tags) REFERENCES tags(id_tag)" +
//                    ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS tema ("
            		+ "id_tema INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            		+ "nombre_tema varchar(150) NOT NULL"
            		+ ");");
            resul = statement.executeQuery("SELECT id_tema from tema;");
            
            if (resul.next ()== false) {
            	res = statement.executeUpdate("insert into tema (nombre_tema) values ('Animales')");
                res = statement.executeUpdate("insert into tema (nombre_tema) values ('Espacio')");
                res = statement.executeUpdate("insert into tema (nombre_tema) values ('Medio Ambiente')");
//                System.out.println("Creo tema");
			}
            
           
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS idioma ("
            		+ "id_idioma INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            		+ "nombre_idioma varchar(150) NOT NULL"
            		+ ");");
            
            resul = statement.executeQuery("SELECT id_idioma from idioma;");
            if (resul.next ()== false) {
            res = statement.executeUpdate("insert into idioma (nombre_idioma) values ('Castellano')"); 
            res = statement.executeUpdate("insert into idioma (nombre_idioma) values ('Ingles')"); 
            res = statement.executeUpdate("insert into idioma (nombre_idioma) values ('Frances')"); 
            }
            
            
//            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS peliculas ("
            		+"id_peli INTEGER PRIMARY KEY AUTOINCREMENT,"
            		+"nombre varchar(150) NOT NULL,"
            		+"anyo varchar(4) NOT NULL,"
            		+"director varchar(150) NOT NULL,"
            		+"archivo varchar(150) NOT NULL,"
            		+"imagen varchar(150) NOT NULL,"
            		+"tema int,"
            		+"idioma int,"
            		+"FOREIGN KEY(tema) REFERENCES tags(id_tema),"
            		+"FOREIGN KEY(idioma) REFERENCES tags(id_idioma)"
            		+" );");
//            		+"fk_tags INTEGER NOT NULL,"
//            		+"FOREIGN KEY(fk_tags) REFERENCES tags(id_tag)"
            		
            resul = statement.executeQuery("SELECT id_peli from peliculas;");
            if (resul.next ()== false) {
            	System.out.println("paso2");
            	res = statement.executeUpdate("INSERT INTO peliculas (nombre, anyo, director, archivo, imagen, idioma)"
            			+ "VALUES('titanic', '1999', 'Mikel', './peliculas/titanic.mp4', 'http://foto.com/titanic', 1);");
            	System.out.println("1");
            	res = statement.executeUpdate("INSERT INTO peliculas (nombre, anyo, director, archivo, imagen, idioma)"
            			+ "VALUES('el padrino', '1988', 'Pepe', './peliculas/padrino.mp4', 'http://foto.com/padrino', 1);");
            	System.out.println("2");
            }
//            res = statement.executeUpdate("insert into peliculas () values ('')");
//            System.out.println(res);

//            statement.executeUpdate("create table IF NOT EXISTS usuarios(" +
//                    "id_usuario int NOT NULL AUTO_INCREMENT,\n" +
//                    "nombre VARCHAR(100) NOT NULL,\n" +
////                    "password VARCHAR(100) NOT NULL,\n" +
////                    "rango int NOT NULL,\n" +
//                    "PRIMARY KEY (id_usuario)" +
//                    ");");
            
            
            
            
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS tags_peliculas ("
            		+ "id_pelis INTEGER,"
            		+ "id_tags INTEGER,"
            		+ "FOREIGN KEY (id_pelis) REFERENCES peliculas(id_peli),"
            		+ "FOREIGN KEY (id_tags) REFERENCES tags(id_tag)"            		
            		+ ");");
            
            resul = statement.executeQuery("SELECT id_pelis from tags_peliculas;");
            if (resul.next ()== false) {
            	System.out.println("creo tabla intermedia");
            	res = statement.executeUpdate("INSERT INTO tags_peliculas(id_pelis, id_tags)"
            			+ "VALUES(1,1);");
            	res = statement.executeUpdate("INSERT INTO tags_peliculas(id_pelis, id_tags)"
            			+ "VALUES(1,2);");
            	res = statement.executeUpdate("INSERT INTO tags_peliculas(id_pelis, id_tags)"
            			+ "VALUES(2,4);");
            }
            
            //TODO Tengo dudas si hay que introducir la tabla intermedia.
            System.out.println("hola1");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios ("
            		+"id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,"
            		+"nombre VARCHAR(100) NOT NULL"
            		+");");
            
            resul = statement.executeQuery("SELECT id_usuario from usuarios;");
            if (resul.next ()== false) {
            	System.out.println("hola2");
            	statement.executeUpdate("INSERT INTO usuarios (nombre) VALUES ('Pepe');");
            	System.out.println("hola3");
            	statement.executeUpdate("INSERT INTO usuarios (nombre) VALUES ('Jose');");
            	System.out.println("hola4");
            	statement.executeUpdate("INSERT INTO usuarios (nombre) VALUES ('Ana');");

            	System.out.println("hola5"); 
            }
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuario_peli("
            		+ "id_usuario INTEGER,"
            		+ "id_pelis INTEGER,"
                    + "tiempo INTEGER default 0,"
            		+ "FOREIGN KEY (id_pelis) REFERENCES peliculas(id_peli),"
            		+ "FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario));");
            
            
            
            System.out.println("paso 3");
            
//            res = statement.executeUpdate("insert into usuarios () values ('')");
//            System.out.println(res);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // Cierre de conexión fallido
                System.err.println(e);
            }
        }
    }
}