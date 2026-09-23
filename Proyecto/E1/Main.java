import java.util.Scanner;

public class Main {
    private static final Scanner entrada = new Scanner(System.in);
    private static Sistema sistema;

    public static void main(String[] args) {
        System.out.print("Nombre del hospital: ");
        String nombreHospital = entrada.nextLine().trim();

        if (nombreHospital.isEmpty()) {
            nombreHospital = "Hospital";
        }

        sistema = new Sistema(nombreHospital);

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarMedico();
                    break;
                case 2:
                    registrarEnfermero();
                    break;
                case 3:
                    registrarPaciente();
                    break;
                case 4:
                    asignarPacienteMedico();
                    break;
                case 5:
                    asignarPacienteEnfermero();
                    break;
                case 6:
                    solicitarConsulta();
                    break;
                case 7:
                    darTratamientoMedico();
                    break;
                case 8:
                    darTratamientoEnfermero();
                    break;
                case 9:
                    verListaMedico();
                    break;
                case 10:
                    verListaEnfermero();
                    break;
                case 11:
                    verEstadoPaciente();
                    break;
                case 12:
                    mostrarResumen();
                    break;
                case 0:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

            if (opcion != 0) {
                System.out.println();
            }
        } while (opcion != 0);

        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("========================================");
        System.out.println(sistema.getNombreHospital());
        System.out.println("========================================");
        System.out.println("1. Registrar medico");
        System.out.println("2. Registrar enfermero");
        System.out.println("3. Registrar paciente");
        System.out.println("4. Asignar paciente a medico");
        System.out.println("5. Asignar paciente a enfermero");
        System.out.println("6. Solicitar consulta");
        System.out.println("7. Dar tratamiento a paciente (medico)");
        System.out.println("8. Dar tratamiento a paciente (enfermero)");
        System.out.println("9. Ver lista de pacientes de un medico");
        System.out.println("10. Ver lista de pacientes de un enfermero");
        System.out.println("11. Ver estado de un paciente");
        System.out.println("12. Mostrar resumen del sistema");
        System.out.println("0. Salir");
        System.out.println("========================================");
    }

    private static void registrarMedico() {
        System.out.println("--- Registro de medico ---");
        String nombre = leerTexto("Apellidos y nombres: ");
        String cedula = leerTexto("Cedula: ");
        String especialidad = leerTexto("Especialidad: ");

        Medico medico = new Medico(nombre, cedula, especialidad);

        if (medico.registroEnSistema(sistema)) {
            System.out.println("Medico registrado correctamente.");
        } else {
            System.out.println("No fue posible registrar al medico.");
        }
    }

    private static void registrarEnfermero() {
        System.out.println("--- Registro de enfermero ---");
        String nombre = leerTexto("Apellidos y nombres: ");
        String cedula = leerTexto("Cedula: ");
        String especialidad = leerTexto("Especialidad: ");

        Enfermero enfermero = new Enfermero(nombre, cedula, especialidad);

        if (enfermero.registroEnSistema(sistema)) {
            System.out.println("Enfermero registrado correctamente.");
        } else {
            System.out.println("No fue posible registrar al enfermero.");
        }
    }

    private static void registrarPaciente() {
        System.out.println("--- Registro de paciente ---");
        String nombre = leerTexto("Apellidos y nombres: ");
        String especialidad = leerTexto("Especialidad de atencion: ");

        Paciente paciente = new Paciente(nombre, especialidad);

        if (paciente.registroEnSistema(sistema)) {
            System.out.println("Paciente registrado correctamente.");
        } else {
            System.out.println("No fue posible registrar al paciente.");
        }
    }

    private static void asignarPacienteMedico() {
        System.out.println("--- Asignacion a medico ---");

        Paciente paciente = buscarPacientePorNombre();
        if (paciente == null) {
            return;
        }

        Medico medico = buscarMedicoPorCedula();
        if (medico == null) {
            return;
        }

        if (sistema.asignarPaciente(paciente, medico)) {
            System.out.println("Paciente asignado correctamente al medico.");
        } else {
            System.out.println("No fue posible asignar al paciente.");
            System.out.println("Verifique la especialidad, el limite de 10 pacientes o si ya esta asignado.");
        }
    }

    private static void asignarPacienteEnfermero() {
        System.out.println("--- Asignacion a enfermero ---");

        Paciente paciente = buscarPacientePorNombre();
        if (paciente == null) {
            return;
        }

        Enfermero enfermero = buscarEnfermeroPorCedula();
        if (enfermero == null) {
            return;
        }

        if (sistema.asignarPaciente(paciente, enfermero)) {
            System.out.println("Paciente asignado correctamente al enfermero.");
        } else {
            System.out.println("No fue posible asignar al paciente.");
            System.out.println("Verifique la especialidad, el limite de 3 pacientes o si ya esta asignado.");
        }
    }

    private static void solicitarConsulta() {
        System.out.println("--- Solicitud de consulta ---");

        Paciente paciente = buscarPacientePorNombre();
        if (paciente == null) {
            return;
        }

        Medico medico = buscarMedicoPorCedula();
        if (medico == null) {
            return;
        }

        if (paciente.solicitarConsulta(medico)) {
            System.out.println("La consulta ha comenzado.");
        } else {
            System.out.println("No fue posible iniciar la consulta.");
            System.out.println("Verifique la asignacion, especialidad, estado del paciente y disponibilidad del medico.");
        }
    }

    private static void darTratamientoMedico() {
        System.out.println("--- Tratamiento por medico ---");

        Medico medico = buscarMedicoPorCedula();
        if (medico == null) {
            return;
        }

        Paciente paciente = buscarPacientePorNombre();
        if (paciente == null) {
            return;
        }

        if (medico.darTratamiento(paciente)) {
            System.out.println("El paciente esta ahora en tratamiento.");
        } else {
            System.out.println("No fue posible dar tratamiento a este paciente con el medico seleccionado.");
        }
    }

    private static void darTratamientoEnfermero() {
        System.out.println("--- Tratamiento por enfermero ---");

        Enfermero enfermero = buscarEnfermeroPorCedula();
        if (enfermero == null) {
            return;
        }

        Paciente paciente = buscarPacientePorNombre();
        if (paciente == null) {
            return;
        }

        if (enfermero.darTratamiento(paciente)) {
            System.out.println("El paciente esta ahora en tratamiento.");
        } else {
            System.out.println("No fue posible dar tratamiento a este paciente con el enfermero seleccionado.");
        }
    }

    private static void verListaMedico() {
        System.out.println("--- Lista de pacientes de medico ---");

        Medico medico = buscarMedicoPorCedula();
        if (medico == null) {
            return;
        }

        String lista = medico.verListaPacientes();

        if (lista.isEmpty()) {
            System.out.println("El medico no tiene pacientes asignados.");
        } else {
            System.out.println(lista);
        }
    }

    private static void verListaEnfermero() {
        System.out.println("--- Lista de pacientes de enfermero ---");

        Enfermero enfermero = buscarEnfermeroPorCedula();
        if (enfermero == null) {
            return;
        }

        String lista = enfermero.verListaPacientes();

        if (lista.isEmpty()) {
            System.out.println("El enfermero no tiene pacientes asignados.");
        } else {
            System.out.println(lista);
        }
    }

    private static void verEstadoPaciente() {
        System.out.println("--- Estado de paciente ---");

        Paciente paciente = buscarPacientePorNombre();
        if (paciente == null) {
            return;
        }

        System.out.println("Paciente: " + paciente.getNombre());
        System.out.println("Especialidad: " + paciente.getEspecialidadAtencion());
        System.out.println("Estado: " + paciente.getEstado());

        if (paciente.verTratamiento()) {
            System.out.println("El paciente se encuentra en tratamiento.");
        }
    }

    private static void mostrarResumen() {
        System.out.println("--- Resumen del sistema ---");
        System.out.println("Hospital: " + sistema.getNombreHospital());
        System.out.println("Medicos registrados: " + sistema.getNoMedicos());
        System.out.println("Enfermeros registrados: " + sistema.getNoEnfermeros());
        System.out.println("Pacientes registrados: " + sistema.getNoPacientes());
    }

    private static Medico buscarMedicoPorCedula() {
        String cedula = leerTexto("Cedula del medico: ");
        Medico medico = sistema.buscarMedico(cedula);

        if (medico == null) {
            System.out.println("No se encontro un medico con esa cedula.");
        }

        return medico;
    }

    private static Enfermero buscarEnfermeroPorCedula() {
        String cedula = leerTexto("Cedula del enfermero: ");
        Enfermero enfermero = sistema.buscarEnfermero(cedula);

        if (enfermero == null) {
            System.out.println("No se encontro un enfermero con esa cedula.");
        }

        return enfermero;
    }

    private static Paciente buscarPacientePorNombre() {
        String nombre = leerTexto("Apellidos y nombres del paciente: ");
        Paciente paciente = sistema.buscarPaciente(nombre);

        if (paciente == null) {
            System.out.println("No se encontro un paciente con ese nombre.");
        }

        return paciente;
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = entrada.nextLine().trim();

            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }
}
