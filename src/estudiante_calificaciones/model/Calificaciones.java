
package estudiante_calificaciones.model;


public class Calificaciones {
    
    private Integer id;
    private Double nota;
    private Integer materiaId;
    private Integer estudianteId;

    public Calificaciones(Integer id, Double nota, Integer materiaId, Integer estudianteId) {
        this.id = id;
        this.nota = nota;
        this.materiaId = materiaId;
        this.estudianteId = estudianteId;
    }

    public Calificaciones(Integer id) {
        this.id = id;
    }

    public Calificaciones() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Integer getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Integer materiaId) {
        this.materiaId = materiaId;
    }

    public Integer getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

    @Override
    public String toString() {
        return "Calificaciones{" + "id=" + id + ", nota=" + nota + ", materiaId=" + materiaId + ", estudianteId=" + estudianteId + '}';
    }
    
    
}
