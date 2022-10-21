
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
    
    public void ingresarCalificaciones(Estudiantes estudiante){
        
        System.out.println("Agregando notas \n\n");
        System.out.println("O - salir");
        
        List<Materias> materiasDisponibles = materiaDao.listAll();
        for(Materias materia : materiasDisponibles){
            System.out.println(materia.getId() + " - "+materia.getNombre());
        }
        
        Integer eleccion = 1;
        
        while(eleccion>0){
            System.out.println("Escriba codigo de materia, para ingresar nota (O para salir) : ");
            eleccion = sc.nextInt();
            sc.nextLine();
            
            System.out.println("Escriba valor de nota: ");
            Double nota=sc.nextDouble();
            sc.nextLine();
            
            if(eleccion>0){
                Calificaciones calificacion = new Calificaciones();
                calificacion.setEstudianteId(estudiante.getId());
                calificacion.setMateriaId(eleccion);
                calificacion.setNota(nota);
                Integer fila = calificacionesDao.insert(calificacion);
                
                
                if(fila==1){
                    System.out.println("Nota registrada con exitosamente");
                }else{
                    System.out.println("Fallo en registro de nota");
                }
            }else{
                System.out.println("Saliendo");
            }

        }
        
    }
    
    public void actualizarNota(Estudiantes estudiante){
        
        Calificaciones calif = new Calificaciones();
        calif.setEstudianteId(estudiante.getId());
        List<Calificaciones> calificaciones= calificacionesDao.listByEstudianteId(calif);
        
        for(Calificaciones calificacion : calificaciones){

            Materias materia = new Materias(calificacion.getMateriaId());
            materia= materiaDao.listById(materia);

            System.out.println("Codigo de nota: "+calificacion.getId()+ " - Materia: "+materia.getNombre() + "Nota: "+calificacion.getNota());
        }
        
        Integer agregarNota=1;
        
        while(agregarNota==1){
            System.out.print("Escriba el codigo de la nota a actualizar: ");
            Integer codigo=sc.nextInt();
            sc.nextLine();

            System.out.print("Escriba el nuevo valor de la nota: ");
            Double nota=sc.nextDouble();
            sc.nextLine();
            
            Calificaciones calificacionNueva = new Calificaciones(codigo);
            calificacionNueva=calificacionesDao.listById(calificacionNueva);
            calificacionNueva.setNota(nota);
            Integer fila = calificacionesDao.update(calificacionNueva);
            if(fila==1){
                System.out.println("Nota actualizada correctamente");
            }else{
                System.out.println("Error en actualizacion");
            }
            
            System.out.println("Desea agregar otra nota");
            System.out.println(" 1 - si | 0 - No");
            
            agregarNota=sc.nextInt();
            sc.nextLine();
        }
        
        System.out.println("Proceso terminado ..............");
        
    }
}
