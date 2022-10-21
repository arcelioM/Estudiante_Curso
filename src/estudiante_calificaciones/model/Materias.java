
package estudiante_calificaciones.model;


public class Materias {
    
    private Integer id;
    private String nombre;

    public Materias() {
    }

    public Materias(Integer id) {
        this.id = id;
    }

    public Materias(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "id=" + id + " | nombre=" + nombre ;
    }
    
    
}
