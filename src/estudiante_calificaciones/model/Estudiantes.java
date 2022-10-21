
package estudiante_calificaciones.model;

import java.time.LocalDate;

public class Estudiantes {
    
    private Integer id;
    private Integer cedula;
    private String nombre;
    private String apellido;
    private Integer edad;
    private LocalDate fechaCreacion;

    public Estudiantes() {
    }

    public Estudiantes(Integer id) {
        this.id = id;
    }

    public Estudiantes(Integer cedula, String nombre, String apellido, Integer edad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public Estudiantes(Integer id, Integer cedula, String nombre, String apellido, Integer edad, LocalDate fechaCreacion) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", fechaCreacion=" + fechaCreacion + '}';
    }
    
    
}
