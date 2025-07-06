package Logica;

import Logica.Enums.Especies;
import Logica.Enums.TipoContenedor;

import java.util.Arrays;

public abstract class Jaula implements Actualizable {
    private static int idCounter = 0;
    private final Especies[] especiesPermitidas;
    private final int id;
    private final TipoContenedor tipoContenedor;
    private Mascota mascota;
    private boolean vacia;

    public Jaula(Especies[] especiesPermitidas, TipoContenedor tipo) {
        id = idCounter++;
        this.especiesPermitidas = especiesPermitidas;
        tipoContenedor = tipo;
        vacia = true;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void removerMascota() {
        mascota = null;
        vacia = true;
    }

    public int[] getIndicadores() {
        if (vacia) {
            return new int[]{0,0,0,0};
        }
        return mascota.getIndicadores();
    }

    public void ingresarMascota(Mascota mascota) {
        if (!estaVacia() || !admiteEspecie(mascota.getEspecie())) {
            return;
        }
        this.mascota = mascota;
        vacia = false;
    }

    public boolean admiteEspecie(Especies especie) {
        return Arrays.stream(especiesPermitidas).anyMatch(i -> i == especie);
    }

    public boolean estaVacia() {
        return vacia;
    }

    @Override
    public void actualizar() {
        if (vacia) {
            return;
        }
        mascota.actualizar();
    }

    public int getID() {
        return id;
    }

    public TipoContenedor getTipoContenedor() {
        return tipoContenedor;
    }
}

