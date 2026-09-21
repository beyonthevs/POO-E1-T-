public class Paciente {
    private String nombre;
    private String especialidadAtencion;
    private String estado;
    private Sistema sistema;
    private Medico medicoAsignado;
    private Enfermero enfermeroAsignado;

    public Paciente(String nombre, String especialidadAtencion) {
        this.nombre = nombre;
        this.especialidadAtencion = especialidadAtencion;
        this.estado = "PENDIENTE";
    }

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

    public boolean verTratamiento() {
        return "EN TRATAMIENTO".equals(estado);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidadAtencion() {
        return especialidadAtencion;
    }

    public void setEspecialidadAtencion(String especialidadAtencion) {
        this.especialidadAtencion = especialidadAtencion;
    }

    public String getEstado() {
        return estado;
    }

    public Medico getMedicoAsignado() {
        return medicoAsignado;
    }

    public Enfermero getEnfermeroAsignado() {
        return enfermeroAsignado;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }

    public void setEnfermeroAsignado(Enfermero enfermeroAsignado) {
        this.enfermeroAsignado = enfermeroAsignado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
