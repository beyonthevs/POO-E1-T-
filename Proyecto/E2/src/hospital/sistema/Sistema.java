package hospital.sistema;

import hospital.pacientes.Paciente;
import hospital.pacientes.PacienteGeneral;
import hospital.pacientes.PacienteEspecialidad;
import hospital.personal.Medico;
import hospital.personal.MedicoGeneral;
import hospital.personal.MedicoEspecialista;
import hospital.personal.Enfermero;
import hospital.personal.EnfermeroEspecialista;

/**
 * Administra el registro del personal medico, enfermeros y pacientes,
 * asi como las asignaciones y consultas dentro del hospital.
 */
public class Sistema {

    private String nombreHospital;
    private int noMedicos;
    private int noEnfermeros;
    private int noPacientes;
    private Medico[] medicos;
    private Enfermero[] enfermeros;
    private Paciente[] pacientes;

    /** Capacidad inicial por defecto para las listas del sistema. */
    public static final int CAPACIDAD_DEFAULT = 100;

    /**
     * Crea un nuevo sistema hospitalario con su nombre.
     *
     * @param nombreHospital Nombre del hospital.
     */
    public Sistema(String nombreHospital) {
        if (nombreHospital == null || nombreHospital.trim().isEmpty()) {
            this.nombreHospital = "UNAM";
        } else {
            this.nombreHospital = nombreHospital.trim();
        }

        this.medicos = new Medico[CAPACIDAD_DEFAULT];
        this.enfermeros = new Enfermero[CAPACIDAD_DEFAULT];
        this.pacientes = new Paciente[CAPACIDAD_DEFAULT];
        this.noMedicos = 0;
        this.noEnfermeros = 0;
        this.noPacientes = 0;
    }

    /**
     * Registra a un medico en el hospital.
     *
     * @param medico Medico a registrar.
     * @return true si se registro, false si es nulo, esta lleno o ya existe la cedula.
     */
    public boolean registroMedico(Medico medico) {
        if (medico == null) {
            System.out.println("Aviso: El medico no puede ser nulo.");
            return false;
        }

        if (noMedicos >= medicos.length) {
            System.out.println("Aviso: Capacidad maxima de medicos alcanzada en el sistema.");
            return false;
        }

        if (buscarMedico(medico.getCedula()) != null) {
            System.out.println("Aviso: Ya existe un medico registrado con la cedula: " + medico.getCedula());
            return false;
        }

        medicos[noMedicos] = medico;
        noMedicos++;
        if (medico.getSistema() != this) {
            medico.setSistema(this);
        }
        return true;
    }

    /**
     * Registra a un enfermero en el hospital.
     *
     * @param enfermero Enfermero a registrar.
     * @return true si se registro, false si es nulo, esta lleno o ya existe la cedula.
     */
    public boolean registroEnfermero(Enfermero enfermero) {
        if (enfermero == null) {
            System.out.println("Aviso: El enfermero no puede ser nulo.");
            return false;
        }

        if (noEnfermeros >= enfermeros.length) {
            System.out.println("Aviso: Capacidad maxima de enfermeros alcanzada en el sistema.");
            return false;
        }

        if (buscarEnfermero(enfermero.getCedula()) != null) {
            System.out.println("Aviso: Ya existe un enfermero registrado con la cedula: " + enfermero.getCedula());
            return false;
        }

        enfermeros[noEnfermeros] = enfermero;
        noEnfermeros++;
        if (enfermero.getSistema() != this) {
            enfermero.setSistema(this);
        }
        return true;
    }

    /**
     * Registra a un paciente en el hospital.
     *
     * @param paciente Paciente a registrar.
     * @return true si se registro, false si es nulo, esta lleno o ya existe el nombre.
     */
    public boolean registroPaciente(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Aviso: El paciente no puede ser nulo.");
            return false;
        }

        if (noPacientes >= pacientes.length) {
            System.out.println("Aviso: Capacidad maxima de pacientes alcanzada en el sistema.");
            return false;
        }

        if (buscarPaciente(paciente.getNombre()) != null) {
            System.out.println("Aviso: Ya existe un paciente registrado con el nombre: " + paciente.getNombre());
            return false;
        }

