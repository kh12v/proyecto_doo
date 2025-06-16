package Logica;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum TipoContenedor {
    JaulaPerro(1,new Especies[]{Especies.Perro,Especies.Gato});
    final int capacidad;
    final Set<Especies> especiesAdmisibles;

    TipoContenedor(int capacidad, Especies[] especiesAdmisibles) {
        this.capacidad = capacidad;
        this.especiesAdmisibles = new HashSet<>(Arrays.asList(especiesAdmisibles)) ;
    }
}
