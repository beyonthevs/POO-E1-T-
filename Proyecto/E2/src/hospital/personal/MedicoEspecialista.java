package hospital.personal;

import hospital.pacientes.Paciente;
import hospital.pacientes.PacienteEspecialidad;

/**
 * Representa a un medico con especialidad en una rama clinica especifica.
 */
public class MedicoEspecialista extends Medico {

    private final String cedulaEspecialidad;
    private String hospitalCertificador;

    /**
     * Crea un medico especialista con sus datos y certificados de especialidad.
     *
     * @param nombre Nombre y apellidos del medico.
     * @param cedula Cedula profesional.
     * @param especialidad Rama o especialidad medica.
     * @param cedulaEspecialidad Cedula de la especialidad.
     * @param hospitalCertificador Hospital o institucion que certifica la especialidad.
     */
    public MedicoEspecialista(String nombre, String cedula, String especialidad,
                              String cedulaEspecialidad, String hospitalCertificador) {
        super(nombre, cedula, especialidad);
        if (cedulaEspecialidad == null || cedulaEspecialidad.trim().isEmpty()) {
            this.cedulaEspecialidad = "Sin cedula de especialidad";
        } else {
            this.cedulaEspecialidad = cedulaEspecialidad.trim();
        }

        if (hospitalCertificador == null || hospitalCertificador.trim().isEmpty()) {
            this.hospitalCertificador = "Sin asignar";
        } else {
            this.hospitalCertificador = hospitalCertificador.trim();
        }
    }

    /**
     * Atiende en consulta a un paciente de su misma especialidad.
     * Solo atiende un paciente a la vez.
     *
     * @param paciente Paciente de especialidad que entra a consulta.
     * @return true si la consulta inicio, false si la especialidad no coincide o el medico esta ocupado.
     */
    @Override
    public boolean darConsulta(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Aviso: El paciente no puede ser nulo.");
            return false;
        }

        if (getPacienteEnConsulta() != null) {
            System.out.println("Aviso: El medico especialista ya tiene un paciente en consulta.");
            return false;
        }

        if (getPacienteAsignado(paciente) == null) {
            System.out.println("Aviso: El paciente no esta asignado a este especialista.");
            return false;
        }

        if (!(paciente instanceof PacienteEspecialidad) || !getEspecialidad().equalsIgnoreCase(paciente.getEspecialidadAtencion())) {
            System.out.println("Aviso: El medico especialista solo atiende pacientes de su propia especialidad.");
            return false;
        }

        if (!paciente.estaPendiente()) {
            System.out.println("Aviso: El paciente no esta en estado PENDIENTE.");
            return false;
        }

        setPacienteEnConsulta(paciente);
        paciente.setEstado(Paciente.ESTADO_EN_CONSULTA);
        return true;
    }

    /**
     * Aplica tratamiento especializado a un paciente asignado de su especialidad.
     *
     * @param paciente Paciente que recibe el tratamiento.
     * @return true si se aplico el tratamiento, false si no corresponde la especialidad.
     */
    @Override
    public boolean darTratamiento(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Aviso: El paciente no puede ser nulo.");
            return false;
        }

        if (getPacienteAsignado(paciente) == null) {
            System.out.println("Aviso: El paciente no esta asignado a este especialista.");
            return false;
        }

        if (!(paciente instanceof PacienteEspecialidad) || !getEspecialidad().equalsIgnoreCase(paciente.getEspecialidadAtencion())) {
            System.out.println("Aviso: El medico especialista solo atiende pacientes de su propia especialidad.");
            return false;
        }

        paciente.setEstado(Paciente.ESTADO_EN_TRATAMIENTO);
        if (getPacienteEnConsulta() == paciente) {
            setPacienteEnConsulta(null);
        }
        return true;
    }

    /**
     * Emite un dictamen clinico para un paciente de especialidad.
     *
     * @param paciente Paciente evaluado.
     * @param diagnosticoDetallado Diagnostico o conclusiones del caso.
     * @return Texto con el dictamen, o null si el paciente no esta asignado.
     */
    public String emitirDictamenEspecializado(PacienteEspecialidad paciente, String diagnosticoDetallado) {
        if (paciente == null || getPacienteAsignado(paciente) == null) {
            System.out.println("Aviso: No se puede emitir dictamen porque el paciente no esta asignado a este especialista.");
            return null;
        }

        paciente.actualizarDiagnostico(diagnosticoDetallado);
        return "Dictamen de Especialidad (" + getEspecialidad() + ") - Dr(a). " + getNombre() +
               "\nCedula de posgrado: " + this.cedulaEspecialidad +
               "\nPaciente: " + paciente.getNombre() +
               "\nDiagnostico: " + diagnosticoDetallado;
    }

    public String getCedulaEspecialidad() {
        return this.cedulaEspecialidad;
    }

    public String getHospitalCertificador() {
        return this.hospitalCertificador;
    }

    public void setHospitalCertificador(String hospitalCertificador) {
        if (hospitalCertificador == null || hospitalCertificador.trim().isEmpty()) {
            System.out.println("Aviso: El hospital certificador no puede estar vacio.");
        } else {
            this.hospitalCertificador = hospitalCertificador.trim();
        }
    }

    /**
     * Devuelve la descripcion del servicio especializado.
     *
     * @return Texto descriptivo de la especialidad.
     */
    @Override
    public String getDescripcionAtencion() {
        return "Atencion Especializada en " + getEspecialidad() + " (Certificado por: " + this.hospitalCertificador + ")";
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nTipo: Medico Especialista" +
               "\nCedula de Especialidad: " + this.cedulaEspecialidad +
               "\nCertificado por: " + this.hospitalCertificador;
    }
}

