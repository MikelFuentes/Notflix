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
                    "id_peli int NOT NULL AUTO_INCREMENT,\n" +
                    "nombre VARCHAR(100) NOT NULL,\n" +
                    "fecha VARCHAR(100) NOT NULL,\n" +
                    "director VARCHAR(100) NOT NULL,\n" +
                    "archivo VARCHAR(100) NOT NULL,\n" +
                    "link VARCHAR(120) NOT NULL,\n" +
                    "fk_tags int NOT NULL," +

                    "PRIMARY KEY (id_peli),\n" +
                    "FOREIGN KEY (fk_tags) REFERENCES tags(id_tag)" +
                    ");");
//            res = statement.executeUpdate("insert into peliculas () values ('')");
//            System.out.println(res);

            statement.executeUpdate("create table usuarios(" +
                    "id_usuario int NOT NULL AUTO_INCREMENT,\n" +
                    "nombre VARCHAR(100) NOT NULL,\n" +
                    "password VARCHAR(100) NOT NULL,\n" +
                    "rango int NOT NULL,\n" +

                    "PRIMARY KEY (id_usuario)" +
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