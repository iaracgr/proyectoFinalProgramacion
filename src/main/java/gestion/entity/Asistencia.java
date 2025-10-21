package gestion.entity;

import java.util.Date;
import java.util.UUID;

public class Asistencia {
    private final String id;         // ID único generado automáticamente
    private Alumno alumno;
    private Materia materia;
    private String cicloLectivo;     // Ej: "2025"
    private Date fecha;
    private boolean presente;

    // Constructor
    public Asistencia(Alumno alumno, Materia materia, String cicloLectivo, Date fecha, boolean presente) {
        this.id = UUID.randomUUID().toString(); // Genera un ID único automáticamente
        this.alumno = alumno;
        this.materia = materia;
        this.cicloLectivo = cicloLectivo;
        this.fecha = fecha;
        this.presente = presente;
    }

    public String getId() {
        return id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCicloLectivo() {
        return cicloLectivo;
    }

    public void setCicloLectivo(String cicloLectivo) {
        this.cicloLectivo = cicloLectivo;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    @Override
    public String toString() {
        return String.format(
                "Asistencia[id=%s, alumno=%s %s, materia=%s, fecha=%s, presente=%s]",
                id,
                alumno.getNombre(),
                alumno.getApellido(),
                materia.getNombre(),
                fecha,
                presente ? "Sí" : "No"
        );
    }
}
