package estudiante_calificaciones.dao;

import estudiante_calificaciones.dao.connection.Connections;
import estudiante_calificaciones.model.Materias;
import java.util.List;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;


public class MateriaDao {
    
    private Connections conexion= new Connections();
    
    public List<Materias> listAll(){
        
        try{
            //OBTIENE CONEXION
            Connection conexionBD=conexion.getConexion();
            
            //EJECUTAR QUERY
            String query="SELECT * FROM calificaciones";
            PreparedStatement ps=conexionBD.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            
            List<Materias> allMaterias=new ArrayList<>(); //CREACION DE LISTA QUE GUARDARA LAS MATERISA DISPONIBLES
            
            //SE EJECUTARA MIENTRAS EXISTARA REGISTRO POR RECORRER
            while(rs.next()){
                Materias materias= new Materias();
                
                materias.setId(rs.getInt(1));
                materias.setNombre(rs.getString(2));

                //AGREGANDO CADA MATERIAS A LISTA
                allMaterias.add(materias);
            }
            return allMaterias;
        }catch(SQLException e){
            e.getMessage();
            return null;
        }
    }
}
