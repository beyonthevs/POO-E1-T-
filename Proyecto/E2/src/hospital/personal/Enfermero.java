package hospital.personal;

import hospital.interfaces.AtencionMedica;
import hospital.pacientes.Paciente;
import hospital.sistema.Sistema;

/**
 * Clase base abstracta que representa al personal de enfermeria del hospital.
 * Implementa la interfaz {@link AtencionMedica}.
 */
public abstract class Enfermero implements AtencionMedica {

    private String nombre;
    private final String cedula;

    /** Especialidad clinica de enfermeria. */
    protected String especialidad;

    /** Cantidad de pacientes asignados. */
    protected int noPacientes;

    /** Arreglo de pacientes asignados. */
    protected Paciente[] pacientes;

    /** Sistema hospitalario vinculado. */
    protected Sistema sistema;

    /** Capacidad maxima de pacientes que puede atender un enfermero. */
    public static final int CAPACIDAD_MAXIMA_PACIENTES = 3;

    /**
     * Inicializa un enfermero con su nombre, cedula y especialidad.
     *
     * @param nombre Nombre y apellidos del enfermero.
     * @param cedula Cedula profesional.
     * @param especialidad Especialidad de enfermeria.
     */
    public Enfermero(String nombre, String cedula, String especialidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            this.nombre = "Sin asignar";
        } else {
            this.nombre = nombre.trim();
        }

        if (cedula == null || cedula.trim().isEmpty()) {
            this.cedula = "Sin cedula";
        } else {
            this.cedula = cedula.trim();
        }

        if (especialidad == null || especialidad.trim().isEmpty()) {
            this.especialidad = "General";
        } else {
            this.especialidad = especialidad.trim();
        }

        this.noPacientes = 0;
        this.pacientes = new Paciente[CAPACIDAD_MAXIMA_PACIENTES];
    }

    /**
     * Registra al enfermero en el sistema hospitalario.
     *
     * @param sistema Sistema donde se registrara.
     * @return true si el registro fue exitoso, false si no.
     */
    public boolean registroEnSistema(Sistema sistema) {
        if (sistema == null) {
            System.out.println("Aviso: El sistema no puede ser nulo.");
            return false;
        }

        if (this.sistema == sistema) {
            return true;
        }

        if (sistema.registroEnfermero(this)) {
            this.sistema = sistema;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Aplica cuidados o tratamiento al paciente.
     *
     * @param paciente Paciente asignado que recibe atencion.
     * @return true si se realizo el tratamiento, false en caso contrario.
     */
    @Override
    public abstract boolean darTratamiento(Paciente paciente);

    /**
     * Devuelve la lista de pacientes asignados ordenada alfabeticamente de forma descendente.
     *
     * @return Lista con los pacientes y su especialidad.
     */
    @Override
    public String verListaPacientes() {
        if (noPacientes == 0) {
            return "";
        }

        Paciente[] copia = new Paciente[noPacientes];
        for (int i = 0; i < noPacientes; i++) {
            copia[i] = pacientes[i];
        }

        for (int i = 0; i < copia.length - 1; i++) {
            for (int j = 0; j < copia.length - 1 - i; j++) {
                if (copia[j].getNombre().compareToIgnoreCase(copia[j + 1].getNombre()) < 0) {
                    Paciente temporal = copia[j];
                    copia[j] = copia[j + 1];
                    copia[j + 1] = temporal;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < copia.length; i++) {
            sb.append(copia[i].getNombre()).append(" - ").append(copia[i].getEspecialidadAtencion());
            if (i < copia.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Agrega un paciente a cargo del enfermero si hay cupo (hasta 3) y coincide la especialidad.
     *
     * @param paciente Paciente a agregar.
     * @return true si se agrego correctamente, false si ya alcanzo el limite o no coincide.
     */
    public boolean agregarPaciente(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Aviso: El paciente no puede ser nulo.");
            return false;
        }

        if (this.noPacientes >= CAPACIDAD_MAXIMA_PACIENTES) {
            System.out.println("Aviso: El enfermero ha alcanzado su capacidad maxima (3 pacientes).");
            return false;
        }

        if (!this.especialidad.equalsIgnoreCase(paciente.getEspecialidadAtencion())) {
            System.out.println("Aviso: La especialidad del paciente no coincide con la del enfermero.");
            return false;
        }

        if (getPacienteAsignado(paciente) != null) {
            System.out.println("Aviso: El paciente ya esta asignado a este enfermero.");
            return false;
        }

        pacientes[noPacientes] = paciente;
        noPacientes++;
        paciente.setEnfermeroAsignado(this);
        return true;
    }

    /**
     * Busca a un paciente entre los asignados al enfermero.
     *
     * @param paciente Paciente a buscar.
     * @return Paciente si esta asignado, o null si no pertenece a su lista.
     */
    public Paciente getPacienteAsignado(Paciente paciente) {
        if (paciente == null) {
            return null;
        }

        for (int i = 0; i < noPacientes; i++) {
            if (pacientes[i] == paciente) {
                return pacientes[i];
            }
        }
        return null;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Aviso: El nombre no puede estar vacio.");
        } else {
            this.nombre = nombre.trim();
        }
    }

    public String getCedula() {
        return this.cedula;
    }

    public String getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            System.out.println("Aviso: La especialidad no puede estar vacia.");
        } else {
            this.especialidad = especialidad.trim();
        }
    }

    public int getNoPacientes() {
        return this.noPacientes;
    }

    public Paciente[] getPacientes() {
        Paciente[] copia = new Paciente[noPacientes];
        for (int i = 0; i < noPacientes; i++) {
            copia[i] = pacientes[i];
        }
        return copia;
    }

    public Sistema getSistema() {
        return this.sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    @Override
    public String toString() {
        return "Enfermero: " + this.nombre +
               "\nCedula: " + this.cedula +
               "\nEspecialidad: " + this.especialidad +
               "\nPacientes a cargo: " + this.noPacientes + "/" + CAPACIDAD_MAXIMA_PACIENTES;
    }
}
