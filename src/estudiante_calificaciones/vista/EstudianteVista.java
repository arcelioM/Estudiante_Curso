package estudiante_calificaciones.vista;

import estudiante_calificaciones.dao.EstudianteDao;
import estudiante_calificaciones.model.Estudiantes;
import java.util.Scanner;


public class EstudianteVista {
    
    Scanner sc = new Scanner(System.in);
    private EstudianteDao estudianteDao= new EstudianteDao();
    
    public Integer crear(){
        System.out.println("Creacion de estudiante");
        System.out.println("------------------------------------------- \n \n");
        System.out.print("Cedula: ");
        Integer cedula=sc.nextInt();
        sc.nextLine();
        
        System.out.print("Nombre: ");
        String nombre=sc.nextLine();
        
        System.out.print("Apellido: ");
        String apellido=sc.nextLine();
        
        System.out.print("Edad: ");
        Integer edad=sc.nextInt();
        
        Estudiantes estudiante = new Estudiantes(cedula, nombre, apellido, edad);
        
        Integer fila=estudianteDao.insert(estudiante);
        
        return fila;
        
    }
    
    
    
}
