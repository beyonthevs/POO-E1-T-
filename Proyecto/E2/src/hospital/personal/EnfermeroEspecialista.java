package hospital.personal;

import hospital.pacientes.Paciente;

/**
 * Representa a un enfermero especializado en un area clinica determinada.
 */
public class EnfermeroEspecialista extends Enfermero {

    private String areaProcedimientos;

    /**
     * Crea un enfermero especialista con su area de procedimientos.
     *
     * @param nombre Nombre y apellidos del enfermero.
     * @param cedula Cedula profesional.
     * @param especialidad Especialidad de enfermeria.
     * @param areaProcedimientos Area o servicio donde labora (ej. Terapia Intensiva).
     */
    public EnfermeroEspecialista(String nombre, String cedula, String especialidad, String areaProcedimientos) {
        super(nombre, cedula, especialidad);
        if (areaProcedimientos == null || areaProcedimientos.trim().isEmpty()) {
            this.areaProcedimientos = "Sin asignar";
        } else {
            this.areaProcedimientos = areaProcedimientos.trim();
        }
    }

    /**
     * Aplica el tratamiento clinico al paciente asignado.
     *
     * @param paciente Paciente que recibe el tratamiento.
     * @return true si se aplico el tratamiento, false si el paciente no esta asignado.
     */
    @Override
    public boolean darTratamiento(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Aviso: El paciente no puede ser nulo.");
            return false;
        }

        if (getPacienteAsignado(paciente) == null) {
            System.out.println("Aviso: El paciente no esta asignado a este enfermero especialista.");
            return false;
        }

        paciente.setEstado(Paciente.ESTADO_EN_TRATAMIENTO);
        return true;
    }

    /**
     * Administra un medicamento controlado al paciente asignado.
     *
     * @param paciente Paciente que recibe el farmaco.
     * @param medicamento Nombre del medicamento.
     * @param dosis Dosis administrada.
     * @return true si se administro con exito, false en caso contrario.
     */
    public boolean administrarMedicamentoControlado(Paciente paciente, String medicamento, String dosis) {
        if (paciente == null) {
            System.out.println("Aviso: El paciente no puede ser nulo.");
            return false;
        }

        if (getPacienteAsignado(paciente) == null) {
            System.out.println("Aviso: El paciente no esta asignado a este enfermero.");
            return false;
        }

        if (medicamento == null || medicamento.trim().isEmpty() || dosis == null || dosis.trim().isEmpty()) {
            System.out.println("Aviso: El medicamento o la dosis no pueden estar vacios.");
            return false;
        }

        paciente.setEstado(Paciente.ESTADO_EN_TRATAMIENTO);
        return true;
    }

    public boolean suministrarMedicamento(Paciente paciente, String medicamento, String dosis) {
        return administrarMedicamentoControlado(paciente, medicamento, dosis);
    }

    public String getAreaProcedimientos() {
        return this.areaProcedimientos;
    }

    public void setAreaProcedimientos(String areaProcedimientos) {
        if (areaProcedimientos == null || areaProcedimientos.trim().isEmpty()) {
            System.out.println("Aviso: El area de procedimientos no puede estar vacia.");
        } else {
            this.areaProcedimientos = areaProcedimientos.trim();
        }
    }

    /**
     * Devuelve la descripcion del servicio de enfermeria especializada.
     *
     * @return Texto con la especialidad y area.
     */
    @Override
    public String getDescripcionAtencion() {
        return "Enfermeria Especializada en " + getEspecialidad() + " - Area: " + this.areaProcedimientos;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nTipo: Enfermero Especialista" +
               "\nArea de procedimientos: " + this.areaProcedimientos;
    }
}
