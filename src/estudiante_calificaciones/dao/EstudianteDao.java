
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
            
            ResultSet rs=null;
            PreparedStatement ps=null;
            Connection conexionBD=this.conexion.getConexion(); //SE CREA LA CONEXION A LA BD
            String query="SELECT * FROM estudiante";
            List<Estudiantes> allEstudiantes=new ArrayList<>();
            
            ps=conexionBD.prepareStatement(query);
            rs=ps.executeQuery();
            
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
}
