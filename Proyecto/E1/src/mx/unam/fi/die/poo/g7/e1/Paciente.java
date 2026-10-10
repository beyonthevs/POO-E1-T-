package mx.unam.fi.die.poo.g7.e1;

/**
 * Clase Paciente para la practica e1.
 * Sirve para resolver el problema asignado.
 */
public class Paciente {
    private String nombre;
    private String especialidadAtencion;
    private String estado;
    private Sistema sistema;
    private Medico medicoAsignado;
    private Enfermero enfermeroAsignado;

    /**
     * Constructor de Paciente.
     */
    public Paciente(String nombre, String especialidadAtencion) {
        this.nombre = nombre;
        this.especialidadAtencion = especialidadAtencion;
        this.estado = "PENDIENTE";
    }

    /**
     * Metodo para registroEnSistema.
     */
    public boolean registroEnSistema(Sistema sistema) {
        if (sistema == null) {
            return false;
        }
        if (sistema.registroPaciente(this)) {
            this.sistema = sistema;
            return true;
        }
        return false;
    }

    /**
     * Metodo para solicitarConsulta.
     */
    public boolean solicitarConsulta(Medico medico) {
        if (medico == null || medico.getNoPacientes() == 0 || !estado.equals("PENDIENTE")) {
            return false;
        }
        if (!especialidadAtencion.equalsIgnoreCase(medico.getEspecialidad())) {
            return false;
        }
        if (medico.getPacienteAsignado(this) == null) {
            return false;
        }
        return medico.darConsulta(this);
    }

    /**
     * Metodo para verTratamiento.
     */
    public boolean verTratamiento() {
        return "EN TRATAMIENTO".equals(estado);
    }

    /**
     * Metodo para getNombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para setNombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para getEspecialidadAtencion.
     */
    public String getEspecialidadAtencion() {
        return especialidadAtencion;
    }

    /**
     * Metodo para setEspecialidadAtencion.
     */
    public void setEspecialidadAtencion(String especialidadAtencion) {
        this.especialidadAtencion = especialidadAtencion;
    }

    /**
     * Metodo para getEstado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Metodo para getMedicoAsignado.
     */
    public Medico getMedicoAsignado() {
        return medicoAsignado;
    }

    /**
     * Metodo para getEnfermeroAsignado.
     */
    public Enfermero getEnfermeroAsignado() {
        return enfermeroAsignado;
    }

    /**
     * Metodo para getSistema.
     */
    public Sistema getSistema() {
        return sistema;
    }

    /**
     * Metodo para setMedicoAsignado.
     */
    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }

    /**
     * Metodo para setEnfermeroAsignado.
     */
    public void setEnfermeroAsignado(Enfermero enfermeroAsignado) {
        this.enfermeroAsignado = enfermeroAsignado;
    }

    /**
     * Metodo para setEstado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
