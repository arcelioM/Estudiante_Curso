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
        
        System.out.println("Proceso terminado ..................");
    }
}
