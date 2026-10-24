package hospital.sistema;

import java.util.Scanner;
import hospital.pacientes.Paciente;
import hospital.pacientes.PacienteGeneral;
import hospital.pacientes.PacienteEspecialidad;
import hospital.personal.Medico;
import hospital.personal.MedicoGeneral;
import hospital.personal.MedicoEspecialista;
import hospital.personal.Enfermero;
import hospital.personal.EnfermeroEspecialista;
import hospital.personal.JefeEnfermeria;

/**
 * Clase principal que ejecuta el menu y la simulacion del hospital.
 */
public class Main {

    private static Sistema sistema;

    /**
     * Constructor privado para evitar instanciacion.
     */
    private Main() {
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args Argumentos de linea de comandos.
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("--- SIMULADOR DE SISTEMA HOSPITALARIO ---");
        System.out.print("Nombre del hospital: ");
        String nombreHospital = entrada.nextLine().trim();

        if (nombreHospital.isEmpty()) {
            nombreHospital = "UNAM";
        }

        sistema = new Sistema(nombreHospital);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero(entrada, "Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarMedico(entrada);
                    break;
                case 2:
                    registrarEnfermero(entrada);
                    break;
                case 3:
                    registrarPaciente(entrada);
                    break;
                case 4:
                    asignarPacienteMedico(entrada);
                    break;
                case 5:
                    asignarPacienteEnfermero(entrada);
                    break;
                case 6:
                    solicitarConsulta(entrada);
                    break;
                case 7:
                    darTratamientoMedico(entrada);
                    break;
                case 8:
                    darTratamientoEnfermero(entrada);
                    break;
                case 9:
                    verListaMedico(entrada);
                    break;
                case 10:
                    verListaEnfermero(entrada);
                    break;
                case 11:
                    verEstadoPaciente(entrada);
                    break;
                case 12:
                    supervisionJefeEnfermeria(entrada);
                    break;
                case 13:
                    expedirCertificadoSalud(entrada);
                    break;
                case 14:
                    mostrarResumenYPersonalAtencion();
                    break;
                case 0:
                    System.out.println("Sistema hospitalario cerrado correctamente.");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }

            if (opcion != 0) {
                System.out.println();
            }
        } while (opcion != 0);

