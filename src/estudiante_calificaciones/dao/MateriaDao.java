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
    
    private final Connections conexion= new Connections();
    
    public List<Materias> listAll(){
        
        try{
            //OBTIENE CONEXION
            Connection conexionBD=conexion.getConexion();
            
            //EJECUTAR QUERY
            String query="SELECT * FROM materias";
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
    
    
    
    public Materias listById(Materias materia){
        
        if(materia==null || materia.getId()==null || materia.getId()<=0){
            return materia;
        }

        Connection conexionBD=conexion.getConexion();
        String query="SELECT nombre FROM materias WHERE id=?";
        Integer id=materia.getId();
        
        try{
            PreparedStatement ps=conexionBD.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                 
                materia.setNombre(rs.getString(1));
                
            }
            
            return materia;
        }catch(SQLException e){
            e.getMessage();
            return materia;
        }
    }
    
    public Integer insert(Materias materia){
        
        if(materia==null || materia.getId()!=null){
            return 0;
        }
        
        try{
            
            Connection conexionBD=conexion.getConexion();
            String query="INSERT INTO materias (nombre) VALUES(?)";
            PreparedStatement ps=conexionBD.prepareStatement(query);

            ps.setString(1, materia.getNombre());
           
            
            Integer filas=ps.executeUpdate();
            
            return filas;
        }catch(SQLException e){
            e.printStackTrace(System.out);
            return 0;
        }
     
    }
    
    public Integer update(Materias materia){
        
        if(materia==null || materia.getId()==null || materia.getId()<=0){
            return 0;
        }
        
        try{
            Connection conexionBD=conexion.getConexion();
            String query="UPDATE materias SET nombre=? WHERE id=?";
            PreparedStatement ps=conexionBD.prepareStatement(query);

            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getId());
            
            Integer filas=ps.executeUpdate();
            
            return filas;
        }catch(SQLException e){
            e.getMessage();
            return 0;
        }
    }
}
