
package estudiante_calificaciones.vista;

import estudiante_calificaciones.dao.CalificacionesDao;
import estudiante_calificaciones.dao.EstudianteDao;
import estudiante_calificaciones.dao.MateriaDao;
import estudiante_calificaciones.model.Calificaciones;
import estudiante_calificaciones.model.Estudiantes;
import estudiante_calificaciones.model.Materias;
import java.util.List;
import java.util.Scanner;


public class CalificacionesVista {
    
    Scanner sc = new Scanner(System.in);
    private final CalificacionesDao calificacionesDao=new CalificacionesDao();
    private final EstudianteDao estudianteDao= new EstudianteDao();
    private final MateriaDao materiaDao= new MateriaDao();
    
    public void listarTodo(){
        
        System.out.println("Listado de calificaciones \n");
        List<Estudiantes> estudiantes  = estudianteDao.listAll();
        
        
        for(Estudiantes estudiante : estudiantes){
            System.out.println(estudiante);
            System.out.println("Calificaciones registrads: ");
            Calificaciones calif= new Calificaciones();
            calif.setEstudianteId(estudiante.getId());
            List<Calificaciones> calificaciones= calificacionesDao.listByEstudianteId(calif);
            
            for(Calificaciones calificacion : calificaciones){
                
                Materias materia = new Materias(calificacion.getMateriaId());
                materia= materiaDao.listById(materia);
                
                System.out.println("Materia: "+materia.getNombre() + "Nota: "+calificacion.getNota());
            }   
       }
        
        
        System.out.println("Proceso de listado terminado...........");
    }
    
    public void listarCalificacionPorId(Integer cedula){
        
        System.out.println("Listado de calificaciones \n");
        
        Estudiantes estudiante  = new Estudiantes();
        estudiante.setCedula(cedula);
        
        estudiante = estudianteDao.listByCedula(estudiante);
        
        System.out.println(estudiante);
        System.out.println("Calificaciones registrads: ");
        Calificaciones calif= new Calificaciones();
        calif.setEstudianteId(estudiante.getId());
        List<Calificaciones> calificaciones= calificacionesDao.listByEstudianteId(calif);

        for(Calificaciones calificacion : calificaciones){

            Materias materia = new Materias(calificacion.getMateriaId());
            materia= materiaDao.listById(materia);

            System.out.println("Materia: "+materia.getNombre() + "Nota: "+calificacion.getNota());
        }   

    }
    
    
}
