package Controladores.Estado;

import Logica.Empleado;
import Logica.Enums.Cargo;

/**
 * DTO (Data Transfer Object) de un empleado
 *
 * @param nombre  nombre del empleado
 * @param salario salario del empleado
 * @param cargo   cargo del empleado
 * @param id      del empleado
 */
public record EmpleadoState(String nombre,
                            int salario,
                            Cargo cargo,
                            int id) {

    /**
     * Método para extraer el estado de un {@code Empleado}
     * @param empleado Un {@code Empleado}
     * @return Un {@code EmpleadoState} que lo representa
     */
    public static EmpleadoState toState(Empleado empleado) {
        return new EmpleadoState(empleado.getNombre(), empleado.getSalario(), empleado.getCargo(), empleado.getID());
    }


    /**
     * Crea un estado que representa un empleado despedido, se usa por motivos internos
     * @param id id del empleado
     * @return el {@code EmpleadoState}
     */
    public static EmpleadoState createDEL(int id) {
        return new EmpleadoState("", 0, Cargo.DESPEDIDO, id);
    }

    @Override
    public String toString() {
        return "Nombre = " + nombre + ", Salario = " + salario + ", Cargo = " + cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoState s2 = (EmpleadoState) o;
        return nombre.equals(s2.nombre) && salario == s2.salario && cargo.equals(s2.cargo);
    }

}
