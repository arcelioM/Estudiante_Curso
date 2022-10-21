
package estudiante_calificaciones.dao;

import estudiante_calificaciones.dao.connection.Connections;
import estudiante_calificaciones.model.Calificaciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CalificacionesDao {
    
    private final Connections conexion= new Connections();
    
    public List<Calificaciones> listAll(){
        
        try{
            
            Connection conexionBD=this.conexion.getConexion(); //SE CREA LA CONEXION A LA BD
            String query="SELECT * FROM calificaciones";
            List<Calificaciones> allCalificaciones=new ArrayList<>();
            
            PreparedStatement ps=conexionBD.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            
            //AGRAGANDO ESTUDIANTE DISPONIBLE A LA LISTA, PARA RETORNAR
            while(rs.next()){
                Calificaciones calificaciones=new Calificaciones();

                calificaciones.setId(rs.getInt(1));
                calificaciones.setNota(rs.getDouble(2));
                calificaciones.setMateriaId(rs.getInt(3));
                calificaciones.setEstudianteId(rs.getInt(4));
                
                allCalificaciones.add(calificaciones);
            }
            
            return allCalificaciones;
            
        }catch(SQLException e){
            e.getMessage();
            return null;
        }

    }
    
    public Calificaciones listById(Calificaciones calificaciones){
        
        //VALIDAR SI LOS DATOS SEAN VALIDOS PARA EJECUTAR QUERY, Y ASI EVITAR ERRORES
        if(calificaciones==null || calificaciones.getId()==null || calificaciones.getId()<=0){
            return calificaciones;
        }
        
        try{

            Connection conexionBD=conexion.getConexion();
            String query="SELECT * FROM calificaciones WHERE id=?";
            Integer id=calificaciones.getId();
            
            PreparedStatement ps=conexionBD.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
         
               calificaciones.setId(rs.getInt(1));
                calificaciones.setNota(rs.getDouble(2));
                calificaciones.setMateriaId(rs.getInt(3));
                calificaciones.setEstudianteId(rs.getInt(4));

            }
            
            return calificaciones;
        }catch(SQLException e){
            e.getMessage();
            return null;
        }
        
    }
    
    public List<Calificaciones> listByEstudianteId(Calificaciones calificaciones){
        
        if(calificaciones==null || calificaciones.getEstudianteId()==null || calificaciones.getEstudianteId()<=0){
            return null;
        }

        try{
            Connection conexionBD=conexion.getConexion();
            String query="SELECT * FROM calificaciones WHERE estudiante_id=?";
            Integer estudianteId=calificaciones.getEstudianteId();
            PreparedStatement ps=conexionBD.prepareStatement(query);
            ps.setInt(1, estudianteId);
            
            List<Calificaciones> calificacionesAll= new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                
                Calificaciones calificacion = new Calificaciones();
                calificacion.setId(rs.getInt(1));
                calificacion.setNota(rs.getDouble(2));
                calificacion.setMateriaId(rs.getInt(3));
                calificacion.setEstudianteId(rs.getInt(4));
                
                calificacionesAll.add(calificacion);
            }
            
            return calificacionesAll;
        }catch(SQLException e){
            e.printStackTrace(System.out);
            return null;
        }
    }
    
    
    public Integer insert(Calificaciones calificaciones){
        
        if(calificaciones==null || calificaciones.getId()!=null){
            return 0;
        }
        
        
        
        try{
            Connection conexionBD=conexion.getConexion();
            String query="INSERT INTO calificaciones (nota,materia_id,estudiante_id) VALUES(?,?,?)";
            PreparedStatement ps=conexionBD.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ps.setDouble(1, calificaciones.getNota());
            ps.setInt(2, calificaciones.getMateriaId());
            ps.setInt(3, calificaciones.getEstudianteId());
            
            Integer filas=ps.executeUpdate();
            
            
            return filas;
        }catch(SQLException e){
            e.getMessage();
            return 0;
        }
    }
    
    public Integer update(Calificaciones calificaciones){
        
        if(calificaciones==null || calificaciones.getId()==null || calificaciones.getId()<=0){
            return 0;
        }
        
        try{
            Connection conexionBD=conexion.getConexion();
            String query="UPDATE calificaciones SET nota=?, materia_id=?, estudiante_id=? WHERE id=?";
            PreparedStatement ps=conexionBD.prepareStatement(query);
            
            ps.setDouble(1, calificaciones.getNota());
            ps.setInt(2, calificaciones.getMateriaId());
            ps.setInt(3, calificaciones.getEstudianteId());
            ps.setInt(4, calificaciones.getId());
            
            Integer filas=ps.executeUpdate(); //Retornara la cantidad de registro ingresados
            
            return filas;
        }catch(SQLException e){
            e.getMessage();
            return 0;
        }
    }
}
