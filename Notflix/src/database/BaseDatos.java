package database;

import java.sql.*;
//import org.sqlite.SQLiteConfig;

public class BaseDatos {
    public static void main(String[] args) {

        Class.forName("org.sqlite.JDBC");

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jbdc:sqlite:src/database/BaseDatos");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(100);

//        ahora hacemos la tabla y cambios que queramos en ella con statement.executeUpdate(action)
//        para meter datos, en cambio, hacemos int res = statement.executeUpdate(data) sout(res)

            statement.executeUpdate("DROP TABLE IF EXISTS peliculas");
            statement.executeUpdate("DROP TABLE IF EXISTS tags");
            statement.executeUpdate("DROP TABLE IF EXISTS usuarios");

            statement.executeUpdate("create table tags(" +
                    "id_tag int not null auto_increment," +
                    " nombre_tag varchar(150) not null)," +

                    "PRIMARY key(id_tag)" +
                    ");");
            int res = statement.executeUpdate("insert into tags (nombre_tag) values ('terror')");
            System.out.println(res);
            res = statement.executeUpdate("insert into tags (nombre_tag) values ('accion')");
            System.out.println(res);
            res = statement.executeUpdate("insert into tags (nombre_tag) values ('romance')");
            System.out.println(res);
            res = statement.executeUpdate("insert into tags (nombre_tag) values ('terror;romance')");
            System.out.println(res);

            statement.executeUpdate("create table peliculas(" +
                    "" +
                    ");");
//            res = statement.executeUpdate("insert into peliculas () values ('')");
//            System.out.println(res);

            statement.executeUpdate("create table usuarios(" +
                    "" +
                    ");");
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
//        -- Script de nootflix
//        use nootflix;
//        DROP TABLE IF EXISTS peliculas;
//        DROP TABLE IF EXISTS tags;
//        DROP TABLE IF EXISTS usuarios;
//
//        CREATE TABLE tags(
//
//                id_tag int NOT NULL AUTO_INCREMENT,
//        nombre_tag VARCHAR(150) NOT NULL,
//
//        PRIMARY KEY (id_tag)
//);
//
//        INSERT INTO tags (nombre_tag) VALUES("terror");
//        INSERT INTO tags (nombre_tag) VALUES("accion");
//        INSERT INTO tags (nombre_tag) VALUES("romance");
//        INSERT INTO tags (nombre_tag) VALUES("terror;romance");
//
//        CREATE TABLE peliculas(
//
//                id_peli int NOT NULL AUTO_INCREMENT,
//        nombre VARCHAR(100) NOT NULL,
//        fecha VARCHAR(100) NOT NULL,
//        director VARCHAR(100) NOT NULL,
//        archivo VARCHAR(100) NOT NULL,
//        link VARCHAR(120) NOT NULL,
//        fk_tags int NOT NULL,
//
//                PRIMARY KEY (id_peli),
//                FOREIGN KEY (fk_tags) REFERENCES tags(id_tag)
//);
//
//        INSERT INTO peliculas (nombre, fecha, director, archivo, link, fk_tags) VALUES("titanic", "02-02-1999", "Mikel", "./peliculas/titanic.mp4", "http://foto.com/titanic", 1);
//        INSERT INTO peliculas (nombre, fecha, director, archivo, link, fk_tags) VALUES("spiderman", "02-02-2005", "Alex", "./peliculas/spiderman.mp4", "http://foto.com/mc", 3);
//
//        CREATE TABLE usuarios(
//
//                id_usuario int NOT NULL AUTO_INCREMENT,
//        nombre VARCHAR(100) NOT NULL,
//        password VARCHAR(100) NOT NULL,
//        rango int NOT NULL,
//
//                PRIMARY KEY (id_usuario)
//);

