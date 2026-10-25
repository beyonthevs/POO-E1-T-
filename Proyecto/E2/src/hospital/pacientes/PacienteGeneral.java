package hospital.pacientes;

import hospital.personal.Medico;
import hospital.personal.MedicoGeneral;

/**
 * Representa a un paciente de consulta general en el hospital.
 */
public class PacienteGeneral extends Paciente {

    private String clinicaProcedencia;

    /**
     * Crea un paciente general indicando su clinica de origen.
     *
     * @param nombre Nombre y apellidos del paciente.
     * @param clinicaProcedencia Clinica o centro de salud de procedencia.
     */
    public PacienteGeneral(String nombre, String clinicaProcedencia) {
        super(nombre, "General");
        if (clinicaProcedencia == null || clinicaProcedencia.trim().isEmpty()) {
            this.clinicaProcedencia = "Sin asignar";
        } else {
            this.clinicaProcedencia = clinicaProcedencia.trim();
        }
    }

    /**
     * Crea un paciente general indicando la especialidad y clinica de origen.
     *
     * @param nombre Nombre y apellidos del paciente.
     * @param especialidadAtencion Nombre de la especialidad general.
     * @param clinicaProcedencia Clinica de procedencia.
     */
    public PacienteGeneral(String nombre, String especialidadAtencion, String clinicaProcedencia) {
        super(nombre, especialidadAtencion);
        if (clinicaProcedencia == null || clinicaProcedencia.trim().isEmpty()) {
            this.clinicaProcedencia = "Sin asignar";
        } else {
            this.clinicaProcedencia = clinicaProcedencia.trim();
        }
    }

    /**
     * Solicita consulta con un medico general.
     *
     * @param medico Medico al que se solicita consulta.
     * @return true si la consulta fue iniciada, false si no es medico general o no esta disponible.
     */
    @Override
    public boolean solicitarConsulta(Medico medico) {
        if (medico == null) {
            System.out.println("Aviso: El medico no puede ser nulo.");
            return false;
        }

        if (!(medico instanceof MedicoGeneral)) {
            System.out.println("Aviso: Un paciente general solo se atiende con medico general.");
            return false;
        }

        if (!estaPendiente()) {
            System.out.println("Aviso: El paciente no se encuentra en estado PENDIENTE.");
            return false;
        }

        if (medico.getPacienteAsignado(this) == null) {
            System.out.println("Aviso: El paciente no esta asignado a este medico.");
            return false;
        }

        return medico.darConsulta(this);
    }

    /**
     * Genera la ficha basica de atencion para consulta externa.
     * @return Resumen breve con nombre y clinica de procedencia.
     */
    public String obtenerFichaGeneral() {
        return "Ficha General: " + getNombre() + " | Procedencia: " + this.clinicaProcedencia;
    }

    public String getClinicaProcedencia() {
        return this.clinicaProcedencia;
    }

    public void setClinicaProcedencia(String clinicaProcedencia) {
        if (clinicaProcedencia == null || clinicaProcedencia.trim().isEmpty()) {
            System.out.println("Aviso: La clinica de procedencia no puede estar vacia.");
        } else {
            this.clinicaProcedencia = clinicaProcedencia.trim();
        }
    }

    @Override
    public String getTipoPaciente() {
        return "Paciente General";
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nClinica de procedencia: " + this.clinicaProcedencia;
    }
}

