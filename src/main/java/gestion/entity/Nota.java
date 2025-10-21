package gestion.entity;

import java.util.Date;
import java.util.UUID;

public class Nota {
    private final String id;
    Alumno alumno;
    Materia materia;
    String cicloLectivo; // Ej: "2025"
    String tipo; // "Parcial", "Final", etc.
    double valor;
    Date fecha;

    public Nota(Alumno alumno, Materia materia, String cicloLectivo, String tipo, double valor, Date fecha) {
        this.id = UUID.randomUUID().toString(); // Genera un ID único automáticamente
        this.alumno = alumno;
        this.materia = materia;
        this.cicloLectivo = cicloLectivo;
        this.tipo = tipo;
        this.valor = valor;
        this.fecha = fecha;
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

    public String getCicloLectivo() {
        return cicloLectivo;
    }

    public void setCicloLectivo(String cicloLectivo) {
        this.cicloLectivo = cicloLectivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
