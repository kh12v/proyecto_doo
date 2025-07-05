package Logica;

import Logica.Enums.Especies;
import Logica.Enums.TipoContenedor;

public class JaulaPequena extends Jaula {

    public JaulaPequena() {
        super(new Especies[]{Especies.Loro, Especies.Hamster}, TipoContenedor.JaulaPequena);
    }
}
