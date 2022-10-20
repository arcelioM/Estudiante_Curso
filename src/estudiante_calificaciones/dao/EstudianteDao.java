
package estudiante_calificaciones.dao;

import estudiante_calificaciones.dao.connection.Connections;
import estudiante_calificaciones.model.Estudiantes;
import java.util.List;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

public class EstudianteDao {
 
    private Connections conexion= new Connections();
    
    public List<Estudiantes> listAll(){
        
        try{
            
            Connection conexionBD=this.conexion.getConexion(); //SE CREA LA CONEXION A LA BD
            String query="SELECT * FROM estudiante";
            List<Estudiantes> allEstudiantes=new ArrayList<>();
            
            PreparedStatement ps=conexionBD.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            
            //AGRAGANDO ESTUDIANTE DISPONIBLE A LA LISTA, PARA RETORNAR
            while(rs.next()){
                Estudiantes estudiante=new Estudiantes();

                estudiante.setId(rs.getInt(1));
                estudiante.setCedula(rs.getInt(2));
                estudiante.setNombre(rs.getString(3));
                estudiante.setApellido(rs.getString(4));
                estudiante.setEdad(rs.getInt(5));
                estudiante.setFechaCreacion(LocalDate.parse(rs.getString(6)));
                
                allEstudiantes.add(estudiante);
            }
            
            return allEstudiantes;
            
        }catch(SQLException e){
            e.getMessage();
            return null;
        }

    }
    
    public Estudiantes listById(Estudiantes estudiante){
        
        //VALIDAR SI LOS DATOS SEAN VALIDOS PARA EJECUTAR QUERY, Y ASI EVITAR ERRORES
        if(estudiante==null || estudiante.getId()==null || estudiante.getId()<=0){
            return estudiante;
        }
        
        try{

            Connection conexionBD=conexion.getConexion();
            String query="SELECT * FROM estudiante WHERE id=?";
            Integer id=estudiante.getId();
            
            PreparedStatement ps=conexionBD.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
         
                estudiante.setCedula(rs.getInt(2));
                estudiante.setNombre(rs.getString(3));
                estudiante.setApellido(rs.getString(4));
                estudiante.setEdad(rs.getInt(5));
                estudiante.setFechaCreacion(LocalDate.parse(rs.getString(6)));
                
            }
            
            return estudiante;
        }catch(SQLException e){
            e.getMessage();
            return null;
        }
        
    }
}
