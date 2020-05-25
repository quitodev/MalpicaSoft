package malpica;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    // Establezco la conexión con la base de datos remota..
    public static Connection conectar() {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://johnny.heliohost.org/malpicas_app", "malpicas_admin", "@tXWghU@aTWzsF2");
            return con;

        } catch (SQLException e) {

            JOptionPane.showConfirmDialog(null, "Error de conexión!", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }

        return (null);
    }
}
