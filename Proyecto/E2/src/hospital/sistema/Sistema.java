package hospital.sistema;

import java.util.ArrayList;
import java.util.HashMap;
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
    private ArrayList<Medico> medicos;
    private ArrayList<Enfermero> enfermeros;
    private ArrayList<Paciente> pacientes;

    private HashMap<String, Medico> medicosPorCedula;
    private HashMap<String, Enfermero> enfermerosPorCedula;
    private HashMap<String, Paciente> pacientesPorNombre;

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

        this.medicos = new ArrayList<>();
        this.enfermeros = new ArrayList<>();
        this.pacientes = new ArrayList<>();
        this.medicosPorCedula = new HashMap<>();
        this.enfermerosPorCedula = new HashMap<>();
        this.pacientesPorNombre = new HashMap<>();
    }

    /**
     * Registra a un medico en el hospital.
     *
     * @param medico Medico a registrar.
     * @return true si se registro, false si es nulo o ya existe la cedula.
     */
    public boolean registroMedico(Medico medico) {
        if (medico == null) {
            System.out.println("Aviso: El medico no puede ser nulo.");
            return false;
        }

        String clave = medico.getCedula().trim().toUpperCase();
        if (medicosPorCedula.containsKey(clave)) {
            System.out.println("Aviso: Ya existe un medico registrado con la cedula: " + medico.getCedula());
            return false;
        }

        medicos.add(medico);
        medicosPorCedula.put(clave, medico);
        if (medico.getSistema() != this) {
            medico.setSistema(this);
        }
        return true;
    }

    /**
     * Registra a un enfermero en el hospital.
     *
     * @param enfermero Enfermero a registrar.
     * @return true si se registro, false si es nulo o ya existe la cedula.
     */
    public boolean registroEnfermero(Enfermero enfermero) {
        if (enfermero == null) {
            System.out.println("Aviso: El enfermero no puede ser nulo.");
            return false;
        }

        String clave = enfermero.getCedula().trim().toUpperCase();
        if (enfermerosPorCedula.containsKey(clave)) {
            System.out.println("Aviso: Ya existe un enfermero registrado con la cedula: " + enfermero.getCedula());
            return false;
        }

        enfermeros.add(enfermero);
        enfermerosPorCedula.put(clave, enfermero);
        if (enfermero.getSistema() != this) {
            enfermero.setSistema(this);
        }
        return true;
    }

    /**
     * Registra a un paciente en el hospital.
     *
     * @param paciente Paciente a registrar.
     * @return true si se registro, false si es nulo o ya existe el nombre.
     */
    public boolean registroPaciente(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Aviso: El paciente no puede ser nulo.");
            return false;
        }

        String clave = paciente.getNombre().trim().toUpperCase();
        if (pacientesPorNombre.containsKey(clave)) {
            System.out.println("Aviso: Ya existe un paciente registrado con el nombre: " + paciente.getNombre());
            return false;
        }

        pacientes.add(paciente);
        pacientesPorNombre.put(clave, paciente);
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
        return paciente != null && pacientesPorNombre.containsKey(paciente.getNombre().trim().toUpperCase());
    }

    /**
     * Comprueba si un medico esta registrado en el sistema.
     *
     * @param medico Medico a verificar.
     * @return true si esta registrado, false si no.
     */
    private boolean estaRegistrado(Medico medico) {
        return medico != null && medicosPorCedula.containsKey(medico.getCedula().trim().toUpperCase());
    }

    /**
     * Comprueba si un enfermero esta registrado en el sistema.
     *
     * @param enfermero Enfermero a verificar.
     * @return true si esta registrado, false si no.
     */
    private boolean estaRegistrado(Enfermero enfermero) {
        return enfermero != null && enfermerosPorCedula.containsKey(enfermero.getCedula().trim().toUpperCase());
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
        return medicosPorCedula.get(cedula.trim().toUpperCase());
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
        return enfermerosPorCedula.get(cedula.trim().toUpperCase());
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
        return pacientesPorNombre.get(nombre.trim().toUpperCase());
    }

    /**
     * Devuelve el personal de salud registrado bajo la interfaz comun AtencionMedica.
     * Utiliza Fully Qualified Name como demostracion de paquetes.
     *
     * @return Lista con medicos y enfermeros como AtencionMedica.
     */
    public ArrayList<hospital.interfaces.AtencionMedica> getPersonalAtencion() {
        ArrayList<hospital.interfaces.AtencionMedica> lista = new ArrayList<>();
        lista.addAll(this.medicos);
        lista.addAll(this.enfermeros);
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
        return this.medicos.size();
    }

    public int getNoEnfermeros() {
        return this.enfermeros.size();
    }

    public int getNoPacientes() {
        return this.pacientes.size();
    }

    public ArrayList<Medico> getMedicos() {
        return new ArrayList<>(this.medicos);
    }

    public ArrayList<Enfermero> getEnfermeros() {
        return new ArrayList<>(this.enfermeros);
    }

    public ArrayList<Paciente> getPacientes() {
        return new ArrayList<>(this.pacientes);
    }
}
