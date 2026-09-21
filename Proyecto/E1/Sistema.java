public class Sistema {
    private String nombreHospital;
    private int noMedicos;
    private int noEnfermeros;
    private int noPacientes;
    private Medico[] medicos;
    private Enfermero[] enfermeros;
    private Paciente[] pacientes;

    public Sistema(String nombreHospital) {
        this.nombreHospital = nombreHospital;
        this.medicos = new Medico[100];
        this.enfermeros = new Enfermero[100];
        this.pacientes = new Paciente[100];
        this.noMedicos = 0;
        this.noEnfermeros = 0;
        this.noPacientes = 0;
    }

    public boolean registroMedico(Medico medico) {
        if (medico == null || noMedicos >= medicos.length || buscarMedico(medico.getCedula()) != null) {
            return false;
        }
        medicos[noMedicos] = medico;
        noMedicos++;
        return true;
    }

    public boolean registroEnfermero(Enfermero enfermero) {
        if (enfermero == null || noEnfermeros >= enfermeros.length || buscarEnfermero(enfermero.getCedula()) != null) {
            return false;
        }
        enfermeros[noEnfermeros] = enfermero;
        noEnfermeros++;
        return true;
    }

    public boolean registroPaciente(Paciente paciente) {
        if (paciente == null || noPacientes >= pacientes.length || buscarPaciente(paciente.getNombre()) != null) {
            return false;
        }
        pacientes[noPacientes] = paciente;
        noPacientes++;
        return true;
    }

    public boolean asignarPaciente(Paciente paciente, Medico medico) {
        if (paciente == null || medico == null) {
            return false;
        }
        if (!estaRegistrado(paciente) || !estaRegistrado(medico)) {
            return false;
        }
        return medico.agregarPaciente(paciente);
    }

    public boolean asignarPaciente(Paciente paciente, Enfermero enfermero) {
        if (paciente == null || enfermero == null) {
            return false;
        }
        if (!estaRegistrado(paciente) || !estaRegistrado(enfermero)) {
            return false;
        }
        return enfermero.agregarPaciente(paciente);
    }

    private boolean estaRegistrado(Paciente paciente) {
        for (int i = 0; i < noPacientes; i++) {
            if (pacientes[i] == paciente) {
                return true;
            }
        }
        return false;
    }

    private boolean estaRegistrado(Medico medico) {
        for (int i = 0; i < noMedicos; i++) {
            if (medicos[i] == medico) {
                return true;
            }
        }
        return false;
    }

    private boolean estaRegistrado(Enfermero enfermero) {
        for (int i = 0; i < noEnfermeros; i++) {
            if (enfermeros[i] == enfermero) {
                return true;
            }
        }
        return false;
    }

    public Medico buscarMedico(String cedula) {
        for (int i = 0; i < noMedicos; i++) {
            if (medicos[i].getCedula().equals(cedula)) {
                return medicos[i];
            }
        }
        return null;
    }

    public Enfermero buscarEnfermero(String cedula) {
        for (int i = 0; i < noEnfermeros; i++) {
            if (enfermeros[i].getCedula().equals(cedula)) {
                return enfermeros[i];
            }
        }
        return null;
    }

    public Paciente buscarPaciente(String nombre) {
        for (int i = 0; i < noPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return pacientes[i];
            }
        }
        return null;
    }

    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    public int getNoMedicos() {
        return noMedicos;
    }

    public int getNoEnfermeros() {
        return noEnfermeros;
    }

    public int getNoPacientes() {
        return noPacientes;
    }

    public Medico[] getMedicos() {
        Medico[] resultado = new Medico[noMedicos];
        for (int i = 0; i < noMedicos; i++) {
            resultado[i] = medicos[i];
        }
        return resultado;
    }

    public Enfermero[] getEnfermeros() {
        Enfermero[] resultado = new Enfermero[noEnfermeros];
        for (int i = 0; i < noEnfermeros; i++) {
            resultado[i] = enfermeros[i];
        }
        return resultado;
    }

    public Paciente[] getPacientes() {
        Paciente[] resultado = new Paciente[noPacientes];
        for (int i = 0; i < noPacientes; i++) {
            resultado[i] = pacientes[i];
        }
        return resultado;
    }
}