        pacientes[noPacientes] = paciente;
        noPacientes++;
        if (paciente.getSistema() != this) {
            paciente.setSistema(this);
        }
        return true;
    }

    /**
     * Asigna un paciente a un medico comprobando especialidad y registro.
     * Metodo sobrecargado (polimorfismo estatico).
     *
     * @param paciente Paciente a asignar.
     * @param medico Medico que lo atendera.
     * @return true si la asignacion fue exitosa, false si no cumplen las reglas.
     */
    public boolean asignarPaciente(Paciente paciente, Medico medico) {
        if (paciente == null || medico == null) {
            System.out.println("Aviso: Paciente o medico nulo en la asignacion.");
            return false;
        }

        if (!estaRegistrado(paciente)) {
            System.out.println("Aviso: El paciente no se encuentra registrado en el sistema.");
            return false;
        }

        if (!estaRegistrado(medico)) {
            System.out.println("Aviso: El medico no se encuentra registrado en el sistema.");
            return false;
        }

        if (paciente instanceof PacienteGeneral) {
            if (medico instanceof MedicoEspecialista) {
                System.out.println("Aviso: Un medico especialista no puede recibir pacientes generales.");
                return false;
            }
            if (!(medico instanceof MedicoGeneral)) {
                System.out.println("Aviso: Los pacientes generales solo pueden asignarse a medicos generales.");
                return false;
            }
        }

        if (paciente instanceof PacienteEspecialidad) {
            if (!(medico instanceof MedicoEspecialista)) {
                System.out.println("Aviso: Los pacientes de especialidad solo pueden asignarse a medicos especialistas.");
                return false;
            }
            if (!paciente.getEspecialidadAtencion().equalsIgnoreCase(medico.getEspecialidad())) {
                System.out.println("Aviso: La especialidad requerida por el paciente no coincide con la del medico.");
                return false;
            }
        }

        if (!medico.getEspecialidad().equalsIgnoreCase(paciente.getEspecialidadAtencion())) {
            System.out.println("Aviso: No coincide la especialidad de atencion.");
            return false;
        }

        return medico.agregarPaciente(paciente);
    }

    /**
     * Asigna un paciente a un enfermero comprobando especialidad y registro.
     * Metodo sobrecargado (polimorfismo estatico).
     *
     * @param paciente Paciente a asignar.
     * @param enfermero Enfermero que lo cuidara.
     * @return true si la asignacion fue exitosa, false si no cumplen las reglas.
     */
    public boolean asignarPaciente(Paciente paciente, Enfermero enfermero) {
        if (paciente == null || enfermero == null) {
            System.out.println("Aviso: Paciente o enfermero nulo en la asignacion.");
            return false;
        }

        if (!estaRegistrado(paciente)) {
            System.out.println("Aviso: El paciente no se encuentra registrado en el sistema.");
            return false;
        }

        if (!estaRegistrado(enfermero)) {
            System.out.println("Aviso: El enfermero no se encuentra registrado en el sistema.");
            return false;
        }

        if (enfermero instanceof EnfermeroEspecialista) {
            if (paciente instanceof PacienteGeneral) {
                System.out.println("Aviso: El enfermero especialista solo atiende pacientes de su especialidad.");
                return false;
            }
            if (paciente instanceof PacienteEspecialidad) {
                if (!enfermero.getEspecialidad().equalsIgnoreCase(paciente.getEspecialidadAtencion())) {
                    System.out.println("Aviso: La especialidad del enfermero no coincide con la del paciente.");
                    return false;
                }
            }
        }

        if (!enfermero.getEspecialidad().equalsIgnoreCase(paciente.getEspecialidadAtencion())) {
            System.out.println("Aviso: La especialidad del enfermero no coincide con la del paciente.");
            return false;
        }

        return enfermero.agregarPaciente(paciente);
    }

    /**
     * Comprueba si un paciente esta registrado en el sistema.
     *
     * @param paciente Paciente a verificar.
     * @return true si esta registrado, false si no.
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
     * Comprueba si un medico esta registrado en el sistema.
     *
     * @param medico Medico a verificar.
     * @return true si esta registrado, false si no.
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
     * Comprueba si un enfermero esta registrado en el sistema.
     *
     * @param enfermero Enfermero a verificar.
     * @return true si esta registrado, false si no.
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
     * Busca a un medico por su cedula profesional.
     *
     * @param cedula Cedula a buscar.
     * @return Medico encontrado, o null si no existe.
     */
    public Medico buscarMedico(String cedula) {
        if (cedula == null) {
            return null;
        }

        for (int i = 0; i < noMedicos; i++) {
            if (medicos[i].getCedula().equalsIgnoreCase(cedula.trim())) {
                return medicos[i];
            }
        }
        return null;
    }

    /**
     * Busca a un enfermero por su cedula profesional.
     *
     * @param cedula Cedula a buscar.
     * @return Enfermero encontrado, o null si no existe.
     */
    public Enfermero buscarEnfermero(String cedula) {
        if (cedula == null) {
            return null;
        }

        for (int i = 0; i < noEnfermeros; i++) {
            if (enfermeros[i].getCedula().equalsIgnoreCase(cedula.trim())) {
                return enfermeros[i];
            }
        }
        return null;
    }

    /**
     * Busca a un paciente por su nombre y apellidos.
     *
     * @param nombre Nombre a buscar.
     * @return Paciente encontrado, o null si no existe.
     */
    public Paciente buscarPaciente(String nombre) {
        if (nombre == null) {
            return null;
        }

        for (int i = 0; i < noPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                return pacientes[i];
            }
        }
        return null;
    }

    /**
     * Devuelve el personal de salud registrado bajo la interfaz comun AtencionMedica.
     * Utiliza Fully Qualified Name como demostracion de paquetes.
     *
     * @return Arreglo con medicos y enfermeros como AtencionMedica.
     */
    public hospital.interfaces.AtencionMedica[] getPersonalAtencion() {
        int total = noMedicos + noEnfermeros;
        hospital.interfaces.AtencionMedica[] lista = new hospital.interfaces.AtencionMedica[total];
        int indice = 0;
        for (int i = 0; i < noMedicos; i++) {
            lista[indice] = medicos[i];
            indice++;
        }
        for (int i = 0; i < noEnfermeros; i++) {
            lista[indice] = enfermeros[i];
            indice++;
        }
        return lista;
    }

    public String getNombreHospital() {
        return this.nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        if (nombreHospital == null || nombreHospital.trim().isEmpty()) {
            this.nombreHospital = "UNAM";
        } else {
            this.nombreHospital = nombreHospital.trim();
        }
    }

    public int getNoMedicos() {
        return this.noMedicos;
    }

    public int getNoEnfermeros() {
        return this.noEnfermeros;
    }

    public int getNoPacientes() {
        return this.noPacientes;
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
