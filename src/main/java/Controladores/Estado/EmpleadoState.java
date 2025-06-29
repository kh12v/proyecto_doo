package Controladores.Estado;

import Logica.Cargo;
import Logica.Empleado;

/**
 * DTO (Data Transfer Object) de un empleado
 * @param nombre nombre del empleado
 * @param salario salario del empleado
 * @param cargo cargo del empleado
 * @param id del empleado
 */
public record EmpleadoState (String nombre,
                            int salario,
                            Cargo cargo,
                            int id){
    /**
     * DEL representa el empleado nulo, si se recibe esto en un índice, significa que hay que borrarla
     */
    public static final EmpleadoState DEL = new EmpleadoState("BORRAR", 0, Cargo.Null,0);
    public static EmpleadoState toState(Empleado empleado) {
        return new EmpleadoState(empleado.getNombre(), empleado.getSalario(), empleado.getCargo(), empleado.getID());
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
