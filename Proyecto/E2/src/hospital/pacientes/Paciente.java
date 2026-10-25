package hospital.pacientes;

import hospital.personal.Medico;
import hospital.personal.Enfermero;
import hospital.sistema.Sistema;

/**
 * Clase base abstracta que representa a los pacientes del hospital.
 */
public abstract class Paciente {

    private final String nombre;

    /** Especialidad medica requerida. */
    private String especialidadAtencion;

    /** Estado actual del paciente. */
    private String estado;

    /** Medico asignado al paciente. */
    private Medico medicoAsignado;

    /** Enfermero asignado al paciente. */
    private Enfermero enfermeroAsignado;

    /** Sistema hospitalario al que pertenece. */
    private Sistema sistema;

    /** Estado cuando el paciente esta en espera. */
    public static final String ESTADO_PENDIENTE = "PENDIENTE";

    /** Estado cuando el paciente esta en consulta medica. */
    public static final String ESTADO_EN_CONSULTA = "EN CONSULTA";

    /** Estado cuando el paciente esta en tratamiento. */
    public static final String ESTADO_EN_TRATAMIENTO = "EN TRATAMIENTO";

    /**
     * Inicializa un paciente con su nombre y la especialidad requerida.
     *
     * @param nombre Nombre y apellidos del paciente.
     * @param especialidadAtencion Especialidad medica solicitada.
     */
    public Paciente(String nombre, String especialidadAtencion) {
        if (nombre == null || nombre.trim().isEmpty()) {
            this.nombre = "Sin asignar";
        } else {
            this.nombre = nombre.trim();
        }

        if (especialidadAtencion == null || especialidadAtencion.trim().isEmpty()) {
            this.especialidadAtencion = "General";
        } else {
            this.especialidadAtencion = especialidadAtencion.trim();
        }

        this.estado = ESTADO_PENDIENTE;
    }

    /**
     * Registra al paciente en el sistema hospitalario.
     *
     * @param sistema Sistema donde se registrara.
     * @return true si se registro correctamente, false si no.
     */
    public boolean registroEnSistema(Sistema sistema) {
        if (sistema == null) {
            System.out.println("Aviso: El sistema hospitalario no puede ser nulo.");
            return false;
        }

        if (this.sistema == sistema) {
            return true;
        }

        if (sistema.registroPaciente(this)) {
            this.sistema = sistema;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Solicita una consulta con el medico indicado.
     *
     * @param medico Medico al que se solicita consulta.
     * @return true si la consulta fue aceptada, false en caso contrario.
     */
    public abstract boolean solicitarConsulta(Medico medico);

    public boolean verTratamiento() {
        return estaEnTratamiento();
    }

    public boolean estaEnTratamiento() {
        return ESTADO_EN_TRATAMIENTO.equalsIgnoreCase(this.estado);
    }

    public boolean estaEnConsulta() {
        return ESTADO_EN_CONSULTA.equalsIgnoreCase(this.estado);
    }

    public boolean estaPendiente() {
        return ESTADO_PENDIENTE.equalsIgnoreCase(this.estado);
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getEspecialidadAtencion() {
        return this.especialidadAtencion;
    }

    public void setEspecialidadAtencion(String especialidadAtencion) {
        if (especialidadAtencion == null || especialidadAtencion.trim().isEmpty()) {
            System.out.println("Aviso: La especialidad no puede estar vacia.");
        } else {
            this.especialidadAtencion = especialidadAtencion.trim();
        }
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String nuevoEstado) {
        if (nuevoEstado == null) {
            System.out.println("Aviso: El estado no puede ser nulo.");
            return;
        }

        String limpio = nuevoEstado.trim().toUpperCase();
        if (limpio.equals(ESTADO_PENDIENTE) || limpio.equals(ESTADO_EN_CONSULTA) || limpio.equals(ESTADO_EN_TRATAMIENTO)) {
            this.estado = limpio;
        } else {
            System.out.println("Aviso: Estado no valido (" + nuevoEstado + ").");
        }
    }

    public Medico getMedicoAsignado() {
        return this.medicoAsignado;
    }

    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }

    public Enfermero getEnfermeroAsignado() {
        return this.enfermeroAsignado;
    }

    public void setEnfermeroAsignado(Enfermero enfermeroAsignado) {
        this.enfermeroAsignado = enfermeroAsignado;
    }

    public Sistema getSistema() {
        return this.sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public abstract String getTipoPaciente();

    @Override
    public String toString() {
        String nombreMed = "Ninguno";
        if (medicoAsignado != null) {
            nombreMed = medicoAsignado.getNombre();
        }

        String nombreEnf = "Ninguno";
        if (enfermeroAsignado != null) {
            nombreEnf = enfermeroAsignado.getNombre();
        }

        return "Paciente: " + this.nombre +
               "\nTipo: " + getTipoPaciente() +
               "\nEspecialidad solicitada: " + this.especialidadAtencion +
               "\nEstado: " + this.estado +
               "\nMedico asignado: " + nombreMed +
               "\nEnfermero asignado: " + nombreEnf;
    }
}
