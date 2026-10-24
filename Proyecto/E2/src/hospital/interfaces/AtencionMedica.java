package hospital.interfaces;

import hospital.pacientes.Paciente;

/**
 * Interfaz que define las operaciones de atencion medica que realiza
 * el personal de salud del hospital.
 */
public interface AtencionMedica {

    /**
     * Aplica un tratamiento a un paciente asignado.
     *
     * @param paciente Paciente que recibe el tratamiento.
     * @return true si se aplico con exito, false en caso contrario.
     */
    boolean darTratamiento(Paciente paciente);

    /**
     * Devuelve la lista de pacientes asignados ordenada de forma descendente.
     *
     * @return Lista formateada de pacientes.
     */
    String verListaPacientes();

    /**
     * Devuelve la descripcion del servicio o rol de atencion medica.
     *
     * @return Texto con la descripcion de la atencion.
     */
    String getDescripcionAtencion();
}
