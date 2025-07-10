package Logica;

import Logica.Enums.Especie;
import Logica.Enums.TipoJaula;

/**
 * Una jaula grande que puede contener un perro o un gato.
 */
public class JaulaGrande extends Jaula {
    public JaulaGrande() {
        super(new Especie[]{Especie.Gato, Especie.Perro}, TipoJaula.JaulaGrande);
    }
}
