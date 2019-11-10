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