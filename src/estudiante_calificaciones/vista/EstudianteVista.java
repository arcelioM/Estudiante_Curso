package estudiante_calificaciones.vista;

import estudiante_calificaciones.dao.EstudianteDao;
import estudiante_calificaciones.model.Estudiantes;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class EstudianteVista {
    
    Scanner sc = new Scanner(System.in);
    private final EstudianteDao estudianteDao= new EstudianteDao();
    
    public Estudiantes crear(){
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
        sc.nextLine();
        
        LocalDate fechaCreacion=LocalDate.now();
        Estudiantes estudiante = new Estudiantes(cedula, nombre, apellido, edad);
        estudiante.setFechaCreacion(fechaCreacion);
        Integer idGenerado=estudianteDao.insert(estudiante);
        
        if(idGenerado>0){
            estudiante.setId(idGenerado);
            System.out.println("Proceso terminado (Enter para continuear)...........");
            sc.nextLine();
            return estudiante;
        }else{
            System.out.println("Error de creacion");
            System.out.println("Proceso terminado (Enter para continuear)...........");
            sc.nextLine();
            return null;
        }
        
    }
    
    
    public Integer actualizar(Estudiantes estudiante){
        
        System.out.println("Actualizando datos");
        System.out.println("------------------------------------------------- \n\n");
        
        System.out.println("Datos actuales de estudiante..");
        System.out.print("1 - Cedula: "+estudiante.getCedula());
        System.out.print(" | 2 - Nombre: "+estudiante.getNombre());
        System.out.print(" | 3 - Apellido: "+estudiante.getApellido());
        System.out.println(" | 4 - Edad: "+estudiante.getEdad()+" \n");
       
        System.out.println("Escriba el numero del dato que desea actualizar..... \n\n");
        Integer seleccion=1;
        
        while (seleccion>0) { 
            System.out.println("Escriba el numero del dato que desea actualizar (0 para salir)..... \n\n");
            seleccion=sc.nextInt();
            sc.nextLine();
            
            switch (seleccion) {
                
                case 0:
                    System.out.println("Saliendo...............");
                    break;
                
                case 1:
                    System.out.print("Cedula: ");
                    Integer cedula=sc.nextInt();
                    estudiante.setCedula(cedula);
                    sc.nextLine();
                    break;
                    
                case 2:
                    System.out.print("Nombre: ");
                    String nombre=sc.nextLine();
                    estudiante.setNombre(nombre);
                    break;
                    
                case 3: 
                    System.out.print("Apellido: ");
                    String apellido=sc.nextLine();
                    estudiante.setApellido(apellido);
                    break;
                    
                case 4:
                    System.out.print("Edad: ");
                    Integer edad=sc.nextInt();
                    sc.nextLine();
                    estudiante.setEdad(edad);
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
        
        Integer filas=estudianteDao.update(estudiante);
        
        System.out.println("Proceso terminado (Enter para continuear)...........");
        sc.nextLine();
        return filas;
    }
    
    public Estudiantes buscarPorCedula(){
        
        System.out.print("Escriba numero de cedula: ");
        Integer cedula=sc.nextInt();
        sc.nextLine();
        
        Estudiantes estudiante= new Estudiantes();
        estudiante.setCedula(cedula);
        estudiante = estudianteDao.listByCedula(estudiante);
        
        System.out.println(estudiante);
        System.out.println("Proceso terminado (Enter para continuear)...........");
        sc.nextLine();
        return estudiante;
    }
    
    public void buscarTodo(){
        
        List<Estudiantes> estudiantes = estudianteDao.listAll();
        
        for(Estudiantes estudiante : estudiantes){
            System.out.println(estudiante);
        }
        System.out.println("Proceso de listado terminado (Enter para continuear)...........");
        sc.nextLine();
    }
}
