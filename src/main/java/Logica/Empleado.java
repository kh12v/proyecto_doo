package Logica;

import java.util.ArrayList;
import java.util.List;

public class Empleado implements Actualizable {
    private final Tienda t;

    private final String nombre;
    private final int id;
    private static int idActual = 0;
    private int salario;
    private Cargo cargo;

    public Empleado(Tienda t, String nombre, int salario, Cargo cargo) {
        this.t = t;
        this.nombre = nombre;
        this.salario = salario;
        this.id = idActual++;
        this.cargo = cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalario() {
        return salario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public int getID() { return id; }

    @Override
    public void actualizar() {
        t.pagarSalario(this.salario);
    }
}