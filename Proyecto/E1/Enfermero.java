public class Enfermero {
    private String nombre;
    private String cedula;
    private String especialidad;
    private int noPacientes;
    private Paciente[] pacientes;
    private Sistema sistema;

    public Enfermero(String nombre, String cedula, String especialidad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.noPacientes = 0;
        this.pacientes = new Paciente[3];
    }

    public boolean registroEnSistema(Sistema sistema) {
        if (sistema == null) {
            return false;
        }
        if (sistema.registroEnfermero(this)) {
            this.sistema = sistema;
            return true;
        }
        return false;
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

    public boolean darTratamiento(Paciente paciente) {
        if (paciente == null || getPacienteAsignado(paciente) == null) {
            return false;
        }
        paciente.setEstado("EN TRATAMIENTO");
        return true;
    }

    public boolean agregarPaciente(Paciente paciente) {
        if (paciente == null || noPacientes >= 3) {
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
        paciente.setEnfermeroAsignado(this);
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

    public Sistema getSistema() {
        return sistema;
    }
}
