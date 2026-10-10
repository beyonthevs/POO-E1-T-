package mx.unam.fi.die.poo.g7.e1;

/**
 * Clase Medico para la practica e1.
 * Sirve para resolver el problema asignado.
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
     * Constructor de Medico.
     */
    public Medico(String nombre, String cedula, String especialidad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.noPacientes = 0;
        this.pacientes = new Paciente[10];
    }

    /**
     * Metodo para registroEnSistema.
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
     * Metodo para solicitarPaciente.
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
     * Metodo para darConsulta.
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
     * Metodo para darTratamiento.
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
     * Metodo para verListaPacientes.
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
     * Metodo para agregarPaciente.
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
     * Metodo para getPacienteAsignado.
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
     * Metodo para getCedula.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Metodo para setCedula.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Metodo para getEspecialidad.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Metodo para setEspecialidad.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Metodo para getNoPacientes.
     */
    public int getNoPacientes() {
        return noPacientes;
    }

    /**
     * Metodo para getPacienteEnConsulta.
     */
    public Paciente getPacienteEnConsulta() {
        return pacienteEnConsulta;
    }

    /**
     * Metodo para getSistema.
     */
    public Sistema getSistema() {
        return sistema;
    }
}
