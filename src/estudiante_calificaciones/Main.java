 package estudiante_calificaciones;

import estudiante_calificaciones.model.Estudiantes;
import estudiante_calificaciones.vista.CalificacionesVista;
import estudiante_calificaciones.vista.EstudianteVista;
import estudiante_calificaciones.vista.MateriasVista;
import java.util.Scanner;


public class Main {

    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        // TODO code application logic here
        Integer seleccion=1;
        System.out.println("Bienvenido a sistemas \n\n");
        while(seleccion>0){
            
            System.out.println("Menu de opciones");
            System.out.println("0 - Salir");
            System.out.println("1 - Ver calificaciones");
            System.out.println("2 - Buscar calificaciones");
            System.out.println("3 - Ingresar calificaciones");
            System.out.println("4 - Actualizar Calificaciones");
            System.out.println("5 - Ver estudiantes");
            System.out.println("6 - Actualizar estudiante");
            
            seleccion = sc.nextInt();
            sc.nextLine();
            if(seleccion>0){
                Main.ejecutandoSeleccion(seleccion);
            }else{
                System.out.println("Saliendo de sistema");
            }
        }
    }
    
    public  static void ejecutandoSeleccion(Integer eleccion){
        CalificacionesVista calificacionesVista = new CalificacionesVista();
        MateriasVista materiasVista = new MateriasVista();
        EstudianteVista estudianteVista = new EstudianteVista();
        switch(eleccion){
            
            case 1:
                calificacionesVista.listarTodo();
                break;
            
            case 2:
                estudianteVista.buscarTodo();
                System.out.print("Escriba numero de cedula de estudiante: ");
                Integer cedula = sc.nextInt();
                calificacionesVista.listarCalificacionPorId(cedula);
                break;
            case 3:
                System.out.println("Desea ver estudiante disponible (1 - si | 0 - no)");
                Integer eleccionVer=sc.nextInt();
                sc.nextLine();
                if(eleccionVer==1){
                   estudianteVista.buscarTodo(); 
                }
                
                System.out.print("Escriba numero de cedula de estudiantee: ");
                Integer cedulaBusq=sc.nextInt();
                sc.nextLine();
                
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCedula(cedulaBusq);
                calificacionesVista.ingresarCalificaciones(estudiante);
                
        }
    }
    
}
