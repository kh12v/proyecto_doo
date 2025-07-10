package Logica;

import Logica.Enums.Especie;
import Logica.Enums.TipoJaula;

/**
 * Una jaula pequeña que puede contener un loro o un hamster.
 */
public class JaulaPequena extends Jaula {
    public JaulaPequena() {
        super(new Especie[]{Especie.Loro, Especie.Hamster}, TipoJaula.JaulaPequena);
    }
}
