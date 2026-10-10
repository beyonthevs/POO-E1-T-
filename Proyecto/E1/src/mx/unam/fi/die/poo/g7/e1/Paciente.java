package mx.unam.fi.die.poo.g7.e1;

/**
 * Guarda los datos de la gente que viene a recibir tratamiento al hospital.
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
     * @param nombre Nombre de la persona.
     * @param especialidadAtencion Especialidad requerida para la atención.
     */
    public Paciente(String nombre, String especialidadAtencion) {
        this.nombre = nombre;
        this.especialidadAtencion = especialidadAtencion;
        this.estado = "PENDIENTE";
    }

    /**
     * Método para registroEnSistema.
     * @param sistema Sistema de gestión hospitalaria.
     * @return true si la operación fue exitosa, false en caso contrario.
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
     * Método para solicitarConsulta.
     * @param medico Médico involucrado.
     * @return true si la operación fue exitosa, false en caso contrario.
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
     * Método para verTratamiento.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean verTratamiento() {
        return "EN TRATAMIENTO".equals(estado);
    }

    /**
     * Método para getNombre.
     * @return El valor de la propiedad nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para setNombre.
     * @param nombre Nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para getEspecialidadAtencion.
     * @return El valor de la propiedad especialidadatencion.
     */
    public String getEspecialidadAtencion() {
        return especialidadAtencion;
    }

    /**
     * Método para setEspecialidadAtencion.
     * @param especialidadAtencion Especialidad requerida para la atención.
     */
    public void setEspecialidadAtencion(String especialidadAtencion) {
        this.especialidadAtencion = especialidadAtencion;
    }

    /**
     * Método para getEstado.
     * @return El valor de la propiedad estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método para getMedicoAsignado.
     * @return El valor de la propiedad medicoasignado.
     */
    public Medico getMedicoAsignado() {
        return medicoAsignado;
    }

    /**
     * Método para getEnfermeroAsignado.
     * @return El valor de la propiedad enfermeroasignado.
     */
    public Enfermero getEnfermeroAsignado() {
        return enfermeroAsignado;
    }

    /**
     * Método para getSistema.
     * @return El valor de la propiedad sistema.
     */
    public Sistema getSistema() {
        return sistema;
    }

    /**
     * Método para setMedicoAsignado.
     * @param medicoAsignado Médico que se asignará.
     */
    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }

    /**
     * Método para setEnfermeroAsignado.
     * @param enfermeroAsignado Enfermero que se asignará.
     */
    public void setEnfermeroAsignado(Enfermero enfermeroAsignado) {
        this.enfermeroAsignado = enfermeroAsignado;
    }

    /**
     * Método para setEstado.
     * @param estado Estado del paciente.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
