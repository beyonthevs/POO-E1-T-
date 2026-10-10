package mx.unam.fi.die.poo.g7.p6.e1;

/**
 * Controla el registro de doctores y pacientes, y los menús del hospital.
 */
public class Sistema {
    private String nombreHospital;
    private int noMedicos;
    private int noEnfermeros;
    private int noPacientes;
    private Medico[] medicos;
    private Enfermero[] enfermeros;
    private Paciente[] pacientes;

    /**
     * Constructor de Sistema.
     * @param nombreHospital Nombre del hospital.
     */
    public Sistema(String nombreHospital) {
        this.nombreHospital = nombreHospital;
        this.medicos = new Medico[100];
        this.enfermeros = new Enfermero[100];
        this.pacientes = new Paciente[100];
        this.noMedicos = 0;
        this.noEnfermeros = 0;
        this.noPacientes = 0;
    }

    /**
     * Método para registroMedico.
     * @param medico Médico involucrado.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean registroMedico(Medico medico) {
        if (medico == null || noMedicos >= medicos.length || buscarMedico(medico.getCedula()) != null) {
            return false;
        }
        medicos[noMedicos] = medico;
        noMedicos++;
        return true;
    }

    /**
     * Método para registroEnfermero.
     * @param enfermero Enfermero involucrado.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean registroEnfermero(Enfermero enfermero) {
        if (enfermero == null || noEnfermeros >= enfermeros.length || buscarEnfermero(enfermero.getCedula()) != null) {
            return false;
        }
        enfermeros[noEnfermeros] = enfermero;
        noEnfermeros++;
        return true;
    }

    /**
     * Método para registroPaciente.
     * @param paciente Paciente involucrado.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean registroPaciente(Paciente paciente) {
        if (paciente == null || noPacientes >= pacientes.length || buscarPaciente(paciente.getNombre()) != null) {
            return false;
        }
        pacientes[noPacientes] = paciente;
        noPacientes++;
        return true;
    }

    /**
     * Método para asignarPaciente.
     * @param paciente Paciente involucrado.
     * @param medico Médico involucrado.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean asignarPaciente(Paciente paciente, Medico medico) {
        if (paciente == null || medico == null) {
            return false;
        }
        if (!estaRegistrado(paciente) || !estaRegistrado(medico)) {
            return false;
        }
        return medico.agregarPaciente(paciente);
    }

    /**
     * Método para asignarPaciente.
     * @param paciente Paciente involucrado.
     * @param enfermero Enfermero involucrado.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean asignarPaciente(Paciente paciente, Enfermero enfermero) {
        if (paciente == null || enfermero == null) {
            return false;
        }
        if (!estaRegistrado(paciente) || !estaRegistrado(enfermero)) {
            return false;
        }
        return enfermero.agregarPaciente(paciente);
    }

    /**
     * Método para estaRegistrado.
     */
    private boolean estaRegistrado(Paciente paciente) {
        for (int i = 0; i < noPacientes; i++) {
            if (pacientes[i] == paciente) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para estaRegistrado.
     */
    private boolean estaRegistrado(Medico medico) {
        for (int i = 0; i < noMedicos; i++) {
            if (medicos[i] == medico) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para estaRegistrado.
     */
    private boolean estaRegistrado(Enfermero enfermero) {
        for (int i = 0; i < noEnfermeros; i++) {
            if (enfermeros[i] == enfermero) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para buscarMedico.
     * @param cedula Cédula de la persona.
     * @return El objeto encontrado, o null si no existe.
     */
    public Medico buscarMedico(String cedula) {
        for (int i = 0; i < noMedicos; i++) {
            if (medicos[i].getCedula().equals(cedula)) {
                return medicos[i];
            }
        }
        return null;
    }

    /**
     * Método para buscarEnfermero.
     * @param cedula Cédula de la persona.
     * @return El objeto encontrado, o null si no existe.
     */
    public Enfermero buscarEnfermero(String cedula) {
        for (int i = 0; i < noEnfermeros; i++) {
            if (enfermeros[i].getCedula().equals(cedula)) {
                return enfermeros[i];
            }
        }
        return null;
    }

    /**
     * Método para buscarPaciente.
     * @param nombre Nombre de la persona.
     * @return El objeto encontrado, o null si no existe.
     */
    public Paciente buscarPaciente(String nombre) {
        for (int i = 0; i < noPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return pacientes[i];
            }
        }
        return null;
    }

    /**
     * Método para getNombreHospital.
     * @return El valor de la propiedad nombrehospital.
     */
    public String getNombreHospital() {
        return nombreHospital;
    }

    /**
     * Método para setNombreHospital.
     * @param nombreHospital Nombre del hospital.
     */
    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    /**
     * Método para getNoMedicos.
     * @return El valor de la propiedad nomedicos.
     */
    public int getNoMedicos() {
        return noMedicos;
    }

    /**
     * Método para getNoEnfermeros.
     * @return El valor de la propiedad noenfermeros.
     */
    public int getNoEnfermeros() {
        return noEnfermeros;
    }

    /**
     * Método para getNoPacientes.
     * @return El valor de la propiedad nopacientes.
     */
    public int getNoPacientes() {
        return noPacientes;
    }

    /**
     * Método para getMedicos.
     * @return El valor de la propiedad médicos.
     */
    public Medico[] getMedicos() {
        Medico[] resultado = new Medico[noMedicos];
        for (int i = 0; i < noMedicos; i++) {
            resultado[i] = medicos[i];
        }
        return resultado;
    }

    /**
     * Método para getEnfermeros.
     * @return El valor de la propiedad enfermeros.
     */
    public Enfermero[] getEnfermeros() {
        Enfermero[] resultado = new Enfermero[noEnfermeros];
        for (int i = 0; i < noEnfermeros; i++) {
            resultado[i] = enfermeros[i];
        }
        return resultado;
    }

    /**
     * Método para getPacientes.
     * @return El valor de la propiedad pacientes.
     */
    public Paciente[] getPacientes() {
        Paciente[] resultado = new Paciente[noPacientes];
        for (int i = 0; i < noPacientes; i++) {
            resultado[i] = pacientes[i];
        }
        return resultado;
    }
}
