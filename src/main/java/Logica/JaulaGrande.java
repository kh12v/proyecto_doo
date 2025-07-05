package Logica;

import Logica.Enums.Especies;
import Logica.Enums.TipoContenedor;

public class JaulaGrande extends Jaula {
    public JaulaGrande() {
        super(new Especies[]{Especies.Gato, Especies.Perro}, TipoContenedor.JaulaGrande);
    }
}
