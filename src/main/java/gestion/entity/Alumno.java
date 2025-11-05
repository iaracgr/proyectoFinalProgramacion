
package gestion.entity; // declaración del paquete

public class Alumno {

// --- Atributos de la clase ---
private int id;
private String nombre;
private String apellido;
private int edad;
private String dni;
private String carrera;
private String curso;
private String division;


// Constructor
public Alumno(int id, String nombre, String apellido, int edad, String dni,
                String carrera, String curso, String division) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
    this.dni = dni;
    this.carrera = carrera;
    this.curso = curso;
    this.division = division;
}

// --- Getters ---
public int getId() { return id; }
public String getNombre() { return nombre; }
public String getApellido() { return apellido; }
public int getEdad() { return edad; }
public String getDni() { return dni; }
public String getCarrera() { return carrera; }
public String getCurso() { return curso; }
public String getDivision() { return division; }

//--- Setters ---

public void setNombre(String nombre) { this.nombre = nombre; }
public void setApellido(String apellido) { this.apellido = apellido; }
public void setEdad(int edad) { this.edad = edad; }
public void setDni(String dni) { this.dni = dni; }
public void setCarrera(String carrera) { this.carrera = carrera; }
public void setCurso(String curso) { this.curso = curso; }
public void setDivision(String division) { this.division = division; }


// --- Método toString ---
@Override
public String toString() {
    return String.format(
            "ID: %d | DNI: %s | Nombre: %s %s | Edad: %d | Curso: %s %s | Carrera: %s",
            id, dni, nombre, apellido, edad, curso, division, carrera);
}
}