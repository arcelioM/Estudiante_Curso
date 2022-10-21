package estudiante_calificaciones.vista;

import estudiante_calificaciones.dao.MateriaDao;
import estudiante_calificaciones.model.Materias;
import java.util.List;
import java.util.Scanner;


public class MateriasVista {
    
    Scanner sc = new Scanner(System.in);
    private final MateriaDao materiaDao= new MateriaDao();
    
    public void listarMaterias(){
        
        System.out.println("Lista de materias disponible");
        
        List<Materias> materias= materiaDao.listAll();
        for(Materias materia : materias){
            System.out.println(materia);
        }
        
        System.out.println("Proceso terminado (Enter para continuear)...........");
        sc.nextLine();
    }
    
    public void listarPorId(Materias materia){
        
        System.out.println("Busqueda por ID \n");
        System.out.println("Datos de materia: ");
        materia=materiaDao.listById(materia);
        System.out.println(materia);
    
        System.out.println("Proceso de listado terminado (Enter para continuear)...........");
        sc.nextLine();
    }
    
    public Integer crear(){
        
        System.out.println("Nueva Materia");
        System.out.print("Nombre: ");
        String nombre=sc.nextLine();
        
        Materias materia= new Materias();
        materia.setNombre(nombre);
 
       return materiaDao.insert(materia);
    } 
    
    public void actualizar(){
        System.out.println("Actualizar materia \n");
        System.out.print("Escriba ID: ");
        Integer id=sc.nextInt();
        sc.nextLine();
        
        Materias materia = new Materias(id);
        materia=materiaDao.listById(materia);
        
        System.out.println("Datos actuales");
        System.out.println(materia);
        
        System.out.print("Escriba nuevo nombre: ");
        String nombre=sc.nextLine();
        materia.setNombre(nombre);
        
        Integer fila=materiaDao.update(materia);
        if(fila==1){
            System.out.println("Actualizacion correcta");
        }else{
            System.out.println("Fallo en actualizacion");
        }
        
        System.out.println("Proceso de actualizacion terminado (Enter para continuear)...........");
        sc.nextLine();
    }
}
