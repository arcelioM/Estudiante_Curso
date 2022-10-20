package estudiante_calificaciones.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    private  final String USUARIO="root";
    private  final String CONTRASEÑA="holaCOMO";
    private  final String  HOST="localhost";
    private  final String DB="matriculas";
    private  final String PUERTO="3306";

    public Connection getConexion(){
        
        try{
            Connection conn=DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PUERTO+"/"+DB, USUARIO, CONTRASEÑA);
           return conn;
        }catch(SQLException e){
            e.printStackTrace(System.out);
            return null;
        } 
    }
}
