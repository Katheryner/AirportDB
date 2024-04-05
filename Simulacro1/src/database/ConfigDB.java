package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    static Connection objConnection = null;

    public static Connection openConnection() {
        String url = "jdbc:mysql://bzjbep9pnzybj97efdks-mysql.services.clever-cloud.com:3306/bzjbep9pnzybj97efdks";
        String user = "urvzf9szqzlwv575";
        String password = "b5N5buTRehBBZPwWkAEV";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            objConnection = DriverManager.getConnection(url, user, password);
            System.out.println("se conectó correctamente");

        } catch (ClassNotFoundException e) {
            System.out.println("Error >> Driver no Instalado");
        } catch (
                SQLException e) {
            System.out.println("Error >> no se pudo establecer una conexion con la base de datos ");

        }return objConnection;
    }

    public static void closeConnection() {
        try {
            if (objConnection != null) {
                objConnection.close();
            }
        } catch (Exception e) {
            System.out.println("error");
        }

    }

}