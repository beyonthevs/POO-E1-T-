package hospital.personal;

import hospital.pacientes.Paciente;
import hospital.pacientes.PacienteGeneral;

/**
 * Representa a un medico general que atiende en consulta externa y primer contacto.
 */
public class MedicoGeneral extends Medico {

    private int numeroConsultorio;

    /**
     * Crea un medico general con su nombre, cedula y numero de consultorio.
     *
     * @param nombre Nombre y apellidos del medico.
     * @param cedula Cedula profesional.
     * @param numeroConsultorio Numero de consultorio asignado.
     */
    public MedicoGeneral(String nombre, String cedula, int numeroConsultorio) {
        super(nombre, cedula, "General");
        if (numeroConsultorio <= 0) {
            this.numeroConsultorio = 1;
        } else {
            this.numeroConsultorio = numeroConsultorio;
        }
    }

    /**
     * Crea un medico general indicando explicitamente la especialidad.
     *
     * @param nombre Nombre y apellidos del medico.
     * @param cedula Cedula profesional.
     * @param especialidad Nombre de la especialidad general.
     * @param numeroConsultorio Numero de consultorio.
     */
    public MedicoGeneral(String nombre, String cedula, String especialidad, int numeroConsultorio) {
        super(nombre, cedula, especialidad);
        if (numeroConsultorio <= 0) {
            this.numeroConsultorio = 1;
        } else {
            this.numeroConsultorio = numeroConsultorio;
        }
    }

    /**
     * Atiende en consulta a un paciente general.
     * Solo puede tener un paciente a la vez en consulta.
     *
     * @param paciente Paciente general que entra a consulta.
     * @return true si la consulta inicio, false si el medico ya esta ocupado o el paciente no es general.
     */
    @Override
    public boolean darConsulta(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Aviso: El paciente no puede ser nulo.");
            return false;
        }

        if (this.pacienteEnConsulta != null) {
            System.out.println("Aviso: El medico general ya tiene un paciente en consulta.");
            return false;
        }

        if (getPacienteAsignado(paciente) == null) {
            System.out.println("Aviso: El paciente no esta asignado a este medico.");
            return false;
        }

        if (!(paciente instanceof PacienteGeneral)) {
            System.out.println("Aviso: El medico general solo puede atender a pacientes generales.");
            return false;
        }

        if (!paciente.estaPendiente()) {
            System.out.println("Aviso: El paciente no esta en estado PENDIENTE.");
            return false;
        }

        this.pacienteEnConsulta = paciente;
        paciente.setEstado(Paciente.ESTADO_EN_CONSULTA);
        return true;
    }

    /**
     * Expide un certificado de salud para un paciente general.
     *
     * @param p Paciente general evaluado.
     * @return true si se expidio el certificado, false si es nulo.
     */
    public boolean expedirCertificadoSalud(PacienteGeneral p) {
        if (p == null) {
            System.out.println("Aviso: No se puede expedir certificado a un paciente nulo.");
            return false;
        }
        System.out.println("Certificado de salud expedido por el Dr(a). " + this.getNombre() + " para el paciente " + p.getNombre() + " (Clinica: " + p.getClinicaProcedencia() + ").");
        return true;
    }

    public int getNumeroConsultorio() {
        return this.numeroConsultorio;
    }

    public void setNumeroConsultorio(int numeroConsultorio) {
        if (numeroConsultorio <= 0) {
            System.out.println("Aviso: El numero de consultorio debe ser mayor a cero. Se asigna 1 por defecto.");
            this.numeroConsultorio = 1;
        } else {
            this.numeroConsultorio = numeroConsultorio;
        }
    }

    /**
     * Devuelve la descripcion del servicio de medicina general.
     *
     * @return Texto con el consultorio general.
     */
    @Override
    public String getDescripcionAtencion() {
        return "Atencion Medica General - Consultorio " + this.numeroConsultorio;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nTipo: Medico General" +
               "\nConsultorio: " + this.numeroConsultorio;
    }
}
