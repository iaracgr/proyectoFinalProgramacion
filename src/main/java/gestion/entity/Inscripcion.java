package gestion.entity;

import java.util.UUID;

public class Inscripcion {
        private final String id;
        Alumno alumno;
        Materia materia;
        String cicloLectivo;
        boolean activa;

        public Inscripcion(Alumno alumno, Materia materia, String cicloLectivo, boolean activa) {
                this.id = UUID.randomUUID().toString(); // Genera un ID único automáticamente
                this.alumno = alumno;
                this.materia = materia;
                this.cicloLectivo = cicloLectivo;
                this.activa = activa;
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

        public boolean isActiva() {
                return activa;
        }

        public void setActiva(boolean activa) {
                this.activa = activa;
        }
}

