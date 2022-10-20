
package estudiante_calificaciones.model;


public class Materias {
    
    private Integer id;
    private String name;

    public Materias() {
    }

    public Materias(Integer id) {
        this.id = id;
    }

    public Materias(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Materias{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
