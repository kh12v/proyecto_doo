package Logica;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Alimentos {
    Carne(30, new Especies[]{Especies.Perro, Especies.Gato},"Carne"),
    Semilla(20, new Especies[]{Especies.Loro},"Semilla");

    final int valor;
    final Set<Especies> especies;
    final String nombre;

    Alimentos(int valor, Especies[] especies, String nombre) {
        this.valor = valor;
        this.especies = new HashSet<>(Arrays.asList(especies));
        this.nombre = nombre;
    }
}
