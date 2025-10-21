package gestion.service;

import gestion.entity.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnoService {

        private final List<Alumno> listaAlumnos = new ArrayList<>();

        // Agregar alumno (valida DNI único)
        public void agregarAlumno(Alumno alumno) {
            if (buscarPorDni(alumno.getDni()) != null) {
                throw new IllegalArgumentException("Ya existe un alumno con DNI: " + alumno.getDni());
            }
            listaAlumnos.add(alumno);
        }

        // Buscar alumno por DNI
        public Alumno buscarPorDni(String dni) {
            for (Alumno a : listaAlumnos) {
                if (a.getDni().equals(dni)) return a;
            }
            return null;
        }

        // Listar todos los alumnos
        public List<Alumno> listarTodos() {
            return new ArrayList<>(listaAlumnos);
        }

        // Eliminar alumno
        public boolean eliminarAlumno(Alumno alumno) {
            return listaAlumnos.remove(alumno);
        }
    }

