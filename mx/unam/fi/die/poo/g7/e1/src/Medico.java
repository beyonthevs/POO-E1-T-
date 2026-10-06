public class Medico {
    private String nombre;
    private String cedula;
    private String especialidad;
    private int noPacientes;
    private Paciente[] pacientes;
    private Paciente pacienteEnConsulta;
    private Sistema sistema;

    public Medico(String nombre, String cedula, String especialidad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.noPacientes = 0;
        this.pacientes = new Paciente[10];
    }

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

    public Paciente solicitarPaciente() {
        for (int i = 0; i < noPacientes; i++) {
            if (pacientes[i].getEstado().equals("PENDIENTE")) {
                return pacientes[i];
            }
        }
        return null;
    }

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

    public Paciente getPacienteAsignado(Paciente paciente) {
        for (int i = 0; i < noPacientes; i++) {
            if (pacientes[i] == paciente) {
                return pacientes[i];
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getNoPacientes() {
        return noPacientes;
    }

    public Paciente getPacienteEnConsulta() {
        return pacienteEnConsulta;
    }

    public Sistema getSistema() {
        return sistema;
    }
}
