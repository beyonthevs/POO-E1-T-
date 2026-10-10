package mx.unam.fi.die.poo.g7.p6.e1;

/**
 * Guarda la información de los doctores del hospital y su especialidad.
 */
public class Medico {
    private String nombre;
    private String cedula;
    private String especialidad;
    private int noPacientes;
    private Paciente[] pacientes;
    private Paciente pacienteEnConsulta;
    private Sistema sistema;

    /**
     * Constructor de Médico.
     * @param nombre Nombre de la persona.
     * @param cedula Cédula de la persona.
     * @param especialidad Especialidad del médico.
     */
    public Medico(String nombre, String cedula, String especialidad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.noPacientes = 0;
        this.pacientes = new Paciente[10];
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
        if (sistema.registroMedico(this)) {
            this.sistema = sistema;
            return true;
        }
        return false;
    }

    /**
     * Método para solicitarPaciente.
     * @return Resultado de la operación.
     */
    public Paciente solicitarPaciente() {
        for (int i = 0; i < noPacientes; i++) {
            if (pacientes[i].getEstado().equals("PENDIENTE")) {
                return pacientes[i];
            }
        }
        return null;
    }

    /**
     * Método para darConsulta.
     * @param paciente Paciente involucrado.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean darConsulta(Paciente paciente) {
        if (paciente == null || pacienteEnConsulta != null) {
            return false;
        }
        if (getPacienteAsignado(paciente) == null) {
            return false;
        }
        if (!paciente.getEspecialidadAtencion().equalsIgnoreCase(especialidad)) {
            return false;
        }
        if (!paciente.getEstado().equals("PENDIENTE")) {
            return false;
        }
        pacienteEnConsulta = paciente;
        paciente.setEstado("EN CONSULTA");
        return true;
    }

    /**
     * Método para darTratamiento.
     * @param paciente Paciente involucrado.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean darTratamiento(Paciente paciente) {
        if (paciente == null || getPacienteAsignado(paciente) == null) {
            return false;
        }
        paciente.setEstado("EN TRATAMIENTO");
        if (pacienteEnConsulta == paciente) {
            pacienteEnConsulta = null;
        }
        return true;
    }

    /**
     * Método para verListaPacientes.
     * @return Resultado de la operación.
     */
    public String verListaPacientes() {
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
        String resultado = "";
        for (int i = 0; i < copia.length; i++) {
            resultado += copia[i].getNombre() + " - " + copia[i].getEspecialidadAtencion();
            if (i < copia.length - 1) {
                resultado += "\n";
            }
        }
        return resultado;
    }

    /**
     * Método para agregarPaciente.
     * @param paciente Paciente involucrado.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean agregarPaciente(Paciente paciente) {
        if (paciente == null || noPacientes >= 10) {
            return false;
        }
        if (!especialidad.equalsIgnoreCase(paciente.getEspecialidadAtencion())) {
            return false;
        }
        if (getPacienteAsignado(paciente) != null) {
            return false;
        }
        pacientes[noPacientes] = paciente;
        noPacientes++;
        paciente.setMedicoAsignado(this);
        return true;
    }

    /**
     * Método para getPacienteAsignado.
     * @param paciente Paciente involucrado.
     * @return El valor de la propiedad pacienteasignado.
     */
    public Paciente getPacienteAsignado(Paciente paciente) {
        for (int i = 0; i < noPacientes; i++) {
            if (pacientes[i] == paciente) {
                return pacientes[i];
            }
        }
        return null;
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
     * Método para getCedula.
     * @return El valor de la propiedad cédula.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Método para setCedula.
     * @param cedula Cédula de la persona.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Método para getEspecialidad.
     * @return El valor de la propiedad especialidad.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Método para setEspecialidad.
     * @param especialidad Especialidad del médico.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Método para getNoPacientes.
     * @return El valor de la propiedad nopacientes.
     */
    public int getNoPacientes() {
        return noPacientes;
    }

    /**
     * Método para getPacienteEnConsulta.
     * @return El valor de la propiedad pacienteenconsulta.
     */
    public Paciente getPacienteEnConsulta() {
        return pacienteEnConsulta;
    }

    /**
     * Método para getSistema.
     * @return El valor de la propiedad sistema.
     */
    public Sistema getSistema() {
        return sistema;
    }
}
