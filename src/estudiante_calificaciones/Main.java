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
            System.out.println("7 - Ingresar nuevo estudiante");
            System.out.println("8 - Agregar Materias");
            System.out.println("9 - Actualizar Materias");
            
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
                
                
                Estudiantes estudiante = estudianteVista.buscarPorCedula();
                calificacionesVista.ingresarCalificaciones(estudiante);
                break;
                
            case 4:
                System.out.println("Desea ver estudiante disponible (1 - si | 0 - no)");
                Integer eleccionListar=sc.nextInt();
                sc.nextLine();
                if(eleccionListar==1){
                   estudianteVista.buscarTodo(); 
                }
                
                Estudiantes estudianteBusq = estudianteVista.buscarPorCedula();
                calificacionesVista.actualizarNota(estudianteBusq);
                break;
                
            case 5:
                estudianteVista.buscarTodo();
                break;
            case 6:
                System.out.println("Desea ver estudiante disponible (1 - si | 0 - no)");
                Integer eleccionListar6=sc.nextInt();
                sc.nextLine();
                if(eleccionListar6==1){
                   estudianteVista.buscarTodo(); 
                }
                
                
                Estudiantes estudiante6 = estudianteVista.buscarPorCedula();
                estudianteVista.actualizar(estudiante6);
                break;
                
            case 7:
               Estudiantes estudiante7 = estudianteVista.crear();
                System.out.println("Desea agregar notas (1 - si | 0 - no)");
                Integer eleccion7=sc.nextInt();
               if(eleccion7>0){
                   calificacionesVista.ingresarCalificaciones(estudiante7);
               }
               break;
            
            case 8:
                Integer fila=materiasVista.crear();
                if(fila>0){
                    System.out.println("Agregado exitosamente");
                }else{
                    System.out.println("Error de creacion");
                }
                System.out.println("Proceso de listado terminado (Enter para continuear)...........");
                sc.nextLine();
                
                break;
            case 9:
                materiasVista.listarMaterias();
                materiasVista.actualizar();
                break;
            default:
                System.out.println("Eleccion no valida (Presione Enter para continuar)");
                sc.nextLine();
        }
    }
    
}
