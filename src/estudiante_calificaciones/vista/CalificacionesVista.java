
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
    
    //VARIABLES NECESARIAS PARA EJECUTAR PROCESOS DE ESTA CLASE
    Scanner sc = new Scanner(System.in);
    private final CalificacionesDao calificacionesDao=new CalificacionesDao();
    private final EstudianteDao estudianteDao= new EstudianteDao();
    private final MateriaDao materiaDao= new MateriaDao();
    
    /**
     * lISTARA TODAS LAS CALIFICACIONES DISPONIBLES
     */
    public void listarTodo(){
        
        System.out.println("Listado de calificaciones \n");
        List<Estudiantes> estudiantes  = estudianteDao.listAll(); //BUSCANDO ESTUDIANTE
        
        //RECORIENDO LA LISTA DE ESTUDIANTE PARA MOSTRAR
        for(Estudiantes estudiante : estudiantes){
            System.out.println(estudiante);
            System.out.println("Calificaciones registrads: ");
            
            //BUSCANDO CALIFICACIONES DE CADA ESTUDIANTE DISPONIBLES
            Calificaciones calif= new Calificaciones();
            calif.setEstudianteId(estudiante.getId());
            List<Calificaciones> calificaciones= calificacionesDao.listByEstudianteId(calif); //BUSCANDO EN BD
            
            //RECORRIENDO CALIFICACIONES DEL ESTUDIANTES QUE ESTAN DISPONIBLE
            for(Calificaciones calificacion : calificaciones){
                
                //BUSCANDO NOMBRE DE LA MATERIA A LA QUE PERTENECE LA NOTA
                Materias materia = new Materias(calificacion.getMateriaId());
                materia= materiaDao.listById(materia); //BUSCANDO NOMBRE DE MATERIA
                
                System.out.println("Materia: "+materia.getNombre() + " | Nota: "+calificacion.getNota());
            }   
       }
        
        
        System.out.println("Proceso de listado terminado (Enter para continuear)...........");
        sc.nextLine(); //ESTO ES PARA QUE TENGA PRESIONAR EL ENTER
    }
    
    /**
     * HARA BUSQUEDA DE CALIFICACIONES POR ID DE ESTUDIANTE
     * @param cedula 
     */
    public void listarCalificacionPorId(Integer cedula){
        
        System.out.println("Listado de calificaciones \n");
        
        //DEFINIENDO OBJETO ESTUDIANTE QUE SE USARA PARA CONTENER LA INFORMACION DE LA CEDULA PARA HACER BUSQUEDA
        Estudiantes estudiante  = new Estudiantes();
        estudiante.setCedula(cedula);
        
        //BUSCANDO ESTUDIANTE POR CEDULA
        estudiante = estudianteDao.listByCedula(estudiante);
        
        System.out.println(estudiante);
        System.out.println("Calificaciones registrads: ");
        //DEFINIENDO OBJETO DE CALIFICACIONES QUE USARA PARA CONTENER ID DE ESTUDIANTE PARA HACER BUSQUEDA
        Calificaciones calif= new Calificaciones();
        calif.setEstudianteId(estudiante.getId());
        //BUSCANDO CALIFICACIONES
        List<Calificaciones> calificaciones= calificacionesDao.listByEstudianteId(calif);

        //RECORREIDNO CALIFICACIONES DE ESTUDIANTE QUE SE BUSCO
        for(Calificaciones calificacion : calificaciones){

            //BUSCANDO NOMBRE DE MATERIA A LA QUE CORRESPONDE CADA CALIFICACION PARA MOSTRAR
            Materias materia = new Materias(calificacion.getMateriaId());
            materia= materiaDao.listById(materia);

            System.out.println("Materia: "+materia.getNombre() + " | Nota: "+calificacion.getNota());
        }   

        System.out.println("Proceso de listado terminado (Enter para continuear)...........");
        sc.nextLine();
    }
    
    /**
     * AGREGARA NOTAS A UN ESTUDIANTE YA CREADO
     * @param estudiante 
     */
    public void ingresarCalificaciones(Estudiantes estudiante){
        
        System.out.println("Agregando notas \n\n");
        System.out.println("O - salir");
        
        //BUSCARA LAS MATERIAS DISPONIBLES PARA INGRESAR NOTA
        List<Materias> materiasDisponibles = materiaDao.listAll();
        for(Materias materia : materiasDisponibles){
            System.out.println(materia.getId() + " - "+materia.getNombre());
        }
        
        Integer eleccion = 1;
        
        //PARA AGREGAR NOTAS
        while(eleccion>0){
            //ESCRIBIR CODIGO DE LA MATERIA
            System.out.println("Escriba codigo de materia, para ingresar nota (O para salir) : ");
            eleccion = sc.nextInt();
            sc.nextLine(); //LIMPIAR BUFFER
            
            //VALIDAR SI EL CODIGO ES VALIDO
            if(eleccion>0){
                
                //ESCRIBIENDO NOTA 
                System.out.println("Escriba valor de nota: ");
                Double nota=sc.nextDouble();
                sc.nextLine();
            
                //DEFINIENDO OBJETO DE CALIFICACION PARA REGISTRAR
                Calificaciones calificacion = new Calificaciones();
                calificacion.setEstudianteId(estudiante.getId());
                calificacion.setMateriaId(eleccion);
                calificacion.setNota(nota);
                //REGISTRANDO EN BASE DE DATOS
                Integer fila = calificacionesDao.insert(calificacion);
                
                //VALIDAR EXITO DE REGISTRO
                if(fila==1){
                    System.out.println("Nota registrada con exitosamente");
                }else{
                    System.out.println("Fallo en registro de nota");
                }
            }else{
                System.out.println("Saliendo");
            }

        }
        
        System.out.println("Proceso de listado terminado (Enter para continuear)...........");
        sc.nextLine();
    }
    
    /**
     * ACTUALIZANDO NOTA DE UN ESTUDIANTE EN ESPECIFICO
     * @param estudiante 
     */
    public void actualizarNota(Estudiantes estudiante){
        
        //DEFINIENDO OBJETO DE CALIFICACION PARA HACER BUSQUEDA DE LAS NOTAS QUE PERTENECEN A UN ESTUDIANTE
        Calificaciones calif = new Calificaciones();
        calif.setEstudianteId(estudiante.getId());
        //HACIENDO LA BUSQUEDA
        List<Calificaciones> calificaciones= calificacionesDao.listByEstudianteId(calif);
        
        //RECORRIENDO LAS CALIFIACIONES REGISTRADA PARA EL ESTUDAINTE BUSCADO
        for(Calificaciones calificacion : calificaciones){

            //BUSCANDO NOMBRE DE LA MATERIA CORRESPONDIENTE ASOCIADO A CADA NOTA
            Materias materia = new Materias(calificacion.getMateriaId());
            materia= materiaDao.listById(materia);

            System.out.println("Codigo de nota: "+calificacion.getId()+ " - Materia: "+materia.getNombre() + "Nota: "+calificacion.getNota());
        }
        
        Integer agregarNota=1;
        //ACTUALIZANDO NOTA
        while(agregarNota==1){
            //SE ESCRIBIR CODIGO DE LA NOTA QUE SE QUIERE ACTUALIZAR
            System.out.print("Escriba el codigo de la nota a actualizar: ");
            Integer codigo=sc.nextInt();
            sc.nextLine(); //LIMPIANDO BUFFER

            //DEIFINIENDO NUEVO VALOR DE LA NOTA A ACTUALIZAR
            System.out.print("Escriba el nuevo valor de la nota: ");
            Double nota=sc.nextDouble();
            sc.nextLine(); //LIMPIANDO BUFFER
            
            //DEFINIENDO OBJETO DE CALIFIACION CON LOS DATOS NECESARIOS PARA ACTUALIZAR NOTA
            Calificaciones calificacionNueva = new Calificaciones(codigo);
            //BUSCANDO DATOS COMPLETO DE LA CALIFICACION
            calificacionNueva=calificacionesDao.listById(calificacionNueva);
            calificacionNueva.setNota(nota); //AGREGANDO NUEVO VALOR DE LA NOTA QUE SE VA ACTUALIZAR
            //ACTUALIZANDO CALIFICACION
            Integer fila = calificacionesDao.update(calificacionNueva);
            
            //VALIDANDO EXITO DE ACTUALIZACION
            if(fila==1){
                System.out.println("Nota actualizada correctamente");
            }else{
                System.out.println("Error en actualizacion");
            }
            
            //VERIFICANDO SI EL USUARIO DESEA AGREGAR
            System.out.println("Desea agregar otra nota");
            System.out.println(" 1 - si | 0 - No");
            
            agregarNota=sc.nextInt();
            sc.nextLine();
        }
        
        System.out.println("Proceso de listado terminado (Enter para continuear)...........");
        sc.nextLine();
        
    }
}
