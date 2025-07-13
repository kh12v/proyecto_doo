package Logica;

import Logica.Enums.Cargo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Representa un empleado que cumple una funcion en la {@code Tienda} y recibe un salario dependiendo de su cargo.
 */
public class Empleado {
    private final static Random rand = new Random();
    private final static List<String> nombres = Arrays.asList(
            "Sofía", "Santiago", "Emilia", "Mateo", "Isabella", "Benjamín", "Florencia", "Tomás", "Antonella", "Lucas",
            "Agustina", "Joaquín", "Martina", "Martín", "Amanda", "Alonso", "Valentina", "Diego", "Josefa", "Vicente",
            "Camila", "Gabriel", "Catalina", "Cristóbal", "Anaís", "Nicolás", "Fernanda", "Gaspar", "María", "Damián",
            "Abigail", "Maximiliano", "Paula", "Julián", "Renata", "Simón", "Trinidad", "Rafael", "Laura", "Esteban",
            "Elisa", "Ignacio", "Constanza", "Samuel", "Mía", "Francisco", "Antonia", "Bastián", "Javiera", "Rodrigo",
            "Agustín", "Victoria", "Elías", "Amanda", "Pablo", "Olivia", "León", "Romina", "Bruno", "Emilia",
            "Andrés", "Daniela", "Aarón", "Francisca", "Alexander", "Camilo", "Jose", "Clara", "Felipe", "Julieta",
            "Álvaro", "Celeste", "Hugo", "Milagros", "Gael", "Sofía", "Enzo", "Tamara", "Ian", "Florencia",
            "Luciano", "Ximena", "Iker", "Mariana", "Matías", "Nayareth", "Franco", "Génesis", "Valentín", "Ignacia",
            "Marcelo", "Amanda", "Mauricio", "Isidora", "Danilo", "Rafaela", "Pedro", "Maite", "Jorge", "Alma"
    );
    private static int idActual = 0;
    private final String nombre;
    private final int id;
    private final Cargo cargo;
    private boolean trabajando;

    public Empleado(Cargo cargo) {
        this.nombre = getNombreAleatorio();
        this.id = idActual++;
        this.cargo = cargo;
        trabajando = true;
    }

    private String getNombreAleatorio() {
        return nombres.get(rand.nextInt(nombres.size()));
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalario() {
        return cargo.getSalario();
    }

    public Cargo getCargo() {
        return cargo;
    }

    public int getID() {
        return id;
    }

    public boolean isTrabajando() {
        return trabajando;
    }

    public void setTrabajando(boolean estado) {
        trabajando = estado;
    }
}