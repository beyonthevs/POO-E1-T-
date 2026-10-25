package hospital.pacientes;

import hospital.personal.Medico;
import hospital.personal.MedicoEspecialista;

/**
 * Representa a un paciente que requiere atencion en una especialidad especifica.
 */
public class PacienteEspecialidad extends Paciente {

    private String diagnosticoPrevio;
    private int nivelPrioridad;

    /**
     * Crea un paciente de especialidad con su diagnostico y nivel de prioridad.
     *
     * @param nombre Nombre y apellidos del paciente.
     * @param especialidadAtencion Especialidad medica requerida.
     * @param diagnosticoPrevio Diagnostico inicial o motivo de referencia.
     * @param nivelPrioridad Nivel de prioridad (1 = Urgente, 2 = Prioritaria, 3 = Ordinaria).
     */
    public PacienteEspecialidad(String nombre, String especialidadAtencion, String diagnosticoPrevio, int nivelPrioridad) {
        super(nombre, especialidadAtencion);
        if (diagnosticoPrevio == null || diagnosticoPrevio.trim().isEmpty()) {
            this.diagnosticoPrevio = "Sin diagnostico previo";
        } else {
            this.diagnosticoPrevio = diagnosticoPrevio.trim();
        }

        if (nivelPrioridad < 1 || nivelPrioridad > 3) {
            this.nivelPrioridad = 1;
        } else {
            this.nivelPrioridad = nivelPrioridad;
        }
    }

    /**
     * Solicita consulta con un medico especialista de la misma especialidad.
     *
     * @param medico Medico al que se solicita consulta.
     * @return true si la consulta fue aceptada, false si no coincide la especialidad o no esta disponible.
     */
    @Override
    public boolean solicitarConsulta(Medico medico) {
        if (medico == null) {
            System.out.println("Aviso: El medico no puede ser nulo.");
            return false;
        }

        if (!(medico instanceof MedicoEspecialista)) {
            System.out.println("Aviso: Un paciente de especialidad solo puede ser atendido por un medico especialista.");
            return false;
        }

        if (!getEspecialidadAtencion().equalsIgnoreCase(medico.getEspecialidad())) {
            System.out.println("Aviso: La especialidad del medico no coincide con la del paciente.");
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
     * Genera un resumen breve del caso clinico y prioridad del paciente.
     *
     * @return Texto con el resumen del caso.
     */
    public String obtenerResumenClinico() {
        String etiqueta;
        if (this.nivelPrioridad == 1) {
            etiqueta = "Alta / Urgente";
        } else if (this.nivelPrioridad == 2) {
            etiqueta = "Media / Prioritaria";
        } else {
            etiqueta = "Baja / Ordinaria";
        }

        return "Resumen: " + getNombre() + " | Especialidad: " + getEspecialidadAtencion() +
               " | Prioridad: " + etiqueta + " | Diagnostico: " + this.diagnosticoPrevio;
    }

    public String getDiagnosticoPrevio() {
        return this.diagnosticoPrevio;
    }

    public void setDiagnosticoPrevio(String diagnosticoPrevio) {
        if (diagnosticoPrevio == null || diagnosticoPrevio.trim().isEmpty()) {
            System.out.println("Aviso: El diagnostico no puede estar vacio.");
        } else {
            this.diagnosticoPrevio = diagnosticoPrevio.trim();
        }
    }

    public void actualizarDiagnostico(String nuevoDiagnostico) {
        setDiagnosticoPrevio(nuevoDiagnostico);
    }

    public int getNivelPrioridad() {
        return this.nivelPrioridad;
    }

    public void setNivelPrioridad(int nivelPrioridad) {
        if (nivelPrioridad < 1 || nivelPrioridad > 3) {
            System.out.println("Aviso: La prioridad debe estar entre 1 y 3. Se asigna 1 por defecto.");
            this.nivelPrioridad = 1;
        } else {
            this.nivelPrioridad = nivelPrioridad;
        }
    }

    @Override
    public String getTipoPaciente() {
        return "Paciente de Especialidad (" + getEspecialidadAtencion() + ")";
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nDiagnostico previo: " + this.diagnosticoPrevio +
               "\nNivel de prioridad: " + this.nivelPrioridad;
    }
}
