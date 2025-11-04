
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

    // Acepta 8 argumentos: int ID, 4 Strings, y 3 objetos de entidad.
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

    // --- Setters (Opcionales, pero buena práctica si se modifican los datos) ---
    // (Añadir aquí si es necesario)

    // --- Método toString ---
    @Override
    public String toString() {
        return String.format(
                "ID: %d | DNI: %s | Nombre: %s %s | Edad: %d | Curso: %s %s | Carrera: %s",
                id, dni, nombre, apellido, edad, curso, division, carrera);
    }
}