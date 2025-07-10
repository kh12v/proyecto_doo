package Logica.Enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum TipoJaula {
    JaulaGrande(2000, 1, new Especie[]{Especie.Perro, Especie.Gato}),
    JaulaPequena(1000, 1, new Especie[]{Especie.Loro, Especie.Hamster});
    final int precio;
    final int capacidad;
    final Set<Especie> especiesAdmisibles;

    TipoJaula(int precio, int capacidad, Especie[] especiesAdmisibles) {
        this.precio = precio;
        this.capacidad = capacidad;
        this.especiesAdmisibles = new HashSet<>(Arrays.asList(especiesAdmisibles));
    }

    public int getPrecio() {
        return precio;
    }
}
