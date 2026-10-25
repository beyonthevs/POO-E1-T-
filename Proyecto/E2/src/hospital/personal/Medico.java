package hospital.personal;

import java.util.ArrayList;
import hospital.interfaces.AtencionMedica;
import hospital.pacientes.Paciente;
import hospital.sistema.Sistema;

/**
 * Clase base abstracta que representa a un medico del hospital.
 * Implementa la interfaz {@link AtencionMedica}.
 */
public abstract class Medico implements AtencionMedica {

    private String nombre;
    private final String cedula;

    /** Especialidad medica del medico. */
    private String especialidad;

    /** Lista de pacientes asignados. */
    private ArrayList<Paciente> pacientes;

    /** Paciente que se encuentra en consulta actualmente. */
    private Paciente pacienteEnConsulta;

    /** Sistema hospitalario vinculado. */
    private Sistema sistema;

    /** Capacidad maxima de pacientes que puede atender un medico. */
    public static final int CAPACIDAD_MAXIMA_PACIENTES = 10;

    /**
     * Inicializa un medico con su nombre, cedula y especialidad.
     *
     * @param nombre Nombre y apellidos del medico.
     * @param cedula Cedula profesional.
     * @param especialidad Especialidad medica.
     */
    public Medico(String nombre, String cedula, String especialidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            this.nombre = "Sin asignar";
        } else {
            this.nombre = nombre.trim();
        }

        if (cedula == null || cedula.trim().isEmpty()) {
            this.cedula = "Sin cedula";
        } else {
            this.cedula = cedula.trim();
        }

        if (especialidad == null || especialidad.trim().isEmpty()) {
            this.especialidad = "General";
        } else {
            this.especialidad = especialidad.trim();
        }

        this.pacientes = new ArrayList<>();
        this.pacienteEnConsulta = null;
    }

    /**
     * Registra al medico en el sistema hospitalario.
     *
     * @param sistema Sistema donde se registrara.
     * @return true si el registro fue exitoso, false en caso contrario.
     */
    public boolean registroEnSistema(Sistema sistema) {
        if (sistema == null) {
            System.out.println("Aviso: El sistema no puede ser nulo.");
            return false;
        }

        if (this.sistema == sistema) {
            return true;
        }

        if (sistema.registroMedico(this)) {
            this.sistema = sistema;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca al primer paciente asignado que se encuentre en espera.
     *
     * @return Paciente en estado PENDIENTE, o null si no hay ninguno.
     */
    public Paciente solicitarPaciente() {
        for (Paciente p : this.pacientes) {
            if (p != null && p.estaPendiente()) {
                return p;
            }
        }
        return null;
    }

    /**
     * Atiende a un paciente en consulta.
     *
     * @param paciente Paciente que ingresa a consulta.
     * @return true si se inicio la consulta, false si no es posible atenderlo.
     */
    public abstract boolean darConsulta(Paciente paciente);

    /**
     * Aplica el tratamiento al paciente y libera la consulta si estaba en ella.
     *
     * @param paciente Paciente que recibe el tratamiento.
     * @return true si se aplico el tratamiento, false si no esta asignado.
     */
    @Override
    public boolean darTratamiento(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Aviso: El paciente no puede ser nulo.");
            return false;
        }

        if (getPacienteAsignado(paciente) == null) {
            System.out.println("Aviso: El paciente no esta asignado a este medico.");
            return false;
        }

        paciente.setEstado(Paciente.ESTADO_EN_TRATAMIENTO);
        if (this.pacienteEnConsulta == paciente) {
            this.pacienteEnConsulta = null;
        }
        return true;
    }

    /**
     * Concluye la consulta activa y deja libre el consultorio.
     *
     * @return true si habia una consulta y se libero, false si no habia paciente en consulta.
     */
    public boolean liberarConsulta() {
        if (this.pacienteEnConsulta != null) {
            this.pacienteEnConsulta = null;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Devuelve la lista de pacientes asignados ordenada alfabeticamente de forma descendente.
     *
     * @return Lista con apellidos, nombres y especialidad de cada paciente.
     */
    @Override
    public String verListaPacientes() {
        if (this.pacientes.isEmpty()) {
            return "";
        }

        ArrayList<Paciente> copia = new ArrayList<>(this.pacientes);
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).getNombre().compareToIgnoreCase(copia.get(j + 1).getNombre()) < 0) {
                    Paciente temporal = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temporal);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < copia.size(); i++) {
            sb.append(copia.get(i).getNombre()).append(" - ").append(copia.get(i).getEspecialidadAtencion());
            if (i < copia.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Agrega un paciente a la lista del medico si hay cupo y coincide la especialidad.
     *
     * @param paciente Paciente a agregar.
     * @return true si se agrego con exito, false si se supero el limite de 10 o no coincide.
     */
    public boolean agregarPaciente(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Aviso: El paciente no puede ser nulo.");
            return false;
        }

        if (this.pacientes.size() >= CAPACIDAD_MAXIMA_PACIENTES) {
            System.out.println("Aviso: El medico ha alcanzado su capacidad maxima (10 pacientes).");
            return false;
        }

        if (!this.especialidad.equalsIgnoreCase(paciente.getEspecialidadAtencion())) {
            System.out.println("Aviso: La especialidad requerida por el paciente no coincide con la del medico.");
            return false;
        }

        if (getPacienteAsignado(paciente) != null) {
            System.out.println("Aviso: El paciente ya se encuentra asignado a este medico.");
            return false;
        }

        this.pacientes.add(paciente);
        paciente.setMedicoAsignado(this);
        return true;
    }

    /**
     * Busca a un paciente entre los asignados al medico.
     *
     * @param paciente Paciente a buscar.
     * @return Referencia al paciente si esta asignado, o null si no lo esta.
     */
    public Paciente getPacienteAsignado(Paciente paciente) {
        if (paciente == null) {
            return null;
        }
        return this.pacientes.contains(paciente) ? paciente : null;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Aviso: El nombre no puede estar vacio.");
        } else {
            this.nombre = nombre.trim();
        }
    }

    public String getCedula() {
        return this.cedula;
    }

    public String getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            System.out.println("Aviso: La especialidad no puede estar vacia.");
        } else {
            this.especialidad = especialidad.trim();
        }
    }

    public int getNoPacientes() {
        return this.pacientes.size();
    }

    public Paciente getPacienteEnConsulta() {
        return this.pacienteEnConsulta;
    }

    protected void setPacienteEnConsulta(Paciente paciente) {
        this.pacienteEnConsulta = paciente;
    }

    public ArrayList<Paciente> getPacientes() {
        return new ArrayList<>(this.pacientes);
    }

    public Sistema getSistema() {
        return this.sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    @Override
    public String toString() {
        String enConsulta = "Ninguno";
        if (pacienteEnConsulta != null) {
            enConsulta = pacienteEnConsulta.getNombre();
        }

        return "Medico: " + this.nombre +
               "\nCedula: " + this.cedula +
               "\nEspecialidad: " + this.especialidad +
               "\nPacientes a cargo: " + this.pacientes.size() + "/" + CAPACIDAD_MAXIMA_PACIENTES +
               "\nEn consulta activa: " + enConsulta;
    }
}
