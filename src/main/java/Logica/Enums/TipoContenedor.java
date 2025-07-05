package Logica.Enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum TipoContenedor {
    JaulaGrande(2000, 1, new Especies[]{Especies.Perro, Especies.Gato}),
    JaulaPequena(1000, 1, new Especies[]{Especies.Loro, Especies.Hamster});
    final int precio;
    final int capacidad;
    final Set<Especies> especiesAdmisibles;

    TipoContenedor(int precio, int capacidad, Especies[] especiesAdmisibles) {
        this.precio = precio;
        this.capacidad = capacidad;
        this.especiesAdmisibles = new HashSet<>(Arrays.asList(especiesAdmisibles));
    }

    public int getPrecio() {
        return precio;
    }
}