        entrada.close();
    }

    /**
     * Muestra las opciones disponibles en el menu.
     */
    private static void mostrarMenu() {
        System.out.println("Hospital: " + sistema.getNombreHospital());
        System.out.println("1. Registrar medico (General o Especialista)");
        System.out.println("2. Registrar enfermero (Especialista o Jefe)");
        System.out.println("3. Registrar paciente (General o Especialidad)");
        System.out.println("4. Asignar paciente a medico");
        System.out.println("5. Asignar paciente a enfermero");
        System.out.println("6. Solicitar consulta medica");
        System.out.println("7. Dar tratamiento por medico");
        System.out.println("8. Dar tratamiento por enfermero");
        System.out.println("9. Ver lista de pacientes de un medico");
        System.out.println("10. Ver lista de pacientes de un enfermero");
        System.out.println("11. Ver estado clinico de un paciente");
        System.out.println("12. Supervision de enfermeria (Jefe de Enfermeria)");
        System.out.println("13. Expedir certificado de salud (Medico General)");
        System.out.println("14. Resumen general y catalogo de atencion medica");
        System.out.println("0. Salir");
    }

    /**
     * Registra un medico general o especialista.
     *
     * @param entrada Lector de consola.
     */
    private static void registrarMedico(Scanner entrada) {
        System.out.println("-- Registro de Medico --");
        System.out.println("1. Medico General");
        System.out.println("2. Medico Especialista");
        int tipo = leerEntero(entrada, "Seleccione el tipo de medico: ");

        String nombre = leerTexto(entrada, "Apellidos y nombres: ");
        String cedula = leerTexto(entrada, "Cedula profesional: ");

        Medico medico;
        if (tipo == 1) {
            int consultorio = leerEntero(entrada, "Numero de consultorio: ");
            medico = new MedicoGeneral(nombre, cedula, consultorio);
        } else if (tipo == 2) {
            String especialidad = leerTexto(entrada, "Especialidad medica: ");
            String cedulaEsp = leerTexto(entrada, "Cedula de especialidad: ");
            String hospitalCert = leerTexto(entrada, "Hospital o institucion certificadora: ");
            medico = new MedicoEspecialista(nombre, cedula, especialidad, cedulaEsp, hospitalCert);
        } else {
            System.out.println("Aviso: Tipo de medico no valido.");
            return;
        }

        if (medico.registroEnSistema(sistema)) {
            System.out.println("Medico registrado exitosamente.");
        } else {
            System.out.println("No fue posible registrar al medico.");
        }
    }

    /**
     * Registra un enfermero especialista o jefe de enfermeria.
     *
     * @param entrada Lector de consola.
     */
    private static void registrarEnfermero(Scanner entrada) {
        System.out.println("-- Registro de Personal de Enfermeria --");
        System.out.println("1. Enfermero Especialista");
        System.out.println("2. Jefe de Enfermeria");
        int tipo = leerEntero(entrada, "Seleccione el tipo: ");

        String nombre = leerTexto(entrada, "Apellidos y nombres: ");
        String cedula = leerTexto(entrada, "Cedula profesional: ");
        String especialidad = leerTexto(entrada, "Especialidad de enfermeria: ");

        Enfermero enfermero;
        if (tipo == 1) {
            String area = leerTexto(entrada, "Area de procedimientos: ");
            enfermero = new EnfermeroEspecialista(nombre, cedula, especialidad, area);
        } else if (tipo == 2) {
            String depto = leerTexto(entrada, "Departamento coordinado: ");
            int bajoMando = leerEntero(entrada, "Personal subordinado bajo mando: ");
            enfermero = new JefeEnfermeria(nombre, cedula, especialidad, depto, bajoMando);
        } else {
            System.out.println("Aviso: Tipo no valido.");
            return;
        }

        if (enfermero.registroEnSistema(sistema)) {
            System.out.println("Personal de enfermeria registrado correctamente.");
        } else {
            System.out.println("No fue posible registrar al enfermero.");
        }
    }

    /**
     * Registra un paciente general o de especialidad.
     *
     * @param entrada Lector de consola.
     */
    private static void registrarPaciente(Scanner entrada) {
        System.out.println("-- Registro de Paciente --");
        System.out.println("1. Paciente General");
        System.out.println("2. Paciente de Especialidad");
        int tipo = leerEntero(entrada, "Seleccione el tipo de paciente: ");

        String nombre = leerTexto(entrada, "Apellidos y nombres: ");

        Paciente paciente;
        if (tipo == 1) {
            String clinica = leerTexto(entrada, "Clinica de procedencia: ");
            paciente = new PacienteGeneral(nombre, clinica);
        } else if (tipo == 2) {
            String especialidad = leerTexto(entrada, "Especialidad medica requerida: ");
            String diagnostico = leerTexto(entrada, "Diagnostico o referencia previa: ");
            int prioridad = leerEntero(entrada, "Nivel de prioridad (1 = Urgente, 2 = Prioritaria, 3 = Ordinaria): ");
            paciente = new PacienteEspecialidad(nombre, especialidad, diagnostico, prioridad);
        } else {
            System.out.println("Aviso: Tipo de paciente no valido.");
            return;
        }

        if (paciente.registroEnSistema(sistema)) {
            System.out.println("Paciente registrado exitosamente.");
        } else {
            System.out.println("No fue posible registrar al paciente.");
        }
    }

    /**
     * Asigna un paciente a un medico.
     *
     * @param entrada Lector de consola.
     */
    private static void asignarPacienteMedico(Scanner entrada) {
        System.out.println("-- Asignacion de Paciente a Medico --");
        Paciente paciente = buscarPacientePorNombre(entrada);
        if (paciente == null) {
            return;
        }

        Medico medico = buscarMedicoPorCedula(entrada);
        if (medico == null) {
            return;
        }

        if (sistema.asignarPaciente(paciente, medico)) {
            System.out.println("Paciente asignado exitosamente al medico " + medico.getNombre() + ".");
        } else {
            System.out.println("No fue posible asignar al paciente con el medico.");
        }
    }

    /**
     * Asigna un paciente a un enfermero.
     *
     * @param entrada Lector de consola.
     */
    private static void asignarPacienteEnfermero(Scanner entrada) {
        System.out.println("-- Asignacion de Paciente a Enfermero --");
        Paciente paciente = buscarPacientePorNombre(entrada);
        if (paciente == null) {
            return;
        }

        Enfermero enfermero = buscarEnfermeroPorCedula(entrada);
        if (enfermero == null) {
            return;
        }

        if (sistema.asignarPaciente(paciente, enfermero)) {
            System.out.println("Paciente asignado exitosamente al enfermero " + enfermero.getNombre() + ".");
        } else {
            System.out.println("No fue posible asignar al paciente con el enfermero.");
        }
    }

    /**
     * Solicita una consulta medica llamando al metodo del paciente.
     *
     * @param entrada Lector de consola.
     */
    private static void solicitarConsulta(Scanner entrada) {
        System.out.println("-- Solicitud de Consulta Medica --");
        Paciente paciente = buscarPacientePorNombre(entrada);
        if (paciente == null) {
            return;
        }

        Medico medico = buscarMedicoPorCedula(entrada);
        if (medico == null) {
            return;
        }

        if (paciente.solicitarConsulta(medico)) {
            System.out.println("Consulta iniciada con el Dr(a). " + medico.getNombre() + ".");
            System.out.println("Estado del paciente: " + paciente.getEstado());
        } else {
            System.out.println("No fue posible iniciar la consulta medica.");
        }
    }

    /**
     * Aplica el tratamiento clinico mediante el medico asignado.
     *
     * @param entrada Lector de consola.
     */
    private static void darTratamientoMedico(Scanner entrada) {
        System.out.println("-- Administracion de Tratamiento por Medico --");
        Medico medico = buscarMedicoPorCedula(entrada);
        if (medico == null) {
            return;
        }

        Paciente paciente = buscarPacientePorNombre(entrada);
        if (paciente == null) {
            return;
        }

        if (medico.darTratamiento(paciente)) {
            System.out.println("Tratamiento administrado exitosamente.");
            System.out.println("Nuevo estado del paciente: " + paciente.getEstado());
        } else {
            System.out.println("No fue posible dar tratamiento con este medico.");
        }
    }

    /**
     * Aplica el tratamiento clinico mediante el enfermero asignado.
     *
     * @param entrada Lector de consola.
     */
    private static void darTratamientoEnfermero(Scanner entrada) {
        System.out.println("-- Administracion de Tratamiento por Enfermero --");
        Enfermero enfermero = buscarEnfermeroPorCedula(entrada);
        if (enfermero == null) {
            return;
        }

        Paciente paciente = buscarPacientePorNombre(entrada);
        if (paciente == null) {
            return;
        }

        if (enfermero.darTratamiento(paciente)) {
            System.out.println("Tratamiento administrado exitosamente por enfermeria.");
            System.out.println("Nuevo estado del paciente: " + paciente.getEstado());
        } else {
            System.out.println("No fue posible dar tratamiento con este enfermero.");
        }
    }

    /**
     * Muestra la lista de pacientes de un medico en orden descendente.
     *
     * @param entrada Lector de consola.
     */
    private static void verListaMedico(Scanner entrada) {
        System.out.println("-- Lista de Pacientes Asignados al Medico --");
        Medico medico = buscarMedicoPorCedula(entrada);
        if (medico == null) {
            return;
        }

        String lista = medico.verListaPacientes();
        if (lista.isEmpty()) {
            System.out.println("El medico no cuenta con pacientes asignados actualmente.");
        } else {
            System.out.println(lista);
        }
    }

    /**
     * Muestra la lista de pacientes de un enfermero en orden descendente.
     *
     * @param entrada Lector de consola.
     */
    private static void verListaEnfermero(Scanner entrada) {
        System.out.println("-- Lista de Pacientes Asignados al Enfermero --");
        Enfermero enfermero = buscarEnfermeroPorCedula(entrada);
        if (enfermero == null) {
            return;
        }

        String lista = enfermero.verListaPacientes();
        if (lista.isEmpty()) {
            System.out.println("El enfermero no cuenta con pacientes asignados actualmente.");
        } else {
            System.out.println(lista);
        }
    }

    /**
     * Muestra el estado actual y detalles de un paciente.
     *
     * @param entrada Lector de consola.
     */
    private static void verEstadoPaciente(Scanner entrada) {
        System.out.println("-- Estado Clinico del Paciente --");
        Paciente paciente = buscarPacientePorNombre(entrada);
        if (paciente == null) {
            return;
        }

        System.out.println(paciente.toString());
        if (paciente.estaEnConsulta()) {
            System.out.println("Estado: El paciente esta actualmente en consulta.");
        } else if (paciente.estaEnTratamiento()) {
            System.out.println("Estado: El paciente se encuentra en tratamiento.");
        } else {
            System.out.println("Estado: El paciente esta en espera (PENDIENTE).");
        }
    }

    /**
     * Ejecuta el modulo de supervision del jefe de enfermeria.
     *
     * @param entrada Lector de consola.
     */
    private static void supervisionJefeEnfermeria(Scanner entrada) {
        System.out.println("-- Modulo de Supervision de Jefatura de Enfermeria --");
        Enfermero enfermero = buscarEnfermeroPorCedula(entrada);
        if (enfermero == null) {
            return;
        }

        if (enfermero instanceof JefeEnfermeria) {
            JefeEnfermeria jefe = (JefeEnfermeria) enfermero;
            System.out.println(jefe.supervisarPersonal(sistema.getEnfermeros(), sistema.getNoEnfermeros()));
        } else {
            System.out.println("Aviso: El enfermero seleccionado no es Jefe de Enfermeria.");
        }
    }

    /**
     * Permite a un medico general expedir un certificado de salud para un paciente general.
     *
     * @param entrada Lector de consola.
     */
    private static void expedirCertificadoSalud(Scanner entrada) {
        System.out.println("-- Expedicion de Certificado de Salud --");
        Medico medico = buscarMedicoPorCedula(entrada);
        if (medico == null) {
            return;
        }

        if (!(medico instanceof MedicoGeneral)) {
            System.out.println("Aviso: El certificado de salud solo puede ser expedido por un Medico General.");
            return;
        }

        Paciente paciente = buscarPacientePorNombre(entrada);
        if (paciente == null) {
            return;
        }

        if (!(paciente instanceof PacienteGeneral)) {
            System.out.println("Aviso: El certificado de salud solo puede ser expedido a un Paciente General.");
            return;
        }

        MedicoGeneral mg = (MedicoGeneral) medico;
        PacienteGeneral pg = (PacienteGeneral) paciente;
        mg.expedirCertificadoSalud(pg);
    }

    /**
     * Muestra el resumen del sistema y el catalogo unificado de atencion medica.
     */
    private static void mostrarResumenYPersonalAtencion() {
        System.out.println("-- Resumen Global del Sistema --");
        System.out.println("Hospital: " + sistema.getNombreHospital());
        System.out.println("Medicos registrados: " + sistema.getNoMedicos());
        System.out.println("Enfermeros registrados: " + sistema.getNoEnfermeros());
        System.out.println("Pacientes registrados: " + sistema.getNoPacientes());
        System.out.println("\n-- Catalogo de Atencion Medica --");

        hospital.interfaces.AtencionMedica[] personal = sistema.getPersonalAtencion();
        if (personal.length == 0) {
            System.out.println("No hay personal de atencion registrado actualmente.");
        } else {
            for (int i = 0; i < personal.length; i++) {
                hospital.interfaces.AtencionMedica miembro = personal[i];
                System.out.printf("[%02d] %s%n", (i + 1), miembro.getDescripcionAtencion());
            }
        }
    }

    /**
     * Solicita la cedula de un medico y lo busca en el sistema.
     *
     * @param entrada Lector de consola.
     * @return Medico encontrado, o null si no existe.
     */
    private static Medico buscarMedicoPorCedula(Scanner entrada) {
        String cedula = leerTexto(entrada, "Cedula del medico: ");
        Medico medico = sistema.buscarMedico(cedula);
        if (medico == null) {
            System.out.println("Aviso: No se encontro ningun medico con la cedula: " + cedula);
        }
        return medico;
    }

    /**
     * Solicita la cedula de un enfermero y lo busca en el sistema.
     *
     * @param entrada Lector de consola.
     * @return Enfermero encontrado, o null si no existe.
     */
    private static Enfermero buscarEnfermeroPorCedula(Scanner entrada) {
        String cedula = leerTexto(entrada, "Cedula del enfermero: ");
        Enfermero enfermero = sistema.buscarEnfermero(cedula);
        if (enfermero == null) {
            System.out.println("Aviso: No se encontro ningun enfermero con la cedula: " + cedula);
        }
        return enfermero;
    }

    /**
     * Solicita el nombre de un paciente y lo busca en el sistema.
     *
     * @param entrada Lector de consola.
     * @return Paciente encontrado, o null si no existe.
     */
    private static Paciente buscarPacientePorNombre(Scanner entrada) {
        String nombre = leerTexto(entrada, "Apellidos y nombres del paciente: ");
        Paciente paciente = sistema.buscarPaciente(nombre);
        if (paciente == null) {
            System.out.println("Aviso: No se encontro ningun paciente con el nombre: " + nombre);
        }
        return paciente;
    }

    /**
     * Lee texto por consola eliminando espacios sobrantes.
     *
     * @param entrada Lector de consola.
     * @param mensaje Mensaje explicativo.
     * @return Cadena leida.
     */
    private static String leerTexto(Scanner entrada, String mensaje) {
        System.out.print(mensaje);
        String valor = entrada.nextLine().trim();
        return valor;
    }

    /**
     * Lee un numero entero por consola de forma segura.
     *
     * @param entrada Lector de consola.
     * @param mensaje Mensaje explicativo.
     * @return Numero entero leido, o -1 si la entrada es invalida.
     */
    private static int leerEntero(Scanner entrada, String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

