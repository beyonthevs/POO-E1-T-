package hospital.personal;

import hospital.pacientes.Paciente;

/**
 * Representa al jefe de enfermeria del hospital, encargado de coordinar
 * y supervisar al personal de enfermeria a su cargo.
 */
public class JefeEnfermeria extends Enfermero {

    private String departamentoCoordinado;
    private int enfermerosBajoMando;

    /**
     * Crea un jefe de enfermeria con su departamento y personal a cargo.
     *
     * @param nombre Nombre y apellidos.
     * @param cedula Cedula profesional.
     * @param especialidad Especialidad o area gerencial.
     * @param departamentoCoordinado Nombre del departamento a cargo.
     * @param enfermerosBajoMando Cantidad de enfermeros bajo su supervision.
     */
    public JefeEnfermeria(String nombre, String cedula, String especialidad,
                          String departamentoCoordinado, int enfermerosBajoMando) {
        super(nombre, cedula, especialidad);
        if (departamentoCoordinado == null || departamentoCoordinado.trim().isEmpty()) {
            this.departamentoCoordinado = "Sin asignar";
        } else {
            this.departamentoCoordinado = departamentoCoordinado.trim();
        }

        if (enfermerosBajoMando < 0) {
            this.enfermerosBajoMando = 0;
        } else {
            this.enfermerosBajoMando = enfermerosBajoMando;
        }
    }

    /**
     * Aplica el tratamiento clinico al paciente bajo supervision de jefatura.
     *
     * @param paciente Paciente que recibe el tratamiento.
     * @return true si se realizo el tratamiento, false si el paciente no esta asignado.
     */
    @Override
    public boolean darTratamiento(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Aviso: El paciente no puede ser nulo.");
            return false;
        }

        if (getPacienteAsignado(paciente) == null) {
            System.out.println("Aviso: El paciente no esta asignado a la jefatura de enfermeria.");
            return false;
        }

        paciente.setEstado(Paciente.ESTADO_EN_TRATAMIENTO);
        return true;
    }

    /**
     * Funcion de supervision: Revisa la ocupacion de pacientes de cada enfermero
     * y genera un informe de estado.
     *
     * @param listaEnfermeros Arreglo de enfermeros del hospital.
     * @param total Cantidad de enfermeros registrados.
     * @return Texto con el informe de supervision.
     */
    public String supervisarPersonal(Enfermero[] listaEnfermeros, int total) {
        if (listaEnfermeros == null || total <= 0) {
            return "No hay personal de enfermeria registrado para supervisar.";
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("=== INFORME DE SUPERVISION DE ENFERMERIA ===\n");
        reporte.append("Jefe supervisor: ").append(getNombre()).append("\n");
        reporte.append("Departamento: ").append(this.departamentoCoordinado).append("\n");
        reporte.append("--------------------------------------------\n");

        for (int i = 0; i < total; i++) {
            Enfermero e = listaEnfermeros[i];
            if (e != null) {
                int ocupados = e.getNoPacientes();
                int disponibles = Enfermero.CAPACIDAD_MAXIMA_PACIENTES - ocupados;
                reporte.append(String.format("- %s [%s] | Pacientes: %d/%d (Disponibles: %d)\n",
                    e.getNombre(), e.getEspecialidad(), ocupados,
                    Enfermero.CAPACIDAD_MAXIMA_PACIENTES, disponibles));
            }
        }
        reporte.append("============================================");
        return reporte.toString();
    }

    /**
     * Funcion de coordinacion: Asigna y valida una guardia para un enfermero.
     *
     * @param enfermero Enfermero subordinado.
     * @param turnoTurno Horario o turno de la guardia.
     * @return true si se coordino con exito, false si los datos son invalidos.
     */
    public boolean coordinarGuardia(Enfermero enfermero, String turnoTurno) {
        if (enfermero == null || turnoTurno == null || turnoTurno.trim().isEmpty()) {
            System.out.println("Aviso: Datos invalidos para coordinar guardia.");
            return false;
        }
        return true;
    }

    public String getDepartamentoCoordinado() {
        return this.departamentoCoordinado;
    }

    public void setDepartamentoCoordinado(String departamentoCoordinado) {
        if (departamentoCoordinado == null || departamentoCoordinado.trim().isEmpty()) {
            System.out.println("Aviso: El departamento coordinado no puede estar vacio.");
        } else {
            this.departamentoCoordinado = departamentoCoordinado.trim();
        }
    }

    public int getEnfermerosBajoMando() {
        return this.enfermerosBajoMando;
    }

    public void setEnfermerosBajoMando(int enfermerosBajoMando) {
        if (enfermerosBajoMando < 0) {
            System.out.println("Aviso: El numero de enfermeros no puede ser negativo. Se asigna 0 por defecto.");
            this.enfermerosBajoMando = 0;
        } else {
            this.enfermerosBajoMando = enfermerosBajoMando;
        }
    }

    /**
     * Devuelve la descripcion del rol de jefatura de enfermeria.
     *
     * @return Texto con el cargo y departamento.
     */
    @Override
    public String getDescripcionAtencion() {
        return "Jefatura y Coordinacion de Enfermeria - Depto: " + this.departamentoCoordinado;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nCargo: Jefe de Enfermeria" +
               "\nDepartamento coordinado: " + this.departamentoCoordinado +
               "\nPersonal bajo mando: " + this.enfermerosBajoMando;
    }
}
