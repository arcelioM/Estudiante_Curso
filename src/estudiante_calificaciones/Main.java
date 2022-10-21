 package estudiante_calificaciones;

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
            Main.ejecutandoSeleccion(seleccion);
        }
    }
    
    public  static void ejecutandoSeleccion(Integer eleccion){
        
    }
    
}
