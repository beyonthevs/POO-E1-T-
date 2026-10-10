package mx.unam.fi.die.poo.g7.p6.e1;

/**
 * Clase Enfermero, una de nuestras entidades en el hospital.
 */
public class Enfermero {
    private String nombre;
    private String cedula;
    private String especialidad;
    private int noPacientes;
    private Paciente[] pacientes;
    private Sistema sistema;

    /**
     * Constructor de Enfermero.
     * @param nombre Nombre de la persona.
     * @param cedula Cédula de la persona.
     * @param especialidad Especialidad del médico.
     */
    public Enfermero(String nombre, String cedula, String especialidad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.noPacientes = 0;
        this.pacientes = new Paciente[3];
    }

    /**
     * Método para hacer el registro en el sistema.
     * @param sistema Sistema de gestión hospitalaria.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
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

    /**
     * Método para ver la lista de pacientes asignados al enfermero.
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
     * Método para dar tratamiento a un paciente.
     * @param paciente Paciente involucrado.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean darTratamiento(Paciente paciente) {
        if (paciente == null || getPacienteAsignado(paciente) == null) {
            return false;
        }
        paciente.setEstado("EN TRATAMIENTO");
        return true;
    }

    /**
     * Método para agregar un paciente a la lista de pacientes asignados.
     * @param paciente Paciente involucrado.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
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

    /**
     * Método para obtener los pacientes asignados a un enfermero.
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
     * Método para obtener el nombre.
     * @return El valor de la propiedad nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para designar el nombre.
     * @param nombre Nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para obtener la cédula del enfermero.
     * @return El valor de la propiedad cédula.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Método para designar la cédula del enfermero.
     * @param cedula Cédula de la persona.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Método para obtener la especialidad del enfermero.
     * @return El valor de la propiedad especialidad.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Método para designar la especialidad del enfermero.
     * @param especialidad Especialidad del médico.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Método para obtener el número de pacientes asignados.
     * @return El valor de la propiedad nopacientes.
     */
    public int getNoPacientes() {
        return noPacientes;
    }

    /**
     * Método para obtener el sistema al que pertenece el enfermero.
     * @return El valor de la propiedad sistema.
     */
    public Sistema getSistema() {
        return sistema;
    }
}
